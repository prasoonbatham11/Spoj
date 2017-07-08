#include<bits/stdc++.h>
using namespace std;

long long int dp[(1<<15)+1][16];
int n;
int a[16];

long long int solve(int mask, int last)
{
	if(__builtin_popcount(mask)==n)
	{
		return a[last];
	}
	long long int &ans = dp[mask][last];
	if(ans!=-1) return ans;
	ans = 0;
	int i;
	for(i=1;i<=n;i++)
	{
		if((mask&(1<<(i-1)))==0)
		{
			ans = max(abs(a[i]-a[last])+solve(mask|(1<<(i-1)), i),ans);
		}
	}
	return ans;
}

int main()
{
	while(true)
	{
		scanf("%d",&n);
		if(n==0) break;
		int i;
		for(i=1;i<=n;i++) scanf("%d",&a[i]);
		a[0] = 0;

		long long int fact[9];
		fact[0] = 1;
		for(i=1;i<9;i++) fact[i] = i*fact[i-1];
		//for(i=0;i<9;i++) printf("%lld ",fact[i]);
		//printf("%lld\n",fact[19] );

		long long int x[20];
		x[0] = 0;
		for(i=1;i<20;i++)
        {
            if(i%2==0)
            {
                x[i] = 2*fact[n/2]*fact[n/2];
            }
            else
                x[i] = fact[(n-1)/2]*fact[(n+1)/2];
            //printf("%lld ",x[i]);
        }


		memset(dp,-1, sizeof(dp));
		printf("%lld %lld\n",(n*2+solve(0,0)),x[n]);
        int j;
		/*for(i=0;i<=(1<<n);i++)
        {
            for(j=0;j<16;j++)
            printf("%lld ",dp[i][j]);
		printf("\n");
        }*/

	}
	return 0;
}
