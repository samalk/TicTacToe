/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTakToe;

/**
 *
 * @author Samer
 */
public class Point {
    private final int x;
    private final int y;
    public Point(int x, int y){
        this.y= y;
        this.x=x;
        
    }
    public  static Point pointBuilder(int num){
        
        Point p;
        if(num<3)
            p= new Point(0,num);
        else if( num<6)
            p= new Point(1,(num-3));
          
        else 
            p= new Point(2,(num-6));
        
          
        return p;
            
    }
    public int [] getCord(){
        
        int []cord= {this.x,this.y};
        return cord;
    }
    @Override
    public String toString(){
      String s= "( "+x+","+y+")";
      return s;
        
    }
    
}
