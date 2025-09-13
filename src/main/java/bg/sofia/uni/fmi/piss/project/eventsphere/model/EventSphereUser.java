package bg.sofia.uni.fmi.piss.project.eventsphere.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "event_sphere_users")
public class EventSphereUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "role", nullable = false)
  private String role;

  public EventSphereUser(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = "ROLE_USER";
  }

  public EventSphereUser() {
    this.role = "ROLE_USER";
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.role));
    return authorities;
  }

  @Override
  public String toString() {
    return String.format("User[id=%d, username='%s', email='%s', password='%s']", id, username, email, password);
  }
}
