package Words;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Dictionary of five letter words.
 * @author Haley Slaney
 */
public class DictionarySetUp {

    private LinkedList<String> dictionary;

    /**
     * Creates a linked list of real five letter words.
     */
    public DictionarySetUp()  {
        LinkedList<String> fiveLetterWords = new LinkedList<>();
        File dictionaryFile = new File("src/Words/Dictionary");

        try{
            Scanner scanner = new Scanner(dictionaryFile);
            while(scanner.hasNextLine()){
                fiveLetterWords.add(scanner.nextLine().toUpperCase());
            }
            scanner.close();
            this.dictionary = fiveLetterWords;
        }catch (Exception e){
        	System.out.println(e.getMessage());
            System.out.println("Problem with setting up dictionary.");
        }
        
    }

    /**
     * Determines if the string is a real word.
     * @param s String being checked.
     * @return True if the string is a real word. False otherwise.
     */
    public boolean realWord(String s){
        return dictionary.contains(s);
    }
}
