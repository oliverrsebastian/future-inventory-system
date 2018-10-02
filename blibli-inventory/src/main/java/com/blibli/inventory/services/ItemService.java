package com.blibli.inventory.services;

import com.blibli.inventory.models.Item;
import com.blibli.inventory.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository repository;

    public List<Item> getItemList(Pageable pageable){return repository.getItemList(pageable);};
    public String saveItem(Item item){return repository.saveItem(item);};
    public String deleteItemById(String id){return repository.deleteItemById(id);};
}
