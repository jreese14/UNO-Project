package UNO.Model;

import UNO.*;
import UNO.View.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * Represents the game
 */
public class Model {
    public Player user = new Player("User");
    public AIPlayer ai = new AIPlayer("AI Player");
    public Discard discard = new Discard();

    Deck deck = Deck.getInstance();
    boolean usersTurn;
    private boolean isOver;
    public int winner;

    public Model() {
        user.setHand(deck.dealCards());
        ai.setHand(deck.dealCards());

        isOver = false;
        initDiscardPile();
    }


    public void switchTurns() {
        usersTurn = !usersTurn;
    }

    /**
     * Method for drawing a card from the deck and putting it in the players hand
     * @param player represents the person playing the game
     */
    public void drawCard(Player player) {
        Random random = new Random();
        int randomInt = random.nextInt(deck.list.size());
        player.addCard(deck.list.get(randomInt));
        deck.list.remove(randomInt);
    }


    /**
     * Method for drawing two cards to the players hand from the deck
     * @param player represents the person playing the game
     */
    public void drawTwo(Player player) {
        drawCard(player);
        drawCard(player);
    }

    /**
     * Method for drawing four cards to the players hand from the deck
     * @param player represents the person playing the game
     */
    public void drawFour(Player player) {
        drawTwo(player);
        drawTwo(player);
    }

    /**
     * Method for seeing if the game is over by checking if the one of the hands is empty
     * @return boolean representing whether the game is over yet
     */
    public boolean isOver() {
        if (user.getTotalCards() == 0 || ai.getTotalCards() == 0) {
            isOver = true;
        }
        return isOver;
    }

    /**
     * Method to check if the player won or the computer won
     * @return boolean that is true if player won and false if the computer won
     */
    public boolean gameWon() {
        if (user.getTotalCards() == 0) {
            System.out.println("You have won the game!");
            return true;
        } else if (ai.getTotalCards() == 0) {
            System.out.println("PC has won the game!");
            return true;
        }
        return false;
    }

    /**
     * Method to set the first card for the discard pile at the start of the game
     */
    public void initDiscardPile() {

        Random random = new Random(); // random generator
        int randomInt = random.nextInt(deck.list.size());
        Card card = deck.list.get(randomInt);// get a random card
        while (card.getColor() == 5 || card.getNumber() == 10 || card.getNumber() ==11 || card.getNumber() == 12) { // while the selected card is wild
            randomInt = random.nextInt(deck.list.size()); // get a new random index
            card = deck.list.get(randomInt); // get the new card
        }

        discard.addToDiscard(card); // add the card to discard
        deck.list.remove(card); // remove from deck
        // discard.addToDiscard(deck.list.get(deck.list.size()-1));
    }

    /**
     * Method to add the played card to the discard pile
     * @param player represents the person playing the game
     * @param card represents the card that is being played
     */
    public void addToDiscardPile(Player player, Card card) {
        System.out.println("Card can be played!");
        discard.addToDiscard(card);
        player.removeCard(card);
        if(player instanceof AIPlayer){
            if((card.getNumber() == 10) || (card.getNumber() == 11)){
                aiPlayerTurn();
            }
            if(card.getNumber() == 12){
                drawTwo(user);
            }
            if(card.getNumber() == 14){
                drawFour(user);
            }

        }
        else{
            aiPlayerTurn();
            if(card.getNumber() == 12){
                drawTwo(ai);
            }
            if(card.getNumber() == 14){
                drawFour(ai);
            }
        }

    }

    /**
     * Method for the user to play a card
     * @param card represents the card the player is playing
     * @return true if the card can be played by the user and false if the player cannot play the card
     */
    public boolean playCard(Card card) {
        return playCard(user, card);
    }

    // returns whether the player can play the card or not

    /**
     * Method for checking whether the player can play the card
     * @param player represents the person playing the game
     * @param card represents the card the person wants to play
     * @return true if the player can play the card and false if they cannot play the card
     */
    public boolean playCard(Player player, Card card) {

        System.out.println(player + " is attempting to play a card");

        Card topCard = discard.getTopCard();
        System.out.println("Discard Pile Top Card: " + topCard);
        System.out.println("Card to Play: " + card);
        // Color or number/symbol matches
        if ((topCard.getColor() == card.getColor()) || (topCard.getNumber() == card.getNumber())) {
            addToDiscardPile(player, card);
            return true;
        }
        // if card matches chosen wild card color
        else if(topCard.getNumber() == Card.WILD){
            boolean match = ((WildCard) topCard).getChosenColor() == card.getColor();
            if(match){
                addToDiscardPile(player, card);
            }
            else {
                System.out.println("NOT A WILD CARD COLOR MATCH!");
            }
            return match;
        }
        else if(topCard.getNumber() == Card.PLUSFOUR){
            boolean match = ((WildPlusFour) topCard).getChosenColor() == card.getColor();
            if(match){
                addToDiscardPile(player, card);
            }
            else {
                System.out.println("NOT A WILD CARD COLOR MATCH!");
            }
            return match;
        }

//        else if ((topCard.getNumber() == Card.WILD) || (topCard.getNumber() == Card.PLUSFOUR)) {
//            boolean match = ((WildCard) topCard).getChosenColor() == card.getColor()
//                    || ((WildPlusFour) topCard).getChosenColor() == card.getColor();
//            if (match) {
//                addToDiscardPile(player, card);
//            }
//            // System.out.println("MATCH: TRUE");
//            return match;
//            // return ((WildCard) topCard).getChosenColor() == newCard.getColor()
//            // ||((WildPlusFour) topCard).getChosenColor() == newCard.getColor() ;
//        }
        // new card is a wild card/wild+4 (don't have to match card)
        else if ((card.getNumber() == Card.WILD) || (card.getNumber() == Card.PLUSFOUR)) {
            addToDiscardPile(player, card);
            return true;
        } else {
            System.out.println("Card can not be played.");
            return false;
        }
    }

    /**
     * Method for the computer's turn
     * @return the card that the computer is playing
     */
    public Card aiPlayerTurn() {
        ArrayList<Card> hand = ai.getHand();
        boolean canPlay = false;
        Card cardPlayed = null;
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if(card.getNumber() == 13){
                Random random = new Random();
                int num = random.nextInt(4-1+1)+1;
                ((WildCard) card).setChosenColor(num);
                System.out.println("AI CHOOSES "+String.valueOf(num));
            }
            else if(card.getNumber() == 14){
                Random random = new Random();
                int num = random.nextInt(4-1+1)+1;
                ((WildPlusFour) card).setChosenColor(random.nextInt(4-1+1)+1);
                System.out.println("AI CHOOSES "+String.valueOf(num));
            }
            if (playCard(ai, card) == true) {
                //hand.remove(card);
                canPlay = true;
                cardPlayed = card;
                break;
            }
        }
        while (!canPlay) {
//            for(int i = 0; i < deck.list.size(); i++){
//                System.out.print(i + ". " + deck.list.get(i) + " ");
//            }
            System.out.println(deck.list.size());
            hand.add(deck.list.get(deck.list.size() - 1));
            deck.list.remove(deck.list.size() - 1);
        }
        if (ai.getTotalCards() == 1) {
            ai.sayUno();
        }
        // if match == null -> skip turn and dont display card
        return cardPlayed;
    }



}
