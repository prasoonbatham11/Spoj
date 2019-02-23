#include<bits/stdc++.h>
#define YES 1
#define NO 0
using namespace std;
int v[250][250];
int visited[250][250];
int counter = 0;

void search(int i, int j, int n, int m)
{
	if(i>=0 && j>=0 && i<n && j<m && (!visited[i][j]) && v[i][j]==1)
	{
		visited[i][j] = YES;
		counter++;
		search(i-1,j,n,m);
		search(i,j-1,n,m);
		search(i,j+1,n,m);
		search(i+1,j,n,m);
	}
}

int main()
{
	int n,m,i,j;
	int f[256*256];
	while(1)
	{
		scanf("%d%d",&n,&m);
		if(n==0 && m==0) break;

		for(i=0;i<n;i++) { for(j=0;j<m;j++) { scanf("%d",&v[i][j]); } }

		for(i=0;i<250;i++) for(j=0;j<250;j++) visited[i][j] = NO;

		for(i=0;i<256*256;i++) f[i] = 0;

		int c=0;
		for(i=0;i<n;i++)
		{
			for(j=0;j<m;j++)
			{
				if(v[i][j]==1 && (!visited[i][j]) )
				{
					counter = 0;
					search(i,j,n,m);
					if(counter!=0)
					f[counter]++;
					c++;
					//printf("%d ",counter);
				}
			}
		}
		int flag = 0;
		printf("%d\n",c);

		for(i=0;i<256*256;i++)
		{
			if(f[i]!=0)
			{
				printf("%d %d\n",i,f[i]);
				flag = 1;
			}
		}



	}
	return 0;
}
