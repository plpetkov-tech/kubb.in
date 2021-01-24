package in.kubb.api.repositories;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.ReturnDocument.AFTER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.TransactionOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.ClientSession;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.WriteModel;
import in.kubb.api.models.Subscription;

import org.bson.BsonDocument;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDBSubscriptionRepository implements SubscriptionRepository {

    private static final TransactionOptions txnOptions = TransactionOptions.builder()
                                                                           .readPreference(ReadPreference.primary())
                                                                           .readConcern(ReadConcern.MAJORITY)
                                                                           .writeConcern(WriteConcern.MAJORITY)
                                                                           .build();
    private final MongoClient client;
    private MongoCollection<Subscription> personCollection;

    @Value("${kubb.in.prod}")
    private boolean currentEnvironmentIsProd;
    
    public MongoDBSubscriptionRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        personCollection = currentEnvironmentIsProd == false ? client.getDatabase("kubbin-test").getCollection("subscriptions", Subscription.class) : client.getDatabase("kubbin").getCollection("subscriptions", Subscription.class);
    }

    @Override
    public Subscription save(Subscription subscription) {
        subscription.setId(new ObjectId());
        personCollection.insertOne(subscription);
        return subscription;
    }

    @Override
    public List<Subscription> saveAll(List<Subscription> subscriptions) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(() -> {
                subscriptions.forEach(p -> p.setId(new ObjectId()));
                personCollection.insertMany(clientSession, subscriptions);
                return subscriptions;
            }, txnOptions);
        }
    }

    @Override
    public List<Subscription> findAll() {
        return personCollection.find().into(new ArrayList<>());
    }

    @Override
    public List<Subscription> findAll(List<String> ids) {
        return personCollection.find(in("_id", mapToObjectIds(ids))).into(new ArrayList<>());
    }

    @Override
    public Subscription findOne(String id) {
        return personCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public long count() {
        return personCollection.countDocuments();
    }

    @Override
    public long delete(String id) {
        return personCollection.deleteOne(eq("_id", new ObjectId(id))).getDeletedCount();
    }

    @Override
    public long delete(List<String> ids) {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> personCollection.deleteMany(clientSession, in("_id", mapToObjectIds(ids))).getDeletedCount(),
                    txnOptions);
        }
    }

    @Override
    public long deleteAll() {
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> personCollection.deleteMany(clientSession, new BsonDocument()).getDeletedCount(), txnOptions);
        }
    }

    @Override
    public Subscription update(Subscription subscription) {
        FindOneAndReplaceOptions options = new FindOneAndReplaceOptions().returnDocument(AFTER);
        return personCollection.findOneAndReplace(eq("_id", subscription.getId()), subscription, options);
    }

    @Override
    public long update(List<Subscription> subscriptions) {
        List<WriteModel<Subscription>> writes = subscriptions.stream()
                                                 .map(p -> new ReplaceOneModel<>(eq("_id", p.getId()), p))
                                                 .collect(Collectors.toList());
        try (ClientSession clientSession = client.startSession()) {
            return clientSession.withTransaction(
                    () -> personCollection.bulkWrite(clientSession, writes).getModifiedCount(), txnOptions);
        }
    }


    private List<ObjectId> mapToObjectIds(List<String> ids) {
        return ids.stream().map(ObjectId::new).collect(Collectors.toList());
    }
}
