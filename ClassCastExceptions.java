

public class ClassCastExceptions {
    public static void ChuyenDoi() throws ClassCastException
    {
        try {
             Object i = "abc";
            int a = (int)i;
            System.out.println(a);
        } catch (ClassCastException e) {
            System.err.println("Lỗi chuyển đổi.");
        }

    }
    public static void  main(String[] args) {
        ChuyenDoi();
    }
}