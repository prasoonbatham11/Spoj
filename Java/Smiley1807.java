import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Smiley1807
{
    static int a[];
    static int dp[][];
    static int b[];
    static int n;
    static int m[];
	public static void main(String args[])throws Exception
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
		FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
		Input in = new Input();
		Reader r = new Reader();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//the code goes here
        String s = br.readLine();
        m = new int[]{1,8,0,7};

        int i;
        int l = s.length();

        a = new int[l];
        b = new int[l];

        int j = 0;
        a[0] = s.charAt(0)-'0';
        b[0] = 1;
        for(i=1;i<l;i++)
        {
            if(s.charAt(i)==s.charAt(i-1))
            {
                b[j]++;
            }
            else
            {
                j++;
                a[j] = s.charAt(i)-'0';
                b[j] = 1;
            }
        }
        //System.out.println(Arrays.toString(a)+"\n"+Arrays.toString(b)+"\n"+j);
        n = j+1;

        dp = new int[n+1][5];
        for(i=0;i<n;i++) for(j=0;j<4;j++) dp[i][j] = -1;

        for(i=0;i<=n;i++) dp[i][4] = 0;
        for(i=0;i<4;i++) dp[n][i]=Integer.MIN_VALUE;

        for(i=n-1;i>=0;i--)
        {
            for(j=3;j>=0;j--)
            {
                if(a[i]==m[j]) {
                    dp[i][j] = Math.max(b[i]+dp[i+1][j+1], b[i]+dp[i+1][j]);
                }
                else dp[i][j] = dp[i+1][j];
            }
        }


        //int ans = solve(0, 0);
        /*for(i=0;i<n;i++) {
            for(j=0;j<4;j++) System.out.print(dp[i][j]+"\t");
            System.out.println();
        }*/
        int ans = dp[0][0];
        ans = ans>=0?ans:0;
        out.write(ans+"\n");


		out.flush();
	}
	public static int solve(int idx, int want)
    {
        if(want==4) return 0;
        if(idx == n) return Integer.MIN_VALUE;

        if(dp[idx][want]!=-1) return dp[idx][want];

        if(a[idx]==m[want])
        {
            dp[idx][want] = Math.max(b[idx]+solve(idx+1, want+1),
                    Math.max(solve(idx+1, want), b[idx]+solve(idx+1, want)));
        }
        else dp[idx][want] = solve(idx+1, want);
        return dp[idx][want];
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
