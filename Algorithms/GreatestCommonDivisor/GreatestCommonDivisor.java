/*
    Greatest Common Divisor Class
*/

public class GreatestCommonDivisor
{   
    // Instance Variables
    private int absX;
    private int absY;

    // Constructor
    public GreatestCommonDivisor(int x, int y)
    {
        this.absX = Math.abs(x);
        this.absY = Math.abs(y);
    }

    // Instance Methods
    public void setXY(int x, int y)
    {
        this.absX = Math.abs(x);
        this.absY = Math.abs(y);
    }
    public int getGCD() // needs to throw for division by zero
    {   
        int x = this.absX;
        int y = this.absY;
        do
        {
            int n = x % y;
            x = y;
            y = n;
        }while(y>0);
        return x;
    }
}