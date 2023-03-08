package model;

import javax.swing.*;
/**
 * An abstract class that represents the tiles on the game board.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public abstract class Tile
{
    /**
     * Tile attributes.
     */
    private int posX;
    private int posY;
    private ImageIcon tileImg;

    /**
     * Tile constructor.
     *
     * @param a - x coordinate
     * @param b - y coordinate
     */
    public Tile(int a, int b)
    {
        posX = a;
        posY = b;
    }

    /**
     *
     * @return x coordinate
     */
    public int getPosX() { return posX;}

    /**
     *
     * @return y coordinate
     */
    public int getPosY() {return posY;}

    /**
     *
     * @return image of Tile on board
     */
    public ImageIcon getImg() {return tileImg;}

    /**
     *
     * @param i - new x coordinate
     */
    public void setPosX(int i) {posX = i;}

    /**
     *
     * @param j - new y coordinate
     */
    public void setPosY(int j) {posY = j;}

    /**
     *
     * @param icon - sets ImageIcon of Tile
     */
    public void setImg(ImageIcon icon) {tileImg = icon;}
}
