package com.quizz.QuizzMaker.Controller;

import com.quizz.QuizzMaker.Model.User;
import com.quizz.QuizzMaker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @Autowired
    private UserService userService;
    private String user_name = null;

    @GetMapping("/")
    public ModelAndView loadHome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("user" , user_name);
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/signup")
    public ModelAndView loadSignUp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signup");
        return mv;
    }

    @PostMapping("/signup")
    public ModelAndView signUpNext(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String ph,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("frole") String role
    )
    {
        ModelAndView mv = new ModelAndView();
        if(userService.usernameExists(username)){
            mv.addObject("usernameExists" , "Entered Username exists!!");
            mv.setViewName("signup");
            return mv;
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        user.setPhone(ph);
        user.setRole(role);

        userService.SaveUser(user);

        mv.addObject("user",user_name);
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView loadLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginNext(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("frole") String role
    ){
        ModelAndView mv = new ModelAndView();
        if(!userService.usernameExists(username)){
            mv.setViewName("signup");
            return mv;
        }
        User existed_user = userService.findByUsername(username);
        if(!existed_user.getPassword().equals(password)){
            mv.addObject("wrongPassword","You have entered wrong password");
            mv.setViewName("login");
            return mv;
        }
        if(!existed_user.getRole().equals(role)){
            mv.addObject("wrongRole","You have entered wrong role");
            mv.setViewName("login");
            return mv;
        }
        user_name = userService.findNameByUsername(username);
        mv.addObject("user",user_name);
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView Logout(){
        user_name = null;
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user_name);
        mv.setViewName("Home");
        return mv;
    }
}
