#include<bits/stdc++.h>
using namespace std;

int main()
{

    long long int t;
    scanf("%lld",&t);
    char a[50];
    int i;
    long long int ans;
    while(t-->0)
    {
        ans = 1;
        scanf("%s",a);
        int l = strlen(a);
        for(i=1;i<l;i++)
            if(a[i]==a[i-1]) ans *= 2;
        printf("%lld\n",ans);
    }
    return 0;
}
