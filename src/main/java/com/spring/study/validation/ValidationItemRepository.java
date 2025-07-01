package com.spring.study.validation;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ValidationItemRepository {

    private static final Map<Long, ValidationItem> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public ValidationItem save(ValidationItem item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public ValidationItem findById(Long id) {
        return store.get(id);
    }

    public List<ValidationItem> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, ValidationItem updateParam) {
        ValidationItem findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
