/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

/**
 * Kuvaa yhtä pistettä pelissä
 * @author Ilari Richardt
 */
public class Piste implements Siirrettava{
    private int x;
    private int y;
    
    /**
     * Luo piste x,y
     * @param x
     * @param y 
     */
    public Piste(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Luo piste 0,0
     */
    public Piste(){
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Siirrä pistettä 
     * @param dx
     * @param dy 
     */
    public void siirra(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }

}
