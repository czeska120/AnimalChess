package view.frames;

import controller.StartFrameController;
import view.features.*;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
/**
 * The View of the randomizer.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class StartFrame {
    /**
     * Attributes of StartFrame.
     */
    private static final Dimension OUTER_FRAME_DIM = new Dimension(800, 800);
    private static final Dimension TF_DIM = new Dimension(200, 50);
    private JFrame frame = new JFrame("Animal Chess");
    private JLabel background = new JLabel();
    private JTextField p1Name = new JTextField();
    private JTextField p2Name = new JTextField();
    private JButton c1 = new JButton();
    private JButton c2 = new JButton();
    private JButton c3 = new JButton();
    private JButton c4 = new JButton();
    private JButton c5 = new JButton();
    private JButton c6 = new JButton();
    private JButton c7 = new JButton();
    private JButton c8 = new JButton();
    private JLabel p1DoneLabel = new JLabel();
    private JLabel p2DoneLabel = new JLabel();
    private JLabel p1DClrLbl = new JLabel();
    private JLabel p2DClrLbl = new JLabel();
    private JButton playBtn = new JButton();
    private JButton p1SColor = new JButton();
    private JButton p2SColor = new JButton();
    private JButton p1DColor = new RoundButton("", new Color(35, 133, 25),
            new Color(255, 255, 255));;
    private JButton p2DColor = new RoundButton("", new Color(35, 133, 25),
            new Color(255, 255, 255));
    private JButton p1Done = new RoundButton("", new Color(35, 133, 25),
            new Color(255, 255, 255));;
    private JButton p2Done = new RoundButton("", new Color(35, 133, 25),
            new Color(255, 255, 255));

    private StartFrameController startFrameController = new StartFrameController(frame, playBtn, p1Done, p2Done,
                                                            p1Name, p2Name, p1SColor, p2SColor, p1DColor, p2DColor,
                                                            c1, c2, c3, c4, c5, c6, c7, c8);

    /**
     * Constructor of StartFrame.
     */
    public StartFrame() {
        //PLAYER 1 LABEL
        ImageIcon p1Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/P1.png")));
        JLabel p1 = new JLabel();
        p1.setIcon(p1Img);
        p1.setBounds(70, 47, 266, 48);
        p1.setFocusable(false);
        p1.setOpaque(false);
        p1.setOpaque(false);
        p1.repaint();

        //PLAYER 2 LABEL
        ImageIcon p2Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/P2.png")));
        JLabel p2 = new JLabel();
        p2.setIcon(p2Img);
        p2.setBounds(450, 45, 266, 48);
        p2.setFocusable(false);
        p2.setOpaque(false);
        p2.setOpaque(false);
        p2.repaint();

        //PLAYER 1 & 2 NAMES
        TextFieldsButtons();

        //PLAYER 1 & 2 COLORS
        colorButtons();

        //DONE BUTTON (MOVE TO GAME FRAME)
        playBtn();

        //CARDS
        initCards();

        //BACKGROUND
        ImageIcon bgImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/start frame bg.png")));
        background.setSize(OUTER_FRAME_DIM);
        background.setIcon(bgImg);
        //add components
        background.add(p1);
        background.add(p2);
        addCardButtons();

        //FRAME
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(OUTER_FRAME_DIM);
        frame.setResizable(false);
        frame.setLayout(null);
        //add components
        frame.getContentPane().add(background);
    }

    /**
     * Initializes cards for randomized feature.
     */
    public void initCards() {
        //CARD 1 BUTTON
        ImageIcon c1Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c1.setIcon(c1Img);
        c1.setBounds(72, 275, 127, 191);
        c1.setFocusable(false);
        c1.setOpaque(false);
        c1.setContentAreaFilled(false);
        c1.setBorderPainted(false);
        
        //CARD 2 BUTTON
        ImageIcon c2Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c2.setIcon(c2Img);
        c2.setBounds(242, 275, 127, 191);
        c2.setFocusable(false);
        c2.setOpaque(false);
        c2.setContentAreaFilled(false);
        c2.setBorderPainted(false);
        
        //CARD 3 BUTTON
        ImageIcon c3Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c3.setIcon(c3Img);
        c3.setBounds(405, 275, 127, 191);
        c3.setFocusable(false);
        c3.setOpaque(false);
        c3.setContentAreaFilled(false);
        c3.setBorderPainted(false);
        
        //CARD 4 BUTTON
        ImageIcon c4Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c4.setIcon(c4Img);
        c4.setBounds(572, 275, 127, 191);
        c4.setFocusable(false);
        c4.setOpaque(false);
        c4.setContentAreaFilled(false);
        c4.setBorderPainted(false);
        
        //CARD 5 BUTTON
        ImageIcon c5Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c5.setIcon(c5Img);
        c5.setBounds(70, 485, 127, 191);
        c5.setFocusable(false);
        c5.setOpaque(false);
        c5.setContentAreaFilled(false);
        c5.setBorderPainted(false);
        
        //CARD 6 BUTTON
        ImageIcon c6Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c6.setIcon(c6Img);
        c6.setBounds(242, 485, 127, 191);
        c6.setFocusable(false);
        c6.setOpaque(false);
        c6.setContentAreaFilled(false);
        c6.setBorderPainted(false);
        
        //CARD 7 BUTTON
        ImageIcon c7Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c7.setIcon(c7Img);
        c7.setBounds(405, 485, 127, 191);
        c7.setFocusable(false);
        c7.setOpaque(false);
        c7.setContentAreaFilled(false);
        c7.setBorderPainted(false);
        
        //CARD 8 BUTTON
        ImageIcon c8Img = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/cards/animal chess.png")));
        c8.setIcon(c8Img);
        c8.setBounds(572, 485, 127, 191);
        c8.setFocusable(false);
        c8.setOpaque(false);
        c8.setContentAreaFilled(false);
        c8.setBorderPainted(false);

        //DISABLED DEFAULT
        c1.setEnabled(false);
        c2.setEnabled(false);
        c3.setEnabled(false);
        c4.setEnabled(false);
        c5.setEnabled(false);
        c6.setEnabled(false);
        c7.setEnabled(false);
        c8.setEnabled(false);
    }

    /**
     * Adds cards to the view.
     */
    public void addCardButtons() {
        background.add(c1);
        background.add(c2);
        background.add(c3);
        background.add(c4);
        background.add(c5);
        background.add(c6);
        background.add(c7);
        background.add(c8);
    }

    /**
     * Sets the fields where players will put their names.
     */
    public void TextFieldsButtons() {
        //PLAYER 1 NAME TEXT FIELD
        p1Name.setPreferredSize(TF_DIM);
        p1Name.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        p1Name.setBounds(70, 110, 230, 48);
        p1Name.setForeground(new Color(248, 205, 139));
        p1Name.setBackground(new Color(12, 50, 82));
        p1Name.setCaretColor(new Color(248, 205, 139));
        p1Name.setHorizontalAlignment(JTextField.CENTER);
        p1Name.setDocument(new JTextFieldLimit(10));
        p1Name.setText("SET NAME");

        //PLAYER 2 NAME TEXT FIELD
        p2Name.setPreferredSize(TF_DIM);
        p2Name.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        p2Name.setBounds(450, 110, 230, 48);
        p2Name.setForeground(new Color(248, 205, 139));
        p2Name.setBackground(new Color(12, 50, 82));
        p2Name.setCaretColor(new Color(248, 205, 139));
        p2Name.setHorizontalAlignment(JTextField.CENTER);
        p2Name.setDocument(new JTextFieldLimit(10));
        p2Name.setText("SET NAME");

        //SUBMIT ICON
        ImageIcon submitImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/submit.png")));

        //PLAYER 1 SUBMIT LABEL
        p1DoneLabel.setLayout(new FlowLayout());
        p1DoneLabel.setBounds(300, 113, 50, 50);
        p1DoneLabel.setOpaque(false);
        p1DoneLabel.repaint();

        //PLAYER 2 SUBMIT LABEL
        p2DoneLabel.setLayout(new FlowLayout());
        p2DoneLabel.setBounds(680, 113, 50, 50);
        p2DoneLabel.setOpaque(false);
        p2DoneLabel.setOpaque(false);
        p2DoneLabel.repaint();

        //PLAYER 1 CHECK BUTTON
        p1Done.setIcon(submitImg);
        p1Done.setSize(40, 40);
        p1Done.setFocusable(false);
        p1Done.setOpaque(false);
        p1Done.setContentAreaFilled(false);

        //PLAYER 2 CHECK BUTTON
        p2Done.setIcon(submitImg);
        p2Done.setSize(40, 40);
        p2Done.setFocusable(false);
        p2Done.setOpaque(false);
        p2Done.setContentAreaFilled(false);

        //add components
        p1DoneLabel.add(p1Done);
        p2DoneLabel.add(p2Done);
        background.add(p1Name);
        background.add(p2Name);
        background.add(p1DoneLabel);
        background.add(p2DoneLabel);
    }

    /**
     * Sets the fields where players will choose their colors.
     */
    public void colorButtons() {
        //PLAYER 1 COLOR
        p1SColor.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        p1SColor.setBounds(70, 170, 230, 48);
        p1SColor.setFocusable(false);
        p1SColor.setForeground(new Color(248, 205, 139));
        p1SColor.setBackground(new Color(12, 50, 82));
        p1SColor.setHorizontalAlignment(JButton.CENTER);
        p1SColor.setText("SET COLOR");

        //PLAYER 2 COLOR
        p2SColor.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        p2SColor.setBounds(450, 170, 230, 48);
        p2SColor.setFocusable(false);
        p2SColor.setForeground(new Color(248, 205, 139));
        p2SColor.setBackground(new Color(12, 50, 82));
        p2SColor.setHorizontalAlignment(JButton.CENTER);
        p2SColor.setText("SET COLOR");

        //SUBMIT ICON
        ImageIcon submitImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/StartFrame/submit.png")));

        //PLAYER 1 SUBMIT COLOR LABEL
        p1DClrLbl.setLayout(new FlowLayout());
        p1DClrLbl.setBounds(300, 172, 50, 50);
        p1DClrLbl.setOpaque(false);
        p1DClrLbl.setOpaque(false);
        p1DClrLbl.repaint();

        //PLAYER 2 SUBMIT COLOR LABEL
        p2DClrLbl.setLayout(new FlowLayout());
        p2DClrLbl.setBounds(680, 172, 50, 50);
        p2DClrLbl.setOpaque(false);
        p2DClrLbl.setOpaque(false);
        p2DClrLbl.repaint();

        //PLAYER 1 CHECK BUTTON
        p1DColor.setIcon(submitImg);
        p1DColor.setSize(40, 40);
        p1DColor.setFocusable(false);
        p1DColor.setOpaque(false);
        p1DColor.setContentAreaFilled(false);

        //PLAYER 2 CHECK BUTTON
        p2DColor.setIcon(submitImg);
        p2DColor.setSize(40, 40);
        p2DColor.setFocusable(false);
        p2DColor.setOpaque(false);
        p2DColor.setContentAreaFilled(false);

        //add components
        background.add(p1SColor);
        background.add(p2SColor);
        p1DClrLbl.add(p1DColor);
        p2DClrLbl.add(p2DColor);
        background.add(p1DClrLbl);
        background.add(p2DClrLbl);
    }

    /**
     * Displays play button.
     */
    public void playBtn() {
        playBtn.setBounds(310, 690, 150, 48);
        playBtn.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        playBtn.setFocusable(false);
        playBtn.setForeground(new Color(12, 50, 82));
        playBtn.setBackground(new Color(248, 205, 139));
        playBtn.setHorizontalAlignment(JButton.CENTER);
        playBtn.setText("PLAY");
        playBtn.setEnabled(false);
        background.add(playBtn);
    }
}
