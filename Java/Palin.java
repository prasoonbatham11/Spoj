import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Palin
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
        int t = Integer.parseInt(br.readLine());
        while(t-->0)
        {
            String s = br.readLine();
            int n = s.length();
            boolean onlyNine = true;
            int i;
            for(i=0;i<n;i++) if(s.charAt(i)!='9') {
                onlyNine = false;
                break;
            }

            if(onlyNine)
            {
                int len = n+1;
                StringBuilder sbr = new StringBuilder();
                sbr.append(1);
                for(i=1;i<=len-2;i++) sbr.append('0');
                sbr.append('1');
                out.write(sbr.toString()+"\n");
            }
            else {
                int j;
                i = n%2==0?n/2-1:n/2;
                j = n/2;
                while(i>=0 && j<n && s.charAt(i)==s.charAt(j)) {
                    i--; j++;
                }
                if(i>=0 && j<n)
                {
                    boolean sat = false;
                    if(s.charAt(i)>s.charAt(j)) sat = true;

                    StringBuilder sbr = new StringBuilder(s.substring(0, j));
                    while(j<n)
                    {
                        sbr.append(s.charAt(n-1-j));
                        j++;
                    }
                    if(sat) {
                        out.write(sbr.toString()+"\n");
                        continue;
                    }
                }

                int carry = 1;
                i = n%2==0?n/2-1:n/2;
                j = n/2;
                String sb = "";
                StringBuilder ssb = new StringBuilder();

                while(i>=0 && j<n)
                {
                    int x = s.charAt(i)-'0'+carry;
                    carry = x/10;
                    x = x%10;
                    ssb.append(x);
                    i--;j++;
                }
                if(carry==1) {
                    ssb.append(1);
                }

                sb = ssb.toString();
                i = n%2==0?n/2-1:n/2;
                j = n/2;
                ssb = new StringBuilder();
                if(i==j) j=1;
                else j=0;
                i = sb.length()-1;
                //System.out.println(sb);
                while(i>=j)
                {
                    ssb.append(sb.charAt(i));
                    i--;
                }
                ssb.append(sb);

                out.write(ssb.toString()+"\n");
            }
        }

		out.flush();
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
