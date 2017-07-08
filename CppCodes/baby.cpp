#include<bits/stdc++.h>
using namespace std;

long long int dp[1<<16];
int baby[16];
int corr[16];
int n;

long long int solve(int mask)
{
	int c = __builtin_popcount(mask);
	if(c==n) return 0;
	if(dp[mask]!=-1) return dp[mask];
	dp[mask] = 1000000;
	for(int i=0;i<n;i++)
	{
		if((mask&(1<<i))==0)
			dp[mask] = min(dp[mask], abs(c-i)+abs(baby[c]-corr[i])+solve(mask|(1<<i)));
	}
	return dp[mask];
}



int main()
{
	while(true)
	{
		scanf("%d",&n);
		if(n==0) break;
		int i;
		for(i=0;i<n;i++) scanf("%d",&baby[i]);
		for(i=0;i<n;i++) scanf("%d",&corr[i]);
		memset(dp, -1, sizeof(dp));
		printf("%lld\n",solve(0));
	}
	return 0;
}
