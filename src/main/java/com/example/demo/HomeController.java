package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ListRepository listRepo;

    @RequestMapping("/")
    public String listTodo(Model model){
        model.addAttribute("addList", listRepo.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String listNow (Model model){
        model.addAttribute("listToday", new TodoList());
        return "listform";
    }
    @PostMapping("/process")
    public String processList (@Valid @ModelAttribute("listToday") TodoList listToday, BindingResult result){
        if(result.hasErrors()){
            return "listform";
        }
        listRepo.save(listToday);
        return "redirect:/";
    }
//    @RequestMapping("/create/{id}")
////    public String showList (@PathVariable ("id") long id, Model model){
////        model.addAttribute("listToday", listRepo.findById(id).get());
//        return "listform";
//    }
    @RequestMapping("/update/{id}")
    public String updateList (@PathVariable ("id") long id, Model model){
        model.addAttribute("listToday", listRepo.findById(id).get());
        return "listform";
    }
    @RequestMapping("/delete/{id}")
    public String delList (@PathVariable ("id") long id){
        listRepo.deleteById(id);
        return "redirect:/";
    }
}
