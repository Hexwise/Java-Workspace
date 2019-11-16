import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Statistics {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
       int n = Integer.parseInt(sc.nextLine().trim());
       int[] array = new int[n];
       for(int i=0; i<n; i++) {
           array[i] = sc.nextInt();
       }
       System.out.println(mean(array, n));
       System.out.println(median(array, n));
       System.out.println(mode(array, n));
       sc.close();
    }

    public static double mean(int[] array) {
        int sum = 0;
        for(int i=0; i<array.length; i++) {
            sum += array[i];
        }
        return (double)(sum)/ (double)(array.length-1);
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

    public static double median(int[] array, int n) {
        Arrays.sort(array);
        if(n%2==0) {
            return ((double) array[n/2-1] + (double) array[n/2]) / 2;
        }
        return (double) array[n/2];
    }

    public static int mode(int[] array, int n) {
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

    public static int mode2(int[] array, int n) {
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
}

