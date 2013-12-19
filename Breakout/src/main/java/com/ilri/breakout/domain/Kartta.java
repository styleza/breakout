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
        this.pallo = new Pallo(Math.PI, 0.0001, pallonSijainti);
        this.palikat = new ArrayList<Palikka>();
    }
    
    public ArrayList<Piste> getAllPisteet(){
        ArrayList<Piste> rv = new ArrayList<Piste>();
        
        // Lisätään alustan pisteet
        Piste p = this.alusta.getSijainti();
        for(int i = 0; i < this.alusta.getLeveys(); i++){
            Piste p2 = new Piste(p.getX()+i,p.getY());
            rv.add(p2);
        }
        
        // Lisätään pallon piste
        rv.add(this.pallo.getSijainti());
        
        // Lisätään palikoiden pisteet
        for(Palikka palikka: this.palikat){
            rv.add(palikka.getSijainti());
        }
        
        return rv;
    }
    
    public Alusta getAlusta(){
        return this.alusta;
    }
    
    public void toimi(){
        this.pallo.siirra();
    }
}
