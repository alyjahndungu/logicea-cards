package com.logicea.cards.domain.models;

import java.util.List;

public record AuthResponse(
        String username,
        String accessToken,
        String type,
        List<String> roles
) {

}
