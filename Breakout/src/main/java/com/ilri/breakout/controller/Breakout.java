/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author Ilari
 */
public class Breakout extends Timer implements ActionListener {
    private Piirtoalusta piirtoalusta;
    private Logiikka kartta;

    private boolean jatkuu;
    private int leveys, korkeus;
    
    /**
     * Luo breakout peli
     */
    public Breakout(){
        super(1000,null);
        
        this.leveys = 30;
        this.korkeus = 40;
        this.kartta = new Logiikka(this.leveys,this.korkeus);
        
        
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
        if(!this.kartta.toimi()){
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
        return this.kartta;
    }
    
    public void setPiirtoalusta(Piirtoalusta piirtoalusta){
        this.piirtoalusta = piirtoalusta;
    }

}
