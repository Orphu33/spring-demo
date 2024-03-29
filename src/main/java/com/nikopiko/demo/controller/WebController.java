package com.nikopiko.demo.controller;

import com.nikopiko.demo.model.JokeForm;
import com.nikopiko.demo.model.LoginForm;
import com.nikopiko.demo.repositories.DemoAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class WebController {

    @Autowired
    DemoAppService appService;

    @GetMapping(value = {"", "/", "jokes"})
    public String getAllJokes(Model model, @RequestParam(name = "pageNumber", required = false, defaultValue = "0") int pageNumber) {
        int pageSize = 10;
        model.addAttribute("jokes", appService.getAllJokes(pageNumber, pageSize));
        return "jokes";
    }

    @GetMapping("/login")
    public String login(LoginForm loginForm, Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @GetMapping("/jokes/like")
    public String likeJoke(@RequestParam int id, String like) {
        appService.updateLikes(id, like);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newJokeForm(Model model, JokeForm jokeForm) {
        model.addAttribute("categories", appService.getAllCategories());
        return "jokeForm";
    }

    @PostMapping("/new")
    public String insetJoke(@Valid JokeForm jokeForm, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("categories", appService.getAllCategories());
            model.addAttribute("errorMessage", "Content must not be empty.");
            return "jokeForm";
        }

        appService.insertJoke(jokeForm.getCatId(), jokeForm.getContent());
        return "redirect:/";
    }

    //mapping to insert numberOfJokes into DB
    @GetMapping("/insert")
    public String insertRandomJokes() {
        appService.insertRandomData(25);
        return "redirect:/";
    }

}
