package com.RockPaperScissor.RPSAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class Game {
    public enum RPS {
        ROCK, PAPER, SCISSORS
    }

    private final UUID gameId;

    Map<String, Player> players = new HashMap<String, Player>();
    Map<Player, RPS> moves = new HashMap<Player, RPS>();
    private boolean gameFinished;
    private String winner;

    public Game(Player firstPlayer) {
        this.gameId = UUID.randomUUID();
        this.gameFinished = false;
        this.winner = "Game not finished";
        players.put(firstPlayer.getName(), firstPlayer);
    }

    // Method called when player wants to make a move. Multiple error cases checked
    // and appropriate status messages are returned.
    public StatusMessage makeMove(Player player, RPS move) {
        if (this.players.size() == 2) {
            if (this.players.containsKey(player.getName())) {
                Player realPlayer = this.players.get(player.getName());
                if (moves.containsKey(this.players.get(player.getName()))) {
                    return new StatusMessage("Player: " + player.getName()
                            + " already made a move in the game with uuid: " + this.gameId, false);
                } else {
                    moves.put(realPlayer, move);
                    if (moves.size() == 2) {
                        this.gameFinished = true;
                        this.winner = this.findWinner();
                    }
                    return new StatusMessage("Player: " + player.getName() + ", made move: " + move, true);
                }
            } else {
                return new StatusMessage("Player with name: " + player.getName() + " cannot be found within the game.",
                        false);
            }
        } else {
            return new StatusMessage(
                    "Please wait until the game has two players. Currently only: " + players.keySet().toArray()[0]
                            + ", registered in the game.",
                    false);
        }
    }

    public UUID getGameId() {
        return gameId;
    }
    //Build a string that represents the game. If only one or none of the players has made a move they are hidden.
    //If both players did their move, the results and moves will be displayes in a string.
    public String getContentString(UUID uuid) {
        StringBuilder sb = new StringBuilder("");
        sb.append("_________ GAME STATS _________");
        sb.append(System.getProperty("line.separator"));
        if (this.gameFinished) {
            for (Entry<String, Player> entry : this.players.entrySet())
                sb.append("NAME: " + entry.getKey() + " MOVE: " + this.getPlayerMove(entry.getValue()));
                sb.append(System.getProperty("line.separator"));
            sb.append(System.getProperty("line.separator"));
            sb.append("WINNER: " + this.winner);
            sb.append(System.getProperty("line.separator"));

            sb.append("_________  _________");
        } else {
            sb.append("_________GAME NOT FINISHED  _________");
            sb.append(System.getProperty("line.separator"));
            for (Entry<String, Player> entry : this.players.entrySet())
                sb.append("NAME: " + entry.getKey() + " MOVE: " + "Hidden");
                sb.append(System.getProperty("line.separator"));    
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    private String getPlayerMove(Player player) {
        if (moves.containsKey(player)) {
            return moves.get(player).toString();
        }
        return "No move";
    }

    public StatusMessage addPlayer(Player secondPlayer) {
        if (this.players.size() != 2) {
            if (this.players.containsKey(secondPlayer.getName())) {
                return new StatusMessage("A0 players already exists in game with name: " + secondPlayer.getName(),
                        false);
            }
            players.put(secondPlayer.getName(), secondPlayer);
            return new StatusMessage("Player added to game with uuid: " + this.gameId.toString(), true);
        }
        return new StatusMessage("Two players already exists in game with uuid: " + this.gameId.toString(), false);

    }

    private String findWinner() {

        String winner = "";
        Player p1 = (Player) moves.keySet().toArray()[0];
        RPS m1 = moves.get(p1);
        Player p2 = (Player) moves.keySet().toArray()[1];
        RPS m2 = moves.get(p2);
        switch (m1) {
            case PAPER:
                switch (m2) {
                    case PAPER:
                        winner = "equal";
                        break;
                    case ROCK:
                        winner = p2.getName();
                        break;

                    case SCISSORS:
                        winner = p2.getName();
                        break;

                }
                break;
            case ROCK:
                switch (m2) {
                    case PAPER:
                        winner = p1.getName();
                        break;
                    case ROCK:
                        winner = "equal";
                        break;

                    case SCISSORS:
                        winner = p1.getName();
                        break;

                }
                break;
            case SCISSORS:
                switch (m2) {
                    case PAPER:
                        winner = p1.getName();
                        break;
                    case ROCK:
                        winner = p2.getName();
                        break;

                    case SCISSORS:
                        winner = "equal";
                        break;

                }
                break;
        }
        return winner;
    }

}
