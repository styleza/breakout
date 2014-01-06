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
}
