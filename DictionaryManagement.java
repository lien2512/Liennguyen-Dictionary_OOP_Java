import java.lang.String;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.IOException;
public class DictionaryManagement extends Dictionary {
    Scanner s=new Scanner(System.in);
    int n = s.nextInt();
    public void  insertFromCommandline()
    {
 //nhap so luong tu vung
        for(int i=1;i<=n;i++)
        {
            String en= s.nextLine();
            String vi= s.nextLine();
            Word w= new Word();
            w.setWord_target(en);
            w.setWord_explain(vi);
            word.add(w);
        }
    }
    public void  insertFromFile() throws IOException {
        Scanner s = new Scanner(Paths.get("C:\\Users\\Admin\\Desktop\\dictionary.txt"),"UTF-8");
        while (s.hasNext()) {
            Word newWord = new Word();
            newWord.setWord_target(s.next());
            newWord.setWord_explain(s.nextLine());
            word.add(newWord);
        }
        //s.close();
    }
    public void  showAllWords()
    {
        System.out.printf("%-20s | %-20s | %-20s","NO","ENGLISH","VIETNAMESE");
        int i=1;
        while (s.hasNextLine())
        {
            String s1 = s.nextLine();
            String [] b=s1.split("\\s",2);
            System.out.printf("%-20s | %-20s | %-20s","NO",i,b[0],b[1]);
            System.out.println();
            i++;
        }

    }
    public String dictionaryLookup()
    {
        Scanner s1 = new Scanner(System.in);
        String eng = s1.nextLine();
        String a= "";
        while (s.hasNextLine())
        {
            String s2 = s.nextLine();
            String [] b=s2.split("\\s",2);
            if(b[0].equals(eng))
            {
                a= s2;
                break;
            }
        }
        return a;
    }


}
