import java.util.Arrays;

public class Probability
{
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
}