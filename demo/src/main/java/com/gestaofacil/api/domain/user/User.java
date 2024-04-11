package com.gestaofacil.api.domain.user;

import com.gestaofacil.api.domain.company.Company;
import com.gestaofacil.api.utils.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "user_id")
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String phone_number;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public void update(UserDTO user){
        if(user.name() != null) this.name = user.name();
        if(user.cpf() != null) this.cpf = user.cpf();
        if(user.email() != null) this.email = user.email();
        if(user.phone_number() != null) this.phone_number = user.phone_number();
        if(user.company() != null) this.company = user.company();
    }

    public User(UserCreationDTO user, Company company){
        this.name = user.name();
        this.email = user.email();
        this.role = user.role();
        this.cpf = user.cpf();
        this.company = company;
        this.phone_number = user.phone_number();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
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
        return true;
    }
}
