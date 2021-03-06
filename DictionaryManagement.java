import java.io.*;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary{

    public Scanner Scan = new Scanner(System.in);
    private static final String fileIN = "E://Dictionaries.txt";
    public int binarySearch(String s)
    {
        int mid,fi = 0;
        int la = Words.size() - 1;
        while (fi <= la)
        {
            mid = fi +(la-fi)/2 ;
            int c = Words.get(mid).getWord_target().compareTo(s);
            if(c>0)
            {
                la = mid -1 ;
            }
            else if(c<0)
            {
                fi = mid+1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }

    public void insertFromCommandline() {
        int size = Scan.nextInt();
        for (int i = 0; i < size; i++) {
            Word newWord = new Word();
            newWord.setWord_target(Scan.next());
            newWord.setWord_explain(Scan.nextLine());
            Words.add(newWord);
        }
    }

//    public void insertFromFile() {
//        File file = new File("E:\\Dictionaries.txt");
//        try (Scanner sc = new Scanner(file)) {
//            while (sc.hasNext()) {
//                Word newWord = new Word();
//                newWord.setWord_target(sc.next().trim().replace("\ufeff", ""));
//                newWord.setWord_explain(sc.nextLine().trim());
//                Words.add(newWord);
//            }
//        } catch (Exception e) { }
//    }
    public void insertFromFile(){
        try {
            File file = new File(fileIN);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            /* Đọc từng dòng (line) dữ liệu.
             Nếu đọc được null nghĩa là kết thúc */
            while ((line = in.readLine()) != null){
                Word w = new Word(line);
                Words.add(w);
            }
            in.close();
        }
        catch (Exception e){
        }
    }

    public void dictionaryLookup()
    {
        System.out.println("Nhập từ cần tra :");
        String s = Scan.nextLine();
        int i = binarySearch(s);
        if(i == -1)
        {
            System.out.println("Từ không có trong từ điển!");
        }
        else
        {
            System.out.println("nghĩa : " + Words.get(i).getWord_explain());
        }
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
                    Words.add(i+1, newWord);
                    System.out.println("Thêm từ thành công!");
                    return;
                }
            }
        }
        Words.add(newWord);
        System.out.println("Thêm từ thành công!");
    }

    public void changeWord()
    {
        System.out.println("Nhập từ muốn sửa: ");
        String change = Scan.nextLine().trim();
        int i = binarySearch(change);
        if(i == -1)
        {
            System.out.println("Từ này không có trong từ điển!");
        }
        else
        {
            System.out.println("Nhập nghĩa mới của từ: ") ;
            Words.get(i).setWord_explain(Scan.nextLine().trim());
            System.out.println("Sửa từ thành công!");
        }
    }

    public void deleteWord() {
        System.out.println("Nhập từ muốn xóa:");
        String delete = Scan.nextLine();
        int i = binarySearch(delete);
        if (i == -1) {
            System.out.println("Không tồn tại từ trong danh sách");
        } else {
            Words.remove(i);
            System.out.println("Xóa từ thành công!");
        }
    }

//    public void dictionaryExportToFile() {
//        try {
//            FileOutputStream fos = new FileOutputStream("E:\\Dictionaries.txt");
//            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
//            BufferedWriter bw = new BufferedWriter(osw);
//            for (Word tu : Words) {
//                String line = tu.getWord_target().trim() + " " + tu.getWord_explain().trim();
//                bw.write(line);
//                bw.newLine();
//            }
//            bw.close();
//            osw.close();
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void dictionaryExportToFile() {
        BufferedWriter file = null;
        try {
            file = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileIN), "UTF-8"));
            for(int i = 0; i < Words.size(); i++) {
                file.write(Words.get(i).getWord_target() + "\t" + Words.get(i).getWord_explain());
                file.newLine();
            }
            file.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}