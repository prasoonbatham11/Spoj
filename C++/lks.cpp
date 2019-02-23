#include<bits/stdc++.h>
using namespace std;


int dp[2][2000001];

int main()
{
	int s, n;
	scanf("%d%d", &s, &n);

    int weight[501];
    int val[501];
    int i;

    for(i=1;i<=n;i++)
    {
    	scanf("%d%d", &val[i], &weight[i]);
    }

    int j;


    for(i=1;i<=n;i++)
    {
        for(j=1;j<=s;j++)
        {
            if(j<weight[i])
            {
                dp[i%2][j] = dp[(i-1+2)%2][j];
            }
            else
            {
                dp[i%2][j] = max(dp[(i-1+2)%2][j], val[i]+dp[(i-1+2)%2][j-weight[i]]);
            }
        }
    }

    printf("%d\n", dp[n%2][s]);
    return 0;

}