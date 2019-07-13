package com.project.service;

import com.project.dao.ConnectionPostgress;
import com.project.dao.ItemDaoImpl;
import com.project.models.Item;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl {
    static final File dir = new File(System.getProperty("user.dir") + "/data");
    static File file = new File(dir, "item.xml");

    public static void getItemInformation(String pathNet) {
        Item itemHTMLObject = ItemHTMLService.getHTMLItemInformation(pathNet);
        if (!dir.exists()) {
            try {
                dir.mkdir();
            } catch (SecurityException se) {
                //handle it
            }
        }
        WriteXMLFileService.fillingXMLFile(file, itemHTMLObject);
    }

    public static void addItemInformation() {
        Item item = ReadXMLFileService.readItemToObject(file);
        Connection connection = new ConnectionPostgress().getConnection();
        if (connection != null) {

            ItemDaoImpl dao = new ItemDaoImpl();
            dao.addItem(item, connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateItemInformation(Item item) {
        Connection connection = new ConnectionPostgress().getConnection();
        if (connection != null) {
            ItemDaoImpl dao = new ItemDaoImpl();
            dao.updateItem(item, connection);
            System.out.println("Was updated");
        }
    }

    public static void deleteItemInformation(String id) {
        Connection connection = new ConnectionPostgress().getConnection();
        if (connection != null) {
            ItemDaoImpl dao = new ItemDaoImpl();
            dao.deleteItem(id, connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String[]> selectItemInformation() {
        Connection connection = new ConnectionPostgress().getConnection();
        List<String[]> listItems = null;
        if (connection != null) {
            ItemDaoImpl dao = new ItemDaoImpl();
            listItems = dao.listItems(connection);
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listItems;

    }
}
