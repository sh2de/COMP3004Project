
import java.util.Scanner;
public class gameTest {
    public static void main(String[] args){
        System.out.println("testing the game");

        GameHandler game = new GameHandler(); //create new game


        
        Scanner getInput = new Scanner(System.in);  // Create a Scanner object
        while(true){
            
            System.out.println("Enter player number");
            Integer playernumber = Integer.parseInt(getInput.nextLine());  // Read user input
            //System.out.println("Username is: " + userName);  // Output user input
            
            System.out.println("Enter action");
            Integer action = Integer.parseInt(getInput.nextLine());

            executeCommand(playernumber, action);
        }


    }

    public static void executeCommand(Integer x, Integer y){
        
    }
    
}
/*
*/