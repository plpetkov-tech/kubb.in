package in.kubb.api.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  
  private List<Subscription> subscriptions;

  @DBRef
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public List<Subscription> getSubscriptions() {
    return subscriptions;
  }
  public void setSubscriptions(List<Subscription> subscriptions) {
    List<Subscription> lSubscriptions = new ArrayList<>();
    lSubscriptions.addAll(subscriptions);
    this.subscriptions = lSubscriptions;
}
  public void setSubscription(Subscription subscription) {
    List<Subscription> lSubscriptions = getSubscriptions();
    lSubscriptions.add(subscription);
    this.subscriptions = lSubscriptions;;
}
  public void putSubscriptions(List<Subscription> subscriptions) {
    List<Subscription> lSubscriptions = this.subscriptions;
    lSubscriptions.addAll(subscriptions);
}
  public void putSubscription(Subscription subscription) {
    List<Subscription> lSubscriptions = this.subscriptions;
    lSubscriptions.add(subscription);
}


public Long countSubs() {
	return (long) this.subscriptions.size();
}

public void deleteSub(String id2) {
this.subscriptions.removeIf((Subscription s) -> s.getId().equals(new ObjectId(id2)));
}

public void deleteSubs(List<String> listIds) {
  listIds.forEach((String id)-> this.subscriptions.removeIf((Subscription s) -> s.getId().equals(new ObjectId(id))));
}

public void deleteSubs() {
  this.subscriptions.clear();
}



}