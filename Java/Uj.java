import java.util.*;
import java.math.*;
class Uj {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            BigInteger n = sc.nextBigInteger();
            int d = sc.nextInt();
            if (n.equals(BigInteger.ZERO) && d == 0) break;
            BigInteger ans = n.pow(d);
            System.out.println(ans);
        }
    }
}
