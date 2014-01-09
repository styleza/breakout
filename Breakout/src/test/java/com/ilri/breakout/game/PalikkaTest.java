package com.ilri.breakout.game;

import com.ilri.breakout.domain.Palikka;
import com.ilri.breakout.domain.Piste;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ilari
 */
public class PalikkaTest {
    Palikka palikka;
    public PalikkaTest() {
    }
    
    @Before
    public void setUp() {
        Piste sijainti = new Piste();
        palikka = new Palikka(sijainti,10);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testaaPisteet(){
        assertEquals(10,palikka.getPisteet());
    }
}
