package com.logicea.cards.domain.repositories;

import com.logicea.cards.domain.entities.Cards;
import com.logicea.cards.domain.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
}
