package UNO.View;

import UNO.AIPlayer;
import UNO.ClickMessage;
import UNO.Model.Card;
import UNO.Message;
import UNO.Model.Model;
import UNO.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
/**
 * Class that has the main display output and corresponding methods
 */
public class View extends JFrame {

    BlockingQueue<Message> queue;
    Model model;
    /** draw button*/
    private JButton draw;
    /** UNO button*/
    private JButton sayUNO;

    private JLabel label;

    private ArrayList<CardView> userView;
    private ArrayList<CardView> aiView;
    private CardView discardView;
    /**
     * Constructor that sets up the buttons, display size, layout, and etc
     */
    public View(BlockingQueue<Message> queue, Model model) {
        this.model = model;
        this.queue = queue;

        userView = new ArrayList<CardView>();
        aiView = new ArrayList<CardView>();

        getContentPane().setBackground(new Color(62,168,238));
        setLayout(null);
        setPreferredSize(new Dimension(1000, 800));

        draw = new JButton("Draw");
        sayUNO = new JButton("UNO");
        label = new JLabel();
        label.setText("UNO!");


        draw.setBounds(800, 450, 100, 50);
        sayUNO.setBounds(800, 510, 100, 50);
        label.setForeground(Color.red);
        label.setBounds(600, 400, 50, 50);


        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // add one card to player hand
                model.drawCard(model.user);
                update();



            }
        });
        sayUNO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                // set saidUNO to true, no penalty
                model.user.sayUno();
                System.out.println("USER SAYS UNO");

            }
        });

        add(draw);
        add(sayUNO);
        //add(label);
        setTitle("UNO!");
        setVisible(true);
        setResizable(true);
        // setLocation(200, 100);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        update();
        gameOver();
    }

    /**
     * Method that displays the hand of the player on the panel
     * @param player object
     */
    public void displayHand(Player player) {

        ArrayList<CardView> handView;

        if (player instanceof AIPlayer) {
            handView = aiView;
        } else {
            handView = userView;
        }

        for (CardView cardView : handView) {
            remove(cardView);
        }

        int x = 0;
        int y = 600;

        if (player instanceof AIPlayer) {
            y = 200;
        }

        for (Card card : player.getHand()) {
            CardView cardView = new CardView(card, this);
            cardView.setPosition(x, y);
            if (player instanceof AIPlayer) {
                cardView.setFaceDown(true);
            }
            handView.add(cardView); // add to hand CardViews array
            add(cardView); // add to panel
            x += cardView.width;
        }

    }


    /**
    Method to display the top card of discard
    @param card
     */
    public void displayDiscard(Card card) {

        if (discardView != null) {
            remove(discardView);
        }

        int x = 400;
        int y = 400;
        discardView = new CardView(card, this);
        discardView.setPosition(x, y);
        add(discardView);
        discardView.repaint();
    }
/**
Method to update/repaint the display
 */
    public void update() {
        displayDiscard(model.discard.getTopCard());
        displayHand(model.user);
        displayHand(model.ai);
        repaint();

    }
/**
Method to remove everything on the dispplay and replace it with game over when the game is over
 */
    public void gameOver(){
        if(model.isOver()){
            removeAll();
            label.setText("GAME OVER");
            add(label);
        }
    }

}
