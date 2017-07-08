import java.util.*;
import java.io.*;
class Egypizza
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt(),c1=0,c2=0,c3=0,p=1;
        String c;
        while(t-->0)
        {
            c=s.next();
            if(c.equals("1/4")) c1++;
            else if(c.equals("1/2")) c2++;
            else if(c.equals("3/4")) c3++;
        }
        //System.out.println(c1+" "+c2+" "+c3);
        p+=c3+c2/2;
        c2=c2%2;
        if(c1>=c3) c1-=c3;
        else c1=0;
        if(c2==1) {p+=1;c1-=2;}
        if(c1>0)
            p+=(int)Math.ceil(c1/4.0);
        System.out.println(p);
    }
}