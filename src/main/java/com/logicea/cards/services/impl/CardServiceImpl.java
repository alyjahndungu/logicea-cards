package com.logicea.cards.services.impl;

import com.logicea.cards.domain.dto.CardDto;
import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import com.logicea.cards.domain.enumeration.EStatus;
import com.logicea.cards.domain.repositories.CardsRepository;
import com.logicea.cards.exceptions.NotFoundException;
import com.logicea.cards.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .status(EStatus.TO_DO).
                users(user).build();
        return cardsRepository.save(cards);
    }

    @Override
    public Page<Cards> getCardsForSingleUser(Users user, Pageable pageable) {
        return cardsRepository.findCardsByUsers(user, pageable);
    }

    @Override
    public Page<Cards> adminGetAllCards(Pageable pageable) {
        return cardsRepository.findAll(pageable);
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
        cards.setStatus(cardDto.status());

        return cardsRepository.save(cards);
    }

    @Override
    public List<Cards> searchCards(String searchName) {
        return cardsRepository.findCardsByNameContainsIgnoreCase(searchName);
    }

    @Override
    public Cards getCard(Users user, Long id) {
        return cardsRepository.findCardsByUsersAndId(user, id);
    }

    private Cards getCardById(Long id) {
        return cardsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No card found::" + id));
    }

}
