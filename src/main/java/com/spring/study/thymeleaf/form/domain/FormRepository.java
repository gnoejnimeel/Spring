package com.spring.study.thymeleaf.form.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FormRepository {

    private static final Map<Long, Form> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Form save(Form item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Form findById(Long id) {
        return store.get(id);
    }

    public List<Form> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Form updateParam) {
        Form findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setOpen(updateParam.getOpen());
        findItem.setRegions(updateParam.getRegions());
        findItem.setItemType(updateParam.getItemType());
        findItem.setDeliveryCode(updateParam.getDeliveryCode());
    }

    public void clearStore() {
        store.clear();
    }

}
