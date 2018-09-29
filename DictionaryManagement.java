import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {

    public static Scanner Scan = new Scanner(System.in);

    public static void insertFromCommandline(List<Word> Words, int size) {
        for (int i = 0; i < size; i++) {
            Word newWord = new Word();
            newWord.setWord_target(Scan.nextLine());
            newWord.setWord_explain(Scan.nextLine());
            Words.add(newWord);
        }
    }
}
