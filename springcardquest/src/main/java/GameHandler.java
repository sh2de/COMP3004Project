import java.util.ArrayList;

//The game handler receives requests for what moves to make in the game
//and then checks if the move is legal, and executes it if so

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameHandler {
    public Game game = new Game();
    


    public GameHandler(){

    }

    @PostMapping("/join")
    public ResponseEntity<Object> joinGame(@RequestBody String name){
        Player newPlayer=new Player(name);
        game.addPlayer(newPlayer);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }


    
    

}
