package com.project.service;

import com.project.models.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class ItemHTMLService {

    public static Item getHTMLItemInformation(String netPath) {
        Item itemObject = new Item();
        Document doc = getDocument(netPath);
        Elements itemElements = doc.getElementsByClass("x-product-info");
        for (Element itemElement : itemElements) {
            Elements itemContent = itemElement.getElementsByClass("x-product-info__content");
            for (Element element : itemContent) {
                itemObject.setName(getName(element));

                itemObject.setPrice(getPrice(doc));
            }
            Elements itemImages = itemElement.getElementsByClass("x-product-info__images");
            for (Element element : itemImages) {
                itemObject.setImageURL(getImageURL(doc));
            }
        }
        return itemObject;
    }

    private static double getPrice(Document doc) {
        double price =0;
        Elements priceElement = doc.getElementsByClass("x-product-price__value");
        for (Element element : priceElement) {
            price = Double.valueOf(element.attr("data-qaprice"));
        }
        return price;
    }


    private static  String getName(Element element) {
        String name;
        name = element.getElementsByTag("h1").text();
        return name;
    }

    public static Document getDocument(String netPath) {
        Document doc = null;
        try {
            doc = Jsoup.connect(netPath).get();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return doc;
    }


    private static String getImageURL(Document doc) {
        String imageURL = null;
        //meta"og:image"
        Elements element = doc.select("meta[property=og:image]");
        if (element != null) {
            imageURL = element.attr("content");
        }
        return imageURL;
    }
}
