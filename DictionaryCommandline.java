import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.IOException;
public class DictionaryCommandline extends DictionaryManagement{
    public  void dictionaryBasic()

    {
        insertFromCommandline();
    }
    public  void  showAllWords()
    {
        System.out.printf("%-20s | %-20s | %-20s","NO","ENGLISH","VIETNAMESE");
        System.out.println();
        for(int i=0;i<word.size();i++)
        {
            System.out.printf("%-20s | %-20s | %-20s",i+1,word.get(i).getWord_target(),word.get(i).getWord_explain());
            System.out.println();
        }
    }
    public  void  dictionaryAdvanced() throws IOException {
        DictionaryCommandline m= new DictionaryCommandline ();

        m.insertFromFile();
        //dictionaryBasic();
        //showAllWords() ;
        //System.out.println(dictionaryLookup());

    }
    public ArrayList<Word> dictionarySearcher() throws IOException
    {
        ArrayList<Word> equal = new ArrayList<>();
        String english = s.next();
        for(int i=0;i<word.size();i++)
        {
            if(Character.toString(english.charAt(0)).equals(Character.toString(word.get(i).getWord_target().charAt(1))))
            {
                equal.add(word.get(i));
            }
        }
        return equal;
    }

    public static void main (String [] args) throws IOException {

        DictionaryCommandline dm= new DictionaryCommandline();
        dm.dictionaryAdvanced();
        ArrayList<Word> w= dm.dictionarySearcher();
        for(int i=0;i<w.size();i++)
        {
            System.out.println(w.get(i).getWord_target());
        }

    }


}
