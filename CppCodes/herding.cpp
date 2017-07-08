#include<bits/stdc++.h>
using namespace std;
int visited[1000][1000];
int n, m;
int c;
char a[1000][1000];


int valid(int i,int j)
{
    return (int)(i>=0 && i<n && j>=0 && j<m);
}


void search(int i, int j)
{
    if(!valid(i,j) || visited[i][j]==1) return;

    visited[i][j]=1;
    if(a[i][j]=='S' || (valid(i+1,j) && visited[i+1][j]==0 && a[i+1][j]=='N')) search(i+1,j);

    if(a[i][j]=='N' || (valid(i-1,j) && visited[i-1][j]==0 && a[i-1][j]=='S')) search(i-1,j);

    if(a[i][j]=='E' || (valid(i,j+1) && visited[i][j+1]==0 && a[i][j+1]=='W')) search(i,j+1);

    if(a[i][j]=='W' || (valid(i,j-1) && visited[i][j-1]==0 && a[i][j-1]=='E')) search(i,j-1);


}




int main()
{
    scanf("%d%d",&n,&m);
    int i,j;
    string s;
    for(i=0;i<n;i++)
    {
        cin>>s;
        for(j=0;j<m;j++)
        {
            a[i][j] = s[j];
        }
    }

    for(i=0;i<n;i++) for(j=0;j<m;j++) visited[i][j]=0;
    c=0;

    for(i=0;i<n;i++)
    {
        for(j=0;j<m;j++)
        {
            if(visited[i][j]==0)
            {
                search(i,j);
                c++;
            }
        }
    }

    printf("%d\n",c);
    return 0;
}

