package view.frames;

import controller.LaunchFrameController;
import javax.swing.*;
import java.awt.*;
/**
 * The View of the window prior to the randomizer.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class LaunchFrame {
    /**
     * Attributes of LaunchFrame.
     */
    private static final Dimension OUTER_FRAME_DIM = new Dimension(800, 800);
    private JFrame frame = new JFrame("Animal Chess");
    private JButton startGame = new JButton();
    private JButton quitGame = new JButton();
    private JButton instructions = new JButton();
    private LaunchFrameController launchFrameController =
            new LaunchFrameController(frame, startGame,  quitGame, instructions);

    /**
     * Constructor of LaunchFrame.
     */
    public LaunchFrame() {
        //TITLE LABEL
        ImageIcon titleImg = new ImageIcon(this.getClass().getResource("/LaunchFrame/launch title.png"));
        JLabel title = new JLabel();
        title.setIcon(titleImg);
        title.setBounds(159, 87, 481, 200);
        title.setOpaque(false);
        title.setOpaque(false);
        title.repaint();

        //START GAME BUTTON
        ImageIcon startImg = new ImageIcon(this.getClass().getResource("/LaunchFrame/start.png"));
        startGame.setIcon(startImg);
        startGame.setBounds(282, 394, 233, 84);
        startGame.setFocusable(true);
        startGame.setOpaque(false);
        startGame.setContentAreaFilled(false);
        startGame.setBorderPainted(false);

        //QUIT GAME BUTTON
        ImageIcon quitImg = new ImageIcon(this.getClass().getResource("/LaunchFrame/quit.png"));
        quitGame.setIcon(quitImg);
        quitGame.setBounds(282, 505, 233, 84);
        quitGame.setFocusable(true);
        quitGame.setOpaque(false);
        quitGame.setContentAreaFilled(false);
        quitGame.setBorderPainted(false);
        
        //INSTRUCTIONS BUTTON
        ImageIcon instructionsImg = new ImageIcon(this.getClass().getResource("/LaunchFrame/instructions.png"));
        instructions.setIcon(instructionsImg);
        instructions.setBounds(592, 690, 157, 56);
        instructions.setFocusable(true);
        instructions.setOpaque(false);
        instructions.setContentAreaFilled(false);
        instructions.setBorderPainted(false);

        //BACKGROUND
        ImageIcon bgImg = new ImageIcon(this.getClass().getResource("/LaunchFrame/launch bg.png"));
        JLabel background = new JLabel();
        background.setSize(OUTER_FRAME_DIM);
        background.setIcon(bgImg);
        //add components
        background.add(title);
        background.add(startGame);
        background.add(quitGame);
        background.add(instructions);

        //FRAME
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(OUTER_FRAME_DIM);
        frame.setResizable(false);
        frame.setLayout(null);
        //add components
        frame.getContentPane().add(background);
    }
}
