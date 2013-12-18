/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilari Richardt
 */
public class AlustaTest {
    private Alusta alusta;
    public AlustaTest() {
    }
    
    @Before
    public void setUp() {
        Piste p = new Piste(1,1);
        this.alusta = new Alusta(p);
    }
    
    @Test
    public void testaaSiirtoa(){
        this.alusta.siirra(1,0);
        assertEquals(2,this.alusta.getSijainti().getX());
        assertEquals(1,this.alusta.getSijainti().getY());
    }
    
    @Test
    public void alustusOikein(){
        assertEquals(1,this.alusta.getSijainti().getX());
        assertEquals(1,this.alusta.getSijainti().getY());
        assertEquals(Alusta.ALUSTAN_LEVEYS,this.alusta.getLeveys());
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
