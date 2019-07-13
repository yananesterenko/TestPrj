package com.project.dao;

import com.project.models.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ItemDaoImpl implements ItemDao {
    private static final Logger logger = Logger.getLogger(ItemDaoImpl.class.getName());
    @Override

    public void addItem(Item item, Connection connection) {
        String sql = "INSERT INTO items (itemname, price, imageurl) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, item.getName());
            stm.setDouble(2, item.getPrice());
            stm.setString(3, item.getImageURL());
            stm.executeUpdate();
            connection.commit();
            logger.info("Item " + item.getName() + " was added");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void updateItem(Item item, Connection connection) {
        String sql = "UPDATE  items SET itemname = '" + item.getName() + "'," +
                " price = " + item.getPrice() + ", imageurl = '" + item.getImageURL() +"' where id = " + item.getId() ;
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.executeUpdate();
            connection.commit();
            logger.info("Item " + item + " was changed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(String id, Connection connection) {
        String sql = "DELETE  FROM items where id = " + id ;
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.executeUpdate();
            connection.commit();
            logger.info("Item with id " + id + " was changed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item getItemById(int id) {
        return null;
    }

    @Override
    public List<String[]> listItems(Connection connection) {
        List<String[]> itemList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM ITEMS;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("itemname");
                float price  = rs.getFloat("price");
                String  imageurl = rs.getString("imageurl");
                String[] elem = new String[]{String.valueOf(id), name, String.valueOf(price), imageurl };
                /*Item item = new Item();
                item.setId(String.valueOf(id));
                item.setName(name);
                item.setPrice(price);
                item.setImageURL(imageurl);*/
                itemList.add(elem);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
