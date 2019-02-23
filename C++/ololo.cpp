#include<stdio.h>
int main()
{

    int n;
    long long int x,y=0;
    scanf("%d",&n);
    while(n-->0)
    {
        scanf("%lld",&x);
        y=y^x;
    }
    printf("%lld",y);
    return 0;
}

