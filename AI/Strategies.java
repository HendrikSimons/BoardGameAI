package AI;


import src.Board;
import src.Hexagon;

import java.util.ArrayList;
import java.util.Hashtable;

import static java.lang.Math.abs;

public class Strategies {



    /* class contains methods that define each strategy, like moving to the center etc.

     */

    //not covered here is the possibility of drawing from a database of openings, this would stronlgy mitigate the importance of the closingDistance strategy

    private ArrayList<String> Player = new ArrayList<String>;
    private ArrayList<String> Opponent = new ArrayList<String>;
    private String Center = "E5";

    public Strategies(Hashtable<String, Hexagon> boardState, boolean AIPlayer1){
        int Number = 1;
        if(!AIPlayer1) Number = 2;
         for(int i = 0; i<boardState.size(); i++) {
             if (!boardState.get(i).empty) {
                 if (boardState.get(Board.hash.get(i)).marble.playerNumber == Number) {
                     Player.add(Board.hash.get(i));
                 } else {
                     Opponent.add(Board.hash.get(i));
                 }
             }
         }
    }


    public double closingDistance(GameState boardState){

        //calculate Manhattan distance of each Players marble to center, high distance should return a high value, since closing in has greater priority

<<<<<<< HEAD

=======
        //PlayerDisAv : Average of the Manhattan distance
        double PlayerDisAv = 0;
        for(int i=0; i<Player.size(); i++){
            PlayerDisAv = PlayerDisAv + (abs((int) Player.get(i).substring(0,0) - (int) Center.substring(0,0)) + abs((int) Player.get(i).substring(1,1) - (int) Center.substring(1,1)));
        }
        PlayerDisAv = PlayerDisAv / Player.size();

        return PlayerDisAv;
>>>>>>> 63330b647376f177bbfc413d9416b891f07b01a1
    }



    public double cohesion(GameState boardState){

        //determine the number of neighbouring teammates for each marble for each player, take the difference
        int cohesion = 0;
        for(int i=0; i<Player.size(); i++){
            for(int j=1; j<Player.size(); j++){
                if(i!=j){
                    if( abs((int) Player.get(i).substring(0,0) - (int) Player.get(j).substring(0,0)) <2 && abs((int) Player.get(i).substring(1,1) - (int) Player.get(j).substring(1,1)) <2){
                        cohesion++;
                    }
                }
            }
        }
        return cohesion;
    }


    public double breakGroup(GameState boardState){

        /*In order to determine value for a player each marble (of this player) is checked for an opponent marble at one adjacent side of the marble and an
         opponent marble at the opposing adjacent side. Again the difference between the values for both players is calculated.*/
        int groupBreaks = 0;
        for(int i=0; i<Player.size(); i++){
            for(int j=0; j<Opponent.size(); j++){
                if( abs((int) Player.get(i).substring(0,0) - (int) Opponent.get(j).substring(0,0)) <2 && abs((int) Player.get(i).substring(1,1) - (int) Opponent.get(j).substring(1,1)) <2){
                    for(int k=0; k<Opponent.size(); k++){
                        if(k!=j && abs((int) Player.get(i).substring(0,0) - (int) Opponent.get(k).substring(0,0)) <2 && abs((int) Player.get(i).substring(1,1) - (int) Opponent.get(k).substring(1,1)) <2){
                            groupBreaks++;
                        }
                    }
                }
            }
        }
        return groupBreaks;
    }


    public double strengthenGroup(GameState boardState){


    }

    public double amountOppMarbles(GameState boardState){


    }

    public double amountOwnMarbles(GameState boardState){


    }


    //additional Strategy: checkKillMove, checks whether pushing out one opponent marble is possible without loosing own marble in subsequent Move,
    //I added this based on the findings of the paper, ie. the agent often had trouble to find that it is already in an position to score

    public void checkKillMove(GameState boardState){

    /* solve by giving a very high weight parameter and binary value for check killMove 0/1, if a kill move is available without consequences for the AI,
    checkKillMove should overvote most other possible moves
   */
    }
}
