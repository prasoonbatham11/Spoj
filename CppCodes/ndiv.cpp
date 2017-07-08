#include<stdio.h>
int main()
{
    long long int a,b,i,j,k;int n;
    scanf("%lld%lld%d",&a,&b,&n);
    int sum,count=0,countP;
        int seive[32007]={0};seive[0]=seive[1]=1;
        int prime[32000];
        int p=2,q,x;
        while(p*p<=32000)
        {
            for(q=p*p;q<=32000;q+=p) seive[q]=1;
            for(q=p+1;q<=32000;q++) if(seive[q]==0) p=q;
        }
        p=0;
        for(q=2;q<=32000;q++)
            if(seive[q]==0) prime[p++]=q;
        for(i=a;i<=b;i++)
        {
            j=i;
            sum=1;
            countP=0;
            x=0;
            for(q=prime[x];q*q<=j;q=prime[++x])
            {
                countP=0;
                while(j%prime[x]==0) {j/=prime[x];countP++;}
                sum*=(countP+1);
            }
            countP=0;
            if(j!=1) countP=1;
            sum*=(countP+1);

            if(sum==n) count++;
        }
        printf("%d",count);
        return 0;
    }
