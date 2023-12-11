package com.todo.todoapp.repositories;

import com.todo.todoapp.models.ToDoItem;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ToDoItem, Long> {
}
