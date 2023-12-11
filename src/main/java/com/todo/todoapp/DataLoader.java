package com.todo.todoapp;

import com.todo.todoapp.models.ToDoItem;
import com.todo.todoapp.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner
{
    @Autowired
    ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception
    {
        loadData();
    }

    private void loadData()
    {
        if (itemRepository.count() == 0)
        {
            ToDoItem item1 = new ToDoItem("zakupy");
            ToDoItem item2 = new ToDoItem("odkurzyc");
            itemRepository.save(item1);
            itemRepository.save(item2);
        }

        System.out.println("Items: " + itemRepository.count());
    }
}
