import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Service
{
    static int a[][];
    static int req[];
    static long dp[][][];
    static int x, y, z;
    static int n;
	public static void main(String args[])throws Exception
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
		FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
		Input in = new Input();
		Reader r = new Reader();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//the code goes here
        int t = r.nextInt();
        while (t-->0)
        {
            int l = r.nextInt();
            n = r.nextInt()+1;
            a = new int[l+1][l+1];
            int i,j;
            for(i=1;i<=l;i++) for(j=1;j<=l;j++) a[i][j] = r.nextInt();
            req = new int[n];
            for(i=1;i<n;i++) req[i] = r.nextInt();
            dp = new long[n+1][l+1][l+1];
            int k;
            for(i=0;i<n;i++) for(j=0;j<=l;j++) for(k=0;k<=l;k++) dp[i][j][k] = Integer.MAX_VALUE;
            for(i=0;i<=l;i++) for(j=0;j<=l;j++) dp[n][i][j] = 0;

            req[0] = 3;

            for(i=n-1;i>=1;i--)
			{
				for(j=1;j<=l;j++)
				{
					for(k=1;k<=l;k++)
					{
						if(j!=k && j!=req[i-1] && k!=req[i-1])
						{
							if(j==req[i]) {
								dp[i][j][k] = dp[i+1][req[i-1]][k];
							}
							else if(k==req[i]) {
								dp[i][j][k] = dp[i+1][j][req[i-1]];
							}
							else if(req[i-1]==req[i]) {
								dp[i][j][k] = dp[i+1][j][k];
							}
							else {
								dp[i][j][k] = Math.min(
										Math.min(
												a[req[i-1]][req[i]]+dp[i+1][j][k],
												a[j][req[i]]+dp[i+1][req[i-1]][k]
										),
										a[k][req[i]]+dp[i+1][j][req[i-1]]);
							}
						}
					}
				}
			}

			long ans = Math.min(dp[1][1][2], dp[1][2][1]);
			out.write(ans+"\n");

            /*long ans = 0;
            req[0] = 1;
            ans = solve(1, 2, 3);
            req[0] = 2;
            ans = Math.min(solve(1, 1, 3), ans);
            req[0] = 3;
            ans = Math.min(ans, solve(1, 1, 2));
            out.write(ans+"\n");*/



        }

		out.flush();
	}

	public static long solve(int i, int x, int y)
    {
        if(i==n) return 0;
        if(dp[i][x][y]!=-1) return dp[i][x][y];

        if(x==req[i]) return dp[i][x][y] = solve(i+1, req[i-1], y);
        else if(y==req[i]) return dp[i][x][y] = solve(i+1, x, req[i-1]);
        else if(req[i-1]==req[i]) return dp[i][x][y] = solve(i+1, x, y);

        return dp[i][x][y] = Math.min(
                Math.min(
                        a[req[i-1]][req[i]]+solve(i+1, x, y),
                        a[x][req[i]]+solve(i+1, req[i-1], y)
                ),
                a[y][req[i]]+solve(i+1, x, req[i-1])
        );


    }
	public static class Reader
	{
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader()
		{
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException
		{
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException
		{
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1)
			{
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException
		{
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
			{
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException
		{
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException
		{
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			}
			while ((c = read()) >= '0' && c <= '9');

			if (c == '.')
			{
				while ((c = read()) >= '0' && c <= '9')
				{
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException
		{
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException
		{
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException
		{
			if (din == null)
				return;
			din.close();
		}
	}

	public static class Input
 	{
		BufferedReader br;
		StringTokenizer st;

		public Input()
		{
		 	br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next()
		{
	  		while (st == null || !st.hasMoreElements())
	  		{
		      	try
		      	{
	          		st = new StringTokenizer(br.readLine());
		      	}
		      	catch (IOException e)
		      	{
		          	e.printStackTrace();
		      	}
		  	}
		  	return st.nextToken();
		}

		int nextInt()
		{
	 		return Integer.parseInt(next());
		}

		long nextLong()
		{
		  	return Long.parseLong(next());
		}

		double nextDouble()
		{
		  	return Double.parseDouble(next());
		}

		String nextLine()
		{
		  	String str = "";
		  	try
		  	{
		     	str = br.readLine();
		  	}
		  	catch (IOException e)
		  	{
		     	e.printStackTrace();
		  	}
		  	return str;
		}
	}
}
