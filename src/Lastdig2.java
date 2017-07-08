import java.io.*;
class Lastdig2
{
    public static void main(String args[])throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while(t-->0)
        {
            String p[] = br.readLine().split(" ");
            long a = p[0].charAt(p[0].length()-1)-'0';
            long b = Long.parseLong(p[1]);
            long d = m(a,b,10);
            System.out.println(d);
        }

    }
    static long m(long a,long b,long c)
    {
        long x=1,y=a;
        while(b>0)
        {
            if(b%2==1)
            {
                x=(x*y)%c;
            }
            y=(y*y)%c;
            b/=2;
        }
        return x%c;
    }
}