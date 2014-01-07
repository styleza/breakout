/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        assertEquals("PRESS SPACE TO START",logiikka.getMessage());
        assertEquals(new Piste(15,38),logiikka.getPallo().getSijainti());
    }
    
    @Test
    public void testaaPalikatJaAlusta(){
        assertEquals(100,logiikka.getPalikatJaAlusta().size());
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
        assertEquals(37.7,logiikka.getPallo().getSijaintiY(),0.001);
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
        logiikka.getPallo().setSuuntaY(5);
        logiikka.getPallo().siirra(0, -40);
        assertTrue(logiikka.testaaTormaykset());
        assertEquals(-5.0,logiikka.getPallo().getSuuntaY(),0.001);
    }
    
    @Test
    public void testaaAlalaitaTormays(){
        logiikka.siirraAlustaa(-10, 0);
        logiikka.getPallo().setSuuntaY(5);
        logiikka.getPallo().siirra();
        assertFalse(logiikka.testaaTormaykset());
    }
    
}