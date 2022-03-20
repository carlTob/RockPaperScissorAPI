package com.RockPaperScissor.RPSAPI;

public class StatusMessage {
    //Status message. PosorNeg repserents wheter the message should be presented as a success or not.
    private final String statusMessage;
    private final boolean posOrNeg;
    public StatusMessage(String statusMessage,boolean posOrNeg){
        this.statusMessage = statusMessage;
        this.posOrNeg = posOrNeg;
    }
    public String toString(){
        if (posOrNeg){
            return "Success: " + statusMessage;
        }
        return "Failure: " + statusMessage;
    }
    
}
