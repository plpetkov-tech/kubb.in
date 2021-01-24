package in.kubb.api.controllers;

import in.kubb.api.models.Subscription;
import in.kubb.api.repositories.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping("subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public Subscription postSubscription(@RequestBody Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    @PostMapping("subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Subscription> postSubscriptions(@RequestBody List<Subscription> subscriptions) {
        return subscriptionRepository.saveAll(subscriptions);
    }

    @GetMapping("subscriptions")
    public List<Subscription> getSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @GetMapping("subscription/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable String id) {
        Subscription subscription = subscriptionRepository.findOne(id);
        if (subscription == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(subscription);
    }

    @GetMapping("subscriptions/{ids}")
    public List<Subscription> getSubscriptions(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        return subscriptionRepository.findAll(listIds);
    }

    @GetMapping("subscriptions/count")
    public Long getCount() {
        return subscriptionRepository.count();
    }

    @DeleteMapping("subscription/{id}")
    public Long deleteSubscription(@PathVariable String id) {
        return subscriptionRepository.delete(id);
    }

    @DeleteMapping("subscriptions/{ids}")
    public Long deleteSubscriptions(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        return subscriptionRepository.delete(listIds);
    }

    @DeleteMapping("subscriptions")
    public Long deleteSubscriptions() {
        return subscriptionRepository.deleteAll();
    }

    @PutMapping("subscription")
    public Subscription putSubscription(@RequestBody Subscription subscription) {
        return subscriptionRepository.update(subscription);
    }

    @PutMapping("subscriptions")
    public Long putSubscription(@RequestBody List<Subscription> subscriptions) {
        return subscriptionRepository.update(subscriptions);
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
