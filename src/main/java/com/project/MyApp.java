package com.project;


import com.project.views.ViewForm;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class MyApp {
    final static String PATH_LOCAL = System.getProperty("user.dir");
    final static String PATH_NET = "https://prom.ua/p895411456-igrovoj-noutbu-omen.html";

    public static void main(String[] args) {

        ViewForm.runUI(PATH_LOCAL, PATH_NET);

    }

}
