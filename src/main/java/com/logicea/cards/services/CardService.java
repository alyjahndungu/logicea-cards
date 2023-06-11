package com.logicea.cards.services;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;

import java.util.List;

public interface CardService {
    Cards createCards(Users user, CardDto cardDto);

    List<Cards> getCardsForSingleUser(Users user);
    List<Cards> getAllCards(Users user);

}
