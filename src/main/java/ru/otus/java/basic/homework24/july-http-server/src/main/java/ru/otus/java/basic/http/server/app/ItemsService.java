package ru.otus.java.basic.http.server.app;

import ru.otus.java.basic.http.server.app.dto.ItemDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemsService {
    private List<ItemDto> items;

    public ItemsService() {
        this.items = new ArrayList<>(Arrays.asList(
                new ItemDto(1L, "Milk", 80),
                new ItemDto(2L, "Bread", 40),
                new ItemDto(3L, "Cheese", 400)
        ));
    }

    public List<ItemDto> getAll() {
        return Collections.unmodifiableList(items);
    }

    public ItemDto getById(Long id) {
        return items.stream().filter(i -> i.getId().equals(id)).findFirst().get();
    }

    public ItemDto createNewItem(ItemDto itemDto) {
        Long newId = items.stream().mapToLong(ItemDto::getId).max().orElse(0L) + 1L;
        itemDto.setId(newId);
        items.add(itemDto);
        return itemDto;
    }
    public void deleteItem(Long id) {
        items.removeIf(i -> i.getId().equals(id));
    }
}
