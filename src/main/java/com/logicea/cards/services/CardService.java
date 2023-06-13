package com.logicea.cards.services;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {
    Cards createCards(Users user, CardDto cardDto);

    Page<Cards> getCardsForSingleUser(Users user, Pageable pageable);
    Page<Cards> adminGetAllCards(Pageable pageable);

    void deleteCard(Long id);

    Cards updateCards(Long id, CardDto cardDto);

    List<Cards> searchCards(String searchName);
}
