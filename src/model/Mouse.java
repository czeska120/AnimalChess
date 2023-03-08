package model;

import java.awt.*;
import java.util.LinkedList;
/**
 * A subclass of Piece.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class Mouse extends Piece
{
    /**
     * Mouse constructor.
     *
     * @param n - name of Mouse
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param p - determines to belong to which player
     * @param ps - list to be added into
     * @param r - rank of Mouse
     * @param color - Color of JButton
     */
    public Mouse(String n, int posX, int posY, boolean p, LinkedList<Piece> ps, int r, Color color)
    {
        super( n, posX, posY, p, ps, r, color );
        this.bSwim = true;
        this.bJump = false;
    }

    /**
     * Detects if Mouse is inside river Tile.
     * @return true if Mouse is in river, false otherwise
     */
    public boolean isInRiver()
    {
        if ((getPosX() > 2 && getPosX() < 6) && (getPosY() == 1 || getPosY() == 2 || getPosY() == 4 || getPosY() == 5))// inside river
            return true;
        else
            return false;
    }

    /**
     * called whenever Mouse is not inside of Trap.
     */
    public void revertRank()
    {
        this.bTrapped = false;
        this.rank = 1;
    }

    /**
     * Overridden validator of moves to satisfy Mouse conditions.
     *
     * @param nextX - next x coordinate
     * @param nextY - next y coordinate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValidMove(int nextX, int nextY)
    {
        if (((nextX == getPosX()) && (nextY + 1 == getPosY())) ||
                ((nextX == getPosX()) && (nextY - 1 == getPosY())) ||
                ((nextX + 1 == getPosX()) && (nextY == getPosY())) ||
                ((nextX - 1 == getPosX()) && (nextY == getPosY())))
        {
            if(Model.getPiece(nextX, nextY) != null) //Piece found
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
     * Overridden capture to satisfy Mouse conditions
     * @param otherP - Piece to be captured
     * @return true if valid,
     */
    @Override
    public boolean captures(Piece otherP)
    {
        if((this.isP1() != otherP.isP1())) // opponent's piece
        {
            if((otherP instanceof Elephant || otherP.bTrapped) && !isInRiver()) // elephant (while mouse not in river) or trapped animal
            {
                kill(otherP);
                return true;
            }

            else if(otherP instanceof Mouse)
            {
                if(((Mouse) otherP).isInRiver() == isInRiver()) //if both are either on land/river
                    return true;
            }
            return false;
        }
        else
        {
            return false;
        }
    }
}
