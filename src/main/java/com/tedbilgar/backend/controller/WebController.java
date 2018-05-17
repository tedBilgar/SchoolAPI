package com.tedbilgar.backend.controller;

import com.tedbilgar.backend.model.User;
import com.tedbilgar.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user",user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    /*@RequestMapping(value = "/{page}",method = RequestMethod.GET)
    public ModelAndView getPage(@PathVariable(value = "page")String page){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("user",user);
        modelAndView.setViewName(page);
        return modelAndView;
    }*/

    @RequestMapping(value = "/stat",method = RequestMethod.GET)
    public ModelAndView getStat(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users",userService.findUsersByActiveOrderByScoreDesc(true));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userAuth",user);
        modelAndView.setViewName("stat");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView getLogin(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}
