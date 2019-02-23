#include <stdio.h>

int main(void) {
	// your code here
		long long int t,x,y,z,n,d,a;
		scanf("%lld",&t);
		while(t-->0)
		{
			scanf("%lld%lld%lld",&x,&y,&z);
			n = (2*z)/(x+y);
			d = (y-x)/(n-5);
			a = x-2*d;
			printf("%lld\n",n);
			while(n-->0)
			{
				printf("%lld ",a);
				a = a+d;
			}
			printf("\n");
		}

	return 0;
}