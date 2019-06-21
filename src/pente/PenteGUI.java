/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pente;

import apcscvm.GraphicsUtilityFunctions;
import apcsgamecontrolview.GameControlView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


/**
 *
 * @author nlam1
 */
public class PenteGUI extends GameControlView<PenteGame> 
{
    Font f = new Font("Helvetica", 1, 12);
    
    public PenteGUI(String pn) 
    {
        super(pn);
    }

    @Override
    public String handleInputRequest(PenteGame m, String request) 
    {
        super.setInputMode(request);
        String result = super.getInputFromGUI();
        super.setInputMode("");
        
        return result;
        
    }
    
    public void paint(PenteGame game, Graphics g, int w, int h)
    {
       super.paintInputRequest(g, w, h);
       
       GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, "BLACK SCORE: " + game.getp1Score(), f, 100, 752, 50,50 );
       GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, "WHITE SCORE: " + game.getp2Score(), f, 600, 752, 50, 50);
       
       if (game.getp1Score() == 5)
       {
           GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, "BLACK WINS", f, 325, 852, 50,50 );
       }
       
       if (game.getp2Score() == 5)
       {
           GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, "WHITE WINS", f, 325, 852, 50,50 );
       }
       
       for (int i = 0; i < 19; i++)
       {
           GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, "" + i, f, 32 + 38*i, 14, 12, 32);
       }
       for (int i = 1; i < 19; i++)
       {
           GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, "" + i, f, 24, 64 + 38*(i-1), 12, 32);
       }
       
       g.setColor(new Color(26, 168, 52));
       g.fillRect(38, 38, 684, 684);
       
       g.setColor(Color.red);
       g.fillOval(370, 370, 20, 20);
       
       g.setColor(Color.black);
       
       for (int i =0; i < 18; i++)
       {
           for (int j = 0; j < 18; j++)
           {
               g.drawRect(38*i + 38, 38*j + 38, 38, 38);
           }
       }
       
       g.setColor(new Color(255, 15, 55));
       g.drawRect(38*7 + 38, 38*7 + 38, 152, 152);
       
       
       for (int i = 0; i < 19; i++)
       {
           for (int j = 0; j < 19; j++)
           {
               int piece = game.getBoardSpace(i, j);
               
               if (piece == 0)
               {
                  g.setColor(Color.black);
                  g.fillOval(19+38*j, 19+38*i , 30, 30);
               }
               
               if (piece == 1)
               {
                   g.setColor(Color.white);
                   g.fillOval(19+38*j, 19+38*i , 30, 30);
               }
           }
       }
       
      
       
     

    } 

    @Override 
    public void handleMouseClick(PenteGame gm, int n, MouseEvent me)
    {
        int x = me.getX();
        int y = me.getY();
        
        
        
        int inputx = (x / 39) ;
        int inputy = (y / 39) ;
        
        setInputFromGUI("" + inputy + " " + inputx);
       
    }
}
