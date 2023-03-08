package controller;

import model.Model;
import view.frames.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
/**
 * Controller for the StartFrame.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class StartFrameController {

    //PLAYER DETAILS
    private String p1Name; //player 1 name
    private String p2Name; //player 2 name
    private Color p1Color = new Color(136, 4, 4); //player 1 color (default : red)
    private Color p2Color = new Color(40, 38, 112); //player 2 color (default : blue)
    private int p1Rank; //rank of player 1's chosen animal card
    private int p2Rank; //rank of player 1's chosen animal card

    //CARDS WILL BE ENABLED ONLY IF ALL OF THESE ARE TRUE
    private boolean p1NC = false; //if set to true, player 1 has set his name
    private boolean p2NC = false; //if set to true, player 2 has set his name
    private boolean p1CC = false; //if set to true, player 1 has set his color
    private boolean p2CC = false; //if set to true, player 2 has set his color

    //card image icons
    private ImageIcon mouse, cat, wolf, dog, leopard, tiger, lion, elephant, ac;

    //for shuffling
    private ArrayList<String> listAnimal = new ArrayList<>(Arrays.asList("Mouse", "Cat", "Wolf", "Dog",
            "Leopard", "Tiger", "Lion", "Elephant"));

    int randomIndex = 0; //placeholder for the index of the random element
                         //to be drawn from the array list

    int actionCtr = 0; //counts how many cards have been unfolded

    //initial placeholder for ranks
    //used to prevent unfolding two similar cards
    int rank1;
    int rank2;

    /**
     * Constructor of StartFrameController.
     *
     * @param startFrame - StartFrame
     * @param playBtn - JButton to begin game
     * @param p1Done - determines if player 1 has confirmed choices
     * @param p2Done - determines if player 2 has confirmed choices
     * @param p1N - player 1 name
     * @param p2N - player 2 name
     * @param p1SColorBtn - player 1 chosen color
     * @param p2SColorBtn - player 2 chosen color
     * @param p1DColorBtn - player 1 confirmed color
     * @param p2DColorBtn - player 2 confirmed color
     * @param c1 - card for randomized feature
     * @param c2 - card for randomized feature
     * @param c3- card for randomized feature
     * @param c4- card for randomized feature
     * @param c5- card for randomized feature
     * @param c6- card for randomized feature
     * @param c7- card for randomized feature
     * @param c8- card for randomized feature
     */
    public StartFrameController(JFrame startFrame, JButton playBtn,
                                JButton p1Done, JButton p2Done,
                                JTextField p1N, JTextField p2N,
                                JButton p1SColorBtn, JButton p2SColorBtn,
                                JButton p1DColorBtn, JButton p2DColorBtn,
                                JButton c1, JButton c2, JButton c3, JButton c4,
                                JButton c5, JButton c6, JButton c7, JButton c8) {

        //ACTION LISTENERS
        //for name check buttons
        ActionListener p1NListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == p1Done) {
                    p1NC = true;
                    p1Done.setEnabled(false);
                    p1N.setEditable(false);
                    if(p1N.getText().equals("SET NAME")) {
                        p1N.setText("Player 1");
                    }
                    p1Name = p1N.getText();

                    if(p1NC && p2NC & p1CC & p2CC) {
                        c1.setEnabled(true);
                        c2.setEnabled(true);
                        c3.setEnabled(true);
                        c4.setEnabled(true);
                        c5.setEnabled(true);
                        c6.setEnabled(true);
                        c7.setEnabled(true);
                        c8.setEnabled(true);
                        JOptionPane.showMessageDialog(null, p1Name + " choose a card.");
                    }
                }
            }
        };

        ActionListener p2NListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == p2Done) {
                    p2NC = true;
                    p2Done.setEnabled(false);
                    p2N.setEditable(false);
                    if (p2N.getText().equals("SET NAME")) {
                        p2N.setText("Player 2");
                    }
                    p2Name = p2N.getText();
                    if(p1NC && p2NC & p1CC & p2CC) {
                        c1.setEnabled(true);
                        c2.setEnabled(true);
                        c3.setEnabled(true);
                        c4.setEnabled(true);
                        c5.setEnabled(true);
                        c6.setEnabled(true);
                        c7.setEnabled(true);
                        c8.setEnabled(true);
                        JOptionPane.showMessageDialog(null, p1Name + " choose a card.");
                    }
                }
            }
        };

        //for color chooser buttons
        ActionListener p1ColorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == p1SColorBtn) {
                    new JColorChooser();
                    p1Color = JColorChooser.showDialog(null, p1Name + "'s Color", Color.black);
                    p1SColorBtn.setText("CHANGE");
                    p1SColorBtn.setBackground(p1Color);

                    if(p1Color.equals(Color.BLACK)) {
                        p1SColorBtn.setForeground(Color.WHITE);
                    } else {
                        p1SColorBtn.setForeground(new Color(0, 0, 0));
                    }
                }
            }
        };

        ActionListener p2ColorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == p2SColorBtn) {
                    new JColorChooser();
                    p2Color = JColorChooser.showDialog(null, p1Name + "'s Color", Color.black);
                    p2SColorBtn.setBackground(p2Color);
                    p2SColorBtn.setText("CHANGE");

                    if(p2Color.equals(new Color(0, 0, 0))) {
                        p2SColorBtn.setForeground(new Color(255, 255, 255));
                    } else {
                        p2SColorBtn.setForeground(new Color(0, 0, 0));
                    }
                }
            }
        };

        //for color check buttons
        ActionListener p1DListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == p1DColorBtn) {
                    p1CC = true;
                    p1DColorBtn.setEnabled(false);
                    p1SColorBtn.setEnabled(false);
                    p1SColorBtn.setBackground(p1Color);
                    p1SColorBtn.setText("");
                    if(p1Color == new Color(136, 4, 4)) {
                        p1SColorBtn.setText("DEFAULT");
                    }
                    if(p1NC && p2NC & p1CC & p2CC) {
                        c1.setEnabled(true);
                        c2.setEnabled(true);
                        c3.setEnabled(true);
                        c4.setEnabled(true);
                        c5.setEnabled(true);
                        c6.setEnabled(true);
                        c7.setEnabled(true);
                        c8.setEnabled(true);
                        JOptionPane.showMessageDialog(null, p1Name + " choose a card.");
                    }
                }
            }
        };

        ActionListener p2DListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == p2DColorBtn) {
                    p2CC = true;
                    p2DColorBtn.setEnabled(false);
                    p2SColorBtn.setEnabled(false);
                    p2SColorBtn.setBackground(p2Color);
                    p2SColorBtn.setText("");
                    if(p2Color == new Color(40, 38, 112)) {
                        p2SColorBtn.setText("DEFAULT");
                    }
                    if(p1NC && p2NC & p1CC & p2CC) {
                        c1.setEnabled(true);
                        c2.setEnabled(true);
                        c3.setEnabled(true);
                        c4.setEnabled(true);
                        c5.setEnabled(true);
                        c6.setEnabled(true);
                        c7.setEnabled(true);
                        c8.setEnabled(true);
                        JOptionPane.showMessageDialog(null, p1Name + " choose a card.");
                    }
                }
            }
        };

        //for all the cards
        ActionListener c1Listener = new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c1) {
                    flipCard(c1, getRandomElement());
                    c1.setDisabledIcon(c1.getIcon());
                    c1.setEnabled(false);
                    actionCtr++;

                    do {
                        if(actionCtr == 1) {
                            rank1 = getCardRank(c1);
                        } else if(actionCtr == 2) {
                            rank2 = getCardRank(c1);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c1);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else if(actionCtr == 2) {
                        p2Rank = getCardRank(c1);
                        disableRemainingCards(c2, c3, c4, c5, c6, c7, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c2Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c2) {
                    flipCard(c2, getRandomElement());
                    c2.setDisabledIcon(c2.getIcon());
                    c2.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c2);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c2);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c2);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c2);
                        disableRemainingCards(c1, c3, c4, c5, c6, c7, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c3Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c3) {
                    flipCard(c3, getRandomElement());
                    c3.setDisabledIcon(c3.getIcon());
                    c3.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c3);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c3);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c3);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c3);
                        disableRemainingCards(c1, c2, c4, c5, c6, c7, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c4Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c4) {
                    flipCard(c4, getRandomElement());
                    c4.setDisabledIcon(c4.getIcon());
                    c4.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c4);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c4);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c4);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c4);
                        disableRemainingCards(c1, c2, c3, c5, c6, c7, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c5Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c5) {
                    flipCard(c5, getRandomElement());
                    c5.setDisabledIcon(c5.getIcon());
                    c5.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c5);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c5);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c5);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c5);
                        disableRemainingCards(c1, c2, c3, c4, c6, c7, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c6Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c6) {
                    flipCard(c6, getRandomElement());
                    c6.setDisabledIcon(c6.getIcon());
                    c6.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c6);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c6);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c6);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c6);
                        disableRemainingCards(c1, c2, c3, c4, c5, c7, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c7Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c7) {
                    flipCard(c7, getRandomElement());
                    c7.setDisabledIcon(c7.getIcon());
                    c7.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c7);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c7);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c7);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c7);
                        disableRemainingCards(c1, c2, c3, c4, c5, c6, c8);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        ActionListener c8Listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == c8) {
                    flipCard(c8, getRandomElement());
                    c8.setDisabledIcon(c8.getIcon());
                    c8.setEnabled(false);
                    actionCtr++;

                    do {
                        if (actionCtr == 1) {
                            rank1 = getCardRank(c8);
                        } else if (actionCtr == 2) {
                            rank2 = getCardRank(c8);
                        }
                    } while(rank1 == rank2);

                    if(actionCtr == 1) {
                        p1Rank = getCardRank(c8);
                        JOptionPane.showMessageDialog(null, p2Name + " choose a card.");
                    } else {
                        p2Rank = getCardRank(c8);
                        disableRemainingCards(c1, c2, c3, c4, c5, c6, c7);
                        playBtn.setEnabled(true);
                        firstMovePrompt();
                    }
                }
            }
        };

        //for the play button
        ActionListener playListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == playBtn) {
                    startFrame.dispose();
                    GameFrame gameFrame = new GameFrame(p1Name, p2Name, p1Color, p2Color, p1Rank, p2Rank);
                    Model model = new Model(p1Name, p2Name, p1Color, p2Color, p1Rank, p2Rank);
                    GameFrameController GFController = new GameFrameController( gameFrame,model, p1Name, p2Name, p1Color,
                            p2Color, p1Rank, p2Rank );
                }
            }
        };

        //ADD ACTION LISTENERS
        //for name check buttons
        p1Done.addActionListener(p1NListener);
        p2Done.addActionListener(p2NListener);

        //for color chooser buttons
        p1SColorBtn.addActionListener(p1ColorListener);
        p2SColorBtn.addActionListener(p2ColorListener);

        //for color check buttons
        p1DColorBtn.addActionListener(p1DListener);
        p2DColorBtn.addActionListener(p2DListener);

        //for all the cards
        c1.addActionListener(c1Listener);
        c2.addActionListener(c2Listener);
        c3.addActionListener(c3Listener);
        c4.addActionListener(c4Listener);
        c5.addActionListener(c5Listener);
        c6.addActionListener(c6Listener);
        c7.addActionListener(c7Listener);
        c8.addActionListener(c8Listener);

        //for the play button
        playBtn.addActionListener(playListener);
    }

    /**
     * Unfolds card when selected.
     * @param b - chosen button
     * @param chosenCard - name of card
     */
    public void flipCard(JButton b, String chosenCard) {
        initCardIcons();
        switch(chosenCard) {
            case "Mouse" -> b.setIcon(mouse);
            case "Cat" -> b.setIcon(cat);
            case "Wolf" -> b.setIcon(wolf);
            case "Dog" -> b.setIcon(dog);
            case "Leopard" -> b.setIcon(leopard);
            case "Tiger" -> b.setIcon(tiger);
            case "Lion" -> b.setIcon(lion);
            case "Elephant" -> b.setIcon(elephant);
        }
    }

    /**
     * Used to disable all other cards once two cards have already been unfolded.
     *
     * @param b1 - card to be disabled
     * @param b2 - card to be disabled
     * @param b3 - card to be disabled
     * @param b4 - card to be disabled
     * @param b5 - card to be disabled
     * @param b6 - card to be disabled
     * @param b7 - card to be disabled
     */
    public void disableRemainingCards(JButton b1, JButton b2, JButton b3, JButton b4, JButton b5, JButton b6, JButton b7) {
        if(b1.getIcon() == ac) {
            b1.setDisabledIcon(b1.getIcon());
            b1.setEnabled(false);
        } else if(b1.getIcon() != ac) {
            b1.setEnabled(false);
        }

        if(b2.getIcon() == ac) {
            b2.setDisabledIcon(b2.getIcon());
            b2.setEnabled(false);
        } else if(b2.getIcon() != ac) {
            b2.setEnabled(false);
        }

        if(b3.getIcon() == ac) {
            b3.setDisabledIcon(b3.getIcon());
            b3.setEnabled(false);
        } else if(b3.getIcon() != ac) {
            b3.setEnabled(false);
        }

        if(b4.getIcon() == ac) {
            b4.setDisabledIcon(b4.getIcon());
            b4.setEnabled(false);
        } else if(b4.getIcon() != ac) {
            b4.setEnabled(false);
        }

        if(b5.getIcon() == ac) {
            b5.setDisabledIcon(b5.getIcon());
            b5.setEnabled(false);
        } else if(b5.getIcon() != ac) {
            b5.setEnabled(false);
        }

        if(b6.getIcon() == ac) {
            b6.setDisabledIcon(b6.getIcon());
            b6.setEnabled(false);
        } else if(b6.getIcon() != ac) {
            b6.setEnabled(false);
        }

        if(b7.getIcon() == ac) {
            b7.setDisabledIcon(b7.getIcon());
            b7.setEnabled(false);
        } else if(b7.getIcon() != ac) {
            b7.setEnabled(false);
        }
    }

    /**
     * Gets a random element from the array list, listAnimal.
     *
     * @return name of random animal from list
     */
    public String getRandomElement() {
        Random rand = new Random();
        Collections.shuffle(listAnimal, new Random());
        while(randomIndex > listAnimal.size()) {
            randomIndex = rand.nextInt(listAnimal.size() - 1);
        }
        listAnimal.remove(randomIndex);
        return listAnimal.get(randomIndex);
    }

    /**
     * Pop-up after two players have chosen their cards.
     */
    public void firstMovePrompt() {
        if(p1Rank > p2Rank) {
            JOptionPane.showMessageDialog(null,
                    "Congratulations, "+ p1Name  + "! You got a higher-ranked animal, " +
                            "which means you get to make the first move.");
        } else if(p2Rank > p1Rank) {
            JOptionPane.showMessageDialog(null,
                    "Congratulations, " + p2Name + "! You got a higher-ranked animal, " +
                            "which means you get to make the first move.");
        }
    }

    /**
     * Returns the unfolded card's rank.
     *
     * @param btn - chosen card
     * @return rank of chosen card
     */
    public int getCardRank(JButton btn) {
        if(btn.getIcon().equals(mouse)) {
            return 1;
        } else if(btn.getIcon().equals(cat)) {
            return 2;
        } else if(btn.getIcon().equals(wolf)) {
            return 3;
        } else if(btn.getIcon().equals(dog)) {
            return 4;
        } else if(btn.getIcon().equals(leopard)) {
            return 5;
        } else if(btn.getIcon().equals(tiger)) {
            return 6;
        } else if(btn.getIcon().equals(lion)) {
            return 7;
        } else if(btn.getIcon().equals(elephant)) {
            return 8;
        }
        return 0;
    }

    /**
     * Initializes all card icons.
     *
     */
    public void initCardIcons() {
        mouse = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/1.png")));
        cat = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/2.png")));
        wolf = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/3.png")));
        dog = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/4.png")));
        leopard = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/5.png")));
        tiger = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/6.png")));
        lion = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/7.png")));
        elephant = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/8.png")));
        ac = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
    }

}

