#include<stdio.h>
long long int modular(long long int a,long long int b,long long int c)
		{
		    long long int x=1,y=a;
		    while(b>0)
		    {
		        if(b%2==1)
		        {
		            x=(x*y)%c;
		        }
		        y=(y*y)%c;
		        b/=2;
		    }
		    return x%c;
		}

int main()
	{

		int t;
		long long int a,b;
		scanf("%d",&t);
		while(t-->0)
		{
			scanf("%lld%lld",&a,&b);
			printf("%lld\n",modular(a,b,10));
		}
		return 0;
	}
