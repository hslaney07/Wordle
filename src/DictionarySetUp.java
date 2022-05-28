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
        LinkedList<String> s = new LinkedList<String>();
        File f = new File("C:/Users/haley/wordle/src/Dictionary");
        try{
            Scanner scan = new Scanner(f);
            while(scan.hasNextLine()){
                s.add(scan.nextLine().toUpperCase());
            }
            scan.close();
            dictionary = s;
        }catch (Exception e){
        	System.out.println(e.getMessage());
            System.out.println("PROBLEM WITH DICTIONARY SETUP");
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
