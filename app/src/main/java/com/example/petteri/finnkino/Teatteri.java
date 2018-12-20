package com.example.petteri.finnkino;

public class Teatteri {
    private int teatteri_id;
    private String nimi;

    public String toString() {
        return nimi;
    }

    public Teatteri(int teatteri_id, String nimi) {
        this.teatteri_id = teatteri_id;
        this.nimi = nimi;
    }
    public int getTeatteri_id() {
        return teatteri_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setTeatteri_id(int teatteri_id) {
        this.teatteri_id = teatteri_id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }







}
