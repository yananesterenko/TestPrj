package com.project.dao;

import com.project.models.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDao {

    void addItem(Item item, Connection connection);
    void updateItem(Item item, Connection connection);
    void deleteItem(String id, Connection connection);
    Item getItemById(int id);
    List<String[]> listItems(Connection connection);



}
