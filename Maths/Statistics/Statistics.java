import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Statistics {
    
    /*
    *
    */
    public static double tScore(double a[], int i) {
        return (a[i] - mean(a)) / sStdDev(a);
    }
    
    /*
    *
    */
    public static double tScore(double a[], double x) {
        return (x - mean(a)) / sStdDev(a);
    }

    /*
    *
    */
    public static double zScore(double a[], int i) {
        return (a[i] - mean(a)) / pStdDev(a);
    }

    /*
    *
    */
    public static double zScore(double a[], double x) {
        return (x - mean(a)) / pStdDev(a);
    }

    /*
    *
    */
    public static double pVar(double a[]) { 
        double n = a.length;
        double m = mean(a);
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++)  {
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }
        return sumSqrDiff / n; 
    } 
      
    /*
    *
    */
    public static double pStdDev(double arr[]) { 
        return Math.sqrt(pVar(arr)); 
    }

    /*
    *
    */
    public static double sVar(double a[]) { 
        double n = a.length;
        double m = mean(a); 
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++) {  
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }  
        return sumSqrDiff / (n-1); 
    } 
    
    /*
    *
    */
     public static double sVar(int a[]) { 
        double n = a.length;
        double m = mean(a); 
        double sumSqrDiff = 0; 
        for (int i=0; i<n; i++) {  
            sumSqrDiff += Math.pow((a[i] - m), 2); 
        }  
        return sumSqrDiff / (n-1); 
    } 
      
    /*
    *
    */
    public static double sStdDev(double arr[]) { 
        return Math.sqrt(sVar(arr)); 
    } 

    /*
    *
    */
    public static double IQR(int[] a) {
        double[] q = quartiles(a);
        return q[2] - q[0];
    }

    /*
    *
    */
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

    /*
    *
    */
    public static double mean(int[] a) {
        return sum(a) / (double)(a.length);
    }

    /*
    *
    */
    public static double mean(double[] a) {
        return sum(a) / (double)(a.length);
    }
    
    /*
    *
    */
    public static double meanWeighted(int[] a, int[] weights) {
        int sum = 0;
        int weightSum = 0;
        for(int i=0; i<a.length; i++) {
            sum += a[i] * weights[i];
            weightSum += weights[i];
        }
        return (double)(sum)/ (double)(weightSum);
    }

    /*
    *
    */
    public static double median(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        if(n%2==0) {
            return ((double) a[n/2-1] + (double) a[n/2]) / 2;
        }
        return (double) a[n/2];
    }

    /*
    *
    */
    public static double median(int[] a, int n) {
        Arrays.sort(a);
        if(n%2==0) {
            return ((double) a[n/2-1] + (double) a[n/2]) / 2;
        }
        return (double) a[n/2];
    }

    /*
    *
    */
    public static int mode(int[] a) {
        // Sort the a 
        Arrays.sort(a);
        int n = a.length;
        // Find the max frequency using linear traversal 
        int max_count = 1, res = a[0], curr_count = 1; 
        for (int i = 1; i < n; i++) { 
            if (a[i] == a[i - 1]) 
                curr_count++; 
            else { 
                if (curr_count > max_count) { 
                    max_count = curr_count; 
                    res = a[i - 1]; 
                } 
                curr_count = 1; 
            } 
        } 
    
        // If last element is most frequent 
        if (curr_count > max_count) 
        { 
            max_count = curr_count; 
            res = a[n - 1]; 
        } 
        return res; 
    }
    
    /*
    *
    */
    public static int mode(int[] a, int n) {
        // Sort the a 
        Arrays.sort(a);
    
        // Find the max frequency using linear traversal 
        int max_count = 1, res = a[0], curr_count = 1; 
        for (int i = 1; i < n; i++) { 
            if (a[i] == a[i - 1]) 
                curr_count++; 
            else { 
                if (curr_count > max_count) { 
                    max_count = curr_count; 
                    res = a[i - 1]; 
                } 
                curr_count = 1; 
            } 
        } 
    
        // If last element is most frequent 
        if (curr_count > max_count) 
        { 
            max_count = curr_count; 
            res = a[n - 1]; 
        } 
        return res; 
    }

    /*
    *
    */
    public static int mode2(int[] a, int n) {
        // Insert all elements in hash 
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>(); 
          
        for(int i = 0; i < n; i++) { 
            int key = a[i]; 
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

    /*
    *
    */
    public static int sum(int[] a) {
        int sum = 0;
        for(int i=0; i<2; i++) {
            sum += a[i];
        }
        return sum;
    }

    /*
    *
    */
    public static double sum(double[] a) {
        double sum = 0;
        for(int i=0; i<2; i++) {
            sum += a[i];
        }
        return sum;
    }
}

