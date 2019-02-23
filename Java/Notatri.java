import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Notatri
{
    static long a[];
	public static void main(String args[])throws Exception
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
		FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
		Input in = new Input();
		Reader r = new Reader();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//the code goes here
        while(true)
        {
            int n = r.nextInt();
            if(n==0) break;
            int i,j;
            a = new long[n];
            long ans = 0;
            for(i=0;i<n;i++) a[i] = r.nextLong();

            Arrays.sort(a);
            //System.out.println(Arrays.toString(a));
            for(i=0;i<n;i++)
            {
                for(j=i+1;j<n;j++)
                {
                    int x = upperbound(j, n, a[i]+a[j]);
                    //System.out.println(i+" "+j+" "+a[i]+" "+a[j]+" "+(a[i]+a[j])+" "+x);
                    ans += n-x;
                }
            }
            out.write(ans+"\n");
        }

		out.flush();
	}
    public static int upperbound(int low, int high, long x)
    {
        int l = low;
        int h = high;
        while(l<h)
        {
            int m = (l+h)/2;
            if(x>=a[m]) l = m+1;
            else h = m;
        }
        return l;
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
