package com.RockPaperScissor.RPSAPI;

public class Move {
    //Class representing a move, to make things easier to handle.
    private Game.RPS move;
    private Player player;
    public Move(Game.RPS move , Player player){
        this.move=move;
        this.player = player;
    }
    public Game.RPS getMove(){
        return this.move;
    }
    public Player getPlayer(){
        return player;
    }
    
}
