import java.io.File;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {

    public static void insertFromCommandline(List<Word> Words, int size) {
        Scanner Scan = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            Word newWord = new Word();
            newWord.setWord_target(Scan.next());
            newWord.setWord_explain(Scan.nextLine());
            Words.add(newWord);
        }
        Scan.close();
    }

    public static void insertFromFile(List<Word> Words) {
        File file = new File("Dictionaries.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                Word newWord = new Word();
                newWord.setWord_target(sc.next());
                newWord.setWord_explain(sc.nextLine());
                Words.add(newWord);
            }
        } catch (Exception e) {

        }
    }

    public static void dictionaryLookup (List<Word> Words) {
        Scanner Scan = new Scanner(System.in);
        String Target = Scan.nextLine();
        for (int i = 0; i < Words.size(); i++) {
            if (Target.equals(Words.get(i).getWord_target())) {
                System.out.println(Words.get(i).getWord_explain());
            }
        }
    }
}
