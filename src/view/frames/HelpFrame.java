package view.frames;

import javax.swing.*;
import java.util.Objects;
/**
 * The View of the instructions of the game.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class HelpFrame {
    /**
     * Attributes of HelpFrame.
     */
    private ImageIcon mouse, inst;
    private JFrame frame = new JFrame("How to Play Animal Chess");
    private JLabel background = new JLabel();
    private JButton exit = new JButton();

    /**
     * Constructor of HelpFrame.
     */
    public HelpFrame() {
    mouse = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/pieces/1.png")));
    inst = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/GameFrame/instructions.png")));
    frame.setIconImage(mouse.getImage());
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(610, 440);
    frame.setResizable(false);
    frame.setLayout(null);
    //add components
    frame.getContentPane().add(background);
    background.setSize(600, 400);
    background.setIcon(inst);
    background.setHorizontalAlignment(JLabel.CENTER);
    background.setVerticalAlignment(JLabel.CENTER);
    }
}
