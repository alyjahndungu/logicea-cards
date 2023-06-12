package com.logicea.cards.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserDto (@NotEmpty String name, @Email @NotEmpty String email, @NotEmpty String password){
}
