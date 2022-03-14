package ala.sami.demo.exception;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(String message){
        super(message);
    }
}
