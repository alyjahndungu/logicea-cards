package com.logicea.cards.controllers;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.handlers.ResponseHandler;
import com.logicea.cards.services.CardService;
import com.logicea.cards.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
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

    @Operation(summary = "Endpoint to  add new cards")
    @PostMapping
    public ResponseEntity<Object> createCards(Principal principal, @Valid @RequestBody CardDto cardDto) {
        Users user = userService.getUser(principal);
        Cards cards = cardService.createCards(user, cardDto);
        return ResponseHandler.generateResponse("Cards created success", HttpStatus.CREATED, cards);
    }

    @Operation(summary = "Endpoint to fetching all cards related to  an individual user")
    @GetMapping
    public ResponseEntity<Object> getCardsForSingleUser(Principal principal,  @SortDefault.SortDefaults({@SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable pageable) {
        Users user = userService.getUser(principal);
        Page<Cards> cards = cardService.getCardsForSingleUser(user, pageable);
        return ResponseHandler.generateResponse("Cards fetched success", HttpStatus.OK, cards);
    }

    @Operation(summary = "Endpoint to fetch a single card related to  an individual user")
    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getCard(Principal principal, @PathVariable Long id) {
        Users user = userService.getUser(principal);
        Cards card = cardService.getCard(user, id);
        return ResponseHandler.generateResponse("Card fetched successfully", HttpStatus.OK, card);
    }

    @Operation(summary = "Endpoint to search for cards")
    @GetMapping("search/{searchName}")
    public ResponseEntity<Object> searchCards(@PathVariable String searchName) {
        List<Cards> cards = cardService.searchCards(searchName);
        return ResponseHandler.generateResponse("Cards fetched successfully", HttpStatus.OK, cards);
    }

    @Operation(summary = "Endpoint to update card details")
    @PatchMapping (value = "{id}")
    public ResponseEntity<Object> updateCards(@PathVariable Long id, @Valid @RequestBody CardDto cardDto) {
        Cards cards = cardService.updateCards(id, cardDto);
        return ResponseHandler.generateResponse("Cards updated successfully", HttpStatus.ACCEPTED, cards);
    }

    @Operation(summary = "Endpoint for ADMIN to view all cards")
    @GetMapping(value = "admin")
    public ResponseEntity<Object> adminGetAllCards(@SortDefault.SortDefaults({@SortDefault(sort = "name", direction = Sort.Direction.DESC)}) Pageable pageable,   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "20") int size) {
        Page<Cards> cards = cardService.adminGetAllCards(pageable);
        return ResponseHandler.generateResponse("Cards fetched success", HttpStatus.OK, cards);
    }

    @Operation(summary = "Endpoint to delete cards")
    @DeleteMapping(value = "{id}")
    public  ResponseEntity<Object> deleteCard(@PathVariable Long id){
        cardService.deleteCard (id);
        return ResponseHandler.generateResponse("Card deleted successfully", HttpStatus.ACCEPTED,
        null);
    }
}
