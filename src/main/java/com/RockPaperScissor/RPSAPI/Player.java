package com.RockPaperScissor.RPSAPI;

import com.fasterxml.jackson.annotation.JsonCreator;

public class Player {
    

    private String name;

    @JsonCreator
	public Player(String name) {
		this.name = name; 
    }

    public String getName(){
        return this.name;
    }
}
