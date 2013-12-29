package com.ilri.breakout.gui;

/**
 * Luokka joka siirtää alustaa pelissä
 * @author Ilari
 */
import com.ilri.breakout.domain.Alusta;
import java.awt.event.*;
public class Nappaimistonkuuntelija implements KeyListener {
    
    private Alusta alusta;
    
    /**
     * Luo näppäimistönkuuntelija
     * @param alusta 
     */
    public Nappaimistonkuuntelija(Alusta alusta){
        this.alusta = alusta;
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
                alusta.siirra(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                alusta.siirra(1, 0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    
}
