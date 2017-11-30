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
class Player {
    private final String name;
    private final int identifier;
    private  final char symbol;
    public Player(String name,int identifier)
    {
        this.name= name;
        this.identifier=identifier;
        switch (identifier)
        {
            case 1: this.symbol='x';
            break;
            case 2: this.symbol='O';
            break;
            default: symbol='*';
            break;
        }
        
    }
    public char symbolGetter(){return this.symbol;}
    public String playerNameGetter(){return name;}
    public  int playerIdentifier(){return identifier;}
    
    
    
}
