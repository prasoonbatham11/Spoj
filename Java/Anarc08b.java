import java.util.*;
class Anarc08b
{
    static String dc[]={"063","010","093","079","106","103","119","011","127","107"};
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        String add,temp[]=new String[3];int a,b,c;
        while(true)
        {
            add=s.next();
            if(add.equals("BYE")) return;
            temp=add.split("[+,=]");
            a=convert(temp[0]);b=convert(temp[1]);
            c=a+b;
            System.out.println(add+iconvert(c));
        }
    }
    public static int convert(String s)
    {
        int i,j,l=s.length();int n=0;String temp="";
        for(i=0;i<=l-2;i+=3)
        {
            temp=s.substring(i,i+3);
            for(j=0;j<10;j++)
                if(temp.equals(dc[j]))
                {break;}
            n=n*10+j;
        }
        return n;
    }
    public static String iconvert(int n)
    {
        String s="";int i;
        while(n>0)
        {
            s=dc[n%10]+s;
            n/=10;
        }
        return s;
    }
}