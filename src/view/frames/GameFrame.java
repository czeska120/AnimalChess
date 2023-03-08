package view.frames;

import model.Model;
import model.Mouse;
import model.Piece;
import model.River;
import view.features.RoundButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
/**
 * The View of the game board.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class GameFrame {
    /**
     * GameFrame attributes
     */
    //MODEL
    private Model md = new Model();

    //ROWS & COLUMNS
    private static int ROWS = 9;
    private static int COLS = 7;

    //DIMENSIONS
    private static final Dimension OUTER_FRAME_DIM = new Dimension(800, 800); //frame dimension
    private static final Dimension BOX_DIM = new Dimension(72, 72); //box dimension

    //CONTAINERS
    private JFrame frame = new JFrame("Animal Chess");
    private JPanel mainbar = new JPanel();
    private JLabel background = new JLabel();
    private JLabel border = new JLabel();
    private JLabel board = new JLabel();
    private JLabel[][] boxes = new JLabel[ROWS][COLS];
    private JLabel playerTurn = new JLabel();
    private JLabel ptName = new JLabel();
    private JLabel ptTurn = new JLabel();
    private JLabel info = new JLabel();
    private JLabel l1 = new JLabel();
    private JLabel l2 = new JLabel();
    private JLabel l3 = new JLabel();
    private JLabel l4 = new JLabel();
    private JLabel l5 = new JLabel();
    private JLabel l6 = new JLabel();
    private JLabel c1 = new JLabel();
    private JLabel c2 = new JLabel();
    private JLabel c3 = new JLabel();

    private JButton help = new RoundButton("", new Color(235, 220, 189), Color.WHITE);
    private JButton ranks = new RoundButton("", new Color(235, 220, 189), Color.WHITE);
    private JButton restart = new RoundButton("", new Color(235, 220, 189), Color.WHITE);
    private static final JButton[][] squaresView = new JButton[ROWS][COLS];

    /* image file names */
    private static final String[] FILE_NAMES = {"red_mouse", "red_cat",
            "red_wolf", "red_dog", "red_leopard", "red_tiger", "red_lion",
            "red_elephant", "blue_mouse", "blue_cat", "blue_wolf", "blue_dog",
            "blue_leopard", "blue_tiger", "blue_lion", "blue_elephant"};


    //FROM START FRAME CONTROLLER
    private String p1Name;
    private String p2Name;
    private Color p1Color;
    private Color p2Color;
    private int p1Rank;
    private int p2Rank;

    //IMAGE ICONS
    private ImageIcon bgImg, boardImg, turnImg, detailsBg, inst, mech, ranksImg, resetImg, restartImg;
    private ImageIcon mouse;
    private Image img[] = new Image[16];
    private Image imgIcon[] = new Image[16];
    private Image img2[] = new Image[4];
    private Image imgIcon2[] = new Image[4];

    /**
     * Constructor of GameFrame.
     *
     * @param p1Name - name of player 1
     * @param p2Name - name of player 2
     * @param p1Color - Color of player 1
     * @param p2Color - Color of player 2
     * @param p1Rank - rank of player 1
     * @param p2Rank - rank of player 2
     */
    public GameFrame(String p1Name, String p2Name, Color p1Color, Color p2Color, int p1Rank, int p2Rank) {
        this.p1Name = p1Name;
        this.p2Name = p2Name;
        this.p1Color = p1Color;
        this.p2Color = p2Color;
        this.p1Rank = p1Rank;
        this.p2Rank = p2Rank;

        displayBoard(); //BOARD & BACKGROUND
    }

    /**
     * Initializes icons of buttons and other graphical icons on game window.
     */
    public void initIcons() {
        bgImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/game frame bg.png")));
        boardImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/board img.png")));
        turnImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/turn bg.png")));
        mouse = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/pieces/1.png")));
        detailsBg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/detailsBg.png")));
        inst = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/instructions.png")));
        mech = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/mechanics.png")));
        ranksImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/ranks.png")));
        resetImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/reset.png")));
        restartImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/restart.png")));
    }

    /**
     * Sets up board components in game board.
     */
    private void displayBoard()
    {
        initIcons();
        //BOARD
        frame.getContentPane();
        mainbar.setLayout(new GridLayout(ROWS, COLS, 2, 2));
        try
        {
            img2[0] = (ImageIO.read( Objects.requireNonNull(this.getClass().getResource("/GameFrame/others/den.png")) ));
            img2[1] = (ImageIO.read( Objects.requireNonNull(this.getClass().getResource("/GameFrame/others/grass.png") )));
            img2[2] = (ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/GameFrame/others/river.png") )));
            img2[3] = (ImageIO.read( Objects.requireNonNull(this.getClass().getResource("/GameFrame/others/trap.png") )));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        imgIcon2[0] = img2[0].getScaledInstance( 63, 63, Image.SCALE_SMOOTH );
        imgIcon2[1] = img2[1].getScaledInstance( 63, 63, Image.SCALE_SMOOTH );
        imgIcon2[2] = img2[2].getScaledInstance( 63, 63, Image.SCALE_SMOOTH );
        imgIcon2[3] = img2[3].getScaledInstance( 63, 63, Image.SCALE_SMOOTH );

        for (int i = 0; i < img.length; i++)
        {
            try
            {
                img[i] = (ImageIO.read( Objects.requireNonNull(this.getClass().getResource( "/GameFrame/old pieces/"
                        + FILE_NAMES[i] + ".png" )) ));
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            imgIcon[i] = img[i].getScaledInstance( 50, 50, Image.SCALE_SMOOTH );
        }

        md.createModelBoard();
        //BOARD

        //
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                boxes[i][j] = new JLabel();
                boxes[i][j].setBackground(new Color(255, 255, 255, 39));
                boxes[i][j].setSize(BOX_DIM);
                boxes[i][j].setLayout(new BorderLayout());
                boxes[i][j].setOpaque(true);
                boxes[i][j].repaint();
                boxes[i][j].setBackground(new Color(3, 34, 51));
                board.add(boxes[i][j]);
                if(((i == 3 || i == 4 || i == 5) && (j == 1)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 2)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 4)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 5))) {
                    boxes[i][j].setBackground(new Color(7, 76, 101));
                } else if(((i == 0) && (j == 1 || j == 5)) ||
                        ((i == 1) && (j == 0 || j == 2 || j == 4 || j == 6)) ||
                        ((i == 2) && (j == 1 || j == 3 || j == 5)) ||
                        ((i == 3) && (j == 0 || j == 3 || j == 6)) ||
                        ((i == 4) && (j == 0 || j == 3 || j == 6)) ||
                        ((i == 5) && (j == 0 || j == 3 || j == 6)) ||
                        ((i == 6) && (j == 1 || j == 3 || j == 5)) ||
                        ((i == 7) && (j == 0 || j == 2 || j == 4 || j == 6)) ||
                        ((i == 8) && (j == 1 || j == 5))) {
                    boxes[i][j].setLayout(new BorderLayout());
                }
            }
        }

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {

                if ((i == 0 || i == 8) && (j == 3)) //Den
                {
                    squaresView[i][j] = new RoundButton("", new Color(3, 34, 51), Color.WHITE);
                    boxes[i][j].add( squaresView[i][j] );
                    squaresView[i][j].setFocusPainted(false);
                    squaresView[i][j].setSize(BOX_DIM);
                    squaresView[i][j].setIcon( new ImageIcon( imgIcon2[0] ) );
                    squaresView[i][j].setOpaque(false);
                    boxes[i][j].add(squaresView[i][j]);
                } else if (((i == 0 || i == 8) && (j == 2)) ||
                        ((i == 0 || i == 8) && (j == 4)) ||
                        ((i == 1 || i == 7) && (j == 3))) // Trap
                {
                    squaresView[i][j] = new RoundButton("", new Color(3, 34, 51), Color.WHITE);
                    squaresView[i][j].setFocusPainted(false);
                    squaresView[i][j].setSize(BOX_DIM);
                    squaresView[i][j].setIcon( new ImageIcon( imgIcon2[3] ) );
                    squaresView[i][j].setOpaque(false);
                    boxes[i][j].add(squaresView[i][j]);
                } else if (((i == 3 || i == 4 || i == 5) && (j == 1)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 2)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 4)) ||
                        ((i == 3 || i == 4 || i == 5) && (j == 5))) // River
                {
                    squaresView[i][j] = new RoundButton("", new Color(7, 76, 101), Color.WHITE);
                    squaresView[i][j].setFocusPainted(false);
                    squaresView[i][j].setSize(BOX_DIM);
                    squaresView[i][j].setIcon( new ImageIcon( imgIcon2[2] ) );
                    squaresView[i][j].setOpaque(false);
                    boxes[i][j].add(squaresView[i][j]);
                } else
                {
                    if((((i == 0) && (j == 1 || j == 5)) ||
                            ((i == 1) && (j == 0 || j == 2 || j == 4 || j == 6)) ||
                            ((i == 2) && (j == 1 || j == 3 || j == 5)) ||
                            ((i == 3) && (j == 0 || j == 3 || j == 6)) ||
                            ((i == 4) && (j == 0 || j == 3 || j == 6)) ||
                            ((i == 5) && (j == 0 || j == 3 || j == 6)) ||
                            ((i == 6) && (j == 1 || j == 3 || j == 5)) ||
                            ((i == 7) && (j == 0 || j == 2 || j == 4 || j == 6)) ||
                            ((i == 8) && (j == 1 || j == 5)))) {
                        squaresView[i][j] = new RoundButton("", new Color(3, 34, 51), Color.WHITE);
                        squaresView[i][j].setFocusPainted(false);
                        squaresView[i][j].setSize(BOX_DIM);
                        squaresView[i][j].setOpaque(false);
                        boxes[i][j].add(squaresView[i][j]);
                    } else if(md.getPiece(i, j) != null && md.getPiece(i, j).isP1()) {
                        squaresView[i][j] = new RoundButton("", getTopPieceColor(),
                                Color.WHITE);
                        boxes[i][j].add(squaresView[i][j]);
                    } else if(md.getPiece(i, j) != null && !(md.getPiece(i, j).isP1())) {
                        squaresView[i][j] = new RoundButton("", getBottomPieceColor(),
                                Color.WHITE);
                        boxes[i][j].add(squaresView[i][j]);
                    }
                }
            }
        }
        mainbar.setBounds(10, 13, 504, 648);

        //SET ICONS
        md.getPieces().get( 0 ).setImg( new ImageIcon( imgIcon[0] ) );
        md.getPieces().get( 1 ).setImg( new ImageIcon( imgIcon[1] ) );
        md.getPieces().get( 2 ).setImg( new ImageIcon( imgIcon[2] ) );
        md.getPieces().get( 3 ).setImg( new ImageIcon( imgIcon[3] ) );
        md.getPieces().get( 4 ).setImg( new ImageIcon( imgIcon[4] ) );
        md.getPieces().get( 5 ).setImg( new ImageIcon( imgIcon[5] ) );
        md.getPieces().get( 6 ).setImg( new ImageIcon( imgIcon[6] ) );
        md.getPieces().get( 7 ).setImg( new ImageIcon( imgIcon[7] ) );

        md.getPieces().get( 8 ).setImg( new ImageIcon( imgIcon[8] ) );
        md.getPieces().get( 9 ).setImg( new ImageIcon( imgIcon[9] ) );
        md.getPieces().get( 10 ).setImg( new ImageIcon( imgIcon[10] ) );
        md.getPieces().get( 11 ).setImg( new ImageIcon( imgIcon[11] ) );
        md.getPieces().get( 12 ).setImg( new ImageIcon( imgIcon[12] ) );
        md.getPieces().get( 13 ).setImg( new ImageIcon( imgIcon[13] ) );
        md.getPieces().get( 14 ).setImg( new ImageIcon( imgIcon[14] ) );
        md.getPieces().get( 15 ).setImg( new ImageIcon( imgIcon[15] ) );


