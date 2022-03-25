package team13.cardquest;

import java.util.Scanner;
import java.util.ArrayList;

public class gameTest {
    public static void main(String[] args){
        System.out.println("testing the game");

        Game game = new Game(); //create new game
        //Scanner getInput = new Scanner(System.in);
        //System.out.println("Enter player name");
        //String name = (getInput.nextLine());  // Read user input
        //game.joinGame(name);

        /*while(true){
            executeSignals(game.getUpdates(name), name, game);
        }*/

        //WRITE TEST CODE HERE----------------------------------
        String p1 = "player 1";
        String p2 = "player 2";
        game.joinGame(p1);
        game.joinGame(p2);
        game.startGame(p1);
        game.startGame(p2);
        game.print();
        
        
        
        //TEST CODE END-----------------------------------------
    }

    /*
    public static void executeSignals(ArrayList<String> commands, String name, Game game){
        for (String signal : commands) {
            switch(signal){
                case "ALL_PLAYERS_READY":
                    game.getPlayer(name).print();
                    break;
                case "REQUEST_SPONSORSHIP":
                    break;
                case "":
                    break;
                default:
                    System.out.println("invalid signal");
                    break;
            }
        }
    }*/
 
}
/*
*/