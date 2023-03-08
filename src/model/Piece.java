package model;

import java.awt.*;
import java.util.LinkedList;

/**
 * Piece is a subclass of Tile which represents Tiles that hold animal pieces.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public abstract class Piece extends Tile
{
    /**
     * Attributes of Piece
     */
    protected String name;
    protected boolean bP1;
    protected boolean bCaptured;
    protected boolean bTrapped;
    protected boolean bSwim;
    protected boolean bJump;
    protected LinkedList<Piece> ps;
    protected int rank;
    protected Color color;

    /**
     * Piece constructor
     *
     * @param n - represents name of Piece
     * @param x - x coordinate of Piece
     * @param y - y coordinate of Piece
     * @param b - determines to belong to which player
     * @param ps - list of Pieces belonging to player
     * @param r - rank of Piece
     * @param color - color of Piece button
     */
    public Piece(String n, int x, int y,  boolean b, LinkedList<Piece> ps, int r, Color color)
    {
        super(x,y);
        name = n;
        bP1 = b;
        this.color = color;
        bCaptured = false;
        bTrapped = false;
        this.ps = ps;
        ps.add(this); //adds this piece to linked list of pieces
        rank = r;
    }

    //getters
    /**
     *
     * @return name of Piece
     */
    public String getName() {return name;}

    /**
     *
     * @return true if Piece belongs to player one, false otherwise
     */
    public boolean isP1 () {return bP1;}

    /**
     *
     * @return true if Piece is captured already by opponent, false otherwise
     */
    public boolean isCaptured() {return bCaptured;}

    /**
     *
     * @return true if Piece is trapped, false otherwise
     */
    public boolean isTrapped() {return bTrapped;}

    /**
     *
     * @return true if Piece can swim, false otherwise
     */
    public boolean canSwim() {return bSwim;}

    /**
     *
     * @return true if Piece can jump, false otherwise
     */
    public boolean canJump() {return bJump;}

    /**
     *
     * @return rank of specified Piece
     */
    public int getRank()
    {
        return rank;
    }

    /**
     *
     * @return Color of specified Piece
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Updates the Piece's current position on the game board.
     *
     * @param nextX - updated x coordinate
     * @param nextY - updated y coordinate
     */
    public void movePiece(int nextX, int nextY)
    {
        setPosX( nextX );
        setPosY(nextY);
    }

    /**
     * Removes rank of Piece inside trap.
     */
    public void trap()
    {
        bTrapped = true;
        rank = 0;
    }

    /**
     * Checks if Piece is in den.
     *
     * @param x - x coordinate of Piece
     * @param y - y coordinate of Piece
     * @return true if player 1 is in player 2 den or if player 2 is in player 1 den, false otherwise
     */
    public boolean isInDen(int x, int y)
    {
        //Player 1 must be in (0,3)
        if(bP1 && (x == 0))
            return true;
        //Player 2 must be in (8,3)
        if(!bP1 && (x == 8))
            return true;
        return false;
    }

    /**
     * Captures other Piece.
     *
     * @param killed - Piece to be removed from opponents list of playable Pieces
     */
    public void kill(Piece killed)
    {
        ps.remove(killed);
    }

    /**
     * Validates conditions for the next movement of Piece.
     *
     * @param nextX - next x coordinate
     * @param nextY - next y coordinate
     * @return true if valid, false otherwise
     */
    public boolean isValidMove(int nextX, int nextY)
    {
        if(((nextX == getPosX()) && (nextY+1 == getPosY()))||
                ((nextX == getPosX()) && (nextY-1 == getPosY())) ||
                ((nextX+1 == getPosX()) && (nextY == getPosY())) ||
                ((nextX-1 == getPosX()) && (nextY == getPosY()))) // checks for Piece origin
        {
            if(Model.getSquare( nextX, nextY) instanceof River)
                return false;
            else if(Model.getPiece(nextX, nextY) != null) //Piece found
            {
                if(captures( Model.getPiece(nextX,nextY)))
                    return true;
                else
                    return false;
            }
            else
                return true;
        }
        else
            return false;
    }

    /**
     * Validates if capture is possible on other Piece
     *
     * @param otherP - Piece to be captured
     * @return true if valid, false otherwise
     */
    public boolean captures(Piece otherP)
    {
        if(this.isP1() != otherP.isP1()) // First check if opponent's piece
        {
            if(this.rank >= otherP.getRank() || otherP.bTrapped) // Follows ranking to capture and if opponent is in trap
            {
                kill(otherP);
                return true;
            }
            else
            {
                return false;
            }
        }

        else
        {
            return false;
        }
    }

}
