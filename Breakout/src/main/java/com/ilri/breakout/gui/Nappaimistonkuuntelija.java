package com.ilri.breakout.gui;

/**
 * Luokka joka siirtää alustaa pelissä
 * @author Ilari
 */
import com.ilri.breakout.domain.Logiikka;
import java.awt.event.*;
public class Nappaimistonkuuntelija implements KeyListener {
    
    private Logiikka logiikka;
    
    /**
     * Luo näppäimistönkuuntelija
     * @param alusta 
     */
    public Nappaimistonkuuntelija(Logiikka logiikka){
        this.logiikka = logiikka;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Havaitse napinpainallus
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            /*case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_UP:
                break;*/
            case KeyEvent.VK_LEFT:
                logiikka.siirraAlustaa(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                logiikka.siirraAlustaa(1, 0);
                break;
            case KeyEvent.VK_SPACE:
                logiikka.aloitaLopeta();
                break;
                
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
