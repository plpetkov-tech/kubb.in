package in.kubb.api.controllers;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.BasicDBObject;

import com.mongodb.DBObject;

import in.kubb.api.models.Subscription;
import in.kubb.api.models.User;
import in.kubb.api.repositories.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SubscriptionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    private final UserRepository userRepository;

    public SubscriptionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("subscription")
    @ResponseStatus(HttpStatus.CREATED)
    public User postSubscription(@RequestBody Subscription subscription) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()
        userExist.setSubscription(subscription);

        return userRepository.save(userExist);
    }

    @PostMapping("subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public User postSubscriptions(@RequestBody List<Subscription> subscriptions) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()
        userExist.setSubscriptions(subscriptions);

        return userRepository.save(userExist);
    }

    @GetMapping("subscriptions")
    public List<Subscription> getSubscriptions() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()

        return userExist.getSubscriptions();
    }

    @GetMapping("subscription/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable String id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()
        int match = -1;
        List<Subscription> subs = userExist.getSubscriptions();
        for (int i = 0; i < subs.size(); i++) {
            try {
                if (subs.get(i).getId().toString().equals(new ObjectId(id).toString())) {
                    match = i;
                    break;
                }
            } catch (Exception e) {
                System.err.println("wrong id format");
            }
        }
        if (match == -1)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(subs.get(match));
    }

    /*
     * @GetMapping("subscriptions/{ids}") public List<Subscription>
     * getSubscriptions(@PathVariable String ids) { List<String> listIds =
     * asList(ids.split(",")); return subscriptionRepository.findAll(listIds); }
     */
    @GetMapping("subscriptions/count")
    public Long getCount() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        return userExist.countSubs();
    }

    @DeleteMapping("subscription/{id}")
    public User deleteSubscription(@PathVariable String id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        userExist.deleteSub(id);
        return userRepository.save(userExist);
    }

    @DeleteMapping("subscriptions/{ids}")
    public User deleteSubscriptions(@PathVariable String ids) {
        List<String> listIds = asList(ids.split(","));
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        userExist.deleteSubs(listIds);
        return userRepository.save(userExist);

    }

    @DeleteMapping("subscriptions")
    public User deleteSubscriptions() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        userExist.deleteSubs();
        return userRepository.save(userExist);

    }

    @PatchMapping("subscription/{id}")
    public ResponseEntity<Subscription> patchSubscription(@PathVariable String id,
            @RequestBody Subscription subscription) {
        // userDetails.getAuthorities()
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        int match = -1;
        List<Subscription> subs = userExist.getSubscriptions();
        for (int i = 0; i < subs.size(); i++) {
            try {
                if (subs.get(i).getId().toString().equals(new ObjectId(id).toString())) {
                    match = i;
                    break;
                }
            } catch (Exception e) {
                System.err.println("wrong id format");
            }
        }
        if (match == -1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Subscription matched = subs.get(match);
        matched.setAutoRenewal(subscription.isAutoRenewal());
        matched.setNextRenewal(subscription.getNextRenewal());
        matched.setCurrency(subscription.getCurrency());
        matched.setPrice(subscription.getPrice());
        matched.setLink(subscription.getLink());
        matched.setName(subscription.getName());
        matched.setRegularity(subscription.getRegularity());
        userExist.setSubscription(matched);
        userRepository.save(userExist);
        return ResponseEntity.ok(matched);

    }

    @PutMapping("subscription")
    public User putSubscription(@RequestBody Subscription subscription) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()
        userExist.putSubscription(subscription);

        return userRepository.save(userExist);
    }

    @PutMapping("subscriptions")
    public User putSubscription(@RequestBody List<Subscription> subscriptions) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByUsername(userDetails.getUsername());
        User userExist = user.get();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()
        userExist.putSubscriptions(subscriptions);

        return userRepository.save(userExist);

    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }
}
