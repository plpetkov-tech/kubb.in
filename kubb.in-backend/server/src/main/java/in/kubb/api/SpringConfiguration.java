package in.kubb.api;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class SpringConfiguration {

    @Value("${spring.data.mongodb.uri}")
    private String connectionString;


    @Value("${kubb.in.prod}")
    private boolean currentEnvironmentIsProd;

    
    @Value("${kubb.in.db.pass}")
    private String dbPass;

    @Bean
    public MongoClient mongoClient() {
        MongoCredential credential = currentEnvironmentIsProd == false ? MongoCredential.createCredential("test", "kubbin-test", "test".toCharArray()) : MongoCredential.createCredential("admin", "kubbin", dbPass.toCharArray());
        if(currentEnvironmentIsProd == false){
            connectionString = connectionString.concat(":27018");
        } else {
            connectionString =  connectionString.concat(":27017");
        }
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        System.out.println(currentEnvironmentIsProd == true ? "\u001B[35m---Prod environment--- db name : " + credential.getSource() + " | with username : " + credential.getUserName() + "\n": "\u001B[35m---Test environment--- db name : " + credential.getSource() + " | with username : " + credential.getUserName() + "\n");
        return MongoClients.create(MongoClientSettings.builder()
                                                      .applyConnectionString(new ConnectionString(connectionString))
                                                      .credential(credential)
                                                      .codecRegistry(codecRegistry)
                                                      .build());
    }

}
