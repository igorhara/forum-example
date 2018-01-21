package igor.forum.service;

import igor.forum.dao.UserDao;
import igor.forum.model.UserForum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by igorhara on 18/01/2018.
 */
@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDao dao;

    public UserForum createUser(@Valid UserForum newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return dao.save(newUser);
    }

    public UserForum getUserByUsername(String username){
        return  dao.getUserForumByUsername(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserForum userForum = getUserByUsername(username);

        if(userForum==null){
            throw new UsernameNotFoundException("No user for: "+username);
        }

        return new org.springframework.security.core.userdetails.User(userForum.getUsername(), userForum.getPassword
                (),true,true,true,true,getSimpleRole());

    }

    private static List<GrantedAuthority> getSimpleRole () {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
}
