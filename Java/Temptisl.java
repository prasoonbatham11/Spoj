import java.util.*;
import java.io.*;
class Temptisl
{
	public static void main(String args[])throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp;
		int i, j, l;
		long dp[][][] = new long[50][50][51];

		while(true)
		{
			String s = br.readLine();
			if(s.equals("")) continue;
			temp = s.split(" ");
			int n = Integer.parseInt(temp[0]);
			int k = Integer.parseInt(temp[1]);
			if(n==-1 && k==-1) break;

			for(i=0;i<n;i++) 
			for(j=0;j<n;j++) 
				if(i==j) dp[i][j][0] = 1;
				else dp[i][j][0] = 0;
		
			for(l=1;l<=k;l++)
			{
				for(i=0;i<n;i++)
				{
					for(j=0;j<n;j++)
					{
						dp[i][j][l] = dp[(i+1)%n][j][l-1] + dp[(i-1+n)%n][j][l-1];
					 	if(n==2) dp[i][j][l]/=2;
					}
				}
			}
			
			temp = br.readLine().split(" ");
			int a = Integer.parseInt(temp[0]);int b = Integer.parseInt(temp[1]);
			System.out.println(dp[a-1][b-1][k]);
		}
	}
}

		