//        index to represent piece in image array
        for (Piece p : md.getPieces())
        {
            int ind = 0;
            if (p.getName().equalsIgnoreCase( "mouse" ))
                ind = 0;
            else if (p.getName().equalsIgnoreCase( "cat" ))
                ind = 1;
            else if (p.getName().equalsIgnoreCase( "wolf" ))
                ind = 2;
            else if (p.getName().equalsIgnoreCase( "dog" ))
                ind = 3;
            else if (p.getName().equalsIgnoreCase( "leopard" ))
                ind = 4;
            else if (p.getName().equalsIgnoreCase( "tiger" ))
                ind = 5;
            else if (p.getName().equalsIgnoreCase( "lion" ))
                ind = 6;
            else if (p.getName().equalsIgnoreCase( "elephant" ))
                ind = 7;
            if (!p.isP1())
                ind += 8;
            squaresView[p.getPosX()][p.getPosY()].setIcon( new ImageIcon( imgIcon[ind] ) );

        }

        //BOARD
        board.setBounds(10, 13, 504, 648);
        board.setLayout(new GridLayout(ROWS, COLS, 2, 2));
        board.setOpaque(false);
        board.setIcon(boardImg);

        //BOARD BORDER
        border.setBounds(226, 45, 525, 675);
        border.setBackground(Color.BLACK);
        border.setOpaque(true);
        border.add(board);
        //add components
        border.add(mainbar);

        //BACKGROUND
        background.setSize(OUTER_FRAME_DIM);
        background.setIcon(bgImg);
        displayInformation();
        displayButtons();
        //add components
        background.add(border);

        //FRAME
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(OUTER_FRAME_DIM);
        frame.setIconImage(mouse.getImage());
        //add components
        frame.getContentPane().add(background);
    }

    /**
     * Determines what to repaint in previous square
     * @param i - old x coordinate
     * @param j - old y coordinate
     * @return
     */
    public ImageIcon determineSquare(int i, int j)
    {
        ImageIcon cell;

        //determines what to repaint in previous square
        if ((i == 0 || i == 8) && (j == 3)) //Den
            return cell = new ImageIcon( imgIcon2[0] );

        else if (((i == 0 || i == 8) && (j == 2)) ||
                ((i == 0 || i == 8) && (j == 4)) ||
                ((i == 1 || i == 7) && (j == 3))) // Trap
            return cell = new ImageIcon( imgIcon2[3] );

        else if (((i == 3 || i == 4 || i == 5) && (j == 1)) ||
                ((i == 3 || i == 4 || i == 5) && (j == 2)) ||
                ((i == 3 || i == 4 || i == 5) && (j == 4)) ||
                ((i == 3 || i == 4 || i == 5) && (j == 5))) // River
            return cell = new ImageIcon( imgIcon2[2] );
        else
            return null;

    }

    /**
     * Highlights possible moves to be made by Piece.
     *
     * @param chosen
     */
    public void MarkNextLegalMoves(Piece chosen)
    {
        int i = chosen.getPosX();
        int j = chosen.getPosY();
        //mouse lang pwede sa river
        //LION n tiger can jump OVER river
        //the rest on land only
        if (chosen instanceof Mouse)
        {
            //NORTH
            if (i != 0 && (getPiece( i - 1, j ) == null || (getPiece( i - 1, j ).isP1() != chosen.isP1())))
                getSquare( i - 1, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //SOUTH
            if (i != 8 && (getPiece( i + 1, j ) == null || (getPiece( i + 1, j ).isP1() != chosen.isP1())))
                getSquare( i + 1, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //WEST
            if (j != 0 && (getPiece( i, j - 1 ) == null || (getPiece( i, j - 1 ).isP1() != chosen.isP1())))
                getSquare( i, j - 1 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //EAST
            if (j != 6 && (getPiece( i, j + 1 ) == null || (getPiece( i, j + 1 ).isP1() != chosen.isP1())))
                getSquare( i, j + 1 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
        } else if (chosen.canJump() &&
                (((i > 2 && i < 6) && (j == 0 || j == 3 || j == 6)) ||
                        ((i == 2 || i == 6) && (j == 1 || j == 2 || j == 4 || j == 5)))) //tiger or leopard that is on edge of river
        {
            // HORIZONTAL
            if ((i > 2 && i < 6) && j == 0 && (getPiece( i, j + 3 ) == null || (getPiece( i, j + 3 ).isP1() != chosen.isP1()))) // Leftmost
                getSquare( i, j + 3 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            else if ((i > 2 && i < 6) && j == 3) // Center
            {
                if ((getPiece( i, j - 3 ) == null || (getPiece( i, j - 3 ).isP1() != chosen.isP1())))
                    getSquare( i, j - 3 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
                if (getPiece( i, j + 3 ) == null || (getPiece( i, j + 3 ).isP1() != chosen.isP1()))
                    getSquare( i, j + 3 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            } else if ((i > 2 && i < 6) && j == 6 && (getPiece( i, j - 3 ) == null || (getPiece( i, j - 3 ).isP1() != chosen.isP1()))) // Rightmost
                getSquare( i, j - 3 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );


                // VERTICAL
            else if ((i == 2) && (j == 1 || j == 2 || j == 4 || j == 5) && (getPiece( i + 4, j ) == null || (getPiece( i + 4, j ).isP1() != chosen.isP1()))) // Topside
                getSquare( i + 4, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );

            else if ((i == 6) && (j == 1 || j == 2 || j == 4 || j == 5) && (getPiece( i - 4, j ) == null || (getPiece( i - 4, j ).isP1() != chosen.isP1()))) // Bot side
                getSquare( i - 4, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );

            //NORTH
            if (i != 0 && !(md.getSquare( i - 1, j ) instanceof River)
                    && (getPiece( i - 1, j ) == null || (getPiece( i - 1, j ).isP1() != chosen.isP1())))
                getSquare( i - 1, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //SOUTH
            if (i != 8 && !(md.getSquare( i + 1, j ) instanceof River)
                    && (getPiece( i + 1, j ) == null || (getPiece( i + 1, j ).isP1() != chosen.isP1())))
                getSquare( i + 1, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //WEST
            if (j != 0 && !(md.getSquare( i, j - 1 ) instanceof River)
                    && (getPiece( i, j - 1 ) == null || (getPiece( i, j - 1 ).isP1() != chosen.isP1())))
                getSquare( i, j - 1 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //EAST
            if (j != 6 && !(md.getSquare( i, j + 1 ) instanceof River)
                    && (getPiece( i, j + 1 ) == null || (getPiece( i, j + 1 ).isP1() != chosen.isP1())))
                getSquare( i, j + 1 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );

        } else // if (!(chosen.canSwim()) && !(chosen.canJump())) //not mouse, tiger, or leopard
        {
            //NORTH
            if (i != 0 && !(md.getSquare( i - 1, j ) instanceof River)
                    && (getPiece( i - 1, j ) == null || (getPiece( i - 1, j ).isP1() != chosen.isP1())))
                getSquare( i - 1, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //SOUTH
            if (i != 8 && !(md.getSquare( i + 1, j ) instanceof River)
                    && (getPiece( i + 1, j ) == null || (getPiece( i + 1, j ).isP1() != chosen.isP1())))
                getSquare( i + 1, j ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //WEST
            if (j != 0 && !(md.getSquare( i, j - 1 ) instanceof River)
                    && (getPiece( i, j - 1 ) == null || (getPiece( i, j - 1 ).isP1() != chosen.isP1())))
                getSquare( i, j - 1 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
            //EAST
            if (j != 6 && !(md.getSquare( i, j + 1 ) instanceof River)
                    && (getPiece( i, j + 1 ) == null || (getPiece( i, j + 1 ).isP1() != chosen.isP1())))
                getSquare( i, j + 1 ).setBorder( BorderFactory.createLineBorder( Color.ORANGE, 5 ) );
        }

    }

    /**
     * Removes highlight after move that is successful.
     */
    public void removeHighlight()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                getSquare( i, j ).setBorder( UIManager.getBorder( "Button.border" ) );
            }
        }
    }

    //GET COLOR FROM START FRAME
    /**
     * @return Color of player for top Pieces.
     */
    public Color getTopPieceColor() {
        if(p1Rank > p2Rank) {
            return p1Color;
        } else {
            return p2Color;
        }
    }

    /**
     * @return Color of player for bottom Pieces.
     */
    public Color getBottomPieceColor() {
        if(p1Rank > p2Rank) {
            return p2Color;
        } else {
            return p1Color;
        }
    }

    //LEFT INFO
    /**
     * Information found on the left side of the game window.
     */
    public void displayInformation() {
        //displayPlayerTurn container
        playerTurn.setBounds(34, 110, 163, 122);
        playerTurn.setIcon(turnImg);
        playerTurn.setOpaque(false);
        playerTurn.setHorizontalAlignment(JLabel.CENTER);
        //name
        ptName.setBounds(23, 25, 118, 30);
        ptName.setForeground(new Color(0, 0, 0));
        ptName.setFont(new Font("Times New Roman", Font.BOLD, 25));
        ptName.setOpaque(false);
        ptName.setText(getFirstPlayerName() + "'s");
        ptName.setHorizontalAlignment(JLabel.CENTER);
        //turn
        ptTurn.setBounds(23, 57, 118, 39);
        ptTurn.setForeground(new Color(0, 0, 0));
        ptTurn.setFont(new Font("Times New Roman", Font.BOLD, 25));
        ptTurn.setOpaque(false);
        ptTurn.setText("Turn");
        ptTurn.setHorizontalAlignment(JLabel.CENTER);
        
        //display player 1 details
        info.setBounds(33, 265, 162, 258);
        info.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        info.setIcon(detailsBg);
        info.setOpaque(false);
        info.setHorizontalAlignment(JLabel.CENTER);
        //p1Name
        l1.setBounds(39, 33, 86, 26);
        l1.setForeground(new Color(0, 0, 0));
        l1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        l1.setOpaque(false);
        l1.setText(p1Name);
        l1.setHorizontalAlignment(JLabel.CENTER);
        //p1Color
        l2.setBounds(39, 61, 86, 26);
        l2.setBackground(p1Color);
        l2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        l2.setOpaque(true);
        l2.setText("COLOR");
        l2.setHorizontalAlignment(JLabel.CENTER);
        //p1 no. of pieces
        l3.setBounds(39, 89, 86, 26);
        l3.setForeground(p1Color);
        l3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        l3.setOpaque(false);
        l3.setText("");
        l3.setHorizontalAlignment(JLabel.CENTER);

        //p2Name
        l4.setBounds(39, 152, 86, 26);
        l4.setForeground(new Color(0, 0, 0));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        l4.setOpaque(false);
        l4.setText(p2Name);
        l4.setHorizontalAlignment(JLabel.CENTER);
        //p2Color
        l5.setBounds(39, 178, 86, 26);
        l5.setBackground(p2Color);
        l5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        l5.setOpaque(true);
        l5.setText("COLOR");
        l5.setHorizontalAlignment(JLabel.CENTER);
        //p2 no. of pieces
        l6.setBounds(39, 208, 86, 26);
        l6.setForeground(p1Color);
        l6.setFont(new Font("Times New Roman", Font.BOLD, 16));
        l6.setOpaque(false);
        l6.setText("");
        l6.setHorizontalAlignment(JLabel.CENTER);

        //add components
        background.add(playerTurn);
        playerTurn.add(ptName);
        playerTurn.add(ptTurn);

        background.add(info);
        info.add(l1);
        info.add(l2);
        info.add(l3);
        info.add(l4);
        info.add(l5);
        info.add(l6);
    }

    /**
     * Additional information found on the left side of the game window.
     */
    public void displayButtons() {
        //help label
        c1.setLayout(new BorderLayout());
        c1.setBounds(40, 567, 62, 62);
        c1.setOpaque(false);
        c1.repaint();
        //help button
        help.setIcon(mech);
        help.setSize(62, 62);
        help.setFocusable(false);
        help.setOpaque(false);
        help.setContentAreaFilled(false);
        //add
        background.add(c1);
        c1.add(help);

        //ranks label
        c2.setLayout(new BorderLayout());
        c2.setBounds(124, 567, 62, 62);
        c2.setOpaque(false);
        c2.repaint();
        //ranks button
        ranks.setIcon(ranksImg);
        ranks.setSize(62, 62);
        ranks.setFocusable(false);
        ranks.setOpaque(false);
        ranks.setContentAreaFilled(false);
        //add
        background.add(c2);
        c2.add(ranks);

    }

    /**
     *
     * @return name of player 1
     */
    public String getFirstPlayerName() {
        if(p1Rank > p2Rank) {
            return p1Name;
        } else {
            return p2Name;
        }
    }

    /**
     *
     * @return name of player 2
     */
    public String getSecondPlayerName() {
        if(p1Rank > p2Rank) {
            return p2Name;
        } else {
            return p1Name;
        }
    }

    //ADD ACTION LISTENERS
    /**
     * Adds buttons for board to the GameFrameController.
     * @param listen
     */
    public void addButtonHandler(ActionListener listen)
    {
        for (int i = 0; i < squaresView.length; i++)
        {
            for (int j = 0; j < squaresView[0].length; j++)
            {
                squaresView[i][j].addActionListener( listen );
            }
        }
    }

    /**
     * Adds help button to the GameFrameController.
     * @param listen
     */
    public void addHelpButton(ActionListener listen)
    {
        help.addActionListener(listen);
    }

    /**
     * Adds ranks button to the GameFrameController.
     * @param listen
     */
    public void addRanksButton(ActionListener listen)
    {
        ranks.addActionListener( listen );
    }

    //GETTERS
    /**
     *
     * @param i - x coordinate
     * @param j - y coordinate
     * @return a cell in JLabel board
     */
    public JLabel getSquare(int i, int j)
    {
        return boxes[i][j];
    }

    /**
     *
     * @param i - x coordinate
     * @param j - y coordinate
     * @return a JButton in Model board
     */
    public JButton getSquaresView(int i, int j) {
        return squaresView[i][j];
    }

    /**
     *
     * @param a- x coordinate
     * @param b - y coordinate
     * @return Piece if found in given coordinate, null otherwise
     */
    public Piece getPiece(int a, int b)
    {
        for (int k = 0; k < md.getPieces().size(); k++)
        {
            Integer w = a;
            Integer z = b;
            Integer x = md.getPieces().get( k ).getPosX();
            Integer y = md.getPieces().get( k ).getPosY();

            if (w.equals( x )) // X POS
                if (z.equals( y )) // Y POS
                    return md.getPieces().get( k );
        }
        return null;
    }

    /**
     *
     * @return player turn
     */
    public JLabel getTurnLabel() {
        return ptName;
    }

    /**
     *
     * @return frame of game board
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     *
     * @return help button
     */
    public JButton getHelp() {
        return help;
    }

    /**
     *
     * @return ranks of Pieces
     */
    public JButton getRanks() {
        return ranks;
    }
}
