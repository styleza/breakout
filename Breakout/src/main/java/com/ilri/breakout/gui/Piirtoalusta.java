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
    Logiikka logiikka;
    int palikanKoko;
    int leveys;
    int korkeus;
    static final Color[] varit = {Color.red, Color.green, Color.blue};
    
    /**
     * Luo uusi piirtoalusta tietyllä logiikalla
     * @param logiikka
     * @param palikanKoko
     * @param leveys
     * @param korkeus 
     */
    public Piirtoalusta(Logiikka logiikka, int palikanKoko, int leveys, int korkeus){
        this.logiikka = logiikka;
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
        g.drawString("SCORE: "+logiikka.getPisteet(), 0, 9);
        g.drawString("HIGHSCORE: "+logiikka.getHighscore(), 130, 9);
        g.drawString("LIVES LEFT: "+logiikka.getLives(), 270, 9);
        
        for(Piste p : logiikka.getAlustanPisteet()){
            g.fill3DRect(p.getX() * this.palikanKoko,
                    p.getY() * this.palikanKoko,
                    this.palikanKoko,
                    this.palikanKoko,
                    true);
        }
        for(Palikka palikka : logiikka.getPalikat()){
            g.setColor(varit[(palikka.getPisteet()-10)/10]);
            g.fill3DRect(palikka.getSijainti().getX() * this.palikanKoko,
                    palikka.getSijainti().getY() * this.palikanKoko,
                    this.palikanKoko,
                    this.palikanKoko,
                    true);
        }
        g.setColor(Color.white);
        Pallo p = logiikka.getPallo();
        
        g.fillOval((int)(p.getSijaintiX() * this.palikanKoko),
                (int)(p.getSijaintiY() * this.palikanKoko), 
                this.palikanKoko, 
                this.palikanKoko);
        
        if(!logiikka.getMessage().isEmpty()){
            g.drawString(logiikka.getMessage(), 15 , korkeus*palikanKoko/2);
        }
    }
    
}
