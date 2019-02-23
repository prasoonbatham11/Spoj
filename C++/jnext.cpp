#include<bits/stdc++.h>
using namespace std;
int main()
{
	int t,a[1000007],i,j,n,temp,p,min,k;
	scanf("%d",&t);
	while(t-->0)
	{
		scanf("%d",&n);
		for(i=0;i<n;i++) scanf("%d",&a[i]);
		for(i=n-2;i>=0;i--) if(a[i]<a[i+1]) break;
		if(i<0) {printf("-1\n");continue;}

		min=10000;


		for(j=i+1;j<n;j++) if(a[j]>a[i]&&a[j]-a[i]<min) {p=j;min=a[j]-a[i];}
		temp=a[p];a[p]=a[i];a[i]=temp;
		k=i+1;
		//int x[n-i];
		//while(++i<n) {x[k-i]=a[i];k++;}
		sort(a+i+1,a+n);
		for(k=0;k<n;k++) printf("%d",a[k]);
		//for(k=0;k<n-i;k++) printf("%d",x[k]);
		printf("\n");
	}
	return 0;
}