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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("search/{searchName}")
    public ResponseEntity<Object> searchCards(@PathVariable String searchName) {
        List<Cards> cards = cardService.searchCards(searchName);
        return ResponseHandler.generateResponse("Cards fetched successfully", HttpStatus.OK, cards);
    }

    @PatchMapping (value = "{id}")
    public ResponseEntity<Object> updateCards(@PathVariable Long id, @Valid @RequestBody CardDto cardDto) {
        Cards cards = cardService.updateCards(id, cardDto);
        return ResponseHandler.generateResponse("Cards updated successfully", HttpStatus.ACCEPTED, cards);
    }
    @GetMapping(value = "admin")
    public ResponseEntity<Object> adminGetAllCards(Principal principal) {
        Users user = userService.getUser(principal);
        List<Cards> cards = cardService.adminGetAllCards(user);
        return ResponseHandler.generateResponse("Cards fetched success", HttpStatus.OK, cards);
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<Object> deleteCard(@PathVariable Long id){
        cardService.deleteCard (id);
        return ResponseHandler.generateResponse("Card deleted successfully", HttpStatus.ACCEPTED,
        null);
    }
}
