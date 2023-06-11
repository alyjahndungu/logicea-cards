package com.logicea.cards.services.impl;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService  {
    @Override
    public Cards createCards(Users user, CardDto cardDto) {
        return null;
    }

    @Override
    public List<Cards> getCardsForSingleUser(Users user) {
        return null;
    }

    @Override
    public List<Cards> getAllCards(Users user) {
        return null;
    }

}
