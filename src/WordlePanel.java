import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class WordlePanel extends JPanel {

    private PrettyWords prettyWords;            // frame

    private DictionarySetUp dictionary;         // dictionary

    private LinkedList<JPanel> panels;          // keeps track of all the panels(boxes)
    private Stack<JPanel> currentRow;           // keeps track of panels in row backwards: panel4 is popped first when full
    private Stack<JLabel> currentRowLabels;     // keeps track of the labels of the panels in the current row
    private Stack<JLabel> backLabel;            // keeps track of labels when backspaced is used
    private Stack<Button> buttonsClicked;       // keeps track of buttons clicked - helpful when changing them to yellow/green/black when letter guessed

    private String word;                        // word guessed so far in the current row
    private final String correctWord;           // word of the day!
    private int numLetters;                     // number of letters guessed so far in the current row
    private int rowCount;                       // current row (0->5)


    /**
     * Sets up basic graphics for this panel.
     * @param w The width of the panel.
     * @param h The height of the panel.
     * @param pw The frame which contains this panel.
     */
    WordlePanel(int w, int h, PrettyWords pw){

        // general panel setup
        this.setPreferredSize(new Dimension(w+5,h+10));
        this.setBackground(new Color(90, 140, 255).darker().darker());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 8));
        this.setVisible(true);

        // initialize fields
        panels = new LinkedList<JPanel>();
        currentRow = new Stack<JPanel>();
        currentRowLabels = new Stack<JLabel>();
        backLabel = new Stack<JLabel>();
        buttonsClicked = new Stack<Button>();

        dictionary = new DictionarySetUp();
        correctWord = (new WordHashMap()).getTodayWord();

        numLetters = 0;
        word = "";
        rowCount = 0;

        prettyWords = pw;

        // set up panel with the 30 boxes
        setup((w-40)/5);
    }

    /**
     * Displays 30 boxes in a 5X6 grid.
     * @param boxSide The size of the side of the each box.
     */
    public void setup(int boxSide){
        // set up panel with the 30 boxes
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                JPanel panel = new JPanel();
                panel.setBackground(new Color(90, 140, 255).darker().darker());
                panel.setBorder(new LineBorder(Color.black, 3));
                panel.setPreferredSize(new Dimension(boxSide, boxSide));
                this.add(panel);
                panels.add(panel);
            }
        }
    }

    /**
     * Calls the other magic method.
     * @param label String of the label.
     */
    public void magic(String label) {
        HashMap<String, Button> hashButton = prettyWords.getButtonHash();
        Button b = hashButton.get(label);
        magic(label,b);
    }

    /**
     * Analyzes the key/button pressed and adjusts graphics accordingly.
     * @param label The label of the key/button.
     * @param button The button for the label.
     */
    public void magic(String label, Button button) {

        // if backspace is pressed
        if(label.equals("BACK") && numLetters>0 ){

            JLabel currentLabel = currentRowLabels.pop();
            currentLabel.setText("");
            backLabel.push(currentLabel);

            panels.addFirst(currentRow.pop());

            word = word.substring(0,--numLetters);

            buttonsClicked.pop();
        }

        // if a letter is pressed and row is not full
        else if(!label.equals("ENTER") && numLetters < 5 && !label.equals("BACK") && rowCount < 6){

            JPanel current;
            JLabel text;

            // if backspace has been used
            if(backLabel.size()!=0){
                current = panels.remove();
                text = backLabel.pop();
                text.setText(label);
            }
            //otherwise...
            else{
                current = panels.remove();
                text = new JLabel(label);
                current.add(text);
                text.setForeground(Color.BLACK);
                text.setFont(new Font("Sans-serif", Font.BOLD, 55));
            }

            // adjust fields
            numLetters++;
            word += label;

            currentRow.add(current);
            currentRowLabels.push(text);

            buttonsClicked.push(button);
        }

        // enter is pressed and row is full
        else if(label.equals("ENTER") && numLetters==5){
            // if the word is valid
            if(dictionary.realWord(word)){

                rowCount++;

                //find correct letters in correct spots
                String holder = correctWord;
                String wordGuessed = word;
                boolean guessRight = correctWord.equals(word);
                int i = 0;
                LinkedList<String> letterMoreThanOnce = new LinkedList<String>();

                while(i < 5){
                    String correctWordLetter = "" + holder.charAt(i);
                    String charGuessed = "" +  wordGuessed.charAt(i);

                    // if the characters are the same (same spot and char) - set panel and button green
                    if(charGuessed.equals(correctWordLetter)){

                        JPanel current = currentRow.elementAt(i);
                        current.setBackground(Color.GREEN);

                        Button b = buttonsClicked.elementAt(i);
                        b.setBackground(Color.green);

                        int indexOther = holder.lastIndexOf(charGuessed);
                        if(indexOther!=-1 && indexOther!=i) {
                        	letterMoreThanOnce.push(charGuessed);
                        }

                        if(i==4){
                            holder = holder.substring(0, i) + "!";
                            wordGuessed = wordGuessed.substring(0, i) + "?";
                        }else{
                            holder = holder.substring(0, i) + "!" + holder.substring(i+1);
                            wordGuessed = wordGuessed.substring(0, i) + "?" + wordGuessed.substring(i+1);
                        }
                    }
                    i++;
                }

                // letter appears more than once in word guessed and is correct in at least one spot
                // if other spot where letter is is not correct, panel needs to go white
                for(String s : letterMoreThanOnce){
                    holder = holder.replaceAll(s, "!");
                }

                //find correct letters in wrong spot
                i = 0;
                while(i < 5){

                    String charGuessed = "" +  wordGuessed.charAt(i);
                    int index = holder.indexOf(charGuessed);

                    if(index!=-1){

                        JPanel current =currentRow.elementAt(i);
                        current.setBackground(Color.yellow);

                        Button b = buttonsClicked.elementAt(i);
                        if (!b.getBackground().equals(Color.green)) {
                            b.setBackground(Color.yellow);
                        }

                        holder = holder.replaceAll(charGuessed, "!");
                        wordGuessed = wordGuessed.replaceFirst(charGuessed, "?");
                    }
                    i++;
                }

                //any incorrect letters-> white background
                i = 0;
                while(i < 5){

                    String letter = "" + wordGuessed.charAt(i);

                    if(!letter.equals("?")){

                        Button b = buttonsClicked.elementAt(i);
                        if (!b.getBackground().equals(Color.green)) {
                            if(!b.getBackground().equals(Color.yellow)){
                                b.setBackground(Color.BLACK);
                            }
                        }

                        JPanel current =currentRow.elementAt(i);
                        current.setBackground(Color.white);
                    }
                    i++;
                }

                // see if they guessed right or are out of guesses
                if(guessRight){
                    prettyWords.celebrate();
                    rowCount = 6;
                }
                else if(rowCount==6){
                    prettyWords.fail();
                }

                //adjust fields
                currentRow = new Stack<JPanel>();
                numLetters = 0;
                word = "";
                buttonsClicked = new Stack<Button>();
            }
        }
    }


}
