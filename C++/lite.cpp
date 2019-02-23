#include<bits/stdc++.h>
using namespace std;
int n, c, x, p, q, v=1;
long long int seg[500000], lazy[500000];

void propagate(int ss, int se, int i)
{
	if(lazy[i]%2==1)
		seg[i] = se-ss+1-seg[i];
	if(ss!=se)
	{
		lazy[2*i+1] += lazy[i];
		lazy[2*i+2] += lazy[i];
	}
	lazy[i] = 0;
}

void update(int ss, int se, int i)
{
	if(lazy[i]>0) propagate(ss, se, i);
	if(p>se || q<ss) return;
	if(p<=ss && q>=se)
	{
		lazy[i] += 1;
		propagate(ss,se,i);
		return;
	}
	int mid = (ss+se)/2;
	update(ss,mid,2*i+1);
	update(mid+1, se, 2*i+2);
	seg[i] = seg[2*i+1]+seg[2*i+2];
}

long query(int ss, int se, int i)
{
	if(lazy[i]>0) propagate(ss, se, i);
	if(p>se || q<ss) return 0;
	if(p<=ss && q>=se) return seg[i];
	int mid = (ss+se)/2;
	return query(ss, mid, 2*i+1)+query(mid+1, se, 2*i+2);
}







int main()
{

memset(seg, sizeof(seg),0);
memset(lazy, sizeof(lazy), 0);
	scanf("%d%d",&n, &c);
	while(c-->0)
	{
		scanf("%d%d%d",&x, &p, &q);
		p--;q--;
		if(x==0) update(0,n-1,0);
		else
		printf("%lld\n",query(0,n-1,0));
	}
}

