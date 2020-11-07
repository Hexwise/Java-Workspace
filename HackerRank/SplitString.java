import java.util.*;

public class SplitString {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        // Write your code here.
        s = s.trim();
        if(s.isEmpty()) {
            System.out.println("0");
            scan.close();
            return;
        }
        String[] substrings = s.split("[^A-Za-z]+");
        System.out.println(substrings.length);
        for(int i=0; i<substrings.length; i++) {
            System.out.println(substrings[i]);
        }
        scan.close();
    }
}