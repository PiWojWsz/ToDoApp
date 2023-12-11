package com.todo.todoapp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean complete;

    public ToDoItem() {};
    public ToDoItem(String description) {
        this.description = description;
        this.complete = false;
    }


}
