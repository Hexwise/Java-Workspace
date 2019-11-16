/*
    Sieve of Eratosthenes Class
*/

public class SieveOfEratosthenes
{   
    // Instance Variables
    private boolean[]   isPrime;
    private int[]       primes;
    private int         totalPrimes;

    // Constructors
    public SieveOfEratosthenes(int maximum)
    {
        this.isPrime = new boolean[maximum+1];
        this.totalPrimes = 0;
        this.isPrime[0] = this.isPrime[1] = false;
        for(int i=2; i<this.isPrime.length; i++)
        {   
            this.isPrime[i] = true;
        }

        this.findPrimes();
        this.setPrimes(); 
    }
    
    //Public Instance Methods
    public void setMaximum(int maximum)
    {
        this.isPrime = new boolean[maximum+1];
        this.totalPrimes = 0;
        this.isPrime[0] = this.isPrime[1] = false;
        for(int i=2; i<this.isPrime.length; i++)
        {   
            this.isPrime[i] = true;
        }
        
        this.findPrimes();
        this.setPrimes(); 
    }
    public int[] getPrimes()
    {
        return this.primes.clone();
    }
    public int getTotalPrimes()
    {
        return this.totalPrimes;
    }
    public String toString()
    {
        String primeString = new String("[ " + this.primes[0]);
        for(int i=1; i<primes.length; i++)
        {
            primeString += ", " + this.primes[i];
        }
        primeString += " ]";
        return primeString;
    }

    // Private Instance Methods
    private void findPrimes()
    {   
        for(int i=2; i<=Math.sqrt(this.isPrime.length); i++)
        {
            if(this.isPrime[i])
            {   
                for(int j=2*i; j<this.isPrime.length; j+=i)
                {
                    this.isPrime[j] = false;
                }
            }
        }
    }
    private void setPrimes()
    {   
        for(int i=2; i<this.isPrime.length; i++)
        {
            if(this.isPrime[i])
            {
                this.totalPrimes++;
            }
        }
        primes = new int[this.totalPrimes];
        for(int i=0, j=0; i<this.isPrime.length; i++)
        {
            if(this.isPrime[i])
            {
                this.primes[j++] = i;
            }
        }
    }
}