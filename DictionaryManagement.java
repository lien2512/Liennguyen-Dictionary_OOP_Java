import java.io.*;
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
                newWord.setWord_target(sc.next().trim());
                newWord.setWord_explain(sc.nextLine().trim());
                Words.add(newWord);
            }
        } catch (Exception e) { }
    }

    public void dictionaryLookup() {
        System.out.println("Nhập từ muốn dịch:");
        String Target = Scan.nextLine();
        for (int i = 0; i < Words.size(); i++) {
            if (Target.equals(Words.get(i).getWord_target())) {
                System.out.println("Nghĩa: " + Words.get(i).getWord_explain().trim());
                return;
            }
        }
        System.out.println("Từ không có trong từ điển");
    }

    public void addWord() {
        System.out.println("Nhập từ và nghĩa của từ muốn thêm:");
        Word newWord = new Word();
        newWord.setWord_target(Scan.next().trim());
        newWord.setWord_explain(Scan.nextLine().trim());
        for (int i = 0; i < Words.size() - 1; i++) {
            if (newWord.getWord_target().equals(Words.get(i).getWord_target())) {
                System.out.println("Từ đã tồn tại");
                return;
            } else {
                if (newWord.getWord_target().compareTo(Words.get(i).getWord_target()) > 0
                    && newWord.getWord_target().compareTo(Words.get(i + 1).getWord_target()) < 0) {
                    Words.add(i, newWord);
                    System.out.println("Thêm từ thành công!");
                    return;
                }
            }
        }
        Words.add(newWord);
        System.out.println("Thêm từ thành công!");
    }

    public void changeWord() {
        System.out.println("Nhập từ muốn sửa:");
        String change = Scan.nextLine();
        for (int i = 0; i < Words.size(); i++) {
            if (change.equals(Words.get(i).getWord_target())) {
                System.out.println("Nhập nghĩa mới của từ:");
                Words.get(i).setWord_explain(Scan.nextLine());
                System.out.println("Sửa từ thành công!");
                return;
            }
        }
        System.out.println("Không tồn tại từ trong danh sách");
    }

    public void deleteWord() {
        System.out.println("Nhập từ muốn xóa:");
        String delete = Scan.nextLine();
        for (int i = 0; i < Words.size(); i++) {
            if (delete.equals(Words.get(i).getWord_target())) {
                Words.remove(i);
                System.out.println("Xóa từ thành công!");
                return;
            }
        }
        System.out.println("Không tồn tại từ trong danh sách");
    }

    public void dictionaryExportToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("Dictionaries.txt");

            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            for (Word tu : Words) {
                String line = tu.getWord_target().trim() + " " + tu.getWord_explain().trim();
                bw.write(line);
                bw.newLine();

            }
            bw.close();
            osw.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
