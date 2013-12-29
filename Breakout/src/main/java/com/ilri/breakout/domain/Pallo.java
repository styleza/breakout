/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ilari Richardt
 */
public class Pallo implements Siirrettava, Sijaitsee{
    private double suunta;              // radiaania pisteesta (1,0)
    private double nopeus;              // sijaintia
    private Piste sijainti;
    private double yx;
    private double yy;
    boolean pakotaSiirto;
    Random r;
    
    public Pallo(double suunta, double nopeus, Piste sijainti){
        this.suunta = suunta;
        this.nopeus = nopeus;
        this.sijainti = sijainti;
        yy = yx = 0.0;
        pakotaSiirto = true;
        r = new Random();
    }

    @Override
    public void siirra(int dx, int dy) {
        this.sijainti.siirra(dx, dy);
    }
    
    public void siirra(){
        double dx = Math.sin(suunta) * (pakotaSiirto ? 2 : nopeus) + yx;
        double dy = Math.cos(suunta) * (pakotaSiirto ? 2 : nopeus) + yy;
        
        yx = (dx)- (long)(dx);
        yy = (dy)- (long)(dy);
        
        pakotaSiirto = false;
        this.siirra((int)dx,(int)dy);
    }
    
    public double getSuunta(){
        return this.suunta;
    }
    
    public double getNopeus(){
        return this.nopeus;
    }
    
    public Piste getSijainti(){
        return this.sijainti;
    }
    
    public void setSuunta(double suunta){
        while(suunta >= 2*Math.PI){
            suunta -= 2*Math.PI;
        }
        this.suunta = suunta;
    }
    
    public void setNopeus(double nopeus){
        this.nopeus = nopeus;
    }
    public boolean testaaTormaukset(ArrayList<Palikka> A, Alusta B,int width, int height){
        //Ylälaitatörmäys
        if(this.sijainti.getY() <= 0){
            this.tormaa();
            pakotaSiirto = true;
        }
        // Alalaitatörmäys
        if(this.sijainti.getY() >= height){
            return false; // Game over
        }
        // Alustan törmäys
        if(this.sijainti.getY() == B.getSijainti().getY() &&
                this.sijainti.getX() >= B.getSijainti().getX() &&
                this.sijainti.getX() <= (B.getSijainti().getX() + B.getLeveys())){
            this.tormaa();
            pakotaSiirto = true;
        }
        // Sivutörmäykset
        if(this.sijainti.getX() >= width || this.sijainti.getX() <= 0){
            this.tormaa();
        }
        return true;
    }
    public void tormaa(){
        this.setSuunta(this.suunta+Math.PI+r.nextDouble()*(r.nextBoolean() ? -0.1 : 0.1));
    }
    
}
