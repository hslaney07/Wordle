import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Class to allow user to type on keyboard.
 * @author Haley Slaney
 */
public class UserKeyBoard {

    private final WordlePanel wordlePanel;

    /**
     * Adds a label to the frame that allows the user to type the alphabet and used enter/back space.
     * @param wp WordlePanel where the results of the user typing are displayed.
     * @param f JFrame displaying the game.
     */
    public UserKeyBoard(WordlePanel wp, JFrame f){
        wordlePanel = wp;

        JLabel label = new JLabel();

        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

        // updating maps for the alphabet keys
        for(int i = 0; i < alphabet.length; i++){
            String s = alphabet[i];
            String action = s + "Action";
            LetterAction letterAction =  new LetterAction(s);
            label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(65+i, 0), action);
            label.getActionMap().put(action,letterAction);
        }

        // updating maps for enter
        LetterAction action =  new LetterAction("ENTER");
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "EnterAction");
        label.getActionMap().put("EnterAction", action);

        // updating maps for back
        action =  new LetterAction("BACK");
        label.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "BackAction");
        label.getActionMap().put("BackAction", action);

        f.add(label);
    }

    /**
     * Class that monitors when keyboard is pressed.
     */
    public class LetterAction extends AbstractAction{

        String letter;

        public LetterAction(String s){
            letter =  s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(letter);
            wordlePanel.magic(letter);
        }
    }


}
