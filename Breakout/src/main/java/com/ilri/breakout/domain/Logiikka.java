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
    private int highscore;
    
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

        this.leveys = leveys;
        this.korkeus = korkeus;
        
        reset();
        
        highscore = 0;
        
    }
    
    /**
     * Hae kaikkien komponenttien pisteet (Alusta, Palikat, Pallo)
     * @return 
     */
    public ArrayList<Palikka> getPalikat(){
        return this.palikat;
    }
    
    public Pallo getPallo(){
        return this.pallo;
    }

    /**
     * Palauttaa Alustan pisteet (palikoiden sijainnit)
     * @return ArrayList
     */
    public ArrayList<Piste> getAlustanPisteet(){
        ArrayList<Piste> rv = new ArrayList<Piste>();
        Piste p = this.alusta.getSijainti();
        for(int i = 0; i < this.alusta.getLeveys(); i++){
            Piste p2 = new Piste(p.getX()+i,p.getY());
            rv.add(p2);
        }
        return rv;
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
         boolean siirtyyYlos = pallo.getSuuntaY() < 0.0D;
         boolean siirtyyVasemalle = pallo.getSuuntaX() < 0.0D;
        //Ylälaitatörmäys
        if(pY <= 0 && siirtyyYlos){
            pallo.tormaa(Laita.YLA);
        }
        // Alalaitatörmäys
        if(pY >= korkeus){
            this.message = "GAME OVER SCORE: "+this.pisteet+" PRESS X TO RESTART";
            return false; // Game over
        }
        // Alustan törmäys
        if(pY + (siirtyyYlos ? -1 : 1) == alusta.getSijainti().getY() &&
                pX >= alusta.getSijainti().getX() &&
                pX <= (alusta.getSijainti().getX() + alusta.getLeveys())){
            
            for(int i = alusta.getSijainti().getX(); 
                    i < (alusta.getSijainti().getX() + alusta.getLeveys());
                    i++){
                if(i == pX){
                    double kallistus = i - alusta.getSijainti().getX();
                    kallistus -= alusta.getLeveys()/2;
                    kallistus *= 0.05D;
                    pallo.tormaa(Laita.ALA, kallistus);
                    pallo.nopeuta();
                }
            }
            
        }
        // Sivutörmäykset
        if((pX >= leveys-1 && !siirtyyVasemalle) || 
                (pX <= 0 && siirtyyVasemalle)){
            pallo.tormaa(Laita.VASEN);
        }
        
        // Palikkatörmäykset
        for(Palikka p : palikat){
            boolean tormaa = false;
            if(p.getSijainti().getX() == pX+1 &&
                    p.getSijainti().getY() == pY &&
                    !siirtyyVasemalle){
                pallo.tormaa(Laita.OIKEA);
                tormaa = true;
            }
            if(p.getSijainti().getX() == pX-1 &&
                    p.getSijainti().getY() == pY &&
                    siirtyyVasemalle){
                pallo.tormaa(Laita.VASEN);
                tormaa = true;
            }
            if(p.getSijainti().getX() == pX &&
                    p.getSijainti().getY() == pY+1 &&
                    !siirtyyYlos){
                pallo.tormaa(Laita.YLA);
                tormaa = true;
            }
            if(p.getSijainti().getX() == pX &&
                    p.getSijainti().getY() == pY-1 &&
                    siirtyyYlos){
                pallo.tormaa(Laita.ALA);
                tormaa = true;
            }
            
            if(tormaa){
                pisteet += p.getPisteet();
                paivitaHighscore(pisteet);
                palikat.remove(p);
                if(palikat.size() == 0){
                    aloitaLopeta();
                    message = "YOU WON! SCORE"+this.pisteet;
                }
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
                 alusta.getSijainti().getX()+dx >= 0 &&
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
             message = "PRESS SPACE TO START, X TO RESTART";
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
     
     public void paivitaHighscore(int pisteet){
         if(pisteet > highscore){
             highscore = pisteet;
         }
     }
     
     public int getHighscore(){
         return highscore;
     }
     
     public void reset(){
         if(jatkuu){
             return;
         }
         
         palikat.clear();
         
         for(int y = 2; y < 5 ; y++){
            for(int x = 0; x < leveys; x++){
                Piste palikanSijainti = new Piste(x,y);
                Palikka p = new Palikka(palikanSijainti,(ran.nextInt(3)+1)*10);
                palikat.add(p);
            }
        }
        
        Piste alustanSijantin = new Piste(
                leveys / 2 - Alusta.ALUSTAN_LEVEYS / 2,     // X - koordinaatti
                korkeus - 1);                               // Y - koordinaatti
        
        this.alusta = new Alusta(alustanSijantin);
        
        Piste pallonSijainti = new Piste(
                leveys / 2,                                 // X - koordinaatti
                korkeus - 2);                               // Y - koordinaatti
        this.pallo = new Pallo(0,-0.1, 0.1, pallonSijainti);
        
        this.pisteet = 0;
        
        jatkuu = true;
        aloitaLopeta();
        
     }
     
}
