package view.features;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
/**
 * Customized JButton.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class RoundButton extends JButton {
    /**
     * Attributes of RoundButton.
     */
    private Color color;
    private Color colorClick;

    /**
     * RoundButton constructor.
     * @param label - label of JButton
     * @param color - color to be set
     * @param click - color when clicked
     */
    public RoundButton(String label, Color color, Color click) {
        super(label);

        this.color = color;
        colorClick = click;

        setBackground(color);
        setFocusable(false);

/*
 These statements enlarge the button so that it
 becomes a circle rather than an oval.
*/
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

/*
 This call causes the JButton not to paint the background.
 This allows us to paint a round background.
*/
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(colorClick);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
//        g.setColor(color);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    // Hit detection.
    Shape shape;

    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}

