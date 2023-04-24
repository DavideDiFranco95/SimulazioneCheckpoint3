package co.develhope.SimulazioneCheckpoint3.controllers;

import co.develhope.SimulazioneCheckpoint3.entities.Newsletter;
import co.develhope.SimulazioneCheckpoint3.entities.Subscription;
import co.develhope.SimulazioneCheckpoint3.entities.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {
    User user = new User();
    Newsletter newsletter = new Newsletter(generateId(),"Steam");

    @PostMapping("/register/{name}/{surname}")
    public User register(@PathVariable String name,@PathVariable String surname){
        if (!name.matches("^[a-zA-Z\s]*$")) {
            throw new IllegalArgumentException("Name isn't correct");
        }
        if (!surname.matches("^[a-zA-Z\s]*$")){
            throw new IllegalArgumentException("Surname isn't correct");
        }
        user.setId(generateId());
        user.setName(name);
        user.setSurname(surname);
        user.setName(capitalizeFirstLetter(name));
        user.setSurname(capitalizeFirstLetter(surname));
        System.out.println(user);
        return user;
    }

    private String capitalizeFirstLetter(String word) {
        List<String> words = List.of(word.split(" "));
        final String[] finalWord = {" "};
        words.forEach(w->{
            finalWord[0] = finalWord[0] + Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase() + " ";
        });
            return finalWord[0];
    }
    @PostMapping("/subscribe/{id}/")
    public ResponseEntity<Subscription> subscribe (@PathVariable int id, @RequestHeader("X-UserId") int XUserId) throws Exception{
        User user1 = new User();
        Newsletter newsletter1 = new Newsletter();
        if (XUserId == user.getId() && id == newsletter.getId()){
            user1 = user;
            newsletter1 = newsletter;
        }else{
            throw new Exception("Gli ID non sono presenti");
        }

        Subscription subscription = new Subscription(LocalDateTime.now(),user1,newsletter1);
        System.out.println(subscription);


        return new ResponseEntity<>(subscription,HttpStatus.OK);
    }

    public int generateId(){
        int minimum = 1;
        Random random = new Random();
        return (int)Math.floor(Math.random()) * (random.nextInt()) + minimum;
    }
}
