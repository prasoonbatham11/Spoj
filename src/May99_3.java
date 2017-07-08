import java.util.*;
class May99_3
{
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        while(t-->0) {
            long x=s.nextLong(),y=s.nextLong(),z=s.nextLong();
            if(z<=Math.max(x,y)&&z%gcd(x,y)==0) System.out.println("YES");
            else System.out.println("NO");

        }
    }
    public static long gcd(long a,long b)
    {
        if(b==0) return a;
        return gcd(b,a%b);
    }
}