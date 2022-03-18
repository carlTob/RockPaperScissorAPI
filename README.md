# RockPaperScissorAPI
GET: http://localhost:8080/games?name=gameUUID
With Parameter: name : gameuuid
To get Status of game with gameUUID

POST: http://localhost:8080/games 
With request body: 
{
"name": "Player name"
}
To create a game with owner playername

POST: http://localhost:8080/games/gameUUID/move
With request body:
{
"move":"PAPER",
"player":{
    "name": "Player name"
    }
}
To make a move for player: player name.

POST: http://localhost:8080/games/gameUUID/join
With request body:
{
    "name": "Second player name"
}
To join game with gameUUID.
