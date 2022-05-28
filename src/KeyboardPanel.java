import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

/**
 * Sets up the keyboard panel
 * @author Haley Slaney
 */
public class KeyboardPanel extends JPanel {
    private final int width;                              // width for buttons
    private final int height;                             // height for buttons
    private final int ogWidth;                            // width of total panel
    private HashMap<String, Button> buttonHashMap;       // hashmap of buttons

    /**
     * Sets up the panel.
     * @param w Width of the panel.
     * @param h Height of the panel.
     */
    KeyboardPanel(int w, int h){
        this.setPreferredSize(new Dimension(w, h));
        this.setBorder(new LineBorder(Color.black, 3));
        this.setVisible(true);

        ogWidth = w;
        width = (w-90)/10;
        height = (h -20)/3;
        buttonHashMap = new HashMap<String, Button>();
    }

    /**
     * Sets up the buttons in this panel.
     * @param wordlePanel The panel that displays the result of clicking the buttons.
     */
    public void setButtons(WordlePanel wordlePanel){
        // arrays for letters in alphabet by row on keyboard
        String[] alphabet1 = new String[]{"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"};
        String[] alphabet2 = new String[]{"A", "S", "D", "F", "G", "H", "J", "K", "L"};
        String[] alphabet3 = new String[]{"Z", "X", "C", "V", "B", "N", "M"}; // row also has enter and back

        Clicklistener click= new Clicklistener(wordlePanel);

        //setup row 1 of keyboard q...p
        for (String s : alphabet1) {
            Button current = new Button(s);
            current.setBackground(Color.gray);
            current.setFont(new Font("Sans-serif", Font.BOLD, 20));
            current.setPreferredSize(new Dimension(width, height));
            current.addActionListener(click);
            add(current);
            buttonHashMap.put(s, current);
        }

       // setup row 2 of keyboard a...l
        for (String s : alphabet2) {
            Button current = new Button(s);
            current.setBackground(Color.gray);
            current.setFont(new Font("Sans-serif", Font.BOLD, 20));
            current.setPreferredSize(new Dimension(width, height));
            current.addActionListener(click);
            add(current);
            buttonHashMap.put(s, current);
        }

        // setup row 3 of keyboard, enter, z... m, back
       int extra = (int)((ogWidth - 7*width - 60)/2.2);
        Button enter = new Button("ENTER");
        enter.setBackground(Color.gray);
        enter.setFont(new Font("Sans-serif", Font.BOLD, 10));
        enter.setPreferredSize(new Dimension(extra, height));
        enter.addActionListener(click);
        buttonHashMap.put("ENTER", enter);
        add(enter);

        for (String s : alphabet3) {
            Button current = new Button(s);
            current.setBackground(Color.gray);
            current.setFont(new Font("Sans-serif", Font.BOLD, 20));
            current.setPreferredSize(new Dimension(width, height));
            current.addActionListener(click);
            add(current);
            buttonHashMap.put(s, current);
        }

        extra = (int)((ogWidth - 7*width - 60)/2.2);
        Button back = new Button("BACK");
        back.setBackground(Color.gray);
        back.setFont(new Font("Sans-serif", Font.BOLD, 10));
        back.setPreferredSize(new Dimension(extra, height));
        back.addActionListener(click);
        buttonHashMap.put("BACK", back);
        add(back);
    }

    /**
     * Returns a hashmap of the buttons in this panel.
     * @return A hashmap of the buttons in this panel.
     */
    public HashMap<String, Button> getButtonHash(){
        return buttonHashMap;
    }


    /**
     * Class for when a button is clicked.
     */
    static class Clicklistener implements ActionListener {
        private final WordlePanel wordlePanel;

        /**
         * Initializes wordlePanel field.
         * @param wp The panel being stored in a field.
         */
        Clicklistener(WordlePanel wp){
            this.wordlePanel = wp;
        }

        /**
         * When button is clicked, sends info to the wordlePanel.
         * @param e The ActionEvent (Button clicked).
         */
        public void actionPerformed(ActionEvent e) {
            Button b = (Button) e.getSource();
            wordlePanel.magic(b.getLabel(), b);
        }
    }

}



