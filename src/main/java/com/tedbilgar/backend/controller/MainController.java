package com.tedbilgar.backend.controller;

import com.tedbilgar.backend.model.Location;
import com.tedbilgar.backend.model.User;
import com.tedbilgar.backend.service.LocationService;
import com.tedbilgar.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/findUser")
    public String findUser() {
        return userService.findUser();
    }

    @RequestMapping("/findTheFirst")
    public User findFUser() {
        return userService.findFUser();
    }

    @RequestMapping("/users/{email}/name")
    public String getUserNameByEmail(@PathVariable("email")String email){
        return userService.getUserNameByEmail(email);
    }
    @RequestMapping("/users/{email}/score")
    public int  getScoreByEmail(@PathVariable("email")String email){
        return userService.getScoreByEmail(email);
    }

    @RequestMapping(value = "/users/setscore",method = RequestMethod.POST)
    public String setScoreByEmail(@Valid User user,BindingResult bindingResult){
        System.out.println(user.getEmail());
         userService.setScoreByEmail(user.getEmail(),2);
         return "Nice score";
    }

    //API for locations

    @RequestMapping("/locations")
    public List<Location> findAllLocations(){
        return locationService.findAllLocations();
    }

    @RequestMapping("/locations/{name}")
    public Location findLocationByName(@PathVariable("name")String name){
        return locationService.findLocationByName(name);
    }


    // Mapping for registration and login
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUserExample4(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("example4");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("example4");

        }
        return "Nice registration";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(@Valid User user, BindingResult bindingResult){
        if(userService.initUser(user))
        {
            return user.getEmail();
        }
        return "Bad Registration";
       //return user.getPassword();
    }

}
