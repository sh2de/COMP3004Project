package team13.cardquest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team13.cardquest.service.CardService;
import team13.cardquest.model.Card;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardResource {
    private final CardService cardService;

    public CardResource(CardService cardService){
        this.cardService = cardService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Card>> getAllCards(){
        List<Card> cards = cardService.findAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable("id") Long id){
        Card card = cardService.findCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Card> addCard(@RequestBody Card card){
        Card _card = cardService.addCard(card);
        return new ResponseEntity<>(_card, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Card> updateCard(@RequestBody Card card){
        Card _card = cardService.updateCard(card);
        return new ResponseEntity<>(_card, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id){
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
