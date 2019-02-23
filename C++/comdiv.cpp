#include<bits/stdc++.h>
using namespace std;
#define MAX 1000001
int gcd(int a, int b)
{
    if(b==0) return a;
    return gcd(b, a%b);
}
int main()
{
    int seive[MAX] = {0};
    seive[0] = seive[1] = 1;
    int p,j;
    for(p=2;p*p<MAX;p++) if(seive[p]==0) for(j=p*p;j<MAX;j+=p) seive[j] = 1;

    int prime[MAX];
    j = 0;
    for(p=2;p<MAX;p++) if(seive[p]==0) prime[j++] = p;

    //System.out.println(Arrays.toString(prime).substring(0, 30));

    int i;
    int num[MAX];
    for(p=1;p<MAX;p++)
    {
        i = 0;
        int n = p;
        int ans = 1;
        while(prime[i]*prime[i]<=n)
        {
            int c = 0;
            while(n%prime[i]==0) {
                n/=prime[i];
                c++;
            }
            ans *= (c+1);
            i++;
        }
        if(n>1) ans*=2;
        num[p] = ans;
    }

    //System.out.println(Arrays.toString(num).substring(0, 30));
    int t;scanf("%d", &t);
    int a,b;
    while(t-->0)
    {
        scanf("%d%d", &a, &b);
        printf("%d\n", num[gcd(a,b)]);
    }
}
