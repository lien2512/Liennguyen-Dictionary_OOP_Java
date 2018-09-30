import java.io.*;
import java.lang.String;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;
public class DictionaryManagement extends Dictionary {
    static Scanner s=new Scanner(System.in);
    static int n = s.nextInt();
    public  void  insertFromCommandline()
    {
        for(int i=0;i<n;i++)
        {
            String en= s.next();
            s.nextLine();
            String vi= s.nextLine();
            Word w= new Word();
            w.setWord_target(en);
            w.setWord_explain(vi);
            word.add(w);
        }
    }
    public  void  insertFromFile()throws IOException {

        FileInputStream file = new FileInputStream("E:/dictionaries.txt");

        try (Scanner x = new Scanner(file)) {
            while (x.hasNextLine()) {
                String es = x.next();
                String vn = x.nextLine();
                Word w = new Word();
                w.setWord_target(es);
                w.setWord_explain(vn);

                word.add(w);
            }
        } catch (Exception e) {

        }
    }
    public  String dictionaryLookup()
    {
        String eng = s.next();
        for ( int i=0;i<word.size();i++)
        {
            String s2 = word.get(i).getWord_target();

            if(s2.equals(eng))
            {
                return word.get(i).getWord_explain();
            }
        }
        return "khong tim thay";
    }
    public void dictionaryExportToFile(String eng, String viet){ //eng - target; viet - explain
        File file = new File("Dictionary_explain.txt");
        try(PrintWriter pw = new PrintWriter(file)){
            pw.println(eng + " - " + viet);
        } catch (FileNotFoundException ex) {

        }
    }


}
