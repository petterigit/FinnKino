package com.example.petteri.finnkino;

import java.util.ArrayList;

public class Teatteri_lista {

    ArrayList<Teatteri> teatteri_lista;

    public ArrayList<Teatteri> getList() {
        return teatteri_lista;
    }

    private Teatteri_lista() {
        this.teatteri_lista = Xml_handler.getInstance().getTeatterilista();
    }

    public static Teatteri_lista getInstance() {
        return instance;
    }
    private static Teatteri_lista instance = new Teatteri_lista();
}
