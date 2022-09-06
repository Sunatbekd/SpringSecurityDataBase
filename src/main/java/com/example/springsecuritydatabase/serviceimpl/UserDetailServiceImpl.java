package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.SecurityUser;
import com.example.springsecuritydatabase.entity.User;
import com.example.springsecuritydatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("could not found user");
        }
        return new SecurityUser(user);
    }
}
