package UNO.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**Class for card test
 */
class CardTest {
    /**
     * Method for getting the color of the card
     */
    @Test
    void getColor() {
        Card card = new Card(2,5);
        assertEquals(2,card.getColor());
    }

    /**
     * Method for getting the number of the card
     */
    @Test
    void getNumber() {
        Card card = new Card(2,5);
        assertEquals(5,card.getNumber());
    }

}