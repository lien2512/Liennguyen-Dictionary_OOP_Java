import java.io.IOException;
public class DictionaryCommandline extends DictionaryManagement{
    public void dictionaryBasic()
    {
        insertFromCommandline();
    }
    public void showAllWords()
    {
        System.out.printf("%-20s | %-20s | %-20s","NO","ENGLISH","VIETNAMESE");
        for(int i=1;i<=n;i++)
        {
            System.out.printf("%-20s | %-20s | %-20s",i,word.get(i).getWord_explain(),word.get(i).getWord_explain());
            System.out.println();
        }
    }
    public void  dictionaryAdvanced()
    {
        //insertFromFile();
        showAllWords() ;
        dictionaryLookup();

    }

}
