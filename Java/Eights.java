import java.util.*;
import java.lang.*;
import java.io.*;
class Eights
{
    public static void main(String args[])throws IOException
    {
        Scanner s=new Scanner(System.in);
        int t=s.nextInt();
        long k,mod,digit;
        int ext;
        while(t-->0)
        {
            k=s.nextLong();
            mod=k%4;
            digit=(long)Math.ceil(k/4.0)-1;
            if(mod==1)
                ext=192;
            else if(mod==2)
                ext=442;
            else if(mod==3)
                ext=692;
            else
                ext=942;
            if(digit==0)
                System.out.println(ext);
            else
                System.out.println(digit+""+ext);
        }
    }
}