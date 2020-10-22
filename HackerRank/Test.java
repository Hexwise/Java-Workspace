public static String getSmallestAndLargest(String s, int k) {
    String smallest = "";
    String largest = "";
    
    // Complete the function
    // 'smallest' must be the lexicographically smallest substring of length 'k'
    // 'largest' must be the lexicographically largest substring of length 'k'

    String[] stringArray = new String[s.length()-k+1];
    String substring;

    for(int i=0; i<stringArray.length; i++) {
        stringArray[i] = s.substring(0, k);
        s = s.substring(1);
    }

    smallest = largest = stringArray[0];

    for(int i=0; i<stringArray.length; i++) {
        substring = stringArray[i];
        if(smallest.compareTo(substring) > 0)
            smallest = substring;
        if(largest.compareTo(substring) < 0)
            largest = substring;
    }

    return smallest + "\n" + largest;
}