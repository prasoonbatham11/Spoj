import java.util.*;
import java.lang.*;
class Acpc10a
{
    public static void main(String args[])throws Exception
    {
        Scanner s=new Scanner(System.in);
        int a,b,c;
        while(true)
        {
            a=s.nextInt();
            b=s.nextInt();
            c=s.nextInt();
            if(a==b&&b==c&&c==0)
                return;
            if(a!=b&&b!=c&&c!=a)
            {
                if(a*c==b*b)
                    System.out.println("GP "+(c*(b/a)));

                else if(a+c==2*b)
                    System.out.println("AP "+(c+b-a));}
        }
    }
}