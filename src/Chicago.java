import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Chicago
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
        while(true)
        {
            int n = r.nextInt();
            if(n==0) break;
            int m = r.nextInt();

            Graph g = new Graph(n+1);
            int i;
            for(i=0;i<m;i++)
            {
                g.addEdge(r.nextInt(), r.nextInt(), -Math.log(r.nextDouble()/100.0));
            }

            out.write(djikstra(g)+" percent\n");

        }

        out.flush();
    }
    public static double djikstra(Graph k)
    {
        PriorityQueue<Pair> pq = new PriorityQueue<>(k.n, new PairComp());
        pq.add(new Pair(1, 0));
        boolean visited[] = new boolean[k.n];
        double dist[] =new double[k.n];
        Arrays.fill(visited, false);
        Arrays.fill(dist,Double.MAX_VALUE);
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
                double w = q.second;
                //System.out.println(x+" "+u+" "+dist[u]+" "+w);
                if(dist[u]>dist[x]+w)
                {
                    dist[u] = dist[x]+w;
                    pq.add(new Pair(u, dist[u]));
                }
            }
        }



        //System.out.println(Arrays.toString(dist));
        return Math.exp(-dist[k.n-1])*100;
    }
    public static class PairComp implements Comparator<Pair>
    {
        public int compare(Pair first, Pair second)
        {
            if(first.second<=second.second) return -1;
            return 1;
        }
    }
    public static class Pair
    {
        int first;double second;
        Pair(int first, double second)
        {
            this.first = first;
            this.second = second;
        }
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
        public void addEdge(int a, int b, double w)
        {
            adj[a].add(new Pair(b,w));
            adj[b].add(new Pair(a,w));
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