/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

import java.util.ArrayList;

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
    
    public Pallo(double suunta, double nopeus, Piste sijainti){
        this.suunta = suunta;
        this.nopeus = nopeus;
        this.sijainti = sijainti;
        yy = yx = 0.0;
    }

    @Override
    public void siirra(int dx, int dy) {
        this.sijainti.siirra(dx, dy);
    }
    
    public void siirra(){
        double dx = Math.sin(suunta) * nopeus + yx;
        double dy = Math.cos(suunta) * nopeus + yy;
        yx += (dx)- (long)(dx+yx);
        yy += (dy)- (long)(dy+yy);

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
        this.suunta = suunta;
    }
    
    public void setNopeus(double nopeus){
        this.nopeus = nopeus;
    }
    public void testaaTormaukset(ArrayList<Palikka> A, Siirrettava B){
        if(this.sijainti.getY() <= 0){
            this.tormaa();
        }
    }
    public void tormaa(){
        this.setSuunta(0);
    }
    
}
