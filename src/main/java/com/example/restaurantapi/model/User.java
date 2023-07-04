package com.example.restaurantapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Objects;
import java.util.Set;

@Document("users")
public class User implements UserDetails {

    @Id
    @Getter
    @Setter
    private String _id;

    @Getter
    @Setter
    @Indexed(unique = true)
    private String username;

    @Setter
    private String password;

    @Getter
    @Setter
    private Long lastLogin;

    @Getter
    @Setter
    private Long createdAt;

    @Getter
    @Setter
    private Set<UserRole> userRoles;

    @Setter
    private boolean accountNonExpired;

    @Setter
    private boolean accountNonLocked;

    @Setter
    private boolean credentialsNonExpired;

    @Setter
    private boolean enabled;

    User(){
        this.enabled = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked =  true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Set<UserRole> getAuthorities() {
        return this.userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
