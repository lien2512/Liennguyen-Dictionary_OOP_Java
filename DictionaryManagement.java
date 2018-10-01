import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{

    public Scanner Scan = new Scanner(System.in);

    public void insertFromCommandline() {
        int size = Scan.nextInt();
        for (int i = 0; i < size; i++) {
            Word newWord = new Word();
            newWord.setWord_target(Scan.next());
            newWord.setWord_explain(Scan.nextLine());
            Words.add(newWord);
        }
    }

    public void insertFromFile() {
        File file = new File("Dictionaries.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                Word newWord = new Word();
                newWord.setWord_target(sc.next());
                newWord.setWord_explain(sc.nextLine());
                Words.add(newWord);
            }
        } catch (Exception e) { }
    }

    public void dictionaryLookup() {
        String Target = Scan.nextLine();
        for (int i = 0; i < Words.size(); i++) {
            if (Target.equals(Words.get(i).getWord_target())) {
                System.out.println(Words.get(i).getWord_explain());
            }
        }
    }

    public void addWord() {
        Word newWord = new Word();
        newWord.setWord_target(Scan.next());
        newWord.setWord_explain(Scan.nextLine());
        Words.add(newWord);
    }

    public void changeWord(int i) {
        Words.get(i).setWord_explain(Scan.nextLine());
    }

    public void deleteWord(int i) {
        Words.remove(i);
    }

    public void dictionaryExportToFile() {
        try (FileOutputStream fos = new FileOutputStream(new File("Output.txt"))){
            ObjectOutputStream oos = new ObjectOutputStream((fos));
            oos.writeObject(Words);
        } catch (Exception e) {
        }
    }
}
