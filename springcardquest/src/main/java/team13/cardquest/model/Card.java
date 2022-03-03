package team13.cardquest.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Card implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String type;
    private String cardText;
    //in reality these fields suck at describing a card object, but it they are the minimum we need to show functionality
    //could also use this class as a template to construct cards in memory that have more advanced functionality

    public Card(){}
    public Card(Long id, String name, String type, String cardText){
        this.id = id;
        this.name = name;
        this.type = type;
        this.cardText = cardText;
    }

    public Long GetId(){return id;}
    public String GetName(){return name;}
    public String GetType(){return type;}
    public String GetCardText(){return cardText;}

    public void SetId(Long id){this.id = id;}
    public void SetName(String name){this.name = name;}
    public void SetType(String type){this.type = type;}
    public void SetCardText(String cardText){this.cardText = cardText;}

    public String toString(){
        return "| id: " + id + " | name: " + name + " | type: " + type + " | cardText: " + cardText + " |";
    }
}
