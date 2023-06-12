package com.logicea.cards.services;

import com.logicea.cards.domain.dto.LoginDto;
import com.logicea.cards.domain.dto.UserDto;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.domain.models.AuthResponse;

import java.security.Principal;

public interface UserService {

    Users addUsers(UserDto userDto);
    Users getUser(Principal principal);

    AuthResponse loginWithUsernameAndPassword(LoginDto loginDto);
}
