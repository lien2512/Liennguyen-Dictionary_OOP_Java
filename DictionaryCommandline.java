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
    public ArrayList<Word>   dictionarySearcher()
    {
        ArrayList<Word> equal = new ArrayList<>();
        String english = s.nextLine();
        for(int i=0;i<word.size();i++)
        {
            if(english.charAt(0)==word.get(i).getWord_target().charAt(0))
            {
                equal.add(word.get(i));
            }
        }
        return equal;
    }

    public static void main (String [] args) throws IOException {

        DictionaryCommandline dm= new DictionaryCommandline();
        dm.dictionaryAdvanced();
        dm.dictionarySearcher();

    }


}
