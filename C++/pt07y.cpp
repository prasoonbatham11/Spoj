#include<bits/stdc++.h>
using namespace std;
vector<int> v[10001];
int visited[10001]={0};
int vis[10001]={0};
int flag = 0;
void dfs(int a)
{
	//if(visited[a]) flag = 1;
	visited[a] = 1;
	int i;
	for(i=0;i<v[a].size();i++)
		if(!visited[v[a][i]])
			dfs(v[a][i]);
		else flag = 1;
}

bool iscycle(int u, int p)
{

    vis[u] = 1;
    int i;

    for(i=0;i<v[u].size();i++)
    {
        int next = v[u][i];
        if(next!=p)
        {
            if(vis[next]) return true;
            if(iscycle(next,u)) return true;
        }
    }
    return false;
}

int main()
{
	int n,m;
	scanf("%d%d",&n,&m);
	int x,y,i;
	for(i=0;i<m;i++)
	{
		scanf("%d%d",&x,&y);
		v[x].push_back(y);
		v[y].push_back(x);
	}
	if(m!=n-1) printf("NO\n");
	else
	{
		dfs(1);
		for(i=1;i<=n;i++) if(!visited[i]) {printf("NO\n");return 0;}
		if(iscycle(1,1)) {printf("NO\n");return 0;}
		printf("YES\n");

	}
}
