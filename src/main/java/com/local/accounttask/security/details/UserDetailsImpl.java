package com.local.accounttask.security.details;

import com.local.accounttask.models.Client;
import com.local.accounttask.models.State;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private Client client;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String clientRole = client.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(clientRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return client.getHashPassword();
    }

    @Override
    public String getUsername() {
        return client.getClientLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !client.getState().equals(State.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return client.getState().equals(State.ACTIVE);
    }

    public Client getClient() {
        return client;
    }
}
