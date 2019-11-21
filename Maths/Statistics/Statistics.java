import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Statistics {

    public static double tScore(double a[], int i) {
        return (a[i] - mean(a)) / sampleStdDev(a);
    }
    
    public static double tScore(double a[], double x) {
        return (x - mean(a)) / sampleStdDev(a);
    }

    public static double zScore(double a[], int i) {
        return (a[i] - mean(a)) / popStdDev(a);
    }

    public static double zScore(double a[], double x) {
        return (x - mean(a)) / popStdDev(a);
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
        int sumSqr = 0;
        int n = array.length;
        for(int i=0; i<n; i++) {
            sum += array[i];
            sumSqr += (int) Math.pow(array[i], 2);
        }
        return Math.sqrt((sumSqr - Math.pow(sum, 2) / n) / n - 1); 
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
        for(int i=0; i<2; i++) {
            sum += a[i];
        }
        return sum;
    }
}

