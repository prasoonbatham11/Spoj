/* @(#)Gcpc11j.java
 */
/**
 *@author <a href="mailto:prasoonbatham@gmail.com">Prasoon Batham</a>
 */

import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Gcpc11j
{
    static long MOD = 1000000007;
    public static void main(String args[])throws Exception
    {
	BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
	Reader r = new Reader();
	Scanner sc = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	int t = r.nextInt();
	while(t-->0) {
	    int n = r.nextInt();
	    int i;
	    Graph g = new Graph(n);
	    for(i=0;i<n-1;i++) {
		g.addEdge(r.nextInt(), r.nextInt());
	    }

	    int maxDiam = 0;
	    boolean vis[] = new boolean[n];
	    boolean vis2[] = new boolean[n];
	    for(i=0;i<n;i++) {
		if(!vis[i]) {
		    int in[] = bfs(g, i, vis);
		    in = bfs(g, in[0], vis2);
		    maxDiam = Math.max(maxDiam, in[1]);
		}
	    }
	    out.write((int)Math.ceil(maxDiam/2.0)+"\n");
	}

	out.flush();
    }
    public static int[] bfs(Graph g, int node, boolean vis[]) {
	LinkedList<Integer> que = new LinkedList<>();
	que.add(node);que.add(0);
	int maxC = 0; int x = 0;
	while(!que.isEmpty()) {
	    int u = que.pollFirst();
	    int c = que.pollFirst();
	    vis[u] = true;
	    Iterator<Integer> i = g.getListIterator(u);
	    while(i.hasNext()) {
		int v = i.next();
		if(!vis[v]) {
		    que.add(v);
		    que.add(c+1);
		    maxC = c+1;
		    x = v;
		}
	    }
	}
	int ans[] = {x, maxC};
	return ans;
    }
    static class Graph {
	int n;
	LinkedList<Integer> adj[];
	boolean vis[];
	Graph(int n) {
	    this.n = n;
	    adj = new LinkedList[n];
	    for(int i=0;i<n;i++) adj[i] = new LinkedList<>();
	    vis = new boolean[n];
	    Arrays.fill(vis, false);
	}
	public void addEdge(int i, int j) {
	    addEdgeDirect(i,j);
	    addEdgeDirect(j,i);
	}
	public void addEdgeDirect(int i, int j) {
	    adj[i].add(j);
	}
	public Iterator<Integer> getListIterator(int i) {
	    return adj[i].listIterator();
	}
	public boolean isVisited(int i) {
	    return vis[i];
	}
	public void visit(int i) {
	    vis[i] = true;
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
}

