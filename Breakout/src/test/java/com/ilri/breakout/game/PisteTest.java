/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilri.breakout.game;

import com.ilri.breakout.domain.Piste;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilari Richardt
 */
public class PisteTest {
    private Piste piste;
    public PisteTest() {
    }
    
    @Before
    public void setUp() {
        this.piste = new Piste(1,1);
    }
    
    @Test
    public void testaaAlustus(){
        assertEquals(1,this.piste.getX());
        assertEquals(1,this.piste.getY());
        
    }
    
    @Test
    public void testaaAlustus2(){
        Piste p = new Piste();
        assertEquals(0,p.getX());
        assertEquals(0,p.getY());
    }
    
    @Test
    public void testaaSiirto(){
        piste.siirra(1,1);
        assertEquals(2,this.piste.getX());
        assertEquals(2,this.piste.getY());
    }
    
    @Test
    public void testaaEquals(){
        
        Piste p2 = new Piste(1,1);
        assertTrue(p2.equals(piste));
        
        assertFalse(p2.equals(null));
        
        Object f = new Object();
        assertFalse(p2.equals(f));
        
        Piste p3 = new Piste();
        assertFalse(p3.equals(piste));
        
        Piste p4 = new Piste(1,0);
        assertFalse(p4.equals(piste));
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
