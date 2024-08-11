package prbrios.nfeapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prbrios.nfeapi.entities.UserEntity;
import prbrios.nfeapi.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity employee = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario invalido"));

        Set<GrantedAuthority> authorities = employee.getRoles().stream()
            .map((roles) -> new SimpleGrantedAuthority(roles.getName()))
            .collect(Collectors.toSet());

        return new User (
            username,
            employee.getPassword(),
            authorities
        );

    }

}
