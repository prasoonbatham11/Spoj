import java.io.*;
class Funprob {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String temp[] = br.readLine().split(" ");
            double n = Double.parseDouble(temp[0]);
            double m = Double.parseDouble(temp[1]);
            if (n == 0 && m == 0) break;
            double ans = n > m ? 0 : (m - n + 1) / (m + 1);
            ans = Math.round(ans * 1000000.0) / 1000000.0;
            System.out.printf("%.6f\n", ans);
        }
    }
}