import java.io.*;
class Tutmrbl
{
    public static void main(String args[])throws IOException
    {
        BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
        int a[]=new int[100000];
        int k,c,i;
        long n,m,pro;
        while(true)
        {
            n=Long.parseLong(b.readLine());m=n;
            if(n==0) break;
            k=0;

            c=0;
            pro=1;
            while(n%2==0)
            {n/=2;c++;a[k++]=2;}
            if(c>0) {pro*=(c+1);}
            for(i=3;i*i<=n;i+=2)
            {
                c=0;
                while(n%i==0) {n/=i;c++;a[k++]=i;}
                if(c>0) {pro*=(c+1);}
            }
            if(n>1) {a[k++]=(int)n;pro*=2;}

            if(pro%2==1) pro++;
            pro/=2;
            System.out.print(m+" = ");
            for(i=0;i<k-1;i++) System.out.print(a[i]+" * ");
            System.out.print(a[i]);
            System.out.println();
            System.out.println("With "+m+" marbles, "+pro+" different rectangles can be constructed.");
        }
    }
}
