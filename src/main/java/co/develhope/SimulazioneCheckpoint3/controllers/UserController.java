package co.develhope.SimulazioneCheckpoint3.controllers;

import co.develhope.SimulazioneCheckpoint3.entities.Newsletter;
import co.develhope.SimulazioneCheckpoint3.entities.Subscription;
import co.develhope.SimulazioneCheckpoint3.entities.User;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/register{name}{surname}")
    public User register(@PathVariable String name, @PathVariable String surname){
        if (!name.matches("^[a-zA-Z\s]*$")) {
            throw new IllegalArgumentException("Name isn't correct");
        }
        if (!surname.matches("^[a-zA-Z\s]*$")){
            throw new IllegalArgumentException("Surname isn't correct");
        }
        /*user.setId(generateId());
        user.setName(name);
        user.setSurname(surname);
        user.setName(capitalizeFirstLetter(name));
        user.setSurname(capitalizeFirstLetter(surname));*/
        System.out.println(new User());
        //user = new User(this.generateId(),user.getName(),user.getSurname());
        return new User();
    }

    private String capitalizeFirstLetter(String word) {
        List<String> words = List.of(word.split(" "));
        final String[] finalWord = {" "};
        words.forEach(w->{
            finalWord[0] = finalWord[0] + Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase() + " ";
        });
            return finalWord[0];
    }
    @PostMapping("/subscribe{id}{userId}")
    public Subscription subscribe (@PathVariable int id, @RequestHeader HttpHeaders userId){

        Newsletter newsletter= new Newsletter(generateId(),"Saffani");
        User user = new User();

        return new Subscription(LocalDateTime.now(),user,newsletter);
    }

    public int generateId(){
        int minimum = 1;
        Random random = new Random();
        return (int)Math.floor(Math.random()) * (random.nextInt()) + minimum;
    }
}
