package UNO;

import UNO.Model.Card;

import java.util.ArrayList;
/**
 * Class for the player and has important methods for modifying the player's hand
 */
public class   Player {
    private ArrayList<Card> hand;
    private boolean saidUno;
    private boolean turn;
    private String name;

    /**
     * Constructor for a player which is the person playing the game
     */
    public Player() {
        hand = new ArrayList<Card>();
        turn = true;
    }

    /**
     * Constructor for a player which is the person playing the game
     * @param name is the name of the player
     */
    public Player(String name) {
        super();
        this.name = name;
    }
    /**
    * Method for adding a card to the players hand
    * @param card that is being added
    */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Method for removing a card from the players hand
     * @param card that is being removed
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
    * Method for if the player has clicked the Uno button
    * @return saidUno boolean value
    */
    public boolean hasSaidUno() {
        return saidUno;
    }

/*
Method for player to set the uno button as true
 */


    public void sayUno() {
        saidUno = true;
    }
/*
Method for setting a player name
@param inputted name
 */
    public void setName(String name) {
        this.name = name;
    }
/*
Method for setting hand
@param the hand
 */
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
/*
Method to get the number of cards in the hand
@return an integer value of the number of cards left in the players hand
 */
    public int getTotalCards() {
        return hand.size();
    }
/*
Method to return the hand
@return the hand
 */

    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        return name;
    }

}
