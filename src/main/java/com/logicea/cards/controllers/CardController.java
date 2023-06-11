package com.logicea.cards.controllers;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.handlers.ResponseHandler;
import com.logicea.cards.services.CardService;
import com.logicea.cards.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {
    private final UserService userService;
    private final CardService cardService;

    @PostMapping
    public ResponseEntity<Object> createCards(Principal principal, @Valid @RequestBody CardDto cardDto) {
        Users user = userService.getUser(principal);
        Cards cards = cardService.createCards(user, cardDto);
        return ResponseHandler.generateResponse("Cards created success", HttpStatus.CREATED, cards);
    }

    @GetMapping
    public ResponseEntity<Object> getCardsForSingleUser(Principal principal) {
        Users user = userService.getUser(principal);
        List<Cards> cards = cardService.getCardsForSingleUser(user);
        return ResponseHandler.generateResponse("Cards fetched success", HttpStatus.OK, cards);
    }
}
