package UNO;

import UNO.Model.Card;

/**
 * Class for the wild card
 */
public class WildCard extends Card {
    private int chosenColor;

    public WildCard() {
        super(ALLCOLORS, WILD);
    }

    /**
     * Method for setting the color
     *
     * @param c color as an integer (defined in card)
     */
    public void setChosenColor(int c) {
        this.chosenColor = c;
    }

    /**
     * Method for getting the selected color
     *
     * @return the integer for the chosen color
     */
    public int getChosenColor() {
        return chosenColor;
    }
}
