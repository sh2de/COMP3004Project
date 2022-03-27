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


        //WRITE TEST CODE HERE----------------------------------
        String p1 = "player";
        String p2 = "player";
        p1 = game.joinGame(p1);
        p2 = game.joinGame(p2);
        game.startGame(p1);
        game.startGame(p2);
        game.print();
        
        
        
        //TEST CODE END-----------------------------------------
    } 
}