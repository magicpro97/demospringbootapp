package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;


    /*@RequestMapping("/user")
    public User user(@RequestParam(value="name", defaultValue="") String name) {
        User user = new User();
        return  getUserByUsername(name);
    }*/

    @RequestMapping("/user/inital")
    public @ResponseBody String initial() {
        repository.save(new User("Linh","123456"));
        repository.save(new User("Phương","33344"));
        repository.save(new User("Hoàng","213123"));
        repository.save(new User("Đức","ccccc"));
        return "Done";
    }

    @RequestMapping("/user/all")
    public @ResponseBody Iterable<User> getAllUsers() {
       return repository.findAll();
    }

    @RequestMapping("/user/update")
    public @ResponseBody User updateAUser(@RequestParam(value = "username", defaultValue = "")String username,
                                           @RequestParam(value = "password",defaultValue = "")String password){
        User oldUser = repository.findByUsername(username).get(0);
        repository.delete(oldUser);
        User newUser = new User(username,password);
        repository.save(newUser);
        return newUser;
    }

    @RequestMapping("/user/delete")
    public @ResponseBody String deleteAUserByUsername(@RequestParam(value = "username", defaultValue = "")String username){
        repository.delete(repository.findByUsername(username).get(0));
        return "Deleted!";
    }


    @RequestMapping("/user/add")
    public @ResponseBody String addNewUser(@RequestParam(value = "username", defaultValue = "")String username,
                                           @RequestParam(value = "password",defaultValue = "")String password){
        User user = new User(username,password);
        repository.save(user);
        return "Save";
    }

}
