package com.project.views;

import com.project.models.Item;
import com.project.service.ItemServiceImpl;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

public class ViewForm {
    public static JTable tableItem = null;
    public static void runUI(String pathLocal, String pathNet) {

        JFrame frame = createFrame();
        JPanel rootPanel = createRootPanel();
        JPanel topPanel = createTopPanel(pathLocal, pathNet);
        rootPanel.add(topPanel);
        JTable tableItem = new JTable();
        JPanel tablePanel = createTablePannel(tableItem);
        rootPanel.add(tablePanel);
        JPanel bottomPanel = createBottomPanel();
        rootPanel.add(bottomPanel);
        frame.add(rootPanel);
        frame.setVisible(true);
    }

    private static JPanel createTopPanel(String pathLocal, String pathNet) {
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setPreferredSize(new Dimension(400, 150));
        JLabel labelNetPath = new JLabel("Path from net:");
        JTextField textLocalPath = new JTextField(pathLocal, 25);
        textLocalPath.setForeground(Color.GRAY);
        JLabel labelLocalPath = new JLabel("Local path for saving:");
        JTextField textNetPath = new JTextField(pathNet, 25);
        textNetPath.setForeground(Color.GRAY);
        GridBagConstraints gbc = new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL,
                new Insets(30, 0, 0, 0), 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(labelNetPath, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(textNetPath, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(labelLocalPath,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        topPanel.add(textLocalPath, gbc);
        JButton saveButtonToFile = new JButton("Save item into file.xml");
        saveButtonToFile.addActionListener((e) -> {
          ItemServiceImpl.getItemInformation(pathNet);
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        topPanel.add(saveButtonToFile, gbc);
        return topPanel;
    }

    private static JPanel createRootPanel() {
        JPanel rootPanel = new JPanel();
        rootPanel.setPreferredSize(new Dimension(800, 400));
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
        return rootPanel;
    }

    private static JFrame createFrame() {
        JFrame frame = new JFrame();
        frame.setTitle("Save item from page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 400));
        frame.setLocation(100, 100);
        return frame;
    }

    private static JTable createTable(){

   /*     String[] columnNames = {
                "ID",
                "Name",
                "price",
                "imageUrl"
        };*/
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("imageUrl");

        List<String[]> listItems = ItemServiceImpl.selectItemInformation();
        for (String[] item : listItems ) {
            tableModel.addRow(item);

        }

       //String[] data = null;
        //data = listItems.toArray(data);


        tableItem = new JTable(tableModel);
        /*tableModel.addTableModelListener(l ->
                System.out.println("something need to do..." + l));*/

        tableItem = new JTable(tableModel);
        tableItem.getDefaultRenderer(tableItem.getColumnClass(1));
        return tableItem;
    }

    private static JPanel createTablePannel(JTable tableItem) {
        JPanel tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(800, 100));
        tableItem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(tableItem, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension size = new Dimension(50, 5);
        //scrollPane.add(createTable());
        scrollPane.setViewportView(createTable());
        scrollPane.setMinimumSize(size);
        GridBagConstraints gbc = new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
                GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 30, 10), 0, 0);
        tablePanel.add(scrollPane, gbc);
        return tablePanel;
    }

    private static JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        JButton saveButtonToDB = new JButton("Save ");
        saveButtonToDB.setPreferredSize(new Dimension(2, 50));
        saveButtonToDB.addActionListener(e -> {
            ItemServiceImpl.addItemInformation();
        });
        JButton updateButtonToDB = new JButton("Update");
        updateButtonToDB.addActionListener(e -> {
            Item item = new Item();
            item.setId((String) tableItem.getValueAt(tableItem.getSelectedRow(),0));
            item.setName((String)  tableItem.getValueAt(tableItem.getSelectedRow(),1));
            item.setPrice(Double.valueOf((String) tableItem.getValueAt(tableItem.getSelectedRow(),2)));
            item.setImageURL((String) tableItem.getValueAt(tableItem.getSelectedRow(),3));
            ItemServiceImpl.updateItemInformation(item);
        });
        JButton deleteButtonToDB = new JButton("Delete");
        deleteButtonToDB.addActionListener(e -> {
            String id = (String) tableItem.getValueAt(tableItem.getSelectedRow(),0);
            ItemServiceImpl.deleteItemInformation(id);
        });
        JButton selectButtonToDB = new JButton("Select");
        selectButtonToDB.addActionListener(e -> {
           List<String[]> listItems = ItemServiceImpl.selectItemInformation();
           DefaultTableModel tableModel = (DefaultTableModel) tableItem.getModel();

            tableModel.setRowCount(0);
            List<String[]> listItemsNew = ItemServiceImpl.selectItemInformation();
            for (String[] item : listItemsNew ) {
                tableModel.addRow(item);
            }

            tableItem = new JTable(tableModel);
            tableItem.revalidate();

        });
        bottomPanel.add(saveButtonToDB);
        bottomPanel.add(updateButtonToDB);
        bottomPanel.add(deleteButtonToDB);
        bottomPanel.add(selectButtonToDB);
        return bottomPanel;

    }
}
