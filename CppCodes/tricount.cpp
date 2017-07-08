#include <stdio.h>

int main(void) {
	int t;
	scanf("%d",&t);
	long long int n, ans;
	while(t-->0)
	{
		scanf("%lld",&n);
		ans = ((n)*(n+2)*(2*n+1)/8);
		printf("%lld\n",ans);
	}
	return 0;
}