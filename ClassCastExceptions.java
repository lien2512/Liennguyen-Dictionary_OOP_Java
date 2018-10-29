

public class ClassCastExceptions {
    public static void ChuyenDoi() throws ClassCastException
    {
        try {
            System.out.println((int)"a");
        } catch (ClassCastException e) {
            System.err.println("Lỗi chuyển đổi.");
        }

    }
    public static void  main(String[] args) {
        ChuyenDoi();
    }
}