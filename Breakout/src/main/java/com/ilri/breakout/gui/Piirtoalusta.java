/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ilri.breakout.gui;
import com.ilri.breakout.domain.Kartta;
import com.ilri.breakout.domain.Piste;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
/**
 *
 * @author Ilari
 */
public class Piirtoalusta extends JPanel {
    Kartta kartta;
    int palikanKoko;
    public Piirtoalusta(Kartta kartta, int palikanKoko){
        this.kartta = kartta;
        this.palikanKoko = palikanKoko;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.black);
        //g.fill3DRect(0, 0, 100, 100,true);
        
        
        for(Piste p : kartta.getAllPisteet()){
            g.fill3DRect(p.getX() * this.palikanKoko,
                    p.getY() * this.palikanKoko,
                    this.palikanKoko,
                    this.palikanKoko,
                    true);
        }
    }
    
}
