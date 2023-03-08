package controller;

import view.frames.HelpFrame;
import view.frames.StartFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controller for the LaunchFrameController.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class LaunchFrameController {

    /**
     * Constructor for LaunchFrameController.
     *
     * @param launchFrame - JFrame of launch frame
     * @param startBtn - button to start game
     * @param quitBtn - button to quit game
     * @param instructionsBtn - button for instructions
     */
    public LaunchFrameController(JFrame launchFrame, JButton startBtn, JButton quitBtn, JButton instructionsBtn) {
        ActionListener startGameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == startBtn) {
                    launchFrame.dispose();
                    StartFrame startFrame = new StartFrame();
                }
            }
        };

        ActionListener quitGameListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == quitBtn) {
                    int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?",
                            " ", JOptionPane.YES_NO_OPTION);

                    if (answer == JOptionPane.YES_OPTION) {
                        launchFrame.dispose();
                    }
                }
            }
        };

        ActionListener instructionsListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == instructionsBtn) {
                    new HelpFrame();
                }
            }
        };

        startBtn.addActionListener(startGameListener);
        quitBtn.addActionListener(quitGameListener);
        instructionsBtn.addActionListener(instructionsListener);
    }
}
