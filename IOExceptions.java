import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class IOExceptions {

    public static void InsertFromFile(String ten_file) throws IOException {
        String S;

        File f = new File(ten_file);
        BufferedReader file = new BufferedReader(new FileReader(f));
        if (!file.ready()) {
            System.err.println("Chưa mở được file");
        }
        else{
            System.out.println("mở thành công");
        }


    }
    public static void main(String[] args) throws IOException  {

        String s = new Scanner(System.in).nextLine();
        InsertFromFile(s);
    }
}
