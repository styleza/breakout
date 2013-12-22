package com.ilri.breakout;

import com.ilri.breakout.controller.Breakout;
import com.ilri.breakout.gui.Kayttoliittyma;
import javax.swing.SwingUtilities;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Breakout peli = new Breakout();
 
        Kayttoliittyma kali = new Kayttoliittyma(peli);
        SwingUtilities.invokeLater(kali);
 
        while (kali.getPiirtoalusta()== null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Piirtoalustaa ei ole vielä luotu.");
            }
        }
 
        peli.setPiirtoalusta(kali.getPiirtoalusta());
        peli.start();
    }
}
