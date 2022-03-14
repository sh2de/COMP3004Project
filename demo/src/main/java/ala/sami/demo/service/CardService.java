package ala.sami.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ala.sami.demo.model.Card;
import ala.sami.demo.repo.CardRepo;
import ala.sami.demo.exception.*;

import java.util.UUID;

@Service
public class CardService {
    private final CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo) {
        this.cardRepo = cardRepo;
    }

    public Card addCard(Card card){
        card.SetId(Long.valueOf(Integer.parseInt(UUID.randomUUID().toString())));
        return cardRepo.save(card);
    }

    public List<Card> findAllCards(){return cardRepo.findAll();}
    public Card findCardById(Long id){
        return cardRepo.findCardById(id)
            .orElseThrow(() -> new CardNotFoundException ("Card " + id + " was not found"));
    }
    
    public Card updateCard(Card card){return cardRepo.save(card);}
    public void deleteCard(Long id){cardRepo.deleteCardById(id);}
}
