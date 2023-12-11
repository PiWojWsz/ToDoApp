package com.todo.todoapp.controller;

import com.todo.todoapp.models.ToDoItem;
import com.todo.todoapp.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ToDoListController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index.html");
        modelAndView.addObject("toDoList", itemRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public String editItem(@PathVariable("id") long id, Model model)
    {
        ToDoItem toDoItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDoItem not found Id: " + id));
        model.addAttribute("todo", toDoItem);
        return "edit-item";
    }
}
