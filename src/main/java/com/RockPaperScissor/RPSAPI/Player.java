package com.RockPaperScissor.RPSAPI;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Player {
    //Class representing a Player, not neccesary but helps create a more understandable code.

    private String name;

    @JsonCreator
	public Player(String name) {
		this.name = name; 
    }

    public String getName(){
        return this.name;
    }
}
