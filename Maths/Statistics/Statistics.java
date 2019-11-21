import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Statistics {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = Integer.parseInt(sc.nextLine().trim());
       int[] array = new int[n];
       double[] d = new double[n];
       for(int i=0; i<n; i++) {
           array[i] = sc.nextInt();
           d[i] = (double) array[i];
       }
       System.out.println("mean: " + mean(array));
       System.out.println("median: " + median(array));
       System.out.println("mode: " + mode(array, n));
       System.out.println("pop. variance.: " + popVariance(d));
       System.out.println("pop. std. dev.: " + popStdDev(d));
       System.out.println("sample variance.: " + sampleVariance(d));
       System.out.println("sample std. dev.: " + sampleStdDev(d));
       sc.close();
    }

    public static double tScore(double a[], int i) {
        return (a[i] - mean(a)) / sampleStdDev(a));
    }
    
    public static double tScore(double a[], double x) {
        return (x - mean(a)) / sampleStdDev(a));
    }

    public static double zScore(double a[], int i) {
        return (a[i] - mean(a)) / sampleStdDev(a));
    }

    public static double zScore(double a[], double x) {
        return (x - mean(a)) / sampleStdDev(a));
    }

    public static double popVariance(double a[]) { 
        // Compute mean (average  
        // of elements) 
        double n = a.length;
        double sum = 0; 
        for (int i=0; i<(int)n; i++) 
            sum += a[i]; 
        double mean = sum / n; 
      
        // Compute sum squared  
        // differences with mean. 
        double sqDiff = 0; 
        for (int i = 0; i < n; i++)  
            sqDiff += (a[i] - mean) * (a[i] - mean); 
          
        return sqDiff / n; 
    } 
      
    public static double popStdDev(double arr[]) { 
        return Math.sqrt(popVariance(arr)); 
    }

    public static double sampleVariance(double a[]) { 
        // Compute mean (average  
        // of elements) 
        double n = a.length;
        double sum = 0; 
        for (int i=0; i<(int)n; i++) 
            sum += a[i]; 
        double mean = sum / n; 
      
        // Compute sum squared  
        // differences with mean. 
        double sqDiff = 0; 
        for (int i = 0; i < n; i++)  
            sqDiff += (a[i] - mean) * (a[i] - mean); 
          
        return sqDiff / (n-1); 
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

    public static double mean(double[] array) {
        double sum = 0;
        for(int i=0; i<array.length; i++) {
            sum += array[i];
        }
        return sum / (double)(array.length);
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
}

