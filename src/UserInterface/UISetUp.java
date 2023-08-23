package UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.HashMap;

/**
 * Sets up the JFrame/UI for the game.
 * @author Haley Slaney
 */
public class UISetUp {

    private JFrame jFrame;                          // the frame
    private final int panelHeight;          // the top panel height
    private Color backgroundColor;                  // general background color

    private JPanel keyboardPanelOutline;                // bottom panel containing keyboard panel
    private KeyboardPanel keyboardPanelAction;       // keyboard panel

    /**
     * Sets up JFrame.
     */
    public UISetUp() {
        initialize();
        panelHeight = (jFrame.getHeight() / 10);
        setupTitlePanel();
        setupWordleAndKeyboard();
    }

    /**
     * Sets up the basic graphics of the JFrame.
     */
    public void initialize() {

        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setTitle("Mamma Mia Wordle");

        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        backgroundColor = (new Color(42, 82, 168));

        FlowLayout flow = new FlowLayout(FlowLayout.CENTER, 0, 0);
        jFrame.setLayout(flow);
    }

    /**
     * Sets up the top of the frame with a panel containing the text, "MammaMiaOrdle".
     */
    public void setupTitlePanel() {
        // top panel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(jFrame.getWidth(), panelHeight));
        panel.setBackground(backgroundColor);
        panel.setBorder(new LineBorder(Color.white, 5));
        jFrame.add(panel);

        //text part
        JLabel wordle = new JLabel("MammaMiaOrdle");
        panel.add(wordle);
        wordle.setForeground(Color.white);
        wordle.setFont(new Font("Sans-serif", Font.BOLD, 50));
    }

    /**
     * Sets up the rest of the frame including the wordle part and keyboard part.
     */
    public void setupWordleAndKeyboard() {

        // width and height - used for wordle panel and keyboardPanelAction
        int width = (int) (jFrame.getWidth() / 3.5);
        int wordlePartHeight = (((width - 40) / 5) * 6) + 50;
        int keyboardHeight = jFrame.getHeight() - wordlePartHeight - panelHeight - 5;

        // middle panel - will contain wordle panel
        JPanel wordlePanelOutline = new JPanel();
        wordlePanelOutline.setPreferredSize(new Dimension(jFrame.getWidth(), wordlePartHeight + 20));
        wordlePanelOutline.setBackground(backgroundColor);
        wordlePanelOutline.setVisible(true);

        // wordle panel
        WordlePanel wordlePanelAction = new WordlePanel(width, wordlePartHeight, this);

        // bottom panel - will contain keyboardPanelAction panel
        this.keyboardPanelOutline = new JPanel();
        keyboardPanelOutline.setPreferredSize(new Dimension(jFrame.getWidth(), keyboardHeight));
        keyboardPanelOutline.setBackground(backgroundColor);
        keyboardPanelOutline.setVisible(true);

        // keyboardPanelAction panel
        this.keyboardPanelAction = new KeyboardPanel(width * 2, keyboardHeight - 65);
        this.keyboardPanelAction.setButtons(wordlePanelAction);

        // allows user to type
        new UserKeyBoard(wordlePanelAction, jFrame);

        wordlePanelOutline.add(wordlePanelAction);
        keyboardPanelOutline.add(this.keyboardPanelAction);

        jFrame.add(wordlePanelOutline);
        jFrame.add(keyboardPanelOutline);

        jFrame.validate();
    }


    /**
     * When the user wins, graphics of the bottom panel change.
     */
    public void celebrate() {
        // get rid of keyboard and change background on bottom panel
        keyboardPanelOutline.removeAll();
        keyboardPanelOutline.setBackground(Color.magenta);

        // add text to bottom panel
        JLabel text = new JLabel("YOU DID IT! \n DONNA IS PROUD!");
        text.setFont(new Font("Sans-serif", Font.BOLD, 40));
        text.setForeground(backgroundColor);

        keyboardPanelOutline.add(text);

        // add image to bottom panel
        File sourceImage = new File("src/Images/DonnaCelebrate.jpg");
        try {
            Image image = ImageIO.read(sourceImage).getSubimage(30, 20, 400, 150);
            JLabel pic = new JLabel(new ImageIcon(image));
            keyboardPanelOutline.add(pic);
        } catch (Exception e) {
            System.out.println("Problem with celebrate image");
        }

        keyboardPanelOutline.validate();
    }

    /**
     * When the user loses, graphics of the bottom panel change.
     */
    public void fail() {

        // get rid of keyboard and change background on bottom panel
        keyboardPanelOutline.removeAll();
        keyboardPanelOutline.setBackground(Color.red.darker());

        // add text to bottom panel
        JLabel text = new JLabel("YOU FAILED!");
        text.setFont(new Font("Sans-serif", Font.BOLD, 40));
        text.setForeground(Color.black);
        keyboardPanelOutline.add(text);

        // add image to bottom panel

        File sourceImage = new File("src/Images/DonnaFail.jpg");
        try {
            Image image = ImageIO.read(sourceImage).getSubimage(75, 5, 145, 150);
            JLabel pic = new JLabel(new ImageIcon(image));
            keyboardPanelOutline.add(pic);
        } catch (Exception e) {
            System.out.println("Problem with fail image.");
        }

        keyboardPanelOutline.validate();
    }


    /**
     * Returns the hashmap containing the buttons on the keyboard.
     *
     * @return The hashmap containing the buttons on the keyboard.
     */
    public HashMap<String, Button> getButtonHash() {
        return this.keyboardPanelAction.getButtonHash();
    }
}

