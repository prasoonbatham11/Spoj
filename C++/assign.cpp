#include<bits/stdc++.h>
using namespace std;

vector<int> cap[20];
long long int dp[1<<20];
int n;
//mask stores if ith student has been assigned a cap or not.
long long int solve(int mask)
{	    //printf("mask %d %d\n",mask, c);

    int c = __builtin_popcount(mask);
	if(c==n)
	{
	    //printf("mask %d %d\n",mask, c);
		if(mask==((1<<n)-1)) // check if all students have been assigned a cap or not
			return 1;
		return 0;
	}
	if(dp[mask]!=-1) return dp[mask];
	long long int &ans = dp[mask];
	ans = 0;
	//ans += solve(mask,c+1); // if current cap is not assigned to any student.
	int i;
	for(i=0;i<cap[c].size();i++)
	{
	    //printf("%d\n",mask);
		int j = cap[c][i];
		if((mask&(1<<j))==0)
        {
            //printf("mask2 %d\n",(mask&(1<<j)));
           // jth person has not been assigned a cap
			ans += solve(mask|(1<<j));
        }
	}
	return ans;

}

int main()
{
	int t;
	scanf("%d",&t);
	while(t-->0)
	{
	    int i,j,a;
		 memset(dp,-1, sizeof(dp));
		 for(i=0;i<20;i++) cap[i].clear();
		 scanf("%d",&n);

		 for(i=0;i<n;i++)
		 {
		 	for(j=0;j<n;j++)
		 	{
		 		scanf("%d",&a);
		 		if(a)
		 		{
		 			cap[j].push_back(i); //jth cap can be assigned to ith person
		 		}
		 	}
		 }
		 /*for(i=0;i<n;i++)
		 {
		    for(j=0;j<cap[i].size();j++) printf("%d ",cap[i][j]);
            printf("\n");
		 }*/

		 printf("%lld\n",solve(0));

	}
}
