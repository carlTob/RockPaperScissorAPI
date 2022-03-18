package com.RockPaperScissor.RPSAPI;

public class StatusMessage {
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
