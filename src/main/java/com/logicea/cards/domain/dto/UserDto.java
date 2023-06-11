package com.logicea.cards.domain.dto;

import com.logicea.cards.domain.enumeration.ERole;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDto (@NotEmpty String name, @Email @NotEmpty String email, @Enumerated ERole role){
}
