import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Incards
{
    static Graph g;
    static Graph h;
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
            int n = r.nextInt();
            int m = r.nextInt();
            g = new Graph(n+1);
            h = new Graph(n+1);
            int i;
            for(i=0;i<m;i++)
            {
                int x = r.nextInt();
                int y = r.nextInt();
                int w = r.nextInt();

                g.addEdge(x,y,w);
                h.addEdge(y,x,w);
            }

            //print(g);
            //print(h);

            out.write((djikstra(g)+djikstra(h))+"\n");


        }


        out.flush();
    }
    public static void print(Graph k)
    {
        int i;
        for(i=1;i<k.n;i++)
        {
            Iterator<Pair> j = k.adj[i].listIterator();
            while(j.hasNext())
            {
                Pair x = j.next();
                System.out.println(i+" "+x.first+" "+x.second);
            }
        }
    }
    public static int djikstra(Graph k)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>(k.n, new PairComp());
        pq.add(new Pair(1, 0));
        boolean visited[] = new boolean[k.n];
        int dist[] =new int[k.n];
        Arrays.fill(visited, false);
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        //visited[1] = true;
        while(!pq.isEmpty())
        {
            Pair p = pq.remove();
            int x = p.first;
            //System.out.println(x+" "+dist[x]);
            if(visited[x]) continue;
            visited[x] = true;

            Iterator<Pair> i = k.adj[x].listIterator();
            while(i.hasNext())
            {
                Pair q = i.next();
                int u = q.first;
                int w = q.second;
                //System.out.println(x+" "+u+" "+dist[u]+" "+w);
                if(dist[u]>dist[x]+w)
                {
                    dist[u] = dist[x]+w;
                    pq.add(new Pair(u, dist[u]));
                }
            }
        }

        //System.out.println(Arrays.toString(dist));
        int sum = 0;
        for(int j = 1; j<k.n;j++)
            sum+=dist[j];
        return sum;

    }
    public static class Graph
    {
        LinkedList<Pair> adj[];
        int n;
        public Graph(int v)
        {
            n = v;
            adj = new LinkedList[n];
            int i;
            for(i=0;i<v;i++)
                adj[i] = new LinkedList<Pair>();
        }
        public void addEdge(int a, int b, int weight)
        {
            adj[a].add(new Pair(b, weight));
            //adj[b].add(a);
        }
    }
    static class PairComp implements Comparator<Pair>
    {
        public int compare(Pair first, Pair second)
        {
            return first.second - second.second;
        }
    }
    public static class Pair
    {
        int first, second;
        Pair(int first, int second)
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