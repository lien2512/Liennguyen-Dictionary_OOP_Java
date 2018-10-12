import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class DictionaryManagement extends Dictionary{
    private Scanner sc = new Scanner(System.in);
    //tìm kiếm nhị phân vị trí từ
    public int brinarySearch(String s)
    {
        int mid,fri = 0;
        int las = word.size() - 1;


        while (fri <= las)
        {
                mid = fri + (las - fri) / 2;
                int cp = word.get(mid).compareTo(s);

                if (cp < 0) {
                    fri = mid + 1;
                } else if (cp > 0) {
                    las = mid - 1;
                } else {
                    return mid;
                }
            }
            return -1;
    }
    //in toàn bộ danh sách
    public void showAllWord()
    {
        System.out.printf("%-5s | %-20s | %-20s","NO","ENGLISH","VIETNAMESE");
        System.out.println();
        for( int i=0;i<word.size();i++)
        {
            System.out.printf("%-5s | %-20s | %-20s",i+1,word.get(i).getWord_target(),word.get(i).getWord_explain());
            System.out.println();
        }
    }
//    //tìm vị trí thêm từ vào từ điển theo đúng thứ tự
//    public int binaryInsert()
//    {
//        String s = sc.nextLine();
//        int fri = 0;
//        int las = word.size() - 1;
//        int mid = fri + las/2;
//        int temp = word.get(mid).getWord_target().compareTo(s); // so sanh 2 String
//        while (fri <= las)
//        {
//            if ( temp < 0) // tu tai vi tri mid be hon chuoi s
//            {
//                las = mid +1;
//            }
//            else if ( temp > 0) // tu tai vi tri mid lon hon chuoi s
//            {
//                las = mid -1;
//            }
//            else return mid;
//        }
//        return -1;
//    }
//    //thêm từ bàn phím
    public void insertFromCommandLine()
    {
        System.out.println("Thêm từ vào từ điển");
        System.out.println("Số lượng từ muốn thêm là : ");
                int n= sc.nextInt();
                sc.nextLine();
        for(int i=0;i<n;i++)
        {
            System.out.println("Nhập tiếng anh :");
            String eng = sc.nextLine();
            System.out.println("Nhập nghĩa : ");
            String vi = sc.nextLine();
            int k = brinarySearch(eng);
            if(k == -1)
            {
                Word a = new Word();
                a.setWord_target(eng);
                a.setWord_explain(vi);
                word.add(a);
                System.out.println("Đã thêm từ : " + eng + " thành công");
            }
            else
            {
                System.out.println("từ : "+ eng +" đã có trong từ điển" );
            }
        }
    }
    //thêm từ file
    public void insertFromFile()
    {
        File file = new File("E:\\Dictionaries.txt");
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                Word newWord = new Word();
                newWord.setWord_target(s.next());
                newWord.setWord_explain(s.nextLine());
                word.add(newWord);
            }
        } catch (Exception e) { }
    }
    //tra từ
    public void dictionaryLookup()
    {
        System.out.println("Tra từ");
        System.out.println("nhập từ cần tra : ");
        String search = sc.nextLine();
        int i =  brinarySearch(search);
        System.out.println(i);
        if(i == -1)
        {
            System.out.println("từ này chưa có trong từ điển");
        }
        else
        {
            System.out.println(word.get(i).getWord_target()+" | "+ word.get(i).getWord_explain());
        }
    }
    //tra một từ trả về một danh sách các từ gợi ý
    public void dictionarySearcher() {
        System.out.println("nhập từ cần tra");
        String s = sc.nextLine();
        ArrayList<Word> Searcher = new ArrayList<Word>();
        for (int i = 0; i < word.size(); i++) {
            if (word.get(i).getWord_target().startsWith(s)) {
                Searcher.add(word.get(i));
            }
        }
        for (int i=0;i<Searcher.size();i++)
        {
            System.out.println(Searcher.get(i).getWord_target() + "  |  " + Searcher.get(i).getWord_explain());
        }
    }
    //sửa từ
    public void changeWord()
    {
        System.out.println("Sửa từ");
        System.out.println("Nhập từ muốn sửa ");
        String s = sc.nextLine();
        int i = brinarySearch(s);
        if ( i== -1)
        {
            System.out.println("Không có từ này trong từ điển");
        }
        else
        {
            System.out.println("Sửa thành : ");
            String eng = sc.nextLine();
            System.out.println("Nhập nghĩa : ");
            String vi = sc.nextLine();
            word.get(i).setWord_target(eng);
            word.get(i).setWord_explain(vi);
            System.out.println("Sửa thành công");
        }
    }
    // xóa từ
    public void deleteWord()
    {
        System.out.println("Nhập từ muốn xóa :");
        String s = sc.nextLine();
        int i = brinarySearch(s);
        if ( i ==-1)
        {
            System.out.println("Không có từ này trong từ điển");
        }
        else
        {
            word.remove(i);
            System.out.println("Xóa thành công");
        }
    }
    public void dictionaryExportToFile() {
        try (FileOutputStream fos = new FileOutputStream(new File("Output.txt"))){
            ObjectOutputStream oos = new ObjectOutputStream((fos));
            oos.writeObject(word);
        } catch (Exception e) {
        }
    }



}

