package com.example.petteri.finnkino;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Xml_handler {

    private Xml_handler() {

    }
    private static Xml_handler instance = new Xml_handler();

    public static Xml_handler getInstance() {
        return instance;
    }

    public ArrayList<Teatteri> getTeatterilista () {
        ArrayList<Teatteri> teatteri_lista = new ArrayList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse("https://www.finnkino.fi/xml/TheatreAreas/");
            doc.getDocumentElement().normalize();
            NodeList nlist = doc.getDocumentElement().getElementsByTagName("TheatreArea");
            for (int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String t_nimi = element.getElementsByTagName("Name").item(0).getTextContent();
                    int t_ID = Integer.parseInt(element.getElementsByTagName("ID").item(0).getTextContent());
                    Teatteri t = new Teatteri(t_ID, t_nimi);
                    teatteri_lista.add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE");
        }
        return (teatteri_lista);
    }


    public ArrayList<Elokuva> getElokuvaList (String t_ID, String pvm) {
        ArrayList<Elokuva> elokuva_lista = new ArrayList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse("https://www.finnkino.fi/xml/Schedule/?area="+t_ID+"&dt="+pvm);
            doc.getDocumentElement().normalize();
            NodeList nlist = doc.getDocumentElement().getElementsByTagName("Show");
            for (int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String e_dttm = element.getElementsByTagName("dttmShowStart").item(0).getTextContent(); // dttmShowStart
                    String[] e_parsed_dttm = e_dttm.split("T");

                    String e_pvm = e_parsed_dttm[0];

                    String e_nimi = element.getElementsByTagName("Title").item(0).getTextContent(); // Title

                    String e_alku = e_parsed_dttm[1];

                    String e_paikka = element.getElementsByTagName("Theatre").item(0).getTextContent(); // Theatre

                    int e_paikka_ID = Integer.parseInt(element.getElementsByTagName("TheatreAuditriumID").item(0).getTextContent()); // TheatreAuditriumID

                    Elokuva e = new Elokuva(e_pvm, e_nimi, e_alku, e_paikka, e_paikka_ID);
                    elokuva_lista.add(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("DONE");
        }
        return (elokuva_lista);
    }
}