package co.develhope.SimulazioneCheckpoint3.controllers;

import co.develhope.SimulazioneCheckpoint3.entities.Newsletter;
import co.develhope.SimulazioneCheckpoint3.entities.Subscription;
import co.develhope.SimulazioneCheckpoint3.entities.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    List<Subscription> subscriptionList = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<Newsletter> newsletters = new ArrayList<>();

    @PostMapping("/register")
    public User register(@RequestParam int id,@RequestParam String name, @RequestParam String surname){
        User user= new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        if (!name.matches("^[a-zA-Z\s]*$")) {
            throw new IllegalArgumentException("Name isn't correct");
        }
        if (!surname.matches("^[a-zA-Z\s]*$")){
            throw new IllegalArgumentException("Surname isn't correct");
        }

        user.setName(capitalizeFirstLetter(name));
        user.setSurname(capitalizeFirstLetter(surname));
        System.out.println(user);
        users.add(user);
        newsletters.add(new Newsletter(1,"Saffani"));
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
    @PostMapping("/subscribe")
    public Subscription subscribe (@RequestParam int newsletter_id, @RequestHeader int user_id){

        Newsletter newsletter = null;
        User user = new User();

        for (User u : users) {
            if (u.getId() == user_id) {
                user = u;
                break;
            }else{
                throw new NullPointerException();
            }
        }
        for (Newsletter n : newsletters){
            if (n.getId() == newsletter_id){
                newsletter = n;
                break;
            }else{
                throw new NullPointerException();
            }
        }
        Subscription subscription = new Subscription(LocalDateTime.now(),user,newsletter);
        subscriptionList.add(subscription);
        return subscription;
    }
}
