#include<bits/stdc++.h>
int a[110][110],n,t,i,j;main(){std::cin>>t;while(t--){std::cin>>n;for(i=0;i<n;i++) for(j=0;j<=i;j++) std::cin>>a[i][j];for(i=n-2;i>=0;i--) for(j=0;j<=i;j++) a[i][j]+=std::max(a[i+1][j],a[i+1][j+1]);std::cout<<a[0][0]<<"\n";}}
