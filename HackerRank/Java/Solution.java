import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        


        
    }

    public static double[] quartiles(int[] a) {
        double[] q = new double[3];
        int n = a.length;
        q[1] = median(a);
        if(n%2==0)  {
            q[0] = median(Arrays.copyOfRange(a, 0, n/2));
            q[2] = median(Arrays.copyOfRange(a, n/2, n)); 
        }
        else {
             q[0] = median(Arrays.copyOfRange(a, 0, n/2));
             q[2] = median(Arrays.copyOfRange(a, n/2+1, n));                 
        }
        return q;
    }

    public static double interquartileRange(int[] a) {
        double[] q = quartiles(a);
        return q[2] - q[0];
    }

}// End Solution