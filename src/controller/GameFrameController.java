package controller;

import model.*;
import view.frames.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
/**
 * Controller for the GameFrame.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class GameFrameController
{
    /**
     * Attributes of GameFrameController.
     */
    private GameFrame gw;
    private Model md;

    //FROM START FRAME CONTROLLER
    private String p1Name;
    private String p2Name;
    private Color p1Color;
    private Color p2Color;
    private int p1Rank;
    private int p2Rank;

    /**
     * Constructor of GameFrameController
     * @param game - GameFrame
     * @param model - Model
     * @param p1Name - Name of player 1
     * @param p2Name - Name of player 2
     * @param p1Color - Color of player 1
     * @param p2Color - Color of player 2
     * @param p1Rank - Rank of player 1
     * @param p2Rank - Rank of player 2
     */
    public GameFrameController(GameFrame game, Model model,
                               String p1Name, String p2Name,
                               Color p1Color, Color p2Color,
                               int p1Rank, int p2Rank)
    {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.p1Color = p1Color;
        this.p2Color = p2Color;
        this.p1Rank = p1Rank;
        this.p2Rank = p2Rank;

        gw = game;
        gw.addButtonHandler( new ButtonHandler() );
        gw.addHelpButton( new HelpButton() );
//        gw.addRestartButton( new RestartButton() );
        gw.addRanksButton(new RanksButton());
        md = model;
    }
    private Piece chosenCell = null;

    /**
     * ButtonHandler class that implements ActionListener.
     */
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton pieceClick = (JButton) e.getSource();
            for(int i=0; i < 9; i++)
            {
                for(int j=0; j<7; j++)
                {
                    if(pieceClick == (gw.getSquaresView( i,j )))
                    {
                        if(chosenCell == null) // SELECT
                        {
                            chosenCell = Model.getPiece( i,j );
                            if(chosenCell != null) // gets non-empty cell
                            {
                                if(!(chosenCell.isP1() == (md.getTurn()))) // If piece belongs to currently playing player
                                {
                                    chosenCell = null;
                                }
                                else
                                    gw.MarkNextLegalMoves( chosenCell );
                            }
                        }
                        else // STEP
                        {
                            if(!chosenCell.isValidMove( i, j ))
                            {
                                gw.removeHighlight();
                            }
                            else
                            {
                                if(Model.getSquare(i,j) instanceof Trap) // trap
                                {
                                    chosenCell.trap();
                                    movePiece( i,j );
                                }

                                else if((Model.getSquare( i,j ) instanceof Den)) // den
                                {
                                    if(chosenCell.isInDen(i,j))
                                    {
                                        movePiece( i,j );
                                        JOptionPane.showMessageDialog(null, "You won " +
                                                                            p1Name + "!");
                                        gw.getFrame().dispose();
                                    }
                                    else
                                    {
                                        gw.removeHighlight();
                                    }

                                }

                                else if (Model.getPiece(i,j) == null)
                                {
                                    movePiece( i,j );
                                    if(md.isOutOfPieces())
                                    {
                                        if(md.getP1count() == 0) {
                                            JOptionPane.showMessageDialog(null, "You won " + p2Name + "!");
                                            gw.getFrame().dispose();
                                        } else if(md.getP2count() == 0) {
                                            JOptionPane.showMessageDialog(null, "You won " + p1Name + "!");
                                            gw.getFrame().dispose();
                                        }
                                    }
                                    if (chosenCell instanceof Mouse)
                                        ((Mouse) chosenCell).revertRank();
                                    if (chosenCell instanceof Cat)
                                        ((Cat) chosenCell).revertRank();
                                    if (chosenCell instanceof Wolf)
                                        ((Wolf) chosenCell).revertRank();
                                    if (chosenCell instanceof Dog)
                                        ((Dog) chosenCell).revertRank();
                                    if (chosenCell instanceof Leopard)
                                        ((Leopard) chosenCell).revertRank();
                                    if (chosenCell instanceof Tiger)
                                        ((Tiger) chosenCell).revertRank();
                                    if (chosenCell instanceof Lion)
                                        ((Lion) chosenCell).revertRank();
                                    if (chosenCell instanceof Elephant)
                                        ((Elephant) chosenCell).revertRank();
                                }
                                else // If animal is present
                                {
                                    if (chosenCell.captures( Objects.requireNonNull( Model.getPiece( i, j ) )))
                                        movePiece( i,j );
                                }

                            }
                            chosenCell = null;
                        }
                    }
                }
            }
        }
    }

    /**
     * HelpButton class that implements ActionListener.
     */
    private class HelpButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton help = (JButton) e.getSource();
            if(help == gw.getHelp()) {
                new HelpFrame();
            }
        }
    }

    /**
     * RanksButton class that implements ActionListener.
     */
    private class RanksButton implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton ranks = (JButton) e.getSource();
            if(ranks == gw.getRanks()) {
                JOptionPane.showMessageDialog(null, "1 MOUSE\n" +
                        "2 CAT\n" +
                        "3 WOLF\n" +
                        "4 DOG\n" +
                        "5 LEOPARD\n" +
                        "6 TIGER\n" +
                        "7 LION\n" +
                        "8 ELEPHANT\n");
            }
        }
    }

    /**
     * Updates the GameFrame and the Model chosen Piece values.
     *
     * @param i - new x coordinate
     * @param j - new y coordinate
     */
    private void movePiece(int i, int j)
    {
        //repaint non animal piece
        gw.getSquaresView(chosenCell.getPosX(), chosenCell.getPosY())
                .setIcon(gw.determineSquare(chosenCell.getPosX(), chosenCell.getPosY()));

        //newPos
        gw.getSquaresView(i,j).setIcon(chosenCell.getImg());
        if(chosenCell.isP1()) {
            gw.getSquaresView(i,j).setBackground(getBottomPieceColor());
        } else {
            gw.getSquaresView(i,j).setBackground(getTopPieceColor());
        }

        int oldX = chosenCell.getPosX();
        int oldY = chosenCell.getPosY();

        gw.getSquaresView(oldX, oldY).setBackground(null);

        gw.removeHighlight();
        //update chosen cell values (Piece model values)
        md.getPiece(oldX,oldY).movePiece(i,j);

        md.setTurn();

        if(md.getTurn())
        {

            gw.getTurnLabel().setText(gw.getFirstPlayerName() + "'s");
        }

        else
        {
            gw.getTurnLabel().setText(gw.getSecondPlayerName() + "'s");
        }

    }

    /**
     *
     * @return Color of player 2
     */
    public Color getTopPieceColor() {
        if(p1Rank > p2Rank) {
            return p2Color;
        } else {
            return p1Color;
        }
    }

    /**
     *
     * @return Color of player 1
     */
    public Color getBottomPieceColor() {
        if(p1Rank > p2Rank) {
            return p1Color;
        } else {
            return p2Color;
        }
    }
}

