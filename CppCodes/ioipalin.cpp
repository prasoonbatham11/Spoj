#include<bits/stdc++.h>
using namespace std;
int main()
{
    int t;
    scanf("%d",&t);
		int i,j;
			char str[5005];
			scanf("%s",str);

			int n = strlen(str);
			char a[n];
			char b[n];
			for(i=0;i<n;i++) {a[i] = str[i]; b[i] = str[n-i-1];}
			//System.out.println(Arrays.toString(a)+"\n"+Arrays.toString(b));
			int dp[2][n+1];
			memset(dp, 0, sizeof(dp));
			for(i=1;i<=n;i++)
			{
				for(j=1;j<=n;j++)
				{
					if(a[i-1]==b[j-1]) dp[i%2][j] = 1+dp[(i-1+2)%2][j-1];
					else dp[i%2][j] = max(dp[(i-1+2)%2][j],dp[i%2][j-1]);
					//System.out.print(dp[i][j]+"\t");
				}
				//System.out.println();
			}

			printf("%d\n",(n-dp[(n)%2][(n)]));
}
