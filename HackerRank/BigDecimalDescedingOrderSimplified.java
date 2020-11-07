import java.math.BigDecimal;
import java.util.*;

class Solution{

    public static void main(String []args){
        //Input
        Scanner sc= new Scanner(System.in);
        int n=sc.nextInt();
        String []s=new String[n+2];
        for(int i=0;i<n;i++){
            s[i]=sc.next();
        }
      	sc.close();
        
        // My code
        List<BigDecimal> decimals = new ArrayList<BigDecimal>();

        for(int i=0; i<n; i++) {
            decimals.add(new BigDecimal(s[i]));
        }

        //sort both at the same time
        boolean sorted; 
        for(int i=0; i<n-1; i++) {
            sorted = true; 
            for(int j=0; j<n-i-1; j++) {
                if(decimals.get(j).compareTo(decimals.get(j+1)) < 0) {
                    BigDecimal tempD = decimals.get(j);
                    String tempS = s[j];
                    decimals.set(j, decimals.get(j+1));
                    s[j] = s[j+1];
                    decimals.set(j+1, tempD);
                    s[j+1] = tempS;
                    sorted = false; 
                } 
            } 
            if (sorted) 
                break; 
        }
        
        //Output
        for(int i=0;i<n;i++)
        {
            System.out.println(s[i]);
        }
    }

}