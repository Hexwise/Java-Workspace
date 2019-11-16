/*
    Greatest Common Divisor
*/

public class GreatestCommonDivisorTester
{
    public static void main(String[] args)
    {
        int x = 5;
        int y = 3;
        for(int i=0; i<10; i++)
        {
            x *= (i + 1);
            y *= (i + 1);
            GreatestCommonDivisor gcd = new GreatestCommonDivisor(x, y);
            System.out.println(gcd.getGCD());
        }
    }
}