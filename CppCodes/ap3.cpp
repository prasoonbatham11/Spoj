#include<stdio.h>
#include<math.h>
using namespace std;

typedef unsigned long long int bigint;

typedef long double bigdouble;
int main()
{
	int t;
	scanf("%i", &t);

    while(t-->0)
    {
    	bigint x,y,z;
        scanf("%lli %lli %lli", &x, &y, &z);

        bigdouble A = y+x*1.0;
        bigdouble B = 7.0*x + 5.0*y + 2.0*z;
        bigdouble C = 12.0*z;

        bigdouble n = (B+sqrt(B*B-4*A*C))/(2*A);

        bigint N = llrint(n);

        bigdouble dd = (y-x)/(N-6);
        bigint d = llrint(dd);
        bigint a = x-2*d;

        printf("%lli\n",N);
        int i;
        for(i=0;i<N;i++)
        {
            printf("%lli ", (a+i*d));
        }
        printf("\n");
	}
	return 0;
}