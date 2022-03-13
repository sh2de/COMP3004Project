package team13.cardquest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/game")
public class GameResource {

    @GetMapping("/hello")
    public ResponseEntity<Object> helloWorld(){
        String s = "hello World";
        return new ResponseEntity<>(new String[]{"hello world"}, HttpStatus.OK);
    }


}
