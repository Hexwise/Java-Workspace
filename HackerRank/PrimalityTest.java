import java.math.*;
import java.util.*;

public class PrimalityTest {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String line = scanner.nextLine();
        BigInteger number = new BigInteger(line);
        if(number.isProbablePrime(10)) {
            System.out.println("prime");
        }
        else System.out.println("not prime");
        scanner.close();
    }
}
