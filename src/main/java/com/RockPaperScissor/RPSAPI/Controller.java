package com.RockPaperScissor.RPSAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.RockPaperScissor.RPSAPI.Game.RPS;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    

    Map<UUID,Game> ongoingGames = new HashMap<UUID,Game>();

    public StatusMessage createGame(Player createrPlayer){
        if(createrPlayer.getName().strip()!=""){
            Game nGame = new Game(createrPlayer);
            ongoingGames.put(nGame.getGameId(), nGame);
            return new StatusMessage("Game started by: " +createrPlayer.getName() + ", with game uuid: " + nGame.getGameId().toString(), true);
        }
        return new StatusMessage("Please enter playername thats not an empty string.", false);
        
    }
    public StatusMessage addToGame(UUID uuid,Player newPlayer){
        if(ongoingGames.containsKey(uuid)){
        Game game = ongoingGames.get(uuid);
        return game.addPlayer(newPlayer);     
    }
    return new StatusMessage("Cannot fint game with uuid: " + uuid.toString(), false);
    }
    public StatusMessage makeMove(UUID uuid,Player player, Move move){
        String tempMove = move.getMove().toString().toUpperCase();
        RPS rpsMove = RPS.valueOf(tempMove);

        if(ongoingGames.containsKey(uuid)){
            Game g = ongoingGames.get(uuid);
            
            if( tempMove.equalsIgnoreCase(RPS.PAPER.toString()) ||tempMove.equalsIgnoreCase(RPS.ROCK.toString())||tempMove.equalsIgnoreCase(RPS.SCISSORS.toString())){
                return g.makeMove(player,rpsMove);
            }
            else{
                return new StatusMessage("Invalid move requested, move: "+ move.getMove()+ ", please pick between rock, paper and scissors." ,false);
            }
        }
        return new StatusMessage("Game with id: "+ uuid+ ", could not be found. Please verify that it exists." ,false);
    }
    public String getGameContent(UUID uuid){
        if(ongoingGames.containsKey(uuid)){
            return ongoingGames.get(uuid).getContentString(uuid);
        }
        return "Game with the following uuid could not be found: " + uuid.toString();
    }



	@PostMapping("/games")
	public String all(@RequestBody Player newUser) {
		return this.createGame(newUser).toString();
	}
	@PostMapping("/games/{uuid}/join")
	public String allse(@PathVariable("uuid") UUID uuid,@RequestBody Player newUser) {
		return this.addToGame(uuid, newUser).toString();
		
	}
	@PostMapping("/games/{uuid}/move")
	public String alls(@PathVariable("uuid") UUID uuid, @RequestBody Move move) {
		return this.makeMove(uuid,move.getPlayer(), move).toString();
	}
	@GetMapping("/games")
	public String greeting(@RequestParam(value = "uuid", defaultValue = "") UUID uuid) {
		return this.getGameContent(uuid);
	}
}
