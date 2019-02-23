#include<stdio.h>
int main()
    {
        //int y;
        //scanf("%d",&y);

        //printf("RUNES\n");
        int seive[1000];
        seive[0]=seive[1]=0;
        int p,j,i;
        int prime[200];
        for(i=2;i<1000;i++) seive[i]=1;

        for(p=2;p<1000;p++)
        {

        	if(seive[p]==1)
        	{
        		//printf("%d\n",p);
        		for(j=p*p;j<1000;j+=p)
        		{
        			seive[j] = 0;
        			}
        	}
        }
        j=0;
        for(p=2;p<1000;p++)
        {
        	if(seive[p]==1)
        	{prime[j++]=p;
        	//printf("%d\n",prime[j-1]);
        	}
        }

        //printf("RUNES\n");

        int d[1000001];
        int temp,c,pc,a,b;
        int okay[1000001];
        for(i=1;i<=1000000;i++)
        {
            d[i]=1;
            temp = i;
            p=0;
            pc=0;
            a=0;b=0;
            while(temp>1&&p<j)
            {
                c=1;
                if(temp%prime[p]==0) pc++;
                while(temp%prime[p]==0)
                {
                    temp/=prime[p];
                    c++;
                    if(pc==1) a++;
                    if(pc==2) b++;
                }
                d[i]*=c;
                p++;
            }
            if(temp>1) d[i]*=2;
            //printf("pc = %d\n",pc);
            if(pc==2&&a==1&&b==1) okay[i]=1;
            //printf("%d\n",okay[i]);
        }


        //printf("RUNES\n");
        int dn[1000000];
        j=0;
        for(i=1;i<=1000000;i++)
        {
        	//printf("%d\n",j);
            if(okay[d[i]]==1) dn[j++]=i;
        }

        //printf("%d\n",j);
        for(i=8;i<j;i+=9)
        {
            printf("%d\n",dn[i]);
        }

        return 0;
    }
