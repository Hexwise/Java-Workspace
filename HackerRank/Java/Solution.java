import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double lambda = sc.nextDouble();
        int k = sc.nextInt();
        System.out.printf("%.3f\n", poisson(k, lambda));
        sc.close();
    }

    public static double tScore(double a[], int i) {
        return (a[i] - mean(a)) / sampleStdDev(a);
    }
    
    public static double zScore(double a[], int i) {
        return (a[i] - mean(a)) / popStdDev(a);
    }

    public static double popVariance(double a[]) { 
        double n = a.length;
        double m = mean(a);
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++)  {
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }
        return sumSqrDiff / n; 
    } 
 
    public static double popVariance(int a[]) { 
        double n = a.length;
        double m = mean(a);
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++)  {
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }
        return sumSqrDiff / n; 
    } 
      
    public static double popStdDev(double arr[]) { 
        return Math.sqrt(popVariance(arr)); 
    }

    public static double sampleVariance(double a[]) { 
        double n = a.length;
        double m = mean(a); 
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++) {  
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }  
        return sumSqrDiff / (n-1); 
    } 
    
     public static double sampleVariance(int a[]) { 
        double n = a.length;
        double m = mean(a); 
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++) {  
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }  
        return sumSqrDiff / (n-1); 
    } 
      
    public static double sampleStdDev(double arr[]) { 
        return Math.sqrt(sampleVariance(arr)); 
    } 

    public static double CompstdDev(int[] array) { // not working
        int sum = 0;
        int sqrSum = 0;
        int n = array.length;
        for(int i=0; i<n; i++) {
            sum += array[i];
            sqrSum += (int) Math.pow(array[i], 2);
        }
        return Math.sqrt((sqrSum - Math.pow(sum, 2) / n) / n - 1); 
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

    public static double mean(int[] array) {
        return sum(array) / (double)(array.length);
    }

    public static double mean(double[] array) {
        return sum(array) / (double)(array.length);
    }
    
    public static double meanRecursive(int[] array, int n) {
        if (n == 1)
            return (double) array[n-1]; 
        else
            return ((double)(meanRecursive(array, n-1)*(n-1) + array[n-1]) / n); 
    }
    
    public static double meanWeighted(int[] array, int[] weights) {
        int sum = 0;
        int weightSum = 0;
        for(int i=0; i<array.length; i++) {
            sum += array[i] * weights[i];
            weightSum += weights[i];
        }
        return (double)(sum)/ (double)(weightSum);
    }

    public static double median(int[] array) {
        Arrays.sort(array);
        int n = array.length;
        if(n%2==0) {
            return ((double) array[n/2-1] + (double) array[n/2]) / 2;
        }
        return (double) array[n/2];
    }

    public static double median(int[] array, int n) {
        Arrays.sort(array);
        if(n%2==0) {
            return ((double) array[n/2-1] + (double) array[n/2]) / 2;
        }
        return (double) array[n/2];
    }

    public static int mode(int[] array) {
        // Sort the array 
        Arrays.sort(array);
        int n = array.length;
        // Find the max frequency using linear traversal 
        int max_count = 1, res = array[0], curr_count = 1; 
        for (int i = 1; i < n; i++) { 
            if (array[i] == array[i - 1]) 
                curr_count++; 
            else { 
                if (curr_count > max_count) { 
                    max_count = curr_count; 
                    res = array[i - 1]; 
                } 
                curr_count = 1; 
            } 
        } 
    
        // If last element is most frequent 
        if (curr_count > max_count) 
        { 
            max_count = curr_count; 
            res = array[n - 1]; 
        } 
        return res; 
    }
    
    public static int mode(int[] array, int n) {
        // Sort the array 
        Arrays.sort(array);
    
        // Find the max frequency using linear traversal 
        int max_count = 1, res = array[0], curr_count = 1; 
        for (int i = 1; i < n; i++) { 
            if (array[i] == array[i - 1]) 
                curr_count++; 
            else { 
                if (curr_count > max_count) { 
                    max_count = curr_count; 
                    res = array[i - 1]; 
                } 
                curr_count = 1; 
            } 
        } 
    
        // If last element is most frequent 
        if (curr_count > max_count) 
        { 
            max_count = curr_count; 
            res = array[n - 1]; 
        } 
        return res; 
    }

    public static int mode2(int[] array, int n) {
        // Insert all elements in hash 
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>(); 
          
        for(int i = 0; i < n; i++) { 
            int key = array[i]; 
            if(hm.containsKey(key)) { 
                int freq = hm.get(key); 
                freq++; 
                hm.put(key, freq); 
            } 
            else { 
                hm.put(key, 1); 
            } 
        } 
        // find max frequency.
        int max_count = 0, res = -1; 
          
        for(Map.Entry<Integer, Integer> val : hm.entrySet()) { 
            if (max_count < val.getValue()) { 
                res = val.getKey(); 
                max_count = val.getValue(); 
            } 
        } 
        return res; 
    }

    public static int sum(int[] a) {
        int sum = 0;
        for(int i=0; i<2; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static double sum(double[] a) {
        double sum = 0;
        for(int i=0; i<a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    public static double poissonCDF(int k_0, int k_1, int lambda)
    {
        double sum = 0.0;
        for(int i=k_0; i<=k_1; i++)
        {
            sum += poisson(i, lambda);
        }
        return sum;
    }

    public static double poisson(int k, double lambda)
    {
        return Math.pow(lambda, k) * Math.pow(Math.E, -lambda) / factorial(k);
    }


    public static double gCDF(int n, double p)
    {
        double sum = 0.0;
        for(int i=1; i<=n; i++)
        {
            sum += g(i, p);
        }
        return sum;
    }

    public static double g(int n, double p)
    {
        return Math.pow((1-p), (n-1)) * p;
    }

    // binomial Cumulative Distribution Function
    public static double bCDF(int x_0, int x_1, int n, double p) 
    {   double sum = 0.0;
        for(int i=x_0; i<=x_1; i++) 
        {
            sum += b(i, n, p);
        }
        return sum;
    }

    //Binomial Distribution
    public static double b(int x, int n, double p)
    {
        return nCr(n,x) * Math.pow(p, x) * Math.pow((1-p), (n-x)); 
    }
    
    public static double bN(int x, int n, double p)
    {
        return b(x-1, n-1, p);
    }
    
    public static int nCr(int n, int r)
    {
        return nPr(n,r) / factorial(r);
    }

    public static int nPr(int n, int r)
    {
        return factorial(n) / factorial(n-r);
    }

    public static int factorial(int n)
    {   
        if(n<2)
            return 1;
        if(n==2) 
            return 2;
        return n * factorial(n-1);
    }
}// End Solution