import java.util.*;

public class ArrayListQuery2D {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan =  new Scanner(System.in);
        int n = scan.nextInt();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<ArrayList<Integer>>(n);
        for (int i=0; i<n; i++) {
            int d = scan.nextInt();
            ArrayList<Integer> integers = new ArrayList<Integer>();
            for (int j=0; j<d; j++) {
                integers.add(scan.nextInt());
            }
            lists.add(integers);
        }

        n = scan.nextInt();
        int x,y;
        for (int i=0; i<n; i++) {
            x = scan.nextInt() - 1;
            y = scan.nextInt() - 1;
            if(x < lists.size() && y < lists.get(x).size())
                System.out.println(lists.get(x).get(y));
            else
                System.out.println("ERROR!");
        }
        scan.close();
    }
}