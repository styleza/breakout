/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilri.breakout.gui;

 
import com.ilri.breakout.controller.Breakout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 * Pelin käyttöliittymä (pelaajalle näkyvä osa)
 * @author: Ilari Richardt
*/
public class Kayttoliittyma implements Runnable {
 
    private JFrame frame;
    private Breakout peli;    
    private Piirtoalusta piirtoalusta;
    
    private int palikanKoko;
    
    public Kayttoliittyma(Breakout peli) {
        this.peli = peli;
        this.palikanKoko = 5;
    }
 
    /**
     * Luo ikkuna ja komponentit
     */
    @Override
    public void run() {
        frame = new JFrame("Breakout");
        int leveys = this.peli.getLeveys() * palikanKoko+15;
        int korkeus = this.peli.getKorkeus() * palikanKoko +50;
         
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        luoKomponentit(frame.getContentPane());
 
        frame.pack();
        frame.setVisible(true);
    }
 
    /**
     * Luo komponentit
     * @param container 
     */
    public void luoKomponentit(Container container) {

        Piirtoalusta pa = new Piirtoalusta(this.peli.getKartta(),this.palikanKoko,this.peli.getLeveys(),this.peli.getKorkeus());
        this.piirtoalusta = pa;
        container.add(pa);
        
        Nappaimistonkuuntelija nk = new Nappaimistonkuuntelija(this.peli.getKartta());
        
        this.frame.addKeyListener(nk);
    }
 
 
    public JFrame getFrame() {
        return frame;
    }
    
    public Piirtoalusta getPiirtoalusta(){
        return this.piirtoalusta;
    }
}