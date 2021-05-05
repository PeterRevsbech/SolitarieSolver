package com.company;

import com.company.logic.Solitaire;
import com.company.models.states.ClosedSolitaireState;
import com.company.models.states.ISolitaireState;
import com.company.utils.Server;

public class Main {
    public static void main(String[] args) {
        //Init server and game-object
        Server server = new Server();
        Solitaire solitaire = new Solitaire();
        server.startServer();

        //Read start configuration from GUI
        String input = server.readInput();

        //Init game
        ISolitaireState startState = ClosedSolitaireState.newGameFromInput(input.split(" "));
        solitaire.initClosedGame(startState);

        //While game is not over
        while (!solitaire.isGameWon() && !solitaire.isGameLost()){
            //Get moveMsg
            String moveMsg = solitaire.findNextMoveClosedGame().formatGuiMoveMsg(solitaire.isUnkownCard());

            //Write to GUI: unknownCard;move
            server.writeOutput(moveMsg);

            //Read input from camera: String with array of cards, single card or nothing
            input = server.readInput();

            //Update game from input
            solitaire.updateClosedGame(input);
        }


    }
}
