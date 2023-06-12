package com.logicea.cards.services.impl;

import com.logicea.cards.domain.dto.LoginDto;
import com.logicea.cards.domain.dto.UserDto;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.domain.enumeration.ERole;
import com.logicea.cards.domain.models.AuthResponse;
import com.logicea.cards.domain.models.JWTUtils;
import com.logicea.cards.domain.models.MyUserDetailService;
import com.logicea.cards.domain.repositories.UserRepository;
import com.logicea.cards.exceptions.BadRequestException;
import com.logicea.cards.exceptions.NotFoundException;
import com.logicea.cards.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MyUserDetailService myUserDetailService;
    private final JWTUtils jwtUtils;
    private final AuthenticationManager authenticationManager;


    @Override
    public Users addUsers(UserDto userDto) {
        Users users = Users.builder().name(userDto.name())
                .email(userDto.email())
                .password(new BCryptPasswordEncoder().encode(userDto.password()))
                .role(String.valueOf(ERole.ROLE_MEMBER)).build();
        return userRepository.save(users);
    }

    @Override
    public Users getUser(Principal principal) {
        return userRepository.findByEmail(principal.getName()).orElseThrow(() -> new NotFoundException("Not logged in"));
    }


    @Override
    public AuthResponse loginWithUsernameAndPassword(LoginDto loginDto) {

        validateUsernameAndPassword(loginDto);

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(loginDto.username());
        final String accessToken = jwtUtils.generateToken(userDetails);

        Users users = findUserByEmail(loginDto.username());
        List<String> roles = Arrays.asList(users.getRole().split(","));

        return new AuthResponse(userDetails.getUsername(), accessToken, "Bearer", roles);
    }

    private Users findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No Email found::" + email));
    }

    private void validateUsernameAndPassword(LoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect email or password");
        }
    }
}