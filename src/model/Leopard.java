package model;

import java.awt.*;
import java.util.LinkedList;
/**
 * A subclass of Piece.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class Leopard extends Piece
{
    /**
     * Leopard constructor.
     *
     * @param n - name of Leopard
     * @param posX - x coordinate
     * @param posY - y coordinate
     * @param p - determines to belong to which player
     * @param ps - list to be added into
     * @param r - rank of Leopard
     * @param color - Color of JButton
     */
    public Leopard(String n, int posX, int posY, boolean p, LinkedList<Piece> ps, int r, Color color)
    {
        super(n,posX,posY,p,ps, r, color );
        this.bSwim = false;
        this.bJump = false;
    }

    /**
     * called whenever Leopard is not inside of Trap.
     */
    public void revertRank()
    {
        this.bTrapped = false;
        this.rank = 5;
    }
}