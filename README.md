# RockPaperScissorAPI

# Run the server

Locate the main folder and run the following command below:

./mvnw spring-boot:run

Server is found on localhost port 8080.
http://localhost:8080

# Software to use

I used Postman to make calls to the API, easy to install and use. A link to Postman installation is provided below. 

Follow the guide below on how to use which displayes POST and GET request and corresponding request bodies. 
To enter request body follow the steps below.

1. Open the body tab.
2. Choose raw format.
3. Open the scroll menu on the right to change from Text to Json.
4. Enter the request body in the format shown below.

To enter params for the GET calls, follow the steps below.
1. Open the Params tab.
2. Enter Key:uuid.
3. Enter value: Actual game uuid.

https://www.postman.com/downloads/

# How to use

# Create game
This creates a game with owner: name, as specified in the request body below and returns a status message with the game uuid.

POST: http://localhost:8080/games 
With request body: 
{
"name": "Player name"
}
# Get game
This request the game status with game id= uuid. If both players have made their move, the results and respective player move will be presented in a status message. If one of the players hasnt made their move, all moves made will be hidden.


GET: http://localhost:8080/games?name=gameUUID
With Parameter: name : gameuuid
To get Status of game with gameUUID

# Make move
Make a move in game with game id: uuid. The move is sent with a corresponding player in the request body.
Move can be either: PAPER, ROCK or SCISSORS.
POST: http://localhost:8080/games/gameUUID/move


With request body:


{
"move":"PAPER",
"player":{
    "name": "Player name"
    }
}

# Join game

To join a game with game id: uuid. Request body should contain name of player.
POST: http://localhost:8080/games/gameUUID/join


With request body:


{
    "name": "Second player name"
}
