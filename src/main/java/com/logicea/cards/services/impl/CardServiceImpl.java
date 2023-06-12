package com.logicea.cards.services.impl;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.domain.enumeration.EStatus;
import com.logicea.cards.domain.repositories.CardsRepository;
import com.logicea.cards.exceptions.NotFoundException;
import com.logicea.cards.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardsRepository cardsRepository;

    @Override
    public Cards createCards(Users user, CardDto cardDto) {
        Cards cards = Cards.builder().name(cardDto.name())
                .description(cardDto.description())
                .color(cardDto.color())
                .status(String.valueOf(EStatus.TO_DO)).
                users(user).build();
        return cardsRepository.save(cards);
    }

    @Override
    public List<Cards> getCardsForSingleUser(Users user) {
        return cardsRepository.findCardsByUsers(user);
    }

    @Override
    public List<Cards> adminGetAllCards(Users user) {
        return cardsRepository.findAll();
    }

    @Override
    public void deleteCard(Long id) {
        Cards cards = getCardById(id);
        cardsRepository.delete(cards);
    }

    @Override
    public Cards updateCards(Long id, CardDto cardDto) {
        Cards cards = getCardById(id);
        cards.setName(cardDto.name());
        cards.setDescription(cardDto.description());
        cards.setColor(cardDto.color());
        cards.setStatus(cards.getStatus());

        return cardsRepository.save(cards);
    }

    @Override
    public List<Cards> searchCards(String searchName) {
        return cardsRepository.findCardsByNameContainsIgnoreCase(searchName);
    }

    private Cards getCardById(Long id) {
        return cardsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No card found::" + id));
    }

}
