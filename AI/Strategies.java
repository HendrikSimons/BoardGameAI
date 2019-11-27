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

    private ArrayList<String> Player = new ArrayList();
    private ArrayList<String> Opponent = new ArrayList();


    double CenterX = Board.hashBoard.get("E5").centerX;
    double CenterY = Board.hashBoard.get("E5").centerY;


    public Strategies(Hashtable<String, Hexagon> boardState, boolean AIPlayer1) {
        int Number = 1;
        if (!AIPlayer1) Number = 2;
        for (int i = 0; i < boardState.size(); i++) {
            if (!boardState.get(i).empty) {
                if (boardState.get(Board.hash.get(i)).marble.playerNumber == Number) {
                    Player.add(Board.hash.get(i));
                } else {
                    Opponent.add(Board.hash.get(i));
                }
            }
        }
    }


    public double closingDistance(GameState boardState) {

        double PlayerDisAv = 0;

        for (int i = 0; i < Player.size(); i++) {


            PlayerDisAv += Math.sqrt(Math.pow(boardState.boardState.get(Player.get(i)).centerX - CenterX, 2) + Math.pow(boardState.boardState.get(Player.get(i)).centerY - CenterY, 2));


            /* Distance to Center, use Euclidean Distance:

            (centerPointX - MarbleX )^2 + (centerPointY - MarbleY )^2 = distance^2 ;sqrt.dist^2 = distance

            */
        }
        PlayerDisAv = ( PlayerDisAv / Player.size() );

        return PlayerDisAv;
    }




    public double cohesion(GameState boardState) {

        //determine the number of neighbouring teammates for each marble for each player, take the difference
        int cohesion = 0;
        for (int i = 0; i < Player.size(); i++) {
            for (int j = 1; j < Player.size(); j++) {
                if (i != j) {
                    if (abs((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)) < 2 && abs((int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1)) < 2) {
                        cohesion++;
                    }
                }
            }
        }
        return cohesion;
    }


    public double breakGroup(GameState boardState) {

        /*In order to determine value for a player each marble (of this player) is checked for an opponent marble at one adjacent side of the marble and an
         opponent marble at the opposing adjacent side. Again the difference between the values for both players is calculated.*/
        double groupBreaks = 0;
        for (int i = 0; i < Player.size(); i++) {
            for (int j = 0; j < Opponent.size(); j++) {
                if (abs((int) Player.get(i).charAt(0) - (int) Opponent.get(j).charAt(0)) < 2 && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(j).charAt(1)) < 2) {
                    a:
                    for (int k = 0; k < Opponent.size(); k++) {
                        if (k != j && abs((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == abs((int) Player.get(i).charAt(0) - (int) Opponent.get(j).charAt(0)) && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == abs((int) Player.get(i).charAt(1) - (int) Opponent.get(j).charAt(1))) {
                            groupBreaks++;
                            break a;
                        }
                    }
                }
            }
        }
        return groupBreaks / 2;
    }


    public double strengthenGroup(GameState boardState) {
        double groupStrengh = 0;
        for (int i = 0; i < Player.size(); i++) {
            for (int j = 1; j < Player.size(); j++) {
                //if we have a neighbor
                if (abs((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)) < 2 && abs((int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1)) < 2) {
                    a:
                    for (int k = 0; k < Opponent.size(); k++) {

                        for (int k = 0; k < Opponent.size(); k++) {
                            if (k != j && abs((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == abs((int) Player.get(i).charAt(0) - (int) Player.get(j).charAt(0)) && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == abs((int) Player.get(i).charAt(1) - (int) Player.get(j).charAt(1))) {
                                for (int m = 1; m < Opponent.size(); m++) {
                                    if (m != k && abs((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == abs((int) Opponent.get(k).charAt(0) - (int) Opponent.get(m).charAt(0)) && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == abs((int) Opponent.get(k).charAt(1) - (int) Opponent.get(m).charAt(1))) {
                                        for (int n = 1; n < Player.size(); n++) {
                                        }else{
                                            groupStrengh++;
                                            break a;
                                            //found a marble to maybe push needs checking
                                            boolean possible = true;
                                            for (int m = 1; m < Opponent.size(); m++) {
                                                if (m != k && abs((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == abs((int) Opponent.get(k).charAt(0) - (int) Opponent.get(m).charAt(0)) && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == abs((int) Opponent.get(k).charAt(1) - (int) Opponent.get(m).charAt(1))) {
                                                    //can we still push?
                                                    possible = false;
                                                    for (int n = 1; n < Player.size(); n++) {
                                                        if (n != i && n != j && abs((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == abs((int) Player.get(j).charAt(0) - (int) Player.get(n).charAt(0)) && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == abs((int) Player.get(j).charAt(1) - (int) Player.get(n).charAt(1))) {
                                                            //we have three marbles. does the Opponent as well?
                                                            boolean possible2 = true;
                                                            for (int p = 1; p < Opponent.size(); p++) {
                                                                if (p != m && p != k && abs((int) Player.get(i).charAt(0) - (int) Opponent.get(k).charAt(0)) == abs((int) Opponent.get(m).charAt(0) - (int) Opponent.get(p).charAt(0)) && abs((int) Player.get(i).charAt(1) - (int) Opponent.get(k).charAt(1)) == abs((int) Opponent.get(m).charAt(1) - (int) Opponent.get(p).charAt(1))) {
                                                                    possible2 = false;
                                                                }
                                                            }
                                                            if (possible2) groupStrengh++;
                                                        }
                                                    }
                                                }
                                            }
                                            if (possible) groupStrengh++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public int amountOppMarbles(GameState boardState, boolean AIPlayer1) {
        int Number = 1;
        if (!AIPlayer1) Number = 2;
        ArrayList<String> Player = new ArrayList();
        ArrayList<String> Opponent = new ArrayList();

        int opponentCounterOld = 0;

        for (int i = 0; i < boardState.oldGamestate.boardState.size(); i++) {
            if (!boardState.oldGamestate.boardState.get(i).empty) {
                if (boardState.oldGamestate.boardState.get(Board.hash.get(i)).marble.playerNumber == Number) {
                    Player.add(Board.hash.get(i));

                } else {
                    Opponent.add(Board.hash.get(i));
                    opponentCounterOld++;
                }
            }
        }
        int differenceOppMarbles = opponentCounterOld - Opponent.size();


        return differenceOppMarbles;
    }

    public int amountOwnMarbles(GameState boardState, boolean AIPlayer1) {
        int Number = 1;
        if (!AIPlayer1) Number = 2;
        ArrayList<String> Player = new ArrayList();
        ArrayList<String> Opponent = new ArrayList();

        int ownCounterOld = 0;

        for (int i = 0; i < boardState.oldGamestate.boardState.size(); i++) {
            if (!boardState.oldGamestate.boardState.get(i).empty) {
                if (boardState.oldGamestate.boardState.get(Board.hash.get(i)).marble.playerNumber == Number) {
                    Player.add(Board.hash.get(i));
                    ownCounterOld++;

                } else {
                    Opponent.add(Board.hash.get(i));

                }
            }
        }
        int differenceOwnMarbles = ownCounterOld - Player.size();


        return differenceOwnMarbles;

    }


    //additional Strategy: checkKillMove, checks whether pushing out one opponent marble is possible without loosing own marble in subsequent Move,
    //I added this based on the findings of the paper, ie. the agent often had trouble to find that it is already in an position to score

    public void checkKillMove(GameState boardState) {

    /* solve by giving a very high weight parameter and binary value for check killMove 0/1, if a kill move is available without consequences for the AI,
    checkKillMove should overvote most other possible moves
   */
    }
}


