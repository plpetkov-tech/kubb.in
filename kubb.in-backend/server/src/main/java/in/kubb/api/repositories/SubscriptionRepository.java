package in.kubb.api.repositories;

import in.kubb.api.models.Subscription;

import java.util.List;


public interface SubscriptionRepository {

    Subscription save(Subscription subscription);

    List<Subscription> saveAll(List<Subscription> subscriptions);

    List<Subscription> findAll();

    List<Subscription> findAll(List<String> ids);

    Subscription findOne(String id);

    long count();

    long delete(String id);

    long delete(List<String> ids);

    long deleteAll();

    Subscription update(Subscription subscription);

    long update(List<Subscription> subscriptions);


}
