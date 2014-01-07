/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

/**
 * Tuhottava palikka pelissä
 * @author Ilari Richardt
 */
public class Palikka implements Sijaitseva {
    
    private Piste sijainti;
    private int pisteet;
    
    /**
     * Luo palikka sijainnilla ja siitä saatavilla pisteillä kun se tuhotaan
     * @param sijainti
     * @param pisteet 
     */
    public Palikka(Piste sijainti, int pisteet){
        this.sijainti = sijainti;
        this.pisteet = pisteet;
        
    }
    
    public Piste getSijainti(){
        return this.sijainti;
    }
    
    public int getPisteet(){
        return this.pisteet;
    }
}
