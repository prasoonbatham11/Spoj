import java.util.*;
import java.io.*;
class Joker1
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b= new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(b.readLine());
        int n,i;
        int[] a;
        long prod,mod=1000000007;
        String[] temp;
        while(t-->0)
        {
            prod=1;
            n=Integer.parseInt(b.readLine());
            a=new int[n];
            temp = b.readLine().split(" ");
            //System.out.println(Arrays.toString(temp));
            for(i=0;i<n;i++)
                a[i]=Integer.parseInt(temp[i]);
            //System.out.println(Arrays.toString(a));
            Arrays.sort(a);
            //System.out.println(Arrays.toString(a));
            for(i=0;i<n;i++)
                prod=(prod*(a[i]-i))%mod;
            System.out.println(prod);
        }
        System.out.println("KILL BATMAN");
    }
}
