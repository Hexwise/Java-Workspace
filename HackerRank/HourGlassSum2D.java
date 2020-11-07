import java.util.*;

public class HourGlassSum2D {

    public class HourGlass2D {
        private int[] hourglass;
        private int[] xy;
        
        public HourGlass2D(int x, int y, final int[][] array) {
            xy =  new int[2];
            xy[0] = x;
            xy[1] = y;

            hourglass = new int[7];
            hourglass[0] = array[x][y];
            hourglass[1] = array[x][y+1];
            hourglass[2] = array[x][y+2];
            hourglass[3] = array[x+1][y+1];
            hourglass[4] = array[x+2][y];
            hourglass[5] = array[x+2][y+1];
            hourglass[6] = array[x+2][y+2];
        }

        public int sum() {
            int sum = 0;
            for(int n: hourglass)
                sum += n;
            return sum;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        scanner.close();

        HourGlassSum2D h =  new HourGlassSum2D();
        int sum = 0;
        int max = -99;

        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                sum = h.new HourGlass2D(i, j, arr).sum();
                if(sum > max)
                    max = sum;
            }
        }
        System.out.println(max);
    }
}