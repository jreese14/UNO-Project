package UNO.View;

import UNO.ClickMessage;
import UNO.Model.Card;
import UNO.WildCard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

/**
 * Class for visual aspects of the cards itself along with methods to make these aspects
 */
public class CardView extends JPanel {
    private Color cardColor = Color.BLACK;

    Dimension SMALL = new Dimension(100, 150);
    private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
    private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);
    Card card = new Card();
    public int width = SMALL.width;
    public int height = SMALL.height;
    Color newYellow = new Color(253, 212, 22);
    Color newBlue = new Color(7, 72, 157);
    Color newRed = new Color(190, 15, 2);
    Color newGreen = new Color(51, 134, 20);
    // Color.RED
    View view;
    JLabel notifyColor;

    private boolean faceDown = false;

    /**
     * Constructor for a cardview which is for a visual representation of a card
     * @param card is the card to represent visually
     * @param v is the view where the card is
     */
    public CardView(Card card, View v) {
        this.view = v;
        if (card.getColor() == Card.RED) {
            this.cardColor = newRed;
        } else if (card.getColor() == Card.BLUE) {
            this.cardColor = newBlue;
        } else if (card.getColor() == Card.GREEN) {
            this.cardColor = newGreen;
        } else if (card.getColor() == Card.YELLOW) {
            this.cardColor = newYellow;
        } else {
            this.cardColor = Color.BLACK;
        }
        this.setPreferredSize(SMALL);
        this.setBorder(defaultBorder);

        this.addMouseListener(new UNO.View.CardView.MouseHandler());

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // CardView _this = (CardView) e.getSource();

                // System.out.println(card.getNumber());

                if (card.getNumber() == 13 || card.getNumber() == 14) {
                    JPopupMenu popup = new JPopupMenu();
                    JButton color1 = new JButton("RED");
                    JButton color2 = new JButton("BLUE");
                    JButton color3 = new JButton("GREEN");
                    JButton color4 = new JButton("YELLOW");

                    color1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            int color = 1;
                            if(card.getNumber() == 13){
                                ((WildCard)card).setChosenColor(card.RED);
                            }
                            else{
                                ((UNO.WildPlusFour)card).setChosenColor(card.RED);
                            }

                            System.out.println("USER CHOOSES RED");
                            notifyColor = new JLabel();
//                            popup.removeAll();
//                            notifyColor.setText("Color = red");
//                            popup.add(notifyColor);
                            popup.setVisible(false);
//                            notifyColor.setText("Color = red");
//                            notifyColor.setForeground(newRed);
//                            notifyColor.setBounds(600, 400, 50, 50);
//                            view.add(notifyColor);
                            //view.model.aiPlayerTurn();
                        }
                    });

                    color2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            int color = 2;
                            if(card.getNumber() == 13){
                                ((WildCard)card).setChosenColor(card.BLUE);
                            }
                            else{
                                ((UNO.WildPlusFour)card).setChosenColor(card.BLUE);
                            }

                            System.out.println("USER CHOOSES BLUE");
                            popup.setVisible(false);
                        }
                    });

                    color3.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            int color = 3;
                            if(card.getNumber() == 13){
                                ((WildCard)card).setChosenColor(card.GREEN);
                            }
                            else{
                                ((UNO.WildPlusFour)card).setChosenColor(card.GREEN);
                            }

                            System.out.println("USER CHOOSES GREEN");
                            popup.setVisible(false);
                        }
                    });

                    color4.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            int color = 4;
                            if(card.getNumber() == 13){
                                ((WildCard)card).setChosenColor(card.YELLOW);
                            }
                            else{
                                ((UNO.WildPlusFour)card).setChosenColor(card.YELLOW);
                            }
                            System.out.println("USER CHOOSES YELLOW");
                            popup.setVisible(false);
                        }
                    });

                    popup.add(color1);
                    popup.add(color2);
                    popup.add(color3);
                    popup.add(color4);


                    popup.setSize(400, 275);

                    popup.setLocation(400, 400);
                    popup.setVisible(true);

                }
                try {
                    System.out.println("Clicked card view: " + card);
                    ClickMessage msg = new ClickMessage(card);
                    // System.out.println(view);
                    view.queue.put(new ClickMessage(card));
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                revalidate();
            }

        });
        this.card = card;
    }
    /**
     * Method for setting the position for the card
     *
     * @param x is the x coord
     * @param y is the y coord
     */
    public void setPosition(int x, int y) {
        setBounds(x, y, width, height);
    }

    /**
     * Method for coloring the card
     *
     * @param g is the variable for graphics component
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, width, height);

        int margin = 2;
        if (!faceDown) {
            g2.setColor(cardColor);
        } else {
            g2.setColor(Color.black);
        }
        g2.fillRect(margin, margin, width - 2 * margin, height - 2 * margin);

        g2.setColor(Color.white);
        AffineTransform org = g2.getTransform();
        g2.rotate(Math.toRadians(33), width * 10 / 6, height * 10 / 12);

        g2.fillOval(0, height / 2, width * 4 / 5, height * 20 / 21);
        g2.setTransform(org);

        if (!faceDown) {
            g2.setColor(cardColor);
        } else {
            g2.setColor(newRed);
        }
        AffineTransform org2 = g2.getTransform();
        g2.rotate(Math.toRadians(33), width * 10 / 6, height * 6 / 7 + 3);

        g2.fillOval(0, height / 2 + 3, width * 6 / 8, height * 10 / 11 - 2);
        g2.setTransform(org2);

        if (faceDown) {
            Font defaultFont = new Font("Helvetica", Font.BOLD, width /3 );
            FontMetrics fm = this.getFontMetrics(defaultFont);
            int StringWidth = defaultFont.getSize() / 2;
            int FontHeight = defaultFont.getSize() / 2;
            g2.setFont(defaultFont);
            g2.setColor(Color.BLACK);
            drawRotate(g2, 10, 98, 350, "UNO");

            defaultFont = new Font("Helvetica", Font.BOLD, width /3 );
            g2.setFont(defaultFont);
            g2.setColor(newYellow);
            drawRotate(g2, 15, 98, 350, "UNO");

            //g2.drawString("UNO", width / 2 - StringWidth *2, height / 2 + FontHeight-10);

            return;
        }

        // Value in the center
        Font defaultFont = new Font("Helvetica", Font.BOLD, width / 2 + 5);
        FontMetrics fm = this.getFontMetrics(defaultFont);
        int StringWidth = fm.stringWidth(String.valueOf(card.getNumber())) / 2;
        int FontHeight = defaultFont.getSize() / 3;

        g2.setColor(Color.BLACK);
        g2.setFont(defaultFont);
        if (card.getNumber() < 10) {
            g2.drawString(String.valueOf(card.getNumber()), width / 2 - StringWidth * 4 / 3, height / 2 + FontHeight);
        } else if (card.getNumber() == 10) {
            g2.drawString("⊘", width / 2 - StringWidth * 9 / 8, height / 2 + FontHeight);
        } else if (card.getNumber() == 11) {
            g2.drawString("R", width / 2 - StringWidth * 6 / 5, height / 2 + FontHeight);
        } else if (card.getNumber() == 12) {
            g2.drawString("+2", width / 2 - StringWidth * 6 / 5, height / 2 + FontHeight);
        } else if (card.getNumber() == 13) {
            g2.drawString("W", width / 2 - StringWidth, height / 2 + FontHeight);
        } else if (card.getNumber() == 14) {
            g2.drawString("+4", width / 2 - StringWidth * 6 / 5, height / 2 + FontHeight);
        }

        defaultFont = new Font("Helvetica", Font.BOLD, width / 2 + 5);
        fm = this.getFontMetrics(defaultFont);
        StringWidth = fm.stringWidth(String.valueOf(card.getNumber())) / 2;
        FontHeight = defaultFont.getSize() / 3;

        g2.setColor(Color.WHITE);
        g2.setFont(defaultFont);
        if (card.getNumber() < 10) {
            g2.drawString(String.valueOf(card.getNumber()), width / 2 - StringWidth, height / 2 + FontHeight);
        } else if (card.getNumber() == 10) {
            g2.drawString("⊘", width / 2 - StringWidth, height / 2 + FontHeight);
        } else if (card.getNumber() == 11) {
            g2.drawString("R", width / 2 - StringWidth, height / 2 + FontHeight);
        } else if (card.getNumber() == 12) {
            g2.drawString("+2", width / 2 - StringWidth, height / 2 + FontHeight);
        } else if (card.getNumber() == 13) {
            g2.drawString("W", width / 2 - StringWidth, height / 2 + FontHeight);
            ;
        } else if (card.getNumber() == 14) {
            g2.drawString("+4", width / 2 - StringWidth, height / 2 + FontHeight);
        }

        // Value in upper left corner
        defaultFont = new Font("Helvetica", Font.ITALIC, width / 5);
        fm = this.getFontMetrics(defaultFont);
        StringWidth = fm.stringWidth(String.valueOf(card.getNumber())) / 2;
        FontHeight = defaultFont.getSize() / 3;

        g2.setColor(Color.white);
        g2.setFont(defaultFont);

        if (card.getNumber() < 10) {
            g2.drawString(String.valueOf(card.getNumber()), 2 * margin, 9 * margin);
        } else if (card.getNumber() == 10) {
            g2.drawString("⊘", 2 * margin, 9 * margin);
        } else if (card.getNumber() == 11) {
            g2.drawString("Rev", 2 * margin, 9 * margin);
        } else if (card.getNumber() == 12) {
            g2.drawString("+2", 2 * margin, 9 * margin);
        } else if (card.getNumber() == 13) {
            g2.drawString("WILD", 2 * margin, 9 * margin);
        } else if (card.getNumber() == 14) {
            g2.drawString("+4", 2 * margin, 9 * margin);
        }

        // Value in bottom right corner
        defaultFont = new Font("Helvetica", Font.ITALIC, -width / 5);
        fm = this.getFontMetrics(defaultFont);
        StringWidth = fm.stringWidth(String.valueOf(card.getNumber())) / 2;
        FontHeight = defaultFont.getSize() / 3;

        g2.setColor(Color.white);
        g2.setFont(defaultFont);

        if (card.getNumber() < 10) {
            g2.drawString(String.valueOf(card.getNumber()), 45 * margin, 60 * margin);
        } else if (card.getNumber() == 10) {
            g2.drawString("⊘", 45 * margin, 60 * margin);
        } else if (card.getNumber() == 11) {
            g2.drawString("Rev", 49 * margin, 60 * margin);
        } else if (card.getNumber() == 12) {
            g2.drawString("+2", 45 * margin, 60 * margin);
        } else if (card.getNumber() == 13) {
            g2.drawString("WILD", 49 * margin, 60 * margin);
        } else if (card.getNumber() == 14) {
            g2.drawString("+4", 45 * margin, 60 * margin);
        }

        // else if(newCard.getNumber() == 11) {
        // g2.drawString("R",2*margin,9*margin);
        // }
        // else if(newCard.getNumber() == 12) {
        // g2.drawString("+"+String.valueOf(newCard.getNumber()),2*margin,9*margin);
        // }
        // else if(newCard.getNumber() == 13) {
        // g2.drawString("WILD",2*margin,9*margin);
        // }
        // g2.drawString("WILD" + " +4",2*margin,9*margin);
    }

    /**
     * Class for mouse events
     */
    class MouseHandler extends MouseAdapter {
        /**
         * Method for mouse entered
         *
         * @param e is the mouse event
         */
        public void mouseEntered(MouseEvent e) {
            setBorder(focusedBorder);
        }

        /**
         * Method for mouse exit
         *
         * @param e is the mouse event
         */
        public void mouseExited(MouseEvent e) {
            setBorder(defaultBorder);
        }
    }

    /**
     * Method for getting the variable
     * @return a boolean variable indicating if it is face down or not
     */
    public boolean isFaceDown() {
        return faceDown;
    }

    /**
     * Method for setting the facedown variable
     *
     * @param faceDown
     */
    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
    }

    /**
     * Method for writing text
     *
     * @param g2d   is the graphics g2d
     * @param x     is the x var
     * @param y     is the y var
     * @param angle is the angle which it needs to be at
     * @param text  is the text that is gonna be added on the card
     */
    public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text)
    {
        g2d.translate((float)x,(float)y);
        g2d.rotate(Math.toRadians(angle));
        g2d.drawString(text,0,0);
        g2d.rotate(-Math.toRadians(angle));
        g2d.translate(-(float)x,-(float)y);
    }
}
