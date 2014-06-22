package fap_java;

import gui.Constants;
import gui.FAPComboBoxRenderer;
import gui.GameSave;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.awt.image.RescaleOp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.CellRendererPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.metal.MetalComboBoxButton;

/**
 * This class contains several tools that can be used wherever.
 * They are all set to public static to be available everywhere, and are as generic as possible so it would be easier
 * to use them in several conditions
 */
public class Tools {
    /**
     * Picks a random int between two value
     * @param min : min boundary
     * @param max : max boundary
     * @return : a random integer between min and max
     */
    public static int randRange(int min, int max) {
        int randomNum = (int)(Math.random() * (max - min + 1) + min);
        return randomNum;
    }

    /**
     * Picks a random int between two values, excluding the given int
     * Warning : can lead to an infinite loop if too much values are excluded
     * @param min : min boundary
     * @param max : max boundary
     * @exclude The ints that shall be excluded of the random picking
     * @return : a random integer between min and max
     */
    public static int randRange(int min, int max, int[] exclude){
        int rand;
        boolean b = true;
        do{
            b=true;
            // Pick a random number
            rand = randRange(min,max);
            // Check if it's supposed to be excluded
            for(int i=0;i<exclude.length;i++){
                int a = exclude[i];
                if(a==rand){
                    // If yes, start again
                    b = false;
                }
            }
        }
        while(!b);
        return rand;
    }
    
    /**
     * Removes the null values from an ArrayList, and their slots
     * @param al The designated ArrayList to purge
     * @return The purged arrayList
     */
    public static ArrayList removeNull(ArrayList al){
        // Loop on
        while(al.contains(null)){
            // Read the AL
            for(int i=0;i<al.size();i++){
                // Get the object
                Object o = al.get(i);
                if(o==null){
                    // If it's null, remove it
                    al.remove(o);
                }
            }
        }
        return al;
    }
    
    public static boolean intTableContains(int[] table, int value){
        boolean b = false;
        for(int a=0;a<table.length;a++){
            if(table[a] == value){
                b = true;
                break;
            }
        }
        return b;
    }
    
    // Graphical tools
    
    public static BufferedImage getImageToFilter(Image image){
        BufferedImage img = (BufferedImage)image;
        
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        
        Graphics gBi = bi.getGraphics();
        gBi.drawImage(img, 0, 0, null);
        
        return bi;
    }
    
