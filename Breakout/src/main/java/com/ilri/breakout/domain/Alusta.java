package com.ilri.breakout.domain;

/**
 * Siirrettävä alusta jota pelaaja siirtä 
 * @author Ilari Richardt
 */
public class Alusta implements Siirrettava, Sijaitseva {
    public static int ALUSTAN_LEVEYS = 10;
    private Piste sijainti;
    private int leveys;
    
    /**
     * Luo alusta sijainnilla
     * @param sijainti 
     */
    public Alusta(Piste sijainti){
        this.sijainti = sijainti;
        this.leveys = Alusta.ALUSTAN_LEVEYS;
    }
    
    /**
     * Palauttaa alustan leveyden
     * @return int
     */
    public int getLeveys(){
        return this.leveys;
    }
    
    /**
     * Palauttaa alustan sijainnin (vasen yläkulma)
     * @return 
     */
    public Piste getSijainti(){
        return this.sijainti;
    }

    /**
     * Siirtää Alustaa
     * @param dx
     * @param dy 
     */
    @Override
    public void siirra(int dx, int dy) {
        this.sijainti.siirra(dx, dy);
    }
}
