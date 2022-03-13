package team13.cardquest;

import java.util.Scanner;

public class gameTest {
    public static void main(String[] args){
        System.out.println("testing the game");

        GameHandler testgame = new GameHandler(); //create new game


        
        Scanner getInput = new Scanner(System.in);  // Create a Scanner object
        while(true){
            
            System.out.println("Enter player number");
            String playernumber = (getInput.nextLine());  // Read user input
            //System.out.println("Username is: " + userName);  // Output user input
            
            System.out.println("Enter action");
            String action = (getInput.nextLine());

            executeCommand(playernumber, action, testgame);
        }


    }

    public static void executeCommand(String x, String y, GameHandler g){
        if(g.game.update(x, y)){
            System.out.println("command processed!!");
        } else {
            System.out.println("command rejected...");
        }
        System.out.println("current gamestate:");
        g.game.print();
        /*
        if (x == ""){ //if no player is selected output gamestate
            g.game.print();
            return;
            //System.out.println(g.storydeck.draw().id);
        }

        if (!(x == "1" || x == "2" || x == "3" || x == "4")){
            System.out.println("invalid player number");
            return;
        }

        //events here will be sent with a player number
        switch(y){
            case "":

                break;

            default:
                System.out.println("invalid command");
                break;
        }
        */


    }
    
}
/*
*/