    public static void drawFilteredImage(BufferedImage img, float[] scales, float[] offsets, Graphics g, int x, int y){
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, rop, x, y);
    }
    
    public static void drawFilteredImage(BufferedImage img, float Rfactor, float Gfactor, float Bfactor, float alpha, Graphics g, int x, int y){
        float[] offsets = new float[4];
        float[] scales = {Rfactor,Gfactor,Bfactor,alpha};
        drawFilteredImage(img, scales, offsets, g,x,y);
    }
    
    public static void drawFilteredImage(BufferedImage img, float[] scales, float[] offsets, Graphics g, int x, int y, double scaleX, double scaleY){
        RescaleOp rop = new RescaleOp(scales, offsets, null);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scaleX, scaleY);
        g2d.drawImage(img, rop, (int)(x/scaleX), (int)(y/scaleY));
        g2d.scale(1/scaleX, 1/scaleY);
    }
    
    // ---------- Save and Load game
    
    public static void saveGame(GameSave gs){
        try {
            FileOutputStream fileOut = new FileOutputStream(Constants.c.get(Constants.savegameFile));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gs);
            out.close();
            fileOut.close();
            System.out.println("Saved Game in "+Constants.c.get(Constants.savegameFile));
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    
    public static GameSave loadGame(){
        GameSave gs = null;
        try {
            FileInputStream fileIn = new FileInputStream(Constants.c.get(Constants.savegameFile));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            gs = (GameSave)in.readObject();
            in.close();
            fileIn.close();
        } 
        catch(FileNotFoundException e){
            System.out.println("Couldn't load file, creating new one");
            gs = new GameSave();
            Tools.saveGame(gs);
            return gs;
        }
        catch (IOException i) {
            i.printStackTrace();
            return new GameSave();
        } catch (ClassNotFoundException c) {
            System.out.println("Impossibru, class not found");
            c.printStackTrace();
            return null;
        }
        return gs;
    }
    
    public static NPC checkNPCOnCell(Game game, Cell c) {
        NPC npc = null;
        if (game.getAdv() > 0) {
            ArrayList<NPC> listNPCs = game.getListNPCs();
            for (int i = 0; i < listNPCs.size(); i++) {
                NPC np = listNPCs.get(i);
                if (np.getPosition()!=null && np.getPosition().equals(c)) {
                    npc = np;
                    break;
                }
            }
        }
        return npc;
    }
    
    public static NPC checkAutoTriggerNPC(Game game){
        // Checking for auto-trigger NPC
        NPC npc = null;
        ArrayList<NPC> listNPCs = game.getListNPCs();
        for (int i = 0; i < listNPCs.size(); i++) {
            NPC np = listNPCs.get(i);
            if (np.isAutoTrigger()) {
                npc = np;
                break;
            }
        }
        return npc;
    }


    public static void drawMultilineString(Graphics g, String s,int x, int y, int incr){
        drawMultilineString(g, s.split("\n"),x,y,incr);
    }
    
    /**
     * Deprecated : Use JLabel
     */
    public static void drawMultilineString(Graphics g, String[] ss,int x, int y, int incr){
        for(int i=0;i<ss.length;i++){
            //String s = ss[i].substring(0, ss[i].length()-3);
            g.drawString(ss[i], x, y+i*incr);
        }
    }
    
    public static void parametrizeJLabel(JLabel theJLabel, String text, Font font, Color c, int sizeX, int sizeY, int x, int y, int valign){
        theJLabel.setText("<html>"+text+"</html>");
        theJLabel.setFont(font);
        theJLabel.setForeground(c);
        theJLabel.setSize(sizeX,sizeY);
        theJLabel.setLocation(x, y);
        theJLabel.setVerticalAlignment(valign);
    }
    public static void parametrizeJLabel(JLabel theJLabel, String text){
        theJLabel.setText("<html>"+text+"</html>");
    }
    
    public static void tinkerJComboBox(JComboBox jc){
        jc.setRenderer(new FAPComboBoxRenderer());
        jc.setBorder(Graph.NO_BORDER);
        for(int i=0;i<2;i++){
            Component k = jc.getComponent(i);
            if(k!=null){
                if(k instanceof MetalComboBoxButton){
                    MetalComboBoxButton jb = ((MetalComboBoxButton)k);
                    jb.setBackground(Graph.BLACK);
                    jb.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    Image img=Graph.getGuimg().get("iconJCombo");
                    jb.setIconOnly(true);
                    Icon icon = new ImageIcon(img);
                    jb.setComboIcon(icon);
                }
                /*else{
                    CellRendererPane crp = (CellRendererPane)k;
                    crp.setBackground(Graph.BLACK);
                }*/
            }
        }
    }
    
    //-------------
    
    public static void parseOptions(){
        try {
            Scanner scan = new Scanner(new File(Constants.configFileName));
            try {
                while (scan.hasNextLine()) {
                    String s = scan.nextLine();
                    if(!(s.equals("")||s.charAt(0)=='#')){
                        String[] ss = s.split("=");
                        //System.out.println(ss[0]+"--"+ss[1]);
                        Constants.c.put(ss[0], ss[1]);
                    }
                }
            } finally {
                scan.close();
            }
        } catch (IOException e) {
            System.err.println("Erreur dans la methode lectureFichier de la classe d'Outils :");
            e.printStackTrace();
        }
    }
}
