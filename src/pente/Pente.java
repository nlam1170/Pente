/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pente;

import apcsgametester.APCSGameTester;

/**
 *
 * @author nlam1
 */
public class Pente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String [] players = { "BLACK", "WHITE" };
        APCSGameTester.launchGUIGame(PenteGame.class, PenteGUI.class, "Pente", players); /* FromLog */
                //PenteGame.class,
                //PenteTextView.class,    
        

    }
    
}
