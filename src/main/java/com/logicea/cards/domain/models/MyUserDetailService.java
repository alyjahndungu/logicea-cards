package com.logicea.cards.domain.models;

import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.domain.enumeration.ERole;
import com.logicea.cards.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyUserDetailService  implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        final Optional<Users> user = userRepository.findByEmail(email);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Email was not found" + email);
        }
        return User.withUsername(user.get().getEmail()).password(user.get().getPassword()).disabled(false)
                .authorities(String.valueOf(ERole.ROLE_MEMBER)).build();
    }

}
