/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;

/**
 *
 * @author Ilari Richardt
 */
public class Piste implements Siirrettava{
    private int x;
    private int y;
    
    public Piste(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Piste(){
        this.x = 0;
        this.y = 0;
    }
    
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
