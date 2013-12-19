/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

/**
 *
 * @author Ilari Richardt
 */
public class Palikka implements Sijaitsee {
    
    private Piste sijainti;
    private int pisteet;
    
    public Palikka(Piste sijainti, int pisteet){
        this.sijainti = sijainti;
        this.pisteet = pisteet;
        
    }
    
    public Piste getSijainti(){
        return this.sijainti;
    }
}
