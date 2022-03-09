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
    private String pathToImage;
    private int frequency;
    private int mainBattlePower;
    private int secondaryBattlePower;
    private int stages;
    private Long foeId;
    private boolean isMordred;

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
    public String GetPathToImage(){return pathToImage;}
    public int GetFrequency(){return frequency;}
    public int GetMainBattlePower(){return mainBattlePower;}
    public int GetSecondaryBattlePower(){return secondaryBattlePower;}
    public int GetStages(){return stages;}
    public Long GetFoeId(){return foeId;}
    public boolean GetIsMordred(){return isMordred;}

    public void SetId(Long id){this.id = id;}
    public void SetName(String name){this.name = name;}
    public void SetType(String type){this.type = type;}
    public void SetCardText(String cardText){this.cardText = cardText;}
    public void SetPathToImage(String pathToImage){this.pathToImage = pathToImage;}
    public void SetFrequency(int frequency){this.frequency = frequency;}
    public void SetMainBattlePower(int mainBattlePower){this.mainBattlePower = mainBattlePower;}
    public void SetSecondaryBatlePower(int secondaryBattlePower){this.secondaryBattlePower = secondaryBattlePower;}
    public void SetStages(int stages){this.stages = stages;}
    public void SetFoeId(Long foeId){this.foeId = foeId;}
    public void SetIsMordred(boolean isMordred){this.isMordred = isMordred;}

    public String toString(){
        return "| id: " + id + " | name: " + name + " | type: " + type + " | cardText: " + cardText + " |";
    }
}
