package UNO;

import UNO.Model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * Class for creating the deck of cards and has methods with the cards that were created
 */
public class Deck {

    /**
     * Variable for the list of cards in the deck
     */
    public ArrayList<Card> list = new ArrayList<Card>();
    private static Deck obj;

    /**
     * Constructor for the deck which puts the cards into the deck
     */
    private Deck() {
        list = new ArrayList<Card>();

        for (int x = 0; x <= 1; x++) {
            for (int i = 0; i <= 12; i++) {
                for (int j = 1; j <= 4; j++) {
                    list.add(new Card(j, i));
                }
            }
        }
        for (int y = 1; y <= 4; y++) { // 4 cards of each
            list.add(new WildCard()); // 13 and 14 for number (wild, plus four)
        }
        for (int y = 1; y <= 4; y++) { // 4 cards of each
            list.add(new WildPlusFour()); // 13 and 14 for number (wild, plus four)
        }
    }
/**
     * Method for shuffling the cards
     *
     * @param cards is the arraylist of cards that need to be shuffled
     * @return the shuffled cards
     */
    private ArrayList<Card> shuffle(ArrayList<Card> cards) {
        // shuffle arraylist of cards
        Collections.shuffle(cards);
        return cards;
    }
/**
* Method for adding cards to deck
* @param cards is the arraylist of cards that need to be added
* @return the list of cards that were added
 */
    public ArrayList<Card> addToDeck(ArrayList<Card> cards) {
        ArrayList<Card> temp = new ArrayList<Card>();
        temp.addAll(list);
        temp.addAll(cards);
        return temp;
    }
/**
 * Method for dealing cards
 * @return the hand which has the seven cards that were dealt
 */
    public ArrayList<Card> dealCards() {
        Random random = new Random();
        ArrayList<Card> hand = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            int randomInt = random.nextInt(list.size());
            hand.add(list.get(randomInt));
            list.remove(randomInt);
        }
        return hand;
    }

    public static Deck getInstance()
    {
        if (obj==null)
            obj = new Deck();
        return obj;
    }

}
