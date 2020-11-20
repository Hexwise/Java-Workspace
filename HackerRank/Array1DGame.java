import java.util.*;

public class Array1DGame {

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        return move(leap, game, 0);
    }

    private static boolean move(int leap, int[] game, int index) {
        if (index >= game.length)
            return true;
        else if (index < 0 || game[index] != 0)
            return false;
        game[index]++; // keeps method from revisiting same indices;
        return  move(leap, game, index-1) ||
                move(leap, game, index+1) ||
                move(leap, game, index+leap);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}