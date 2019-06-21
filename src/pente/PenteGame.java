/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pente;

import apcsgame.GameBase;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author nlam1
 */
public class PenteGame extends GameBase {
    public static final int ERROR = -2;
    public static final int EMPTY = -1;
    public static final int BLACK = 0;
    public static final int WHITE = 1   ;
    public static final int NUM_ROWS = 19;
    public static final int NUM_COLS = 19;
    
    
   
         

    private int curPlayer;
    private int turn = 1;
    private int [][] board;

    private int BlackScore = 0;
    private int WhiteScore = 0;
    private boolean penta = false;
    

    public int getCurPlayer()
    {
        return curPlayer;
    }
    
    public int getOppPlayer(int currentplayer)
    {
        return (currentplayer + 1) % getNumPlayers();
    }

    public int getNumRows()
    {
        return NUM_ROWS;
    }

    public int getNumCols()
    {
        return NUM_COLS;
    }

    public int getTurn()
    {
        return turn;
    }

    public int getBoardSpace(int r, int c)
    {
        if (r >= 0 && r < NUM_ROWS && c >= 0 && c < NUM_COLS)
        {
            return board[r][c];
        }
     return ERROR;
    }

    public int getp1Score()
    {
        return BlackScore;
    }

    public int getp2Score()
    {
        return WhiteScore;
    }
    
    public int setp1Score(int n)
    {
        BlackScore = BlackScore + n;
        
        if (BlackScore > 5)
        {
            BlackScore = 5;
        }
        
        return BlackScore;
    }
    
    public int setp2Score(int n)
    {
        WhiteScore = WhiteScore + n;
        
        if (WhiteScore > 5)
        {
            WhiteScore = 5;
        }
        
        return WhiteScore;
    }
    
    public void addPoint(int player)
    {
       if ( player == 0)
       {
           BlackScore = BlackScore + 1;
       }
       
       if (player == 1)
       {
           WhiteScore = WhiteScore + 1;
       }
    }

    public boolean getPenta()
    {
        return penta;
    }
    
    public void setPenta(boolean n)
    {
        penta = n;
        
        if (n == true)
        {
            if (getCurPlayer() == 0)
            {
            setp1Score(5); 
            }
        
            if (getCurPlayer() == 1)
            {
                setp2Score(5);
            }
        }
    }
    
    private void setupGame()
    {
        board = new int[19][19];

        for (int i = 0; i < 19; i++)
        {
            for (int j = 0; j < 19; j++)
            {
                board[i][j] = EMPTY;
            }
        }

        curPlayer = BLACK;
    }

    private boolean isLegalMove(int row, int col, int cp) // first turn must place piece between 8 and 12
    {
        
          if (turn == 1 && cp == 0)
        {
            if (row == 9 && col == 9)
            {
                return true;
            }
        }
         
        if (turn == 3 && cp == 0)
        {
            if ((row == 7 || row == 8 || row == 0 || row == 10 || row == 11) && (col == 7 || col ==8 || col == 9 || col == 10 || col == 11))
            {
                return true;
            }
        }
        
        if (turn == 2 && cp == 1 || turn > 3)
        {
            return true;
        }
        
        
    return false;
    }
    
