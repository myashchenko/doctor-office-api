package ua.kpi.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.kpi.entity.User;
import ua.kpi.repository.UserRepository;

import java.util.Collections;
import java.util.Set;

/**
 * @author Mykola Yashchenko
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);

        if (user == null) {
            log.error("User with email {} not found", s);
            throw new UsernameNotFoundException("User with email=" + s + " doesn't exist");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private Set<GrantedAuthority> getAuthorities(User user) {
        // todo add roles
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_DOCTOR"));
    }
}
