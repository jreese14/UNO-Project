package UNO;

import UNO.Model.Card;
/**
 *Class for action cards (reverse, skip, +2)
 * @deprecated
 */
public class ActionCard extends Card {
    private int chosenColor;
    private int chosenNumber;

    /**
     * Constructor for action card that sets chosen color and chosen number
     * @param chosenColor the color to set the chosen color to
     * @param chosenNumber the number to set the chosen color to
     */
    public ActionCard(int chosenColor, int chosenNumber) {
        super(chosenColor, chosenNumber);
        this.chosenColor = chosenColor;
        this.chosenNumber = chosenNumber;
    }

}
