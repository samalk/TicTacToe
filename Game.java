/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTakToe;

import java.util.Scanner;

/**
 *
 * @author Samer
 */
public class Game {
    private final Player[] playerList;
    private final Board board;
    private boolean running;
    private final Layout layout;
    private int playerChoice;
    private Computer machine;
  //   private boolean error= true;
  
    public Game()
    {
        layout=new Layout();
        
        playerList= layout.PlayersGetter();
        board= new Board(playerList); 
        machine= new Computer(board);
        machine.depthSetter(4);
        running=true;
      
    }
  
    private void run(){
        while(/*running && */!board.BoardIsFull())
        {
            layout.render();
            update();
            
            //running = board.winner()==0;
            
             
        }
        layout.render();
        
       if(!running) layout.winnerIS(board);
       else System.out.println("it is a DRAW");
    }
    private void update(){
       
       
        System.out.println("Current Player: "+board.currentPlayer().playerNameGetter()+
                            " ("+board.currentPlayer().symbolGetter()+") ");
        System.out.print("Number to mark: ");
        //try{
           if(board.currentPlayer().playerIdentifier()==1)
           {
         playerChoice=new Scanner(System.in).nextInt();
        
         board.boardAdjust(Point.pointBuilder(playerChoice-1),board.currentPlayer().playerIdentifier());
          layout.fillerAdjust(Point.pointBuilder(playerChoice-1), board);
           }
           else 
           {
                
               machine.alphaBeta(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 2);
                //System.out.println(machine.getBestMove());
               
                    System.out.println(machine.getBestMove());
               board.boardAdjust(machine.getBestMove(), 2);
               //System.out.println(machine.getBestMove());
                layout.fillerAdjust(machine.getBestMove(), board);
           }
         
         board.nextTurn();
        // error= false;
      //  }catch(Exception e){System.out.println("Invalid Value! try again!");}
       }
        

    public static void  main(String[]args)
    {
        new Game().run();
    }
    
}
