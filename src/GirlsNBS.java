import java.util.*;
import java.io.*;
class GirlsNBS
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int g,b,ans,n,m;
        while(true)
        {
            g=s.nextInt();
            b=s.nextInt();
            if(g==-1&&b==-1) break;
            if(g==0&&b==0) {System.out.println(0);continue;}
            if(g==0) {System.out.println(b);continue;}
            if(b==0) {System.out.println(g);continue;}
            if(g==b) {System.out.println(1);continue;}
            if(g>b) {n=b;m=g;}
            else {n=g;m=b;}
            ans=m/(n+1);
            if(m%(n+1)>0) ans++;
            System.out.println(ans);
        }
    }
}