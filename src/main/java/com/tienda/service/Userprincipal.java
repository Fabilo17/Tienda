package com.tienda.service;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.tienda.entity.Persona;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class Userprincipal implements UserDetails{
    private Persona persona;

    public Userprincipal(Persona persona) {
        this.persona = persona;
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    this.persona.getPermissionsList().forEach(p -> {
        GrantedAuthority authority = new SimpleGrantedAuthority(p);
        authorities.add(authority);
    });
    this.persona.getRoleList().forEach(r -> {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+ r);
        authorities.add(authority);
    });
    return authorities;
    }

    @Override
    public String getPassword() {
    return this.persona.getPassword();
    }

    @Override
    public String getUsername() {
        return this.persona.getNombre();
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
    return this.persona.getActive() == 1;
    }
    
    
}
