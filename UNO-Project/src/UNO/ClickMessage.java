package UNO;

import UNO.Model.Card;
import UNO.View.CardView;

/**
 * Class for Clickmessage
 */
public class ClickMessage extends Message {
    Card card;

    public ClickMessage(Card card) {
        this.card = card;
    }

    /**
     * Method to get the card
     *
     * @return card
     */
    public Card getCard() {
        return card;
    }

}
