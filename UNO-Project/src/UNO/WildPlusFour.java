package UNO;

import UNO.Model.Card;
/**
 * Class for Wild Plus Four Cards
 */
public class WildPlusFour extends Card {
    private int chosenColor;

    public WildPlusFour() {
        super(ALLCOLORS,PLUSFOUR);
    }

    /**
    * Method for setting the color
    * @param c is the chosen color as an integer (defined in card)
    */
    public void setChosenColor(int c){
        this.chosenColor = c;
    }

    /**
    *  Method for getting the selected color
    *  @return the integer for the chosen color
    */
    public int getChosenColor() {
        return chosenColor;
    }


}
