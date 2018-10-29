import java.io.*;
import java.util.Scanner;

public class IOExceptions {

    public static void InsertFromFile(String ten) throws IOException, FileNotFoundException {

        File f = new File(ten);
        BufferedReader file = new BufferedReader(new FileReader(f));
        if (!file.ready()) {
            System.err.println("Chưa mở được file");
        }
        else{
            System.out.println("mở thành công");
        }


    }
    public static void main(String[] args) throws IOException  {

        String s = "hi";
        InsertFromFile(s);
    }
}
