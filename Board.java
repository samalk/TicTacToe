/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTakToe;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 *
 * @author Samer
 */
public class Board {
    private final static int Empty=0;
    private final static int XPLAYER=1;
    private final static int OPLAYER=2;
    private int turnCounter;
    private final Player [] playerList;
    private  int [][]board;
    private final ArrayList<Point>possibleState;
    private boolean boardIsFull;
    private int activePlayer;
    public Board( Player[]playerList) {
        possibleState= new ArrayList<>();
       
        board= new int[3][3];
         turnCounter=0;
         
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
                board[i][j]=0;
        }
       
        activePlayer=0;
        this.playerList=playerList;   
    }
    public ArrayList<Point>getPossibleMoves()
    {
            for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++ )
            {
                    if(board[i][j]==0)
                    {
                    this.possibleState.add(new Point(i,j)); 
                    //System.out.println("("+i+","+j+")");
                    }
                    
                    
            }
        }
            //System.out.println(this.possibleState.get(0));
        return this.possibleState;
    }
    public int[][]boardGetter(){return board;}
    public void boardAdjust(Point a,int player){
        int[] cord= a.getCord();

        if(board[cord[0]][cord[1]]==0)
        {
           board[cord[0]][cord[1]]= player;
           
        }
        
        else 
            throw new EmptyStackException();
        
           
        //System.out.println("Cord: "+cord[0]+","+cord[1]);
}
    public void nextTurn()
    {
        turnCounter++;
        System.out.println("turn Counter: "+turnCounter);
        if( activePlayer<1)
        activePlayer++;
        
        else 
            activePlayer=0;
        
    }

    
    public boolean BoardIsFull(){
        return (boardIsFull=(turnCounter==9));
    }
    public Player currentPlayer(){
        return playerList[activePlayer];
    }
    private int checkHorizental(){
         boolean result;
         int firstElem;
         for(int i=0;i<3;i++)// case vertical
        {
           if(board[0][i]!=0)
           {
               firstElem=board[i][0];
              
             result=board[i][1] == firstElem && board[i][2] == firstElem;
                               
            if (result)
                return firstElem;
           }
        }
        
        return 0;
        
    }
    private int checkVertical(){
        boolean result;
        int firstElem;
         for(int i=0;i<3;i++)// case vertical
        {
              
           if(board[0][i]!=0)
           {
               firstElem=board[0][i];
              
             result=board[1][i] == firstElem && board[2][i] == firstElem;
                               
            if (result)
                return firstElem;
           }
        }
        
        return 0;
    }
    private int checkDiagonal(){
        boolean result;
        boolean result2;
        int firstElemL;
        int firstElemR;
             if(board[0][0]!=0)
             {
               firstElemL=board[0][0];
               firstElemR=board[0][2];
              //left diagonal
             result= board[1][1] == firstElemL && board[2][2] == firstElemL;
              // right Diagonal
             result2= board[1][1] == firstElemR && board[2][0] == firstElemR;
                               
            if (result)
                return firstElemL;
            if(result2)
                return firstElemR;
             }
           
        
        
        return 0;
             
    }
    public int winner(){
        
        
        
        if(checkVertical()!=0)
            return checkVertical();
        
        if(checkHorizental()!=0)
            return checkHorizental();
        else if( checkDiagonal()!=0)
            return checkDiagonal();
        else 
            return 0;
    }
    
}
