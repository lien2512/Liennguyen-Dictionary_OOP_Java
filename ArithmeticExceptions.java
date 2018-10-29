import java.util.Scanner;

public class ArithmeticExceptions {
    public static void Mod(int a, int b) throws ArithmeticException{

//        try {
//            System.out.println(a/b);
//        }
//        catch (ArithmeticException e)
//        {
//            System.out.println("Lỗi chia cho so 0");
//        }
        if(b==0)
        {
            System.out.println("Lỗi chia cho so 0");
        }
        else
        {
            System.out.println(a/b);
        }
    }
    public static void main(String[] args) {
        int a, b;
        Scanner scan = new Scanner(System.in);
        a= scan.nextInt();
        b = scan.nextInt();
        Mod(a, b);
    }
}