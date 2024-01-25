package com.todo.todoapp.controller;

import com.todo.todoapp.models.ToDoItem;
import com.todo.todoapp.repositories.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoListController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    private Environment env;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("toDoList", itemRepository.findAll());
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") long id, Model model)
    {
        ToDoItem toDoItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDoItem not found Id: " + id));
        model.addAttribute("todo", toDoItem);
        return "edit";
    }

    @Secured("ROLE_ADMIN")
    @PatchMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") long id, @Valid ToDoItem todoItem, BindingResult result, Model model)
    {
        if (result.hasErrors())
        {
            todoItem.setId(id);
            return "edit";
        }

        itemRepository.save(todoItem);
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") long id, Model model)
    {
        ToDoItem toDoItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDoItem not found Id: " + id));
        itemRepository.delete(toDoItem);
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/create")
    public String create(ToDoItem toDoItem)
    {
        return "add";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/todo")
    public String createItem(@Valid ToDoItem toDoItem, BindingResult result, Model model)
    {
        if (result.hasErrors() || toDoItem.getDescription().isEmpty())
        {
            return "add";
        }

        itemRepository.save(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/api/property?name={propertyName}")
    @ResponseBody
    public String getProperty(@PathVariable("propertyName") String propertyName) {
        System.out.println("### Propertyname: " + propertyName);
        return env.getProperty(propertyName);
    }
}
