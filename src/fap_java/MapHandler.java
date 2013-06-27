package fap_java;

import java.util.ArrayList;

import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MapHandler extends DefaultHandler{
    private CMap map = new CMap();
    private Cell c;
    private int i=0;
    private int j=0;
    
    //flags nous indiquant la position du parseur
    private boolean inFile, inI, inJ;
    //buffer nous permettant de récupérer les données 
    private StringBuffer buffer;
    
    public MapHandler() {
        super();
    }
    
    
    //détection d'ouverture de balise
            public void startElement(String uri, String localName,
                            String qName, Attributes attributes) throws SAXException{
                    if(qName.equals("root")){
                            inFile = true;
                    }else if(qName.equals("ligne")){
                            inI = true;
                    }else if(qName.equals("map")){}
                                            else if(qName.equals("persos")){}
                                        else if(qName.equals("st2")){}
                                        else if(qName.equals("i")){}
                                        else if(qName.equals("j")){}
                                        else if(qName.equals("st3")){}
                else if(qName.equals("st4")){}
                else if(qName.equals("st5")){}
                    else {
                            buffer = new StringBuffer();
                            if(qName.equals("case")){
                                    inJ = true;
                            }else{
                                    //erreur, on peut lever une exception
                                    throw new SAXException("Balise "+qName+" inconnue.");
                            }
                    }
            }
            //détection fin de balise
            public void endElement(String uri, String localName, String qName)
                            throws SAXException{
                    if(qName.equals("root")){
                            inFile = false;
                    }else if(qName.equals("ligne")){
                        i++;
                        j=0;
                        inI = false;
                    }else if(qName.equals("case")){
                        String content = buffer.toString();
                        String preDid = "";
                        String param="";
                        int t=0;
                        int did = 0;
                        
                        String[] tabS = content.split(",", 2);
                        did = Integer.parseInt(tabS[0]);
                        if(tabS.length > 1){
                            param = tabS[1];
                        }
                        
                            if(did >= 100 && did<200){
                                t=20;
                            }
                            else if(did >=200){
                                t=19;
                            }
                            else if(param.equals("")){
                                t=1;
                            }
                            else if(param.indexOf(',') == -1){
                                t=2;
                            }
                            else if(did == 10){
                                t=10;
                            }
                            else {
                                
                            }
                        //int t = Integer.parseInt(buffer.toString());
                            inJ = false;
                            if(did!=0){
                                c = new Cell(i,j,t,param,did);   
                                map.addElement(c);
                             }
                            j++;
                    }else if(qName.equals("map")){}
                    else if(qName.equals("persos")){}
                else if(qName.equals("st2")){}
                else if(qName.equals("i")){}
                else if(qName.equals("j")){}
                else if(qName.equals("st3")){}
                else if(qName.equals("st4")){}
                else if(qName.equals("st5")){}
                    else{
                            //erreur, on peut lever une exception
                            throw new SAXException("Balise "+qName+" inconnue.");
                    }          
            }
            //détection de caractères
            public void characters(char[] ch,int start, int length)
                            throws SAXException{
                    String lecture = new String(ch,start,length);
                    if(buffer != null) buffer.append(lecture);       
            }
            //début du parsing
            public void startDocument() throws SAXException {
                    //System.out.println("Début du parsing");
            }
            //fin du parsing
            public void endDocument() throws SAXException {
                    //System.out.println("Fin du parsing");
                    //System.out.println("Resultats du parsing");
            }

    public void setMap(CMap map) {
        this.map = map;
    }

    public CMap getMap() {
        return map;
    }
}