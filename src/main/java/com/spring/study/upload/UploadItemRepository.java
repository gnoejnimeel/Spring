package com.spring.study.upload;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UploadItemRepository {
    private final Map<Long, UploadItem> store = new HashMap<>();
    private long sequence = 0L;

    public UploadItem save(UploadItem item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public UploadItem findById(Long id) {
        return store.get(id);
    }
}
