package com.project.service;

import com.project.models.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Logger;

public class WriteXMLFileService {
    public static Logger logger = Logger.getLogger("");
    public static void fillingXMLFile(File file, Item itemObject) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //root elements
            Document doc = docBuilder.newDocument();
            Element items = doc.createElement("items");
            doc.appendChild(items);
            Element item = doc.createElement("item");
            items.appendChild(item);
            Element itemName = doc.createElement("itemName");
            itemName.appendChild(doc.createTextNode(itemObject.getName()));
            item.appendChild(itemName);
            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(itemObject.getPriceStr()));
            item.appendChild(price);
            Element imageURL = doc.createElement("imageURL");
            imageURL.appendChild(doc.createTextNode(itemObject.getImageURL()));
            item.appendChild(imageURL);

            //write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            logger.info("File xml is done.");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();

        }





    }
}
