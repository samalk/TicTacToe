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
public class Layout {
    private Player [] playerList;
     private StringBuilder text;
     private  String tmpText;
    // private Board board;
     private boolean error;
     private char[][] filler;
     private int againstWho;
    public Layout(){
        playerList= new Player[2];
        filler=new char[][]{{'1','2','3'},{'4','5','6'},{'7','8','9'}};
        error= true;
        
        System.out.println("Welcome to Tick Tack Toe!");
        Scanner scanner= new Scanner(System.in);
        
        
        while (error)
        {
        
                    
        for(int i= 0; i<2; i++)
        {
            text= new StringBuilder();
            if(i==0)
                 System.out.print("Please Enter Player"+ (i+1)+" name and Symbol in the form  name,Symbol: ");
            else 
                System.out.print("Please Enter Player"+ (i+1)+" name: ");
           
                
        tmpText= scanner.nextLine();
         if(i==0)
        text.append(tmpText);
        else
        {
            try{
            tmpText=tmpText.split(",")[0];
            text.append(tmpText);
            }catch(Exception e){error=true;}
            
        }
        if(i==1)
        {
            
            
            switch(playerList[0].playerIdentifier())
            {
                case 1: text.append(",o");
                break;
                case 2: text.append(",x");
                break;
                
            }
           
        }
        //System.out.println(text);
        tmpText=text.toString();
        String []tmp= tmpText.split(",");
        try{
            
         if(null== tmp[1]) 
             tmp[1]=" ";
         else switch (tmp[1]) {
                case "x":
                case "X":
                    tmp[1]="1";
                    break;
                case "O":
                case "o":
                    tmp[1]="2";
                    break;
                default:
                    tmp[1]=" ";
                    break;
            }
        if(tmp[0].isEmpty())
            tmp[0]="UnNamed";
        playerList[i]= new Player(tmp[0],Integer.parseInt(tmp[1]));
        error=false;
        }catch(Exception e){ 
            i--;
            System.out.println("Invalid namevalue! Please try again! ");
        }
        
        }
        
        }
    }
     public Player[] PlayersGetter(){
         return playerList;
     }
     public void render(){
          System.out.printf("%-15s%-15s","Name","Symbol");
          System.out.println();
        for(int i=0;i<2;i++)
        {
        System.out.printf("%-15s%-15c",playerList[i].playerNameGetter(),playerList[i].symbolGetter());
        System.out.println();
       /* System.out.println("Player 1 Name: "+playerList[0].playerNameGetter()+" | Player 2 Name: "+playerList[1].playerNameGetter());
        System.out.println("  Symbol: "+playerList[0].symbolGetter()+" |       Symbol: "+playerList[1].symbolGetter());*/
        }
         System.out.println("--------------------------------------");
        
        System.out.print("__"+filler[0][0]+"_|_"+filler[0][1]+"_|_"+filler[0][2]+"__\n"+
                         "__"+filler[1][0]+"_|_"+filler[1][1]+"_|_"+filler[1][2]+"__\n" +
                         "  "+filler[2][0]+"_|_"+filler[2][1]+"_|_"+filler[2][2]+"__\n" );
        
         
    }
     public void fillerAdjust(Point a,Board board)
     {
         int []tmp;
        tmp = a.getCord();
         filler[tmp[0]][tmp[1]]=board.currentPlayer().symbolGetter();
         
     }
     public void winnerIS(Board board){
         int i;
         for( i=0;i<playerList.length;i++)
         
             if(playerList[i].playerIdentifier()==board.winner())
         System.out.println( playerList[i].playerNameGetter()+" has won!");
     }
   /* public static void main(String []args)
    {
        Player[] p= new Player[2];
        p= new Layout().PlayersGetter();
        for (Player p1 : p) {
            System.out.println(p1.playerNameGetter() + " " + p1.playerIdentifier());
        }
    }*/
    
}
