package com.project.service;

import com.project.models.Item;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class ReadXMLFileService {
    public static Item readItemToObject(File filename) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        Item item = new Item();
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new InputSource(filename.getAbsolutePath()));
            item.setId("1");
            String itemName = doc.getDocumentElement().getElementsByTagName("itemName").item(0).getFirstChild().getTextContent();
            item.setName(itemName);
            String priceStr = doc.getDocumentElement().getElementsByTagName("price").item(0).getFirstChild().getTextContent();
            double price = Double.valueOf(priceStr);
            item.setPrice(price);
            String imageURL = doc.getDocumentElement().getElementsByTagName("imageURL").item(0).getFirstChild().getTextContent();
            item.setImageURL(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;

    }
}
