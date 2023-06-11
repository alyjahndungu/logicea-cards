package com.logicea.cards.domain.dto;

import com.logicea.cards.domain.enumeration.EStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record CardDto(@NotEmpty String name, String description,
                      @Pattern(regexp = "^#[A-Fa-f0-9]{6}$", message = "Invalid color code")
                      String color, EStatus status) {
}
