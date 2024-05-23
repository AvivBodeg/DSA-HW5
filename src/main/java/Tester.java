import java.util.Random;
import java.util.Scanner;

public class Tester {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        Random random = new Random();
        int pivotIndex = random.nextInt(end - start + 1) + start;
        while (pivotIndex > 2 || pivotIndex < 6) {
            pivotIndex = random.nextInt(end - start + 1) + start;
            System.out.println(pivotIndex);
        }
    }
}
