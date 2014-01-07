/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.game;

import com.ilri.breakout.domain.Laita;
import com.ilri.breakout.domain.Pallo;
import com.ilri.breakout.domain.Piste;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilari Richardt
 */
public class PalloTest {
    private Pallo pallo;
    
    public PalloTest() {
    }
    
    @Before
    public void setUp() {
        double suuntaX = 0;
        double suuntaY = 1;
        double nopeus = 0.1;
        Piste pallonSijainti = new Piste(0,0);
        this.pallo = new Pallo(suuntaX,suuntaY, nopeus,pallonSijainti);
    }
    
    @Test
    public void testaaAlustus(){
        assertEquals(1.0,this.pallo.getSuuntaY(), 0.0);
        assertEquals(0.0,this.pallo.getSuuntaX(), 0.0);
        assertEquals(0.1,this.pallo.getNopeus(), 0.0);
        assertEquals(0,this.pallo.getSijainti().getX());
        assertEquals(0,this.pallo.getSijainti().getY());
    }
    
    @Test
    public void siirraPalloaSimple(){
        pallo.siirra(1,1);
        assertEquals(1,this.pallo.getSijainti().getX());
        assertEquals(1,this.pallo.getSijainti().getY());
    }
    
    @Test
    public void siirraPalloaYksiFrameYlospain(){
        pallo.setNopeus(1);
        pallo.setSuuntaY(1);
        pallo.setSuuntaX(0);
        pallo.siirra();
        assertEquals(0,this.pallo.getSijainti().getX());
        assertEquals(1,this.pallo.getSijainti().getY());
    }
    
    @Test
    public void siirraPalloaYksiFrameOikealle(){
        pallo.setNopeus(1);
        pallo.setSuuntaY(2);
        pallo.siirra();
        assertEquals(0,this.pallo.getSijainti().getX());
        assertEquals(2,this.pallo.getSijainti().getY());
    }
    
    @Test
    public void testaaAsetaNopeus(){
        pallo.setNopeus(16.0);
        assertEquals(16.0, this.pallo.getNopeus(), 0.0);
    }
    
    @Test
    public void testaaAsetaSuunta(){
        pallo.setSuuntaX(1);
        pallo.setSuuntaY(5);
        assertEquals(1, this.pallo.getSuuntaX(), 0.0);
        assertEquals(5, this.pallo.getSuuntaY(), 0.0);
    }
    
    @Test
    public void testaaSmoothLiikkuminen(){
        pallo.setSuuntaX(0.1);
        pallo.setSuuntaY(0.1);
        pallo.siirra();
        assertEquals(0.1,pallo.getSijaintiX(),0.001);
        assertEquals(0.1,pallo.getSijaintiY(),0.001);
    }
    
    @Test
    public void testaaTormaysReaktioVasenOikea(){
        pallo.setSuuntaX(1);
        pallo.tormaa(Laita.VASEN);
        assertEquals(-1.0,pallo.getSuuntaX(),0.0);
        pallo.tormaa(Laita.OIKEA);
        assertEquals(1.0,pallo.getSuuntaX(),0.0);
        
        
    }
    
    @Test
    public void testaaTormaysReaktioYlaAla(){
        pallo.tormaa(Laita.ALA);
        assertEquals(-1.0,pallo.getSuuntaY(),0.0);
        pallo.tormaa(Laita.YLA);
        assertEquals(1.0,pallo.getSuuntaY(),0.0);
        
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
