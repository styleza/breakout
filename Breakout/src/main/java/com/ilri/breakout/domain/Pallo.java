package com.ilri.breakout.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Itsestään liikkuva pallo
 * @author Ilari
 */
public class Pallo implements Siirrettava, Sijaitseva{
    private double suuntaX;              // X liikkuvuus
    private double suuntaY;              // Y liikkuvuus
    private double nopeus;              // sijaintia
    private Piste sijainti;
    private double yx;
    private double yy;
    boolean pakotaSiirto;
    
    /**
     * Luo pallo suunnalla, nopeudella ja sijainnilla
     * Suunta annetaan radiaaneissa
     * @param suunta
     * @param nopeus
     * @param sijainti 
     */
    public Pallo(double suuntaX,double suuntaY, double nopeus, Piste sijainti){
        this.suuntaX = suuntaX;
        this.suuntaY = suuntaY;
        this.nopeus = nopeus;
        this.sijainti = sijainti;
        yy = yx = 0.0;
        pakotaSiirto = true;
    }

    /**
     * Siirtää palloa
     * @param dx
     * @param dy 
     */
    @Override
    public void siirra(int dx, int dy) {
        this.sijainti.siirra(dx, dy);
    }
    
    /**
     * Siirtää palloa yhden peliaskeleen verran oikeaan suuntaan oikealla nopeudella
     */
    public void siirra(){
        double dx = suuntaX + yx;
        double dy = suuntaY + yy;
        
        yx = (dx)- (long)(dx);
        yy = (dy)- (long)(dy);
        
        pakotaSiirto = false;
        this.siirra((int)dx,(int)dy);
    }
    
    public double getSuuntaX(){
        return this.suuntaX;
    }
    public double getSuuntaY(){
        return this.suuntaY;
    }
    
    public double getNopeus(){
        return this.nopeus;
    }
    
    public Piste getSijainti(){
        return this.sijainti;
    }
    
    public double getSijaintiX(){
        return (double)this.getSijainti().getX() + yx;
    }
    
    public double getSijaintiY(){
        return (double)this.getSijainti().getY() + yy;
    }
    
    public void setSuuntaX(double suunta){
        this.suuntaX = suunta;
    }
    public void setSuuntaY(double suunta){
        this.suuntaY = suunta;
    }
    
    public void setNopeus(double nopeus){
        this.nopeus = nopeus;
    }
    

    /**
     * Törmäyttää pallon (muuttaa suunnan)
     */
    public void tormaa(Laita laita){
        if(laita == Laita.OIKEA || laita == Laita.VASEN){
            setSuuntaX(getSuuntaX() * -1.0D);
            yx = (getSuuntaX() < 0.0D ? -0.1 : 0.1);
        }
        
        if(laita == Laita.YLA || laita == Laita.ALA){
            setSuuntaY(getSuuntaY() * -1.0D);
            yy = (getSuuntaY() < 0.0D ? -0.1 : 0.1);
        }
        
    }
    
    /**
     * Törmäyttää pallon (muuttaa sunnan ja kallistaa palloa)
     * @param laita
     * @param kallistus 
     */
    public void tormaa(Laita laita,double kallistus){
       tormaa(laita);
       setSuuntaX(kallistus);
    }
    
    /**
     * Nopeuttaa peliä
     */
    public void nopeuta(){
        if(suuntaX < 1.0){
            this.suuntaX *= 1.1;
        }
        if(suuntaY < 1.0){
            this.suuntaY *= 1.1;
        }
    }
    
}
