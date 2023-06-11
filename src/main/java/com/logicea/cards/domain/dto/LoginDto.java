package com.logicea.cards.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDto(@NotEmpty String username, @NotEmpty String password) {
}
