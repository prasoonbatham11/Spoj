#include<stdio.h>
int main()
{

    int t;
    while(1)
    {

        scanf("%d",&t);
        if(t==0) break;
        double ans = (t*t)/(2*3.14159);
        printf("%.2f\n",ans);
    }
    return 0;
}
