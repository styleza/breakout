/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.game;

/**
 *
 * @author Ilari Richardt
 */
public class Pallo implements Siirrettava{
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
        yx = dx % 1.0;
        yy = dx % 1.0;
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
    
}
