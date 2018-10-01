import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement{

    public Scanner Scan = new Scanner(System.in);

    public void showAllWords () {
        System.out.printf("%-7s%-20s%-20s\n", "NO", "|English", "|Vietnamese");
        for (int i = 0; i < Words.size(); i++) {
        System.out.printf("%-7s%-20s%-20s\n", i+1, "|" + Words.get(i).getWord_target(),
                "|"  + Words.get(i).getWord_explain());
        }
    }

    public void showAllWords (List<Word> Words) {
        System.out.printf("%-7s%-20s%-20s\n", "NO", "|English", "|Vietnamese");
        for (int i = 0; i < Words.size(); i++) {
            System.out.printf("%-7s%-20s%-20s\n", i+1, "|" +Words.get(i).getWord_target(),
                    "|"  + Words.get(i).getWord_explain());
        }
    }

    public void dictionaryBasic() {
        insertFromCommandline();
        addWord();
        showAllWords();
    }

    public void dictionaryAdvance() {
        insertFromFile();
        showAllWords();
        addWord();
        int i = Scan.nextInt();
        changeWord(i);
        int d = Scan.nextInt();
        deleteWord(d);
        showAllWords(dictionarySearcher());
        dictionaryLookup();
        dictionaryExportToFile();
    }

    public List<Word> dictionarySearcher() {
        String s = Scan.nextLine();
        List<Word> Searcher = new ArrayList<Word>();
        for (int i = 0; i < Words.size(); i++) {
            if (Words.get(i).getWord_target().startsWith(s)) {
                Searcher.add(Words.get(i));
            }
        }
        return Searcher;
    }

    public static void main (String[] args) {

        DictionaryCommandline Cmd = new DictionaryCommandline();

        //Cmd.dictionaryBasic();
        Cmd.dictionaryAdvance();
    }
}
