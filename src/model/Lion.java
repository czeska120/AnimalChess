package model;

import java.awt.*;
import java.util.LinkedList;
/**
 * A subclass of Piece.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class Lion extends Piece
{
    /**
     * Lion constructor.
     *
     * @param n - name of Lion
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param p - determines to belong to which player
     * @param ps - list to be added into
     * @param r - rank of Lion
     * @param color - Color of JButton
     */
    public Lion(String n, int posX, int posY, boolean p, LinkedList<Piece> ps, int r, Color color)
    {
        super(n,posX,posY,p,ps, r, color );
        this.bSwim = false;
        this.bJump = true;
    }

    /**
     * called whenever Lion is not inside of Trap.
     */
    public void revertRank()
    {
        this.bTrapped = false;
        this.rank = 7;
    }

    /**
     * Overridden validator to satisfy Lion conditions
     * @param nextX - next x coordinate
     * @param nextY - next y coordinate
     * @return true if valid, false otherwise
     */
    @Override
    public boolean isValidMove(int nextX, int nextY)
    {
        if(((nextX == getPosX()) && (nextY+1 == getPosY())) ||
                ((nextX == getPosX()) && (nextY-1 == getPosY())) ||
                ((nextX+1 == getPosX()) && (nextY == getPosY())) ||
                ((nextX-1 == getPosX()) && (nextY == getPosY())))
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
        else if ((nextX > 2 && nextX < 6) && (nextY == 0 || nextY == 3 || nextY == 6))// river conditions (HORIZONTAL)
            return !(occupiedPath( nextX, nextY ));
        else if ((nextX == 2 || nextX == 6) && (nextY == 1 || nextY == 2 || nextY == 4 || nextY == 5)) // river conditions (VERTICAL)
            return !(occupiedPath( nextX, nextY ));
        else
            return false;
    }

    /**
     * Checks if there is a Mouse in jump path of Lion.
     *
     * @param nextX - next x coordinate
     * @param nextY - next y coordinate
     * @return true if Mouse present, false otherwise
     */
    public boolean occupiedPath(int nextX, int nextY)
    {
        if ((nextX > 2 && nextX < 6) && (nextY == 0))// river conditions (HORIZONTAL, to left)
        {
            // checking inside of river if there is mouse present
            if(((Model.getPiece( nextX, 1 ) != null ||
                    Model.getPiece( nextX, 2 ) != null)))
                return true;
        }

        if ((nextX > 2 && nextX < 6) && ( nextY == 3)) // river conditions (HORIZONTAL, to center)
        {
            // checking inside of river if there is mouse present
            if(getPosY() == 0 ) //from the left
            {
                if(((Model.getPiece( nextX, 1 ) != null ||
                        Model.getPiece( nextX, 2 ) != null)))
                    return true;
            }
            else if(getPosY() == 6) //from the right
            {
                if((( Model.getPiece( nextX, 4 ) != null ||
                        Model.getPiece( nextX, 5 ) != null)))
                    return true;
            }
        }

        if ((nextX > 2 && nextX < 6) && (nextY == 6))// river conditions (HORIZONTAL, to right)
        {
            // checking inside of river if there is mouse present
            if((( Model.getPiece( nextX, 4 ) != null ||
                    Model.getPiece( nextX, 5 ) != null)))
                return true;
        }

        if ((nextX == 2 || nextX == 6) && (nextY == 1 || nextY == 2 || nextY == 4 || nextY == 5)) // river conditions (VERTICAL)
        {
            // checking inside of river if there is mouse present
            if(nextY == 1)
            {
                if((Model.getPiece( 3, 1 ) != null) ||
                        (Model.getPiece( 4, 1 ) != null) ||
                        (Model.getPiece( 5, 1 ) != null))
                    return true;
            }
            if(nextY == 2)
            {
                if((Model.getPiece( 3, 2 ) != null) ||
                        (Model.getPiece( 4, 2 ) != null) ||
                        (Model.getPiece( 5, 2 ) != null))
                    return true;
            }
            if(nextY == 4)
            {
                if((Model.getPiece( 3, 4 ) != null) ||
                        (Model.getPiece( 4, 4 ) != null) ||
                        (Model.getPiece( 5, 4 ) != null))
                    return true;
            }
            if(nextY == 5)
            {
                if((Model.getPiece( 3, 5 ) != null) ||
                        (Model.getPiece( 4, 5 ) != null) ||
                        (Model.getPiece( 5, 5 ) != null))
                    return true;
            }
        }
        return false;
    }
}