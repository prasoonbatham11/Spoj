#include<bits/stdc++.h>
using namespace std;

int dp[1000][1000];
int a[1000];
int n;

int solve(int i, int j)
{
	if(i>j) return 0;

    if(dp[i][j]!=-1) return dp[i][j];
    if(i==j) return dp[i][j] = a[i];

    int p1 = 0, p2 = 0;
    if(a[i+1]>=a[j]) p1 = a[i]+solve(i+2, j);
    else p1 = a[i] + solve(i+1, j-1);

    if(a[i]>=a[j-1]) p2 = a[j]+solve(i+1, j-1);
    else p2 = a[j] + solve(i, j-2);

    return dp[i][j] = max(p1, p2);
}

int main()
{
	int k = 1;
	while(1)
	{
		scanf("%d", &n);
        if(n==0) break;

        int i;
        int sum = 0;
        for(i=0;i<n;i++) {
            scanf("%d", &a[i]);
            sum+=a[i];
        }


        int j;
        for(i=0;i<n;i++) for(j=0;j<n;j++) dp[i][j] = -1;

        int dyn = solve(0, n-1);

        //out.write(dyn+"\n");

        int greedy = sum - dyn;

        int ans = abs(dyn - greedy);

        printf("In game %d, the greedy strategy might lose by as many as %d points.\n",k, ans );
        k++;

	}
	return 0;
}