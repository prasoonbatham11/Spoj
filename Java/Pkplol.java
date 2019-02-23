import java.util.*;
import java.io.*;
class pkplol {
    public static void main(String args[])throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String ans[] = {"abcdef", "bc", "abdeg", "abcdg", "bcfg", "acdfg", "acdefg", "abc", "abcdefg", "abcdfg"};
        int i = 0;
        String x = "";
        while(i<t) {
            if((x=br.readLine()).equals("")) continue;
            int n = Integer.parseInt(x);
            System.out.println("Case "+(i+1)+": "+ans[n]);
            i++;
        }
    }
}
