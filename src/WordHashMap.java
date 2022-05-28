import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Class for the different words in the game.
 * @author Haley Slaney
 */
public class WordHashMap {

    String date;
    private HashMap<String, String> words;

    /**
     * Sets up the hashmap, words, with a word for each day of the month.
     */
    public WordHashMap(){
        words = new HashMap<String, String>();

        words.put("1", "MARRY");
        words.put("2", "DANCE");
        words.put("3", "HONEY");
        words.put("4", "BOATS");
        words.put("5", "BRIDE");
        words.put("6", "TANYA");
        words.put("7", "ROSIE");
        words.put("8", "AISLE");
        words.put("9", "MONEY");
        words.put("10", "DRESS");
        words.put("11", "NIGHT");
        words.put("12", "SUPER");
        words.put("13", "GREEK");
        words.put("14", "HOTEL");
        words.put("15", "LEAVE");
        words.put("16", "FLOOD");
        words.put("17", "TOURS");
        words.put("18", "SUNNY");
        words.put("19", "CHEAT");
        words.put("20", "TRAVEL");
        words.put("21", "PARIS");
        words.put("22", "GOATS");
        words.put("23", "PARTY");
        words.put("24", "QUEEN");
        words.put("25", "MAMMA");
        words.put("26", "WATER");
        words.put("27", "BEACH");
        words.put("28", "DONNA");
        words.put("29", "DREAM");
        words.put("30", "HARRY");
        words.put("31", "MARRY");

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM");
        date = myDateObj.format(myFormatObj);
    }

    /**
     * Returns the word for today.
     * @return The word for today.
     */
    public String getTodayWord(){
        String day = date.substring(0,2);
        return words.get(day);
    }
}
