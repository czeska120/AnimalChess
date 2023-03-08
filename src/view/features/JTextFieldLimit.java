package view.features;

import javax.swing.text.*;

/**
 * Setting limit of letters to name of player.
 *
 * @author ALABANZA, Chelsea
 * @author SILVESTRE, Franczeska
 */
public class JTextFieldLimit extends PlainDocument {
    /**
     * Attributes of JTextFieldLimit.
     */
    private int limit;

    /**
     * Constructor of JTextFieldLimit.
     * @param limit - set limit of characters
     */
    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}