package com.devaleh.apisecurity.services;

import com.devaleh.apisecurity.entities.UserModel;
import com.devaleh.apisecurity.repositories.UserModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserModelRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("UserModel Not Found with username: " +username));
        return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
    }
}
