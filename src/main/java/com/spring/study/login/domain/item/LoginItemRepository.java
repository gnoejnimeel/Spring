package com.spring.study.login.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LoginItemRepository {

    private static final Map<Long, LoginItem> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public LoginItem save(LoginItem item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public LoginItem findById(Long id) {
        return store.get(id);
    }

    public List<LoginItem> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, LoginItem updateParam) {
        LoginItem findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
