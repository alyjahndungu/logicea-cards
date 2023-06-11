package com.logicea.cards.services;

import com.logicea.cards.domain.entities.Users;

import java.security.Principal;

public interface UserService {
    Users getUser(Principal principal);
}
