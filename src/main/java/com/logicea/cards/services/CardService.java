package com.logicea.cards.services;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;

import java.util.List;

public interface CardService {
    Cards createCards(Users user, CardDto cardDto);

    List<Cards> getCardsForSingleUser(Users user);
    List<Cards> adminGetAllCards(Users user);

    void deleteCard(Long id);

    Cards updateCards(Long id, CardDto cardDto);

    List<Cards> searchCards(String searchName);
}