    private void checkPenta(int cp)
    {
        //CHECKS ALL ROWS FIRST//
        int counter = 0;
        for (int i = 0; i < getNumRows(); i++)
        {
            for (int j = 0; j < getNumCols(); j++)
            {
                if (getBoardSpace(i, j) == cp)
                {
                   counter++;
                       
                }
                else
                {
                    counter = 0;
                }
                if (counter == 5)
                {
                
                    setPenta(true);
                
                }
            }
            
            counter = 0;
        
        } 
        //CHECKS ALL COLUMNS FIRST//    
        counter = 0;
        for (int i = 0; i < getNumCols(); i++)
        {
            for (int j = 0; j < getNumRows(); j++)
            {
                if (getBoardSpace(j,i ) == cp)
                {
                   counter++;
                       
                }
                else
                {
                    counter = 0;
                }
                if (counter == 5)
                {
                
                    setPenta(true);
                
                }
            }
        
        counter = 0;
        
        }
        //CHECKS DIAGNOALLY GOING LEFT TO RIGHT//
      
        counter = 0;
        int x = 14;
        int y = 0;
        int endloop = 5;
        
        for(int i = 0; i < 15; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y+j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }
                if (counter == 5)
                {
                
                    setPenta(true);
                
                }
               }   
            endloop++;
            x--;
           counter = 0; 
        }
        
        counter = 0;
        x = 0;
        y = 14;
        endloop = 5;
        
        for(int i = 0; i < 15; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y+j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }  
                if (counter == 5)
                {
                
                    setPenta(true);
                
                }
               }   
            endloop++;
            y--;
           counter = 0; 
        }
        
        //CHECKS IF GOING DIAGONALLY FROM RIGHT TO LEFT//
        
        counter = 0;
        x = 14;
        y = 18;
        endloop = 5;
        
        for(int i = 0; i < 15; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y-j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }
                if (counter == 5)
                {
                
                    setPenta(true);
                
                }
               }   
            endloop++;
            x--;
           counter = 0; 
        }
        
        counter = 0;
        x = 0;
        y = 4;
        endloop = 5;
        
        for(int i = 0; i < 15; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y-j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }  
                if (counter == 5)
                {
                
                    setPenta(true);
                
                }
               }   
            endloop++;
            y++;
           counter = 0; 
        }
    }

    private void checkCapture(int cp)
    {
        String removedPieces="";
        //ALL ROWS FIRST//
        int counter = 0;
        for (int i = 1; i < 18; i++)
        {
            for (int j = 1; j < 18; j++)
            {
                if (getBoardSpace(i, j) == cp)
                {
                    counter++;
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(i, j - 2) == getOppPlayer(cp) && getBoardSpace(i, j +1) == getOppPlayer(cp))
                    {
                        int coordinate = j-1;
                        removedPieces += "" + i + " " + j + " " + i + " " + coordinate+ " ";
                        //removePiece(i, j, cp);
                        //removePiece(i, j-1, cp);
                        addPoint(getOppPlayer(cp));   
                    }
                }
                    
            }
        
        }
        counter = 0;
        for (int i = 1; i < 18; i++)
        {
            for (int j = 1; j < 18; j++)
            {
                if (getBoardSpace(i, j) == getOppPlayer(cp))
                {
                    counter++;
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(i, j - 2) == cp && getBoardSpace(i, j +1) == cp)
                    {
                        int coordinate = j-1;
                        removedPieces += "" + i + " " + j + " " + i + " " + coordinate+ " ";
                        //removePiece(i, j, getOppPlayer(cp));
                        //removePiece(i, j-1, getOppPlayer(cp));
                        addPoint(cp);   
                    }
                }
                    
            }
        
        }
        
        //ALL COLUMNS
        counter = 0;
        for (int i = 1; i < 18; i++)
        {
            for (int j = 1; j < 18; j++)
            {
                if (getBoardSpace(j, i) == cp)
                {
                    counter++;
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(j-2, i) == getOppPlayer(cp) && getBoardSpace(j+1, i) == getOppPlayer(cp))
                    {
                        int coordinate = j-1;
                        removedPieces += "" + j + " " + i + " " + coordinate + " " + i+ " ";
                        //removePiece(j, i, cp);
                        //removePiece(j-1, i, cp);
                        addPoint(getOppPlayer(cp));   
                    }
                }
                    
            }
        
        }
        counter = 0;
        for (int i = 1; i < 18; i++)
        {
            for (int j = 1; j < 18; j++)
            {
                if (getBoardSpace(j, i) == getOppPlayer(cp))
                {
                    counter++;
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(j-2, i) == cp && getBoardSpace(j+1, i) == cp)
                    {
                        int coordinate = j-1;
                        removedPieces += "" + j + " " + i + " " + coordinate + " " + i+ " ";
                        //removePiece(j, i, getOppPlayer(cp));
                        //removePiece(j-1, i-1, getOppPlayer(cp));
                        addPoint(cp);   
                    }
                }
                    
            }
        
        }
        
        //CHECKS FOR LEFT TO RIGHT//
        counter = 0;
        int x = 16;
        int y = 1;
        int endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y+j,x+j) == getOppPlayer(cp))
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(y+j-2, x+j-2) == cp && getBoardSpace(y+j+1,x+j+1) == cp)
                    {
                        int coordinate1 = y+j;
                        int coordinate2 = x+j;
                        int coordinate3 = y+j-1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4+ " ";
                        //removePiece(y+j, x+j, getOppPlayer(cp));
                        //removePiece(y+j-1, x+j-1, getOppPlayer(cp));
                        addPoint(cp);   
                    }
                }
               }   
            endloop++;
            x--;
           counter = 0; 
        }
        
        counter = 0;
        x = 1;
        y = 16;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y+j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }  
                if (counter == 2)
                {
                
                    if (getBoardSpace(y+j-2, x+j-2) == getOppPlayer(cp) && getBoardSpace(y+j+1,x+j+1) == getOppPlayer(cp))
                    {
                        int coordinate1 = y+j;
                        int coordinate2 = x+j;
                        int coordinate3 = y+j-1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4+ " ";
                        //removePiece(y+j, x+j, cp);
                        //removePiece(y+j-1,x+j-1,cp);
                        addPoint(getOppPlayer(cp));   
                    }
                
                }
               }   
            endloop++;
            y--;
           counter = 0; 
        }
        counter = 0;
        x = 16;
        y = 1;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y+j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(y+j-2, x+j-2) == getOppPlayer(cp) && getBoardSpace(y+j+1,x+j+1) == getOppPlayer(cp))
                    {
                        int coordinate1 = y+j;
                        int coordinate2 = x+j;
                        int coordinate3 = y+j-1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4+ " ";
                        //removePiece(y+j, x+j, cp);
                        //removePiece(y+j-1, x+j-1, cp);
                        addPoint(getOppPlayer(cp));   
                    }
                }
               }   
            endloop++;
            x--;
           counter = 0; 
        }
        
        counter = 0;
        x = 1;
        y = 16;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y+j,x+j) == getOppPlayer(cp))
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }  
                if (counter == 2)
                {
                
                    if (getBoardSpace(y+j-2, x+j-2) == cp && getBoardSpace(y+j+1,x+j+1) == cp )
                    {
                        int coordinate1 = y+j;
                        int coordinate2 = x+j;
                        int coordinate3 = y+j-1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4+ " ";
                        //removePiece(y+j, x+j, getOppPlayer(cp));
                        //removePiece(y+j-1,x+j-1,getOppPlayer(cp));
                        addPoint(cp);   
                    }
                
                }
               }   
            endloop++;
            y--;
           counter = 0; 
        }
        
        //CHECKS FOR RIGHT TO LEFT
        counter = 0;
        x = 1;
        y = 2;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y-j,x+j) == getOppPlayer(cp))
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(y-j+2, x+j-2) == cp && getBoardSpace(y-j-1,x+j+1) == cp)
                    {
                        int coordinate1 = y-j;
                        int coordinate2 = x+j;
                        int coordinate3 = y-j+1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4+ " ";
                        //removePiece(y-j, x+j, getOppPlayer(cp));
                        //removePiece(y-j+1, x+j-1, getOppPlayer(cp));
                        addPoint(cp);   
                    }
                }
               }   
            endloop++;
            y++;
           counter = 0; 
        }
        
        counter = 0;
        x = 16;
        y = 17;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y-j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }  
                if (counter == 2)
                {
                
                    if (getBoardSpace(y-j+2, x+j-2) == getOppPlayer(cp) && getBoardSpace(y-j-1,x+j+1) == getOppPlayer(cp))
                    {
                        int coordinate1 = y-j;
                        int coordinate2 = x+j;
                        int coordinate3 = y-j+1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4+ " ";
                        //removePiece(y-j, x+j, cp);
                        //removePiece(y-j+1,x+j-1,cp);
                        addPoint(getOppPlayer(cp));   
                    }
                
                }
               }   
            endloop++;
            x--;
           counter = 0; 
        }
        counter = 0;
        x = 1;
        y = 2;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y-j,x+j) == cp)
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }
                if (counter == 2)
                {
                    if (getBoardSpace(y-j+2, x+j-2) == getOppPlayer(cp) && getBoardSpace(y-j-1,x+j+1) == getOppPlayer(cp))
                    {
                        int coordinate1 = y-j;
                        int coordinate2 = x+j;
                        int coordinate3 = y-j+1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4 + " ";
                        //removePiece(y-j, x+j, cp);
                        //removePiece(y-j+1, x+j-1, cp);
                        addPoint(getOppPlayer(cp));   
                    }
                }
               }   
            endloop++;
            y++;
           counter = 0; 
        }
        
        counter = 0;
        x = 16;
        y = 17;
        endloop = 2;
        
        for(int i = 0; i < 16; i++)
        {
           
               for(int j = 0; j < endloop; j++)
               {
                if (getBoardSpace(y-j,x+j) == getOppPlayer(cp))
                {
                   counter++;      
                }
                else
                {
                    counter = 0;
                }  
                if (counter == 2)
                {
                
                    if (getBoardSpace(y-j+2, x+j-2) == cp && getBoardSpace(y-j-1,x+j+1) == cp)
                    {
                        int coordinate1 = y-j;
                        int coordinate2 = x+j;
                        int coordinate3 = y-j+1;
                        int coordinate4 = x+j-1;
                        removedPieces += "" + coordinate1 + " " + coordinate2 + " " + coordinate3 + " " + coordinate4 + " ";
                        //removePiece(y-j, x+j, getOppPlayer(cp));
                        //removePiece(y-j+1,x+j-1,getOppPlayer(cp));
                        addPoint(cp);   
                    }
                
                }
               }   
            endloop++;
            x--;
           counter = 0; 
        }
    removePiece(removedPieces); 
            
    }
    
    private void addPiece(int row, int col, int cp)
    {
        if (isLegalMove(row, col, cp))
        {
            board[row][col] = cp;
        }
    }
    
    public void removePiece(String coordinates)
    {
        StringTokenizer st = new StringTokenizer(coordinates);
        while(st.hasMoreTokens())
        {
         int row =Integer.parseInt(st.nextToken());
         int col =Integer.parseInt(st.nextToken());
         board[row][col] = EMPTY;
        }
    }

    @Override
    public int getMaxPlayerCount()
    {
        return 2;
    }

    @Override
    public int getMinPlayerCount()
    {
        return 2;
    }

    @Override
    public boolean isGameOver()
    {
        if (BlackScore >= 5 || WhiteScore >=5)
        {
            return true;
        }
    return false;
    }


    @Override
    protected void playGame() {
        setupGame();

        while (!isGameOver()) {
            boolean moved = false;
            int row = 0;
            int col = 0;

            String move = requestInputFromPlayer(curPlayer, "YOUR TURN");

            Scanner scanMove = new Scanner(move);
            if (scanMove.hasNextInt()) {
                 row = scanMove.nextInt();
                 col = scanMove.nextInt();
            }


            if (isLegalMove(row, col, curPlayer)) {
                addPiece(row, col, curPlayer);
                checkPenta(curPlayer);
                checkCapture(curPlayer);
                moved = true;
            }

            if (moved) {
                curPlayer = (curPlayer + 1) % getNumPlayers();
                turn++;
            }
            
        if (isGameOver())
        {
            System.out.println("THE GAME IS NOW OVER");
        }



    }
}
}


