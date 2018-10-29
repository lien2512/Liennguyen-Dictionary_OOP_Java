import java.util.Scanner;

public class ArrayIndexOfBoundExceptions {
    public static void PrintArr(int[] arr,int i) throws ArrayIndexOutOfBoundsException
    {

//        try {
//            System.out.println(arr[i]);
//        } catch (ArrayIndexOutOfBoundsException e)
//        {
//            System.err.println("đã xảy ra ngoại lệ ArrayIndexOfBoundException");
//        }
        if(i<arr.length && i>=0)
        {
            System.out.println(arr[i]);
        }
        else
        {
            System.err.println("đã xảy ra ngoại lệ ArrayIndexOfBoundException");
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[10];
        int i = 3;
        PrintArr(arr,i);
    }
}
