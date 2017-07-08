import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Submerge
{
    static int n,m,time, arpoint;
    static Graph g;
    static int[] vistime, lowtime, parent;
    static boolean[] visited;

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
            n = r.nextInt();
            m = r.nextInt();
            if(n==0) break;
            g = new Graph(n);
            int i;
            for(i=0;i<m;i++)
            {
                int a = r.nextInt()-1;
                int b = r.nextInt()-1;
                g.addEdge(a, b);
            }
            vistime = new int[n];
            lowtime = new int[n];
            parent = new int[n];
            visited = new boolean[n];
            Arrays.fill(visited, false);
            time = 0;
            arpoint = 0;
            parent[0] = 0;

            dfs(0);

            out.write(arpoint+"\n");
        }

        out.flush();
    }
    public static void dfs(int node)
    {
        visited[node] = true; vistime[node] = time; lowtime[node] = time;
        time++; int childcount = 0; boolean isArPoint = false;

        Iterator<Integer> i = g.adj[node].listIterator();

        while(i.hasNext())
        {
            int u = i.next();

            if(u == parent[node]) continue;

            if(!visited[u])
            {
                parent[u] = node;
                childcount++;
                dfs(u);

                if(vistime[node]<=lowtime[u])
                    isArPoint = true;
                else lowtime[node] = Math.min(lowtime[node], lowtime[u]);
            }

            else
            {
                lowtime[node] = Math.min(lowtime[node], vistime[u]);
            }
        }

        if(parent[node] == node && childcount>=2 || parent[node] != node && isArPoint)
            arpoint++;
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
            adj[b].add(a);
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