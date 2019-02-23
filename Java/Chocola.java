import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Chocola
{
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
        while(t-->0)
        {
            int m = r.nextInt();
            int n = r.nextInt();
            int i;
            int a[] = new int[m+n-2];
            char b[] = new char[m+n-2];
            //ArrayList<Pair> p = new ArrayList<Pair>();
            for(i=0;i<m-1;i++) {
                a[i] = r.nextInt();
                b[i] = 'v';
            }
            for(i=m-1;i<m+n-2;i++) {
                a[i] = r.nextInt();
                b[i] = 'h';
            }
            mergeSort(a, b, 0, m+n-3);

            int v = 1, h = 1;

            long ans = 0;
            for(i=0;i<m+n-2;i++)
            {
                int x = a[i];
                if(b[i]=='v') {
                    ans+=x*h;
                    v++;
                }
                else {
                    ans+=x*v;
                    h++;
                }
            }
            out.write(ans+"\n");
        }

		out.flush();
	}
	public static void mergeSort(int a[],char b[], int left,int right)
	{
	    int mid=(left+right)/2;
	    if(left<right)
	    {
	        mergeSort(a,b,left,mid);
	        mergeSort(a,b,mid+1,right);
	        merge(a,b,left,mid,right);
	    }
	}
	public static void merge(int a[],char b[], int left,int mid,int right)
	{
	    int t[]=new int[right-left+1];
	    char s[] = new char[right-left+1];
	    int pos=0,lpos=left,rpos=mid+1;
	    while(lpos<=mid&&rpos<=right)
	    {
	        if(a[lpos]>a[rpos])
            {
                s[pos] = b[lpos];
                t[pos++]=a[lpos++];
            }
	        else {
                s[pos] = b[rpos];
                t[pos++] = a[rpos++];
            }
	    }
	    while(lpos<=mid) {
	        s[pos] = b[lpos];
	        t[pos++]=a[lpos++];
        }
	    while(rpos<=right) {
	        s[pos] = b[rpos];
	        t[pos++]=a[rpos++];
        }
	    while(--pos>=0) {
	        b[pos+left] = s[pos];
	        a[pos+left]=t[pos];
        }
	}
	public static class Pair
	{
	    int first; char second;
	    Pair(int first, char second)
	    {
	        this.first = first;
	        this.second = second;
	    }
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
