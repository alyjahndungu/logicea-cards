package com.logicea.cards.domain.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ERole {
    ROLE_ADMIN  ("ROLE_ADMIN"),
    ROLE_MEMBER("ROLE_MEMBER");

    private final  String type;

}
