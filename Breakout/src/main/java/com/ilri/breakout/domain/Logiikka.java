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
public class Logiikka {
    private ArrayList<Palikka> palikat;
    private Alusta alusta;
    private Pallo pallo;
    private Random ran;
    
    private int leveys;
    private int korkeus;
    
    private int pisteet;
    
    private boolean jatkuu;
    
    private String message;
    
    /**
     * Luo kartta leveydellä ja korkeudella
     * @param leveys
     * @param korkeus 
     */
    public Logiikka(int leveys, int korkeus){
        this.ran = new Random();
        this.palikat = new ArrayList<Palikka>();
        // @TODO: luo palikat
        
        for(int y = 2; y < 5 ; y++){
            for(int x = 0; x < leveys; x++){
                Piste palikanSijainti = new Piste(x,y);
                Palikka p = new Palikka(palikanSijainti,10);
                palikat.add(p);
            }
        }
        
        
        Piste alustanSijantin = new Piste(
                leveys / 2 - Alusta.ALUSTAN_LEVEYS / 2,     // X - koordinaatti
                korkeus - 1);                               // Y - koordinaatti
        
        this.alusta = new Alusta(alustanSijantin);
        
        // @TODO: aseta pallo oikeaan paikkaan ja arvo suunta
        Piste pallonSijainti = new Piste(
                leveys / 2,                                 // X - koordinaatti
                korkeus - 2);                               // Y - koordinaatti
        this.pallo = new Pallo(0,-0.3, 0.1, pallonSijainti);
        
        this.leveys = leveys;
        this.korkeus = korkeus;
        
        this.pisteet = 0;
        
        jatkuu = true;
        aloitaLopeta();
    }
    
    /**
     * Hae kaikkien komponenttien pisteet (Alusta, Palikat, Pallo)
     * @return 
     */
    public ArrayList<Piste> getPalikatJaAlusta(){
        ArrayList<Piste> rv = new ArrayList<Piste>();
        
        lisaaAlustanPisteet(rv);
        lisaaPalikoidenPisteet(rv);
        
        return rv;
    }
    public Pallo getPallo(){
        return this.pallo;
    }
    
    /**
     * Lisää palikoiden pisteet (sijainnit) parametrinä annettuun arraylistiin
     * @param pisteet 
     */
    private void lisaaPalikoidenPisteet(ArrayList<Piste> pisteet){
        for(Palikka palikka : this.palikat){
            pisteet.add(palikka.getSijainti());
        }
    }
    
    /**
     * Lisää Alustan pisteet (palikoiden sijainnit) parametrinä annetuun arraylistiin
     * @param pisteet 
     */
    private void lisaaAlustanPisteet(ArrayList<Piste> pisteet){
        Piste p = this.alusta.getSijainti();
        for(int i = 0; i < this.alusta.getLeveys(); i++){
            Piste p2 = new Piste(p.getX()+i,p.getY());
            pisteet.add(p2);
        }
    }
    
    public Alusta getAlusta(){
        return this.alusta;
    }
    
    /**
     * Pelaa peliä yksi "kellopulssi" eteenpäin, siirtää palloa ja testaa törmäykset
     * @return 
     */
    public boolean toimi(){
        if(!jatkuu){
            return false;
        }
        
        jatkuu = testaaTormaykset();
        if(!jatkuu){
            return false;
        }
        this.pallo.siirra();
        return true;
    }
    
    /**
     * Testaa kaikki törmäykset joita pallolla voi olla,
     * palauttaa true jos peli voi jatkua ja false jos peli täytyy lopettaa
     * @return 
     */
     public boolean testaaTormaykset(){
         int pX = pallo.getSijainti().getX();
         int pY = pallo.getSijainti().getY();
        //Ylälaitatörmäys
        if(pY <= 0){
            pallo.tormaa(Laita.YLA);
        }
        // Alalaitatörmäys
        if(pY >= korkeus){
            this.message = "GAME OVER SCORE: "+this.pisteet;
            return false; // Game over
        }
        // Alustan törmäys
        if(pY + (pallo.getSuuntaY() < 0.0D ? -1 : 1) == alusta.getSijainti().getY() &&
                pX >= alusta.getSijainti().getX() &&
                pX <= (alusta.getSijainti().getX() + alusta.getLeveys())){
            
            for(int i = alusta.getSijainti().getX(); 
                    i < (alusta.getSijainti().getX() + alusta.getLeveys());
                    i++){
                if(i == pX){
                    pallo.tormaa(Laita.ALA,
                            ((double)((i-alusta.getSijainti().getX())-alusta.getLeveys())/
                                    (double)alusta.getLeveys()) * 0.3D
                    );
                    pallo.nopeuta();
                }
            }
            
        }
        // Sivutörmäykset
        if(pX >= leveys || pX <= 0){
            pallo.tormaa(Laita.VASEN);
        }
        
        // Palikkatörmäykset
        for(Palikka p : palikat){
            boolean tormaa = false;
            if(p.getSijainti().getX() == pX+1 && p.getSijainti().getY() == pY){
                pallo.tormaa(Laita.OIKEA);
                tormaa = true;
            }
            if(p.getSijainti().getX() == pX-1 && p.getSijainti().getY() == pY){
                pallo.tormaa(Laita.VASEN);
                tormaa = true;
            }
            if(p.getSijainti().getX() == pX && p.getSijainti().getY() == pY+1){
                pallo.tormaa(Laita.YLA);
                tormaa = true;
            }
            if(p.getSijainti().getX() == pX && p.getSijainti().getY() == pY-1){
                pallo.tormaa(Laita.ALA);
                tormaa = true;
            }
            
            if(tormaa){
                pisteet += p.getPisteet();
                palikat.remove(p);
                break;
            }
        }
        
        return true;
    }
     
     public int getPisteet(){
         return this.pisteet;
     }
     
     /**
      * Siirtää alustaa annetun parametrin verran sivulle
      * @param dx
      * @param dy 
      */
     public void siirraAlustaa(int dx,int dy){
         if(this.jatkuu &&
                 alusta.getSijainti().getX()+dx+alusta.getLeveys() <= this.leveys &&
                 alusta.getSijainti().getX()+dx > 0 &&
                 dy == 0){
             
            this.alusta.siirra(dx, dy);
         }
     }
     
     /**
      * Aloittaa tai lopettaa pelin (start/pause)
      * Lisää myös viestin peliin tarvittaessa
      */
     public void aloitaLopeta(){
         jatkuu = !jatkuu;
         if(!jatkuu){
             message = "PRESS SPACE TO START";
         }
         if(jatkuu){
             message = "";
         }
     }
     
     public String getMessage(){
         return this.message;
     }
     
     public boolean getJatkuu(){
         return this.jatkuu;
     }
}
