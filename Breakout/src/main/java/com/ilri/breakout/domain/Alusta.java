/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

/**
 *
 * @author Ilari Richardt
 */
public class Alusta implements Siirrettava, Sijaitsee {
    public static int ALUSTAN_LEVEYS = 10;
    private Piste sijainti;
    private int leveys;
    
    public Alusta(Piste sijainti){
        this.sijainti = sijainti;
        this.leveys = Alusta.ALUSTAN_LEVEYS;
    }
    
    public int getLeveys(){
        return this.leveys;
    }
    
    public Piste getSijainti(){
        return this.sijainti;
    }

    @Override
    public void siirra(int dx, int dy) {
        this.sijainti.siirra(dx, dy);
    }
}
