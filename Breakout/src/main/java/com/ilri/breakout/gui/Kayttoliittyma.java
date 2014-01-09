package com.ilri.breakout.gui;

 
import com.ilri.breakout.controller.Breakout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Pelin käyttöliittymä (pelaajalle näkyvä osa)
 * @author: Ilari
*/
public class Kayttoliittyma implements Runnable {
 
    private JFrame frame;
    private Breakout peli;    
    private Piirtoalusta piirtoalusta;
    
    private int palikanKoko;
    
    public Kayttoliittyma(Breakout peli) {
        this.peli = peli;
        this.palikanKoko = 12;
    }
 
    /**
     * Luo ikkuna ja komponentit
     */
    @Override
    public void run() {
        frame = new JFrame("Breakout");
        int leveys = this.peli.getLeveys() * palikanKoko+5;
        int korkeus = this.peli.getKorkeus() * palikanKoko +45;
         
        frame.setPreferredSize(new Dimension(leveys, korkeus));
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.setResizable(false);
 
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