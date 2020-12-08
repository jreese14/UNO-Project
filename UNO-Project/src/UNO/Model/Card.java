package UNO.Model;

import UNO.UNO;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Class to represent a card with a color and number
 */
public class Card {

    /**
     * The color of card
     */
    protected int color; // 1 red, 2 blue, 3 green, 4 yellow, 5 all colors

    /**
     * The number of card
     */
    protected int number; // 0 to 9 number cards, 10 skip, 11 reverse, 12 plus two, 13 wild, 14 plus four

    /**
     * The 4 different colors represented as integers
     */
    public static final int RED = 1;
    public static final int BLUE = 2;
    public static final int GREEN = 3;
    public static final int YELLOW = 4;

    /** Wild Cards */
    protected static final int ALLCOLORS = 5;

    /** Skip */
    static final int SKIP = 10;

    /** Reverse */
    static final int REVERSE = 11;

    /**
     * Plus Two
     */
    static final int PLUSTWO = 12;

    /**
     * Wild Card
     */
    public static final int WILD = 13;

    /**
     * Wild Card Plus Four
     */
    public static final int PLUSFOUR = 14;

//    private int chosenColor;

    /**
     * Constructor for default
     */
    public Card() {

    }

    /**
     * Constructor for a card with a color and number
     *
     * @param color  the color for the card
     * @param number the number for the card
     */
    public Card(int color, int number) {
        this.color = color;
        this.number = number;
    }

    /**
     * Method for getting the color of the card
     *
     * @return the color of the card
     */
    public int getColor() {
        return color;
    }

    /**
     * Method for getting the number of the card
     *
     * @return the number of the card
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number of the card
     *
     * @param number the number you are setting the card
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Method for setting the color of the card
     *
     * @param color the color you are setting the card to
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Testing Method for checking the color of a card
     *
     * @return the color of the card
     */
    public String getColorName() {
        // 1 red, 2 blue, 3 green, 4 yellow, 5 all colors
        if (color == RED) {
            return "Red";
        } else if (color == BLUE) {
            return "Blue";
        } else if (color == GREEN) {
            return "Green";
        } else if (color == YELLOW) {
            return "Yellow";
        }
        return "Any Color";
    }


//    public void setChosenColor(int c){
//        this.chosenColor = c;
//
//    }
//    public int getChosenColor() {
//        return chosenColor;
//    }
//

    /**
     * Testing method for getting a string of the color and number for the card
     *
     * @return the color and number of the card
     */
    @Override
    public String toString() {
        return getColorName() + " " + number;
    }
}