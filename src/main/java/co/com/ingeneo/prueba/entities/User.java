package co.com.ingeneo.prueba.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    UUID id;

    @Column
    String email;

    @Column
    String password;

    @Column
    String role;

    @Column
    Boolean enabled;

    @Column(name = "created_at")
    @CreatedDate
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    LocalDateTime updatedAt;

    @PrePersist
    public void logNewUserAttempt() {
        this.enabled = true;        
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {        
      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority(this.role));
      return authorities;
    }

    @Override
    public String getUsername() {        
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {        
        return this.enabled;
    }
}
