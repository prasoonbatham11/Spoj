import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Webisl
{
    static Graph g,h;
    static boolean vis[];
    static LinkedList<Integer> stack;
    static ArrayList<ArrayList<Integer>> ans;
    static int j;

	public static void main(String args[])throws Exception
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
		FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
		Input in = new Input();
		Reader r = new Reader();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//the code goes here
        int n = r.nextInt();
        int m = r.nextInt();
        g = new Graph(n);
        h = new Graph(n);
		stack = new LinkedList<>();
		vis = new boolean[n];
		ans = new ArrayList<>();

		Arrays.fill(vis, false);
        int i;

        for(i=0;i<m;i++) {
        	int x = r.nextInt(), y = r.nextInt();
        	g.addEdge(x,y);
        	h.addEdge(y,x);
		}

		for(i=0;i<n;i++) dfs1(i);

        Arrays.fill(vis, false);
        j = -1;

        while (!stack.isEmpty())
		{
			int x = stack.pop();
			if(!vis[x])
			{
				j++;
				ans.add(new ArrayList<>());
				dfs2(x);
			}
		}

		int min;
        int d[] = new int[n];
        for(i=0;i<=j;i++)
		{
			ArrayList<Integer> a = ans.get(i);
			min = Integer.MAX_VALUE;
			int l =a.size(), k;
			for(k=0;k<l;k++)
			{
				//System.out.print(a.get(k));
				min = Math.min(min, a.get(k));
			}
			//System.out.println();
			for(k=0;k<l;k++)
				d[a.get(k)] = min;
		}

		for(i=0;i<n;i++) out.write(d[i]+"\n");



		out.flush();
	}
	public static void dfs1(int node)
    {
        if(vis[node]) return;
        vis[node] = true;
        Iterator<Integer> i = g.adj[node].listIterator();
        while (i.hasNext())
        {
            int x = i.next();
            if(!vis[x]) {
                dfs1(x);
            }
        }
        stack.push(node);
    }
    public static void dfs2(int node)
	{
		if(vis[node]) return;
		vis[node] = true;
		Iterator<Integer> i = h.adj[node].listIterator();
		while (i.hasNext())
		{
			int x = i.next();
			if(!vis[x]) {
				dfs2(x);
			}
		}
		ans.get(j).add(node);
	}
	public static class Graph
	{
	    LinkedList<Integer> adj[];
	    int n;
	    public Graph(int v)
	    {
	        n = v;
	        adj = new LinkedList[n];
	        int i;
	        for(i=0;i<v;i++)
	            adj[i] = new LinkedList<Integer>();
	    }
	    public void addEdge(int a, int b)
	    {
	        adj[a].add(b);
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
