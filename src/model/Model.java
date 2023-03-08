package model;

import java.awt.*;
import java.util.LinkedList;

public class Model
{
    private static final int ROW = 9;
    private static final int COL = 7;
    private static boolean bTurn;
    private static boolean bWin;
    private static int P1count;
    private static int P2count;
    private static LinkedList<Piece> ps = new LinkedList<>();
    private static Tile[][] modelBoard = new Tile[ROW][COL];
    private String p1Name;
    private String p2Name;
    private Color p1Color;
    private Color p2Color;
    private int p1Rank;
    private int p2Rank;


    public Model(String p1Name, String p2Name, Color p1Color, Color p2Color, int p1Rank, int p2Rank) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.p1Color = p1Color;
        this.p2Color = p2Color;
        this.p1Rank = p1Rank;
        this.p2Rank = p2Rank;

        bTurn = true;
        bWin = false;
        P1count = 0;
        P2count = 0;
    }

    public Model()
    {
        bTurn = true;
        bWin = false;
        P1count = 0;
        P2count = 0;
    }

    public int getP1count()
    {
        return P1count;
    }
    public int getP2count()
    {
        return P2count;
    }

    public boolean getTurn(){return bTurn;}
    public static boolean setTurn()
    {
        if(bTurn)
            return bTurn = false;
        else
            return bTurn = true;
    }

    public LinkedList<Piece> getPieces() {return ps;}
    public static Piece getPiece(int a, int b)
    {
        for (int k = 0; k < ps.size(); k++)
        {
            Integer w = a;
            Integer z = b;
            Integer x = ps.get( k ).getPosX();
            Integer y = ps.get( k ).getPosY();

            if(w.equals( x )) // X POS
                if(z.equals(y)) // Y POS
                    return ps.get(k);
        }
        return null;
    }

    public void createModelBoard()
    {

        //board components
        for(int i=0; i < ROW; i++)
        {
            for(int j=0; j<COL; j++)
            {
                if((i == 0 || i == 8) && (j == 3)) //Den
                {
                    modelBoard[i][j] = new Den(i,j);
                    modelBoard[i][j].setPosX( i );
                    modelBoard[i][j].setPosY( j );
                }

                else if(((i == 0 || i == 8) && (j == 2)) ||
                        ((i == 0 || i == 8) && (j == 4)) ||
                        ((i == 1 || i == 7) && (j == 3))) // Trap
                {
                    modelBoard[i][j] = new Trap(i,j);
                    modelBoard[i][j].setPosX( i );
                    modelBoard[i][j].setPosY( j );
                }

                else if(((i == 3 || i == 4 || i == 5) && (j == 1)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 2)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 4)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 5))) // River
                {
                    modelBoard[i][j] = new River(i,j);
                    modelBoard[i][j].setPosX( i );
                    modelBoard[i][j].setPosY( j );
                }

                else
                {
                    modelBoard[i][j] = new Empty(i,j);
                    modelBoard[i][j].setPosX( i );
                    modelBoard[i][j].setPosY( j );
                }
            }
        }


        Piece m1 = new Mouse("mouse", 6,6, true, ps, 1, getBottomPieceColor());
        Piece c1 = new Cat("cat", 7,1, true, ps,2, getBottomPieceColor());
        Piece w1 = new Wolf("wolf", 6,2, true, ps,3, getBottomPieceColor());
        Piece d1 = new Dog("dog", 7,5, true, ps,4, getBottomPieceColor());
        Piece lp1 = new Leopard("leopard", 6,4, true, ps,5, getBottomPieceColor());
        Piece t1 = new Tiger("tiger", 8,0, true, ps,6, getBottomPieceColor());
        Piece ln1 = new Lion("lion", 8,6, true, ps,7, getBottomPieceColor());
        Piece e1 = new Elephant("elephant", 6,0, true, ps,8, getBottomPieceColor());

        Piece m2 = new Mouse("mouse", 2,0, false, ps,1, getTopPieceColor());
        Piece c2 = new Cat("cat", 1,5, false, ps,2, getTopPieceColor());
        Piece w2 = new Wolf("wolf", 2,4, false, ps,3, getTopPieceColor());
        Piece d2 = new Dog("dog", 1,1, false, ps,4, getTopPieceColor());
        Piece lp2 = new Leopard("leopard", 2,2, false, ps,5, getTopPieceColor());
        Piece t2 = new Tiger("tiger", 0,6, false, ps,6, getTopPieceColor());
        Piece ln2 = new Lion("lion", 0,0, false, ps,7, getTopPieceColor());
        Piece e2 = new Elephant("elephant", 2,6, false, ps,8, getTopPieceColor());

    }

    public static Tile getSquare(int i, int j) {return modelBoard[i][j];}

    public boolean isOutOfPieces()
    {
        P1count = 0;
        P2count = 0;
        for(Piece p: ps)
        {
            if(p.isP1())
                P1count++;
            else
                P2count++;
        }
        if(P1count == 0 || P2count == 0)
            return true;

        return false;
    }

    public Color getTopPieceColor() {
        if(p1Rank > p2Rank) {
            return p2Color;
        } else {
            return p1Color;
        }

    }

    public Color getBottomPieceColor() {
        if(p1Rank > p2Rank) {
            return p1Color;
        } else {
            return p2Color;
        }
    }
}
