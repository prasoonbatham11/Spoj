class Easyprob
{
    static long p[];
    static String o="2(0)",t="2",h="2(2)";
    public static void main(String args[])
    {
        p=new long[15];
        p[0]=1;
        int i;
        for(i=1;i<15;i++)
            p[i]=2*p[i-1];
        long n[]={137,1315,73,136,255,1384,16385};
        for(i=0;i<7;i++)
            System.out.println(n[i]+"="+r(n[i]));
    }
    public static String r(long n)
    {
        if(n==1) return o;
        if(n==2) return t;
        if(n==4) return h;
        int i,f=1;
        String a="";
        for(i=14;i>=0;i--)
            if(n==p[i])
                return "2("+r(i)+")";
        for(i=14;i>=0;i--)
            if(n/p[i]>0)
            {
                if(f==1)
                    a=r(p[i]);
                else
                    a+="+"+r(p[i]);
                n=n-p[i];
                f=0;
            }
        return a;
    }
}