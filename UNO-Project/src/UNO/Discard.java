package UNO;

import UNO.Model.Card;

import java.util.ArrayList;
/**
*Class for the discard pile and has methods to adjust this pile
 */
public class Discard {
    ArrayList<Card> discard = new ArrayList<Card>();
    int color;
/**
*Method for adding to discard
*@param card that is being discarded
 */
    public void addToDiscard(Card card) {
        discard.add(card);
    }
/**
*Method for getting the top card of the discard pile
*@return the top of the card
 */
    public Card getTopCard() {
        return discard.get(discard.size() - 1);
    }
/**
*Method for getting the discard pile
*@return the discard pile
 */
    public ArrayList<Card> getDiscard() {
        return discard;
    }
/**
*Method for setting the color of the discard pile
*@param color integer of the color we are setting it to
 */
    public void setDiscardColor(int color) {
        this.color = color;
    }

}
