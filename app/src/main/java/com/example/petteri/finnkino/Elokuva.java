package com.example.petteri.finnkino;

public class Elokuva {
    String pvm; // dttmShowStart
    String nimi; // Title
    String alku; // dttmShowStart
    String paikka; // Theatre
    int paikka_ID; // TheatreID

    public String toString() {
        return nimi + " " + alku;
    }

    public Elokuva(String pvm, String nimi, String alku, String paikka, int paikka_ID) {
        this.pvm = pvm;
        this.nimi = nimi;
        this.alku = alku;
        this.paikka = paikka;
        this.paikka_ID = paikka_ID;
    }

    public String getPvm() {
        return pvm;
    }

    public void setPvm(String pvm) {
        this.pvm = pvm;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getAlku() {
        return alku;
    }

    public void setAlku(String alku) {
        this.alku = alku;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public int getPaikka_ID() {
        return paikka_ID;
    }

    public void setPaikka_ID(int paikka_ID) {
        this.paikka_ID = paikka_ID;
    }


}
