package com.example.petteri.finnkino;

import java.util.ArrayList;

public class Elokuva_lista {
    ArrayList<Elokuva> elokuva_lista;

    public ArrayList<Elokuva> getList() {
        return elokuva_lista;
    }

    public Elokuva_lista() {
        //this.elokuva_lista = Xml_handler.getInstance().getElokuvaList();
    }

    //public static Elokuva_lista getInstance() {
    //    return instance;
    //}

    private static Elokuva_lista instance = new Elokuva_lista();
}
