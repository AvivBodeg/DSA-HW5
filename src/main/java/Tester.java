import java.util.Random;
import java.util.Scanner;

public class Tester {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Long[] arr = new Long[3];
        arr[0] = 13L;
        arr[1] = 532L;
        arr[2] = 22L;
        Sorter.radixSort(arr, 3);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
    }
}
