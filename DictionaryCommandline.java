import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {

    public static Scanner Scan = new Scanner(System.in);

    public static void showAllWords (List<Word> Words) {
        System.out.printf("%-7s%-20s%-20s\n", "NO", "|English", "|Vietnamese");
        for (int i = 0; i < Words.size(); i++) {
        System.out.printf("%-7s%-20s%-20s\n", i+1, "|" + Words.get(i).getWord_target(),
                "|"  + Words.get(i).getWord_explain());
        }
    }

    public static void dictionaryBasic() {
        int size = Scan.nextInt();
        List<Word> words = new ArrayList<Word>();
        DictionaryManagement.insertFromCommandline(words, size);
        showAllWords(words);
    }

    public static void dictionaryAdvance() {
        List<Word> words = new ArrayList<Word>();
        DictionaryManagement.insertFromFile(words);
        showAllWords(words);
        DictionaryManagement.dictionaryLookup(words);
    }

    public static void main (String[] args) {
        //dictionaryBasic();
        dictionaryAdvance();
    }
}
