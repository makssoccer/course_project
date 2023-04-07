package com.example.opinion_about_the_players.conrtrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class SecurityController {

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
//        Users user= uRepo.findByUsername(userDetial.getUsername());
        return principal.getName();
    }
}
//}@RequestMapping(value="/posts/create", method=RequestMethod.GET)
//public String addPost(String username, Model model){
//    Authentication auth= SecurityContextHolder.getContext().getAuthentication();
//    String name=auth.getName();
//    UserDetails userDetial= (UserDetails) auth.getDetails();