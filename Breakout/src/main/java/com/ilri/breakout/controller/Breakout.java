package com.ilri.breakout.controller;
import com.ilri.breakout.domain.Logiikka;
import com.ilri.breakout.gui.Piirtoalusta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Random;
import javax.swing.Timer;

/**
 * Pelin ohjain
 * Vastaa pelilogiikan ja käyttöliittymän välisestä kommunikoinnista ja alustuksesta
 * 
 * @author Ilari
 */
public class Breakout extends Timer implements ActionListener {
    private Piirtoalusta piirtoalusta;
    private Logiikka logiikka;

    private boolean jatkuu;
    private int leveys, korkeus;
    
    /**
     * Luo breakout pelin
     */
    public Breakout(){
        super(1000,null);
        
        this.leveys = 30;
        this.korkeus = 40;
        this.logiikka = new Logiikka(this.leveys,this.korkeus);
        
        
        addActionListener(this);
        setInitialDelay(20);
        
        jatkuu = true;
        
    }
    public void setJatkuu(boolean jatkuu){
        this.jatkuu = jatkuu;
    }
    
    /**
     * Pelin yksi "kellopulssi" pistää kartan toimimaan kerran ja jää odottamaan seuraavaa
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!jatkuu){
            return;
        }
        if(!this.logiikka.toimi()){
           // jatkuu = false;
        }
        piirtoalusta.repaint();
        super.setDelay(20);
        
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    public Logiikka getKartta(){
        return this.logiikka;
    }
    
    public void setPiirtoalusta(Piirtoalusta piirtoalusta){
        this.piirtoalusta = piirtoalusta;
    }

}
