import java.util.*;

public class DictionaryCommandline extends DictionaryManagement{

    public Scanner Scan = new Scanner(System.in);

    public void showAllWords () {
        System.out.printf("%-7s%-30s%-30s\n", "NO", "English", "Vietnamese");
        for (int i = 0; i < Words.size(); i++) {
            System.out.printf("%-7s%-30s%-30s\n", i+1, Words.get(i).getWord_target(), Words.get(i).getWord_explain());
        }
    }

    public void showAllWords (List<Word> Words) {
        System.out.printf("%-7s%-30s%-30s\n", "NO", "English", "Vietnamese");
        for (int i = 0; i < Words.size(); i++) {
            System.out.printf("%-7s%-30s%-30s\n", i+1, Words.get(i).getWord_target(), Words.get(i).getWord_explain());
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
        int d = Scan.nextInt();
        showAllWords(dictionarySearcher());
        dictionaryLookup();
        dictionaryExportToFile();
    }

    public List<Word> dictionarySearcher() {
        System.out.println("Nhập kí tự:");
        String s = Scan.nextLine();
        List<Word> Searcher = new ArrayList<Word>();
        for (int i = 0; i < Words.size(); i++) {
            if (Words.get(i).getWord_target().startsWith(s)) {
                Searcher.add(Words.get(i));
            }
        }
        return Searcher;
    }

    public static void main (String[] argh) {

        DictionaryCommandline dict = new DictionaryCommandline();
        dict.insertFromFile();

        System.out.println("Chào mừng bạn đến với ứng dụng từ điển Anh - Việt");
        System.out.println("=================================================");

        Scanner scan = new Scanner(System.in);

        while (true) {

            System.out.println("Vui lòng ấn số để chọn chức năng, sau đó ấn Enter");
            System.out.println("*************************************************");
            System.out.println("1: Hiện danh sách từ");
            System.out.println("2: Tra từ");
            System.out.println("3: Tìm từ trong danh sách");
            System.out.println("4: Thêm từ");
            System.out.println("5: Sửa từ");
            System.out.println("6: Xóa từ");
            System.out.println("0: Thoát");
            System.out.println("-------------------------------------------------");
            int Choice = scan.nextInt();
            switch (Choice) {
                case 0: System.exit(0);
                break;
                case 1: dict.showAllWords();
                break;
                case 2: dict.dictionaryLookup();
                break;
                case 3: dict.showAllWords(dict.dictionarySearcher());
                break;
                case 4: dict.addWord();
                dict.dictionaryExportToFile();
                break;
                case 5: dict.changeWord();
                dict.dictionaryExportToFile();
                break;
                case 6: dict.deleteWord();
                dict.dictionaryExportToFile();
                break;
                default: break;
            }
        }
    }
}
