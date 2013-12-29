/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilri.breakout.controller;
import com.ilri.breakout.domain.Kartta;
import com.ilri.breakout.gui.Piirtoalusta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Ilari
 */
public class Breakout extends Timer implements ActionListener {
    private Piirtoalusta piirtoalusta;
    private Kartta kartta;

    private boolean jatkuu;
    private int leveys, korkeus;
    
    public Breakout(){
        super(1000,null);
        
        this.leveys = 100;
        this.korkeus = 100;
        this.kartta = new Kartta(this.leveys,this.korkeus);
        
        
        addActionListener(this);
        setInitialDelay(20);
        
        jatkuu = true;
        
    }
    public void setJatkuu(boolean jatkuu){
        this.jatkuu = jatkuu;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(!jatkuu){
            return;
        }
        if(!this.kartta.toimi()){
           // jatkuu = false;
        }
        piirtoalusta.repaint();
        super.setDelay(50);
        
    }

    public int getLeveys() {
        return leveys;
    }

    public int getKorkeus() {
        return korkeus;
    }
    
    public Kartta getKartta(){
        return this.kartta;
    }
    
    public void setPiirtoalusta(Piirtoalusta piirtoalusta){
        this.piirtoalusta = piirtoalusta;
    }

}
