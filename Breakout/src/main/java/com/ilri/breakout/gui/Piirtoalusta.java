/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilri.breakout.gui;
import com.ilri.breakout.domain.Logiikka;
import com.ilri.breakout.domain.Palikka;
import com.ilri.breakout.domain.Pallo;
import com.ilri.breakout.domain.Piste;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
/**
 * Piirtoalusta piirtää pelin kaikki komponentit
 * @author Ilari
 */
public class Piirtoalusta extends JPanel {
    Logiikka kartta;
    int palikanKoko;
    int leveys;
    int korkeus;
    static final Color[] varit = {Color.red, Color.green, Color.blue};
    
    public Piirtoalusta(Logiikka kartta, int palikanKoko, int leveys, int korkeus){
        this.kartta = kartta;
        this.palikanKoko = palikanKoko;
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    /**
     * Piirrä kaikki pelin komponentit
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fill3DRect(0, 0, leveys * palikanKoko, korkeus * palikanKoko+20,true);
        g.setColor(Color.white);
        g.drawString("SCORE: "+kartta.getPisteet(), 0, 9);
        
        for(Piste p : kartta.getAlustanPisteet()){
            g.fill3DRect(p.getX() * this.palikanKoko,
                    p.getY() * this.palikanKoko,
                    this.palikanKoko,
                    this.palikanKoko,
                    true);
        }
        for(Palikka palikka : kartta.getPalikat()){
            g.setColor(varit[(palikka.getPisteet()-10)/10]);
            g.fill3DRect(palikka.getSijainti().getX() * this.palikanKoko,
                    palikka.getSijainti().getY() * this.palikanKoko,
                    this.palikanKoko,
                    this.palikanKoko,
                    true);
        }
        g.setColor(Color.white);
        Pallo p = kartta.getPallo();
        
        g.fillOval((int)(p.getSijaintiX() * this.palikanKoko),
                (int)(p.getSijaintiY() * this.palikanKoko), 
                this.palikanKoko, 
                this.palikanKoko);
        
        if(!kartta.getMessage().isEmpty()){
            g.drawString(kartta.getMessage(), 1 , korkeus*palikanKoko/2);
        }
    }
    
}
