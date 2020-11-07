import java.util.*;

public class NegativeSubArraySum {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int[] array = new int[scan.nextInt()];
        for (int i=0; i<array.length; i++)
            array[i] = scan.nextInt();
        scan.close();

        int totalNegatives = 0;
        for (int i=1; i<=array.length; i++) {
            for (int j=0; j<array.length-i+1; j++) {
                int sum = 0;
                for (int k=j; k<j+i; k++) {
                    sum += array[k];
                }
                if (sum < 0)
                    totalNegatives++;
            }
        }
        System.out.println(totalNegatives);
    }
}