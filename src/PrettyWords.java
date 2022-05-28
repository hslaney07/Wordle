import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

/**
 * Sets up the JFrame for the game.
 * @author Haley Slaney
 */
public class PrettyWords {

    private JFrame f;                          // the frame
    private final int topPanelHeight;          // the top panel height
    private Color background;                  // general background color

    private JPanel panelBottom;                // bottom panel containing keyboard panel
    private KeyboardPanel keyboardPanel;       // key board panel

    /**
     * Sets up JFrame.
     */
    public PrettyWords(){
        initialize();
        topPanelHeight = (f.getHeight()/10);
        setupPart1();
        setupPart2();
    }

    /**
     * Sets up the basic graphics of the JFrame.
     */
    public void initialize() {

        f = new JFrame();
        f.setVisible(true);
        f.setTitle("Haley's Wordle");

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setBackground(Color.black);
        background = (new Color(90, 140, 255)).darker().darker();

        FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 0, 0);
        f.setLayout(flow);
    }

    /**
     * Sets up the top of the frame with a panel containing the text, "MammaMiaOrdle".
     */
    public void setupPart1() {
        // top panel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(f.getWidth(), topPanelHeight));
        panel.setBackground(background);
        panel.setBorder(new LineBorder(Color.white, 5));
        f.add(panel);

        //text part
        JLabel wordle = new JLabel("MammaMiaOrdle");
        panel.add(wordle);
        wordle.setForeground(Color.white);
        wordle.setFont(new Font("Sans-serif", Font.BOLD, 50));
    }

    /**
     * Sets up the rest of the frame including the wordle part and keyboard part.
     */
    public void setupPart2() {

        // width and height stuff - used for wordle panel and keyboard
        int width = (int)(f.getWidth()/3.5);
        int wordlePartHeight = (((width-40)/5)*6)+50;
        int keyboardHeight = f.getHeight() - wordlePartHeight - topPanelHeight -5;

        // middle panel - will contain wordle panel
        JPanel panel1 = new JPanel();
        panel1.setPreferredSize(new Dimension(f.getWidth(), wordlePartHeight + 20));
        panel1.setBackground(background);
        panel1.setVisible(true);

        // wordle panel
        WordlePanel wp = new WordlePanel(width, wordlePartHeight, this);

        // bottom panel - will contain keyboard panel
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(f.getWidth(), keyboardHeight));
        panel2.setBackground(background);
        panel2.setVisible(true);

        // keyboard panel
        KeyboardPanel keyboard = new KeyboardPanel(width*2, keyboardHeight-65);
        keyboard.setButtons(wp);

        // allows user to type
        UserKeyBoard uskb = new UserKeyBoard(wp, f);

        panel1.add(wp);
        panel2.add(keyboard);

        f.add(panel1);
        f.add(panel2);

        f.validate();

        keyboardPanel = keyboard;
        panelBottom = panel2;
    }


    /**
     * When the user wins, graphics of the bottom panel change.
     */
    public void celebrate(){
        // get rid of keyboard and change background on bottom panel
        panelBottom.removeAll();
        panelBottom.setBackground(Color.magenta);

        // add text to bottom panel
        JLabel text = new JLabel("YOU DID IT! \n DONNA IS PROUD!");
        text.setFont(new Font("Sans-serif", Font.BOLD, 40));
        text.setForeground(background);

        panelBottom.add(text);

        // add image to bottom panel
        File sourceImage = new File("src/DONNAE.jpg");
        try{
            Image image = ImageIO.read(sourceImage).getSubimage(30,20,400,150);
            JLabel pic = new JLabel(new ImageIcon(image));
            panelBottom.add(pic);
        }
        catch (Exception e){
            System.out.println("PROBLEM");
        }

        panelBottom.validate();
    }

    /**
     * When the user loses, graphics of the bottom panel change.
     */
    public void fail(){

        // get rid of keyboard and change background on bottom panel
        panelBottom.removeAll();
        panelBottom.setBackground(Color.red.darker());

        // add text to bottom panel
        JLabel text = new JLabel("YOU FAILED!");
        text.setFont(new Font("Sans-serif", Font.BOLD, 40));
        text.setForeground(Color.black);
        panelBottom.add(text);

        // add image to bottom panel
        File sourceImage = new File("src/DONNAH.jpg");
        try{
            Image image = ImageIO.read(sourceImage).getSubimage(75,5,145,150);
            JLabel pic = new JLabel(new ImageIcon(image));
            panelBottom.add(pic);
        }
        catch (Exception e){
            System.out.println("PROBLEM");
        }

        panelBottom.validate();
    }

    /**
     * Returns the hashmap containing the buttons on the keyboard.
     * @return The hashmap containing the buttons on the keyboard.
     */
    public HashMap<String, Button> getButtonHash(){
        return keyboardPanel.getButtonHash();
    }

}

