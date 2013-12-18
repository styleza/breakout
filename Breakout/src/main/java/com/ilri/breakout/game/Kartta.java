/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.game;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Ilari Richardt
 */
public class Kartta {
    private ArrayList<Palikka> palikat;
    private Alusta alusta;
    private Pallo pallo;
    private Random ran;
    
    private int leveys;
    private int korkeus;
    
    public Kartta(int leveys, int korkeus){
        this.ran = new Random();
        // @TODO: luo palikat
        
        Piste alustanSijantin = new Piste(
                leveys / 2 - Alusta.ALUSTAN_LEVEYS / 2,     // X - koordinaatti
                korkeus - 1);                               // Y - koordinaatti
        
        this.alusta = new Alusta(alustanSijantin);
        
        // @TODO: aseta pallo oikeaan paikkaan ja arvo suunta
        Piste pallonSijainti = new Piste(
                leveys / 2,                                 // X - koordinaatti
                korkeus - 2);                               // Y - koordinaatti
        this.pallo = new Pallo(0, 0.1, pallonSijainti);
    }
}
