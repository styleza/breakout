package com.ilri.breakout.game;

import com.ilri.breakout.domain.Logiikka;
import com.ilri.breakout.domain.Piste;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilari
 */
public class LogiikkaTest {
    private Logiikka logiikka;
    
    public LogiikkaTest() {
        
    }

    
    @Before
    public void setUp() {
        this.logiikka = new Logiikka(30,40);
    }
    


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testaaAlustus(){
        assertEquals("PRESS SPACE TO START, X TO RESTART",logiikka.getMessage());
        assertEquals(new Piste(15,38),logiikka.getPallo().getSijainti());
    }
    
    @Test
    public void testaaPalikatJaAlusta(){
        assertEquals(10,logiikka.getAlustanPisteet().size());
        assertEquals(90,logiikka.getPalikat().size());
    }
    
    @Test
    public void testaaAloitaLopeta(){
        assertFalse(logiikka.getJatkuu());
        logiikka.aloitaLopeta();
        assertTrue(logiikka.getJatkuu());
    }
    
    @Test
    public void testaaTormaysPalikka(){
        logiikka.getPallo().siirra(0, -34);
        logiikka.getPallo().setSuuntaY(-1);
        assertEquals(-1.0,logiikka.getPallo().getSuuntaY(),0.0);
        assertTrue(logiikka.testaaTormaykset());
        assertEquals(1.0,logiikka.getPallo().getSuuntaY(),0.0);        
    }
    
    @Test
    public void testaaTormaysAlusta(){
        logiikka.getPallo().setSuuntaY(1.0);
        assertTrue(logiikka.testaaTormaykset());
        logiikka.toimi();
        assertEquals(-1.1,logiikka.getPallo().getSuuntaY(),0.01);
    }
    
    @Test
    public void testaaAlusta(){
        assertNotNull(logiikka.getAlusta());
    }
    
    @Test
    public void testaaKellopulssi(){
        logiikka.aloitaLopeta();
        assertTrue(logiikka.toimi());
        assertEquals(37.8,logiikka.getPallo().getSijaintiY(),0.001);
    }
    
    @Test
    public void testaaAlustaSiirto(){
        assertEquals(10,logiikka.getAlusta().getSijainti().getX());
        logiikka.aloitaLopeta();
        logiikka.siirraAlustaa(-1, 0);
        assertEquals(9,logiikka.getAlusta().getSijainti().getX());
        logiikka.siirraAlustaa(-10, 0);
        assertEquals(9,logiikka.getAlusta().getSijainti().getX());
        logiikka.siirraAlustaa(20, 0);
        assertEquals(9,logiikka.getAlusta().getSijainti().getX());
    }
    
    @Test
    public void testaaGetPisteet(){
        assertEquals(0,logiikka.getPisteet());
    }
    
    @Test
    public void testaaSivuTormays(){
        logiikka.getPallo().setSuuntaX(5);
        logiikka.getPallo().siirra(15, 0);
        assertTrue(logiikka.testaaTormaykset());
        assertEquals(-5.0,logiikka.getPallo().getSuuntaX(),0.001);
        logiikka.getPallo().siirra(-30, 0);
        assertTrue(logiikka.testaaTormaykset());
        assertEquals(5.0,logiikka.getPallo().getSuuntaX(),0.001);
    }
    
    @Test
    public void testaaYlalaitaTormays(){
        logiikka.getPallo().setSuuntaY(-5);
        logiikka.getPallo().siirra(0, -40);
        assertTrue(logiikka.testaaTormaykset());
        assertEquals(5.0,logiikka.getPallo().getSuuntaY(),0.001);
    }
    
    @Test
    public void testaaAlalaitaTormays(){
        logiikka.siirraAlustaa(-10, 0);
        logiikka.getPallo().setSuuntaY(5);
        logiikka.getPallo().siirra();
        assertFalse(logiikka.testaaTormaykset());
    }
    
    @Test
    public void testaaHighscore(){
        logiikka.paivitaHighscore(10);
        assertEquals(10,logiikka.getHighscore());
        logiikka.paivitaHighscore(20);
        assertEquals(20,logiikka.getHighscore());
        
        logiikka.paivitaHighscore(10);
        assertEquals(20,logiikka.getHighscore());
    }
    
    @Test
    public void testaaLives(){
        assertEquals(3,logiikka.getLives());
        logiikka.aloitaLopeta();
        logiikka.siirraAlustaa(-10, 0);
        logiikka.getPallo().setSuuntaY(10);
        logiikka.getPallo().siirra();
        assertFalse(logiikka.toimi());
        assertEquals(2,logiikka.getLives());
    }
    
    @Test
    public void testaaReset(){
        logiikka.reset(false);
        assertEquals(90,logiikka.getPalikat().size());
        assertEquals(0,logiikka.getPisteet());
        assertEquals(3,logiikka.getLives());
    }
    
    @Test
    public void testaaReset2(){
        assertEquals(3,logiikka.getLives());
        logiikka.aloitaLopeta();
        logiikka.getPallo().siirra(0,10);
        assertFalse(logiikka.toimi());
        assertEquals(2,logiikka.getLives());
        logiikka.reset(false);
        assertEquals(2,logiikka.getLives());
        
    }

    
}
