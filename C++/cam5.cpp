#include<bits/stdc++.h>
using namespace std;

vector <int> v[100000];
int visited[100000];

void dfs(int p)
{
	visited[p] = 1;
	int i, l = v[p].size();
	for(i=0;i<l;i++)
	{
		if(!visited[v[p][i]]) dfs(v[p][i]);
	}
}

int main()
{
	int t,n,e,i,c,a,b;
	scanf("%d",&t);
	while(t-->0)
	{
		scanf("%d",&n);
		scanf("%d",&e);
		c = 0;
		for(i=0;i<100000;i++) {v[i].clear();visited[i] = 0;}

		for(i=0;i<e;i++)
		{
			scanf("%d%d",&a,&b);
			v[a].push_back(b);
			v[b].push_back(a);
		}
		for(i=0;i<n;i++)
		{
			if(!visited[i]) {c++; dfs(i);}
		}
		printf("%d\n",c);

	}
}
