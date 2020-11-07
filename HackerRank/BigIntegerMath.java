import java.util.*;
import java.math.*;

public class BigIntegerMath {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();
        String m = scanner.nextLine();

        BigInteger bigN = new BigInteger(n);
        BigInteger bigM = new BigInteger(m);

        System.out.println(bigN.add(bigM));
        System.out.println(bigN.multiply(bigM));
        scanner.close();
    }
}