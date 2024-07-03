package com.example.service;


import com.example.entity.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getItemsByName(String name) {
        return itemRepository.findByName(name);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item itemDetails) {
        return itemRepository.findById(id).map(item -> {
            item.setName(itemDetails.getName());
            item.setPersona(itemDetails.getPersona());
            return itemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
    }

    public void deleteItem(Long id) {
        itemRepository.findById(id).ifPresentOrElse(
            item -> itemRepository.delete(item),
            () -> {
                throw new RuntimeException("Item not found with id: " + id);
            }
        );
    }
}
