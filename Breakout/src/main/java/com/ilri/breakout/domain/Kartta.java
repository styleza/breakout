/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.domain;
import java.util.ArrayList;
import java.util.Random;
/**
 * Pelilogiikan keskus, tietää Alustan, Palikat, Pallon
 * @author Ilari Richardt
 */
public class Kartta {
    private ArrayList<Palikka> palikat;
    private Alusta alusta;
    private Pallo pallo;
    private Random ran;
    
    private int leveys;
    private int korkeus;
    
    /**
     * Luo kartta leveydellä ja korkeudella
     * @param leveys
     * @param korkeus 
     */
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
        this.pallo = new Pallo(Math.PI, 1, pallonSijainti);
        this.palikat = new ArrayList<Palikka>();
        this.leveys = leveys;
        this.korkeus = korkeus;
    }
    
    /**
     * Hae kaikkien komponenttien pisteet (Alusta, Palikat, Pallo)
     * @return 
     */
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
    
    /**
     * Palauttaa alustan
     * @return 
     */
    public Alusta getAlusta(){
        return this.alusta;
    }
    
    /**
     * Pelaa peliä yksi "kellopulssi" eteenpäin, siirtää palloa ja testaa törmäykset
     * @return 
     */
    public boolean toimi(){
        boolean jatkuu = this.pallo.testaaTormaukset(this.palikat,
                this.alusta,
                this.leveys,
                this.korkeus);
        if(!jatkuu){
            return false;
        }
        this.pallo.siirra();
        return true;
    }
}
