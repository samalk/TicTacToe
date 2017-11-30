/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTakToe;

import java.util.ArrayList;

/**
 *
 * @author Samer
 */
public class Computer {
    private final Board board;
    private int depth;
    private int score;
    private final ArrayList<PointsAndScores>rootsChildrenScore;
    public Computer(Board board){
        rootsChildrenScore= new ArrayList <>();
        this.board=board;
        
        score=0;
    }
    /*comp x /////player o*/
    private int changeScore(int x,int o){
        int change=0;
        if(x==3)
            change=100;
        else if(x==2 && o==0)
            change=10;
        else if(x==1 && o==0)
            change= 1;
        else if(o==3)
            change=-100;
        else if(o==2 && x==0)
            change=-10;
        else if(o==1 && x==0)
            change=-1;
        else 
            change=0;
        
        return change;
    }
    private void evaluateBoard()
    {
        score=0;
        int X=0,O=0,empty=0;
        int [][]table=board.boardGetter();
        //evaluate vertical
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                switch (table[i][j]) {
                    case 0:
                        empty++;
                        break;
                    case 1:
                        X++;
                        break;
                    default:
                        O++;
                        break;
                }
            }
            score+=changeScore(X,O);
        }
        
         //evaluate vertical
         X=0;
         O=0;
         empty=0;
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                switch (table[j][i]) {
                    case 0:
                        empty++;
                        break;
                    case 1:
                        X++;
                        break;
                    default:
                        O++;
                        break;
                }
            }
             score+=changeScore(X,O);
        }
        //check diagonal first
          X=0;
         O=0;
         empty=0;
        for(int i=0,j=0;i<3;j++,i++)
        {
           
                switch (table[j][i]) {
                    case 0:
                        empty++;
                        break;
                    case 1:
                        X++;
                        break;
                    default:
                        O++;
                        break;
                }
           
        }
        score+=changeScore(X,O);
        empty=0;
        X = 0;
        O = 0;

        //Check Diagonal (Second)
        for (int i = 2, j = 0; i > -1; --i, ++j) {
            switch (table[i][j]) {
                case 1:
                    X++;
                    break;
                case 2:
                    O++;
                    break;
                default:
                    empty++;
                    break;
            }
        }

        score+=changeScore(X,O);
        
    }
    
    public void depthSetter(int i)
    {
        depth=i;
    }
    public int alphaBeta(int alpha,int beta,int dep,int turn)
    {
        
        if(beta<=alpha) 
        {
            System.out.println("Pruning at depth = "+depth);
            if (turn==2)return Integer.MAX_VALUE ;
            else return Integer.MIN_VALUE;
        }
        
        if(dep==depth || (board.getPossibleMoves().isEmpty()|| board.winner()!=0) ) {evaluateBoard(); return score;}
        //System.out.println("here under return socre");
        if(board.getPossibleMoves().isEmpty())return 0;
        
        
         if(dep==0) { /*System.out.println("herenow");*/rootsChildrenScore.clear();}
        int maxValue=Integer.MIN_VALUE,minValue=Integer.MAX_VALUE;
        //System.out.println("PossibleMoves number = "+board.getPossibleMoves().size());
        for(int i=0;i<(board.getPossibleMoves().size());i++)
        {
            Point p=board.getPossibleMoves().get(i);
            int currentScore=0;
            if(turn==2)
            {
                
                board.boardAdjust(p,2);
                currentScore=alphaBeta(alpha,beta,dep+1,1);
                maxValue=Integer.max(currentScore,maxValue);
                alpha=Integer.max(alpha,currentScore);
                if(dep==0){System.out.println("filling...");rootsChildrenScore.add(new PointsAndScores(p,currentScore));}
            }
            
            else if(turn==1)
            {
                board.boardAdjust(p, 1);
                currentScore=alphaBeta(alpha,beta,dep+1,2);
                minValue=Integer.min(currentScore, minValue);
                beta= Integer.min(beta, currentScore);
            }
                board.boardAdjust(p,0);
                if(currentScore==Integer.MIN_VALUE || currentScore==Integer.MAX_VALUE)break;
                
            }
        
        
        return turn==2? maxValue:minValue;
        
    }
    public Point getBestMove(){
        System.out.println("hereeekeke");
        int max=-100000;
        int best=-1;
          if(rootsChildrenScore.isEmpty())
              System.out.println("isEmptyyy");
          System.out.print(rootsChildrenScore.size());
        for(int i=0;i<this.rootsChildrenScore.size();i++)
        {
            if(max<rootsChildrenScore.get(i).score)
            {
                max=rootsChildrenScore.get(i).score;
                
                best=i;
            }
        }
        return rootsChildrenScore.get(best).p;
    }
    
    
}
