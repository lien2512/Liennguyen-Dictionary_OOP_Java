import java.util.Scanner;

public class NullPointerExeptions {

    public static void print(String s) throws NullPointerException
    {
        if ( s.length() <  10) {
            System.err.println("đã xảy ra ngoại lệ NullPointerException");
        } else {
            System.out.println(s.subSequence(10,12));
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        print(s);

    }
}
