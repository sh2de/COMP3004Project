package team13.cardquest.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team13.cardquest.repo.CardRepo;
import team13.cardquest.model.Card;
import team13.cardquest.exception.*;

import java.util.List;

@Service
public class CardService {
    private final CardRepo cardRepo;

    @Autowired
    public CardService(CardRepo cardRepo){
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
