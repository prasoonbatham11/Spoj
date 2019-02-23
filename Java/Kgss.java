import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Kgss
{
    static long a[];
	static int tree[];
	public static void main(String args[])throws Exception
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
		FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
		Input in = new Input();
		Reader r = new Reader();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//the code goes here
        int n = Integer.parseInt(br.readLine());
        a = new long[n+1];
        a[n] = Long.MIN_VALUE;
        tree = new int[5*n];
        String sx;
        String temp[];
        int i;
        while((sx = br.readLine()).equals(""));
        temp = sx.split(" ");
        for(i=0;i<n;i++) a[i] = Long.parseLong(temp[i]);

        build(1, 0, n-1);
        int q = Integer.parseInt(br.readLine());
        while (q-->0)
        {
			while((sx = br.readLine()).equals(""));
            temp = sx.split(" ");
            int s = Integer.parseInt(temp[1]);
            int e = Integer.parseInt(temp[2]);
            if(temp[0].equals("U"))
			{
				s--;
				update(1, 0, n-1, s, e);
			}
			else
			{
				s--; e--;
				int m = query(1, 0, n-1, s, e);
				long ans = a[m];
				ans += Math.max(a[query(1, 0, n-1, s, m-1)],
						a[query(1, 0, n-1, m+1, e)]);
				out.write(ans+"\n");
			}
        }

		out.flush();
	}
	public static void build(int n, int l, int r)
    {
        if(l==r)
        {
            tree[n] = l;
            return;
        }
        int m = (l+r)/2;
        build(2*n, l, m);
        build(2*n+1, m+1,r);
        tree[n] = a[tree[2*n]]>=a[tree[2*n+1]]?tree[2*n]:tree[2*n+1];
    }
	public static void update(int n, int l, int r, int i, long x)
    {
        if(l==r && l==i)
        {
            tree[n] = i;
            a[i] = x;
            return;
        }
        int m = (l+r)/2;
        if(l<=i && i<=m)
        {
            update(2*n, l, m, i, x);
        }
        else update(2*n+1, m+1, r, i, x);
		tree[n] = a[tree[2*n]]>=a[tree[2*n+1]]?tree[2*n]:tree[2*n+1];
    }

    public static int query(int n, int s, int e, int l, int r)
	{
		if(r<s || l>e) return a.length-1;
		if(l<=s && e<=r) return tree[n];

		int m = (s+e)/2;
		int p1 = query(2*n, s, m, l, r);
		int p2 = query(2*n+1, m+1, e, l, r);
		return a[p1]>=a[p2]?p1:p2;
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
