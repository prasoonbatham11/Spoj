#include<bits/stdc++.h>
using namespace std;


int n, road, m;
int strength[1000000];
int parent[1000000];
int c;

vector<int> v[1000000];

int main()
{
	int t;
	scanf("%d", &t);
	while(t-->0)
	{
		scanf("%d%d%d", &n, &road, &m);
		memset(strength, -1, sizeof(strength));
		memset(parent, -1, sizeof(parent));
        c=0;
		int i;
		for(i=0;i<n;i++) v[i].clear();

		for(i=0;i<road;i++)
		{
			int a, b;
			scanf("%d%d",&a, &b);
			a--; b--;
			v[a].push_back(b);
			v[b].push_back(a);
		}


		queue<int> q;
		for(i=0;i<m;i++)
		{
			int a,b;
			scanf("%d%d", &a, &b);
			a--;
			strength[a] = b;
			parent[a] = a;
			c++;
			q.push(a);
		}

		int flag = 0;
		while(!q.empty())
		{
			int p = q.front();
                    q.pop();
			if(strength[p]<=0) continue;
			for(i=0;i<v[p].size();i++)
			{
				int u = v[p][i];
				if(strength[p]>0)
				{
					if(strength[u]==-1)
					{
						strength[u] = strength[p] - 1;
						q.push(u);
						parent[u] = parent[p];
						c++;
					}
					else
					{
						if(parent[u]!=parent[p])
						{flag = 1;break;}
					}
				}
			}
			if(flag == 1) break;
		}

		if(flag == 1 || c!=n) printf("No\n");
			else if(c == n)
            	printf("Yes\n");
			else printf("No\n");
	}
    return 0;
}