/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pente;

import apcsgametester.SimpleTextView;
import static pente.PenteGame.EMPTY;
import static pente.PenteGame.WHITE;
import static pente.PenteGame.BLACK;
import java.io.OutputStream;

/**
 *
 * @author nlam1
 */
public class PenteTextView extends SimpleTextView<PenteGame>
{
    @Override
    public void print(PenteGame m)
    {
        for ( int i = 0; i < m.getNumRows(); i++ ) {
            for ( int j = 0; j < m.getNumCols(); j++ ) {
                int piece = m.getBoardSpace(i, j);
                if ( piece == EMPTY ) {
                    getOut().print( "." );
                } else if ( piece == BLACK ) {
                    getOut().print( "B" );
                } else if ( piece == WHITE ) {
                    getOut().print( "W" );
                }
            }
            getOut().println();
        }
        int cp = m.getCurPlayer();
        if ( cp == BLACK ) {
            getOut().println( "BLACK's move" );
            getOut().println("Black score : " + m.getp1Score());
            getOut().println("White score : " + m.getp2Score());
        }
        else {
            getOut().println( "WHITE's move" );
            getOut().println("Black score : " + m.getp1Score());
            getOut().println("White score : " + m.getp2Score());
        }
    }

    public PenteTextView( String pn )
    {
        super( pn );
    }

    public PenteTextView(String pn, OutputStream os)
    {
        super(pn, os);
    }
}
