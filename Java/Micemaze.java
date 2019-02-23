import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Micemaze
{
    public static class Pair
    {
        int edge, weight;
        Pair(int e, int w)
        {
            edge = e;
            weight = w;
        }
    }
    public static class PairComparator implements Comparator<Pair>
    {
        public int compare(Pair f, Pair s)
        {
            return f.weight - s.weight;
        }
    }
    static LinkedList<Pair> adj[];
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
        int exit = r.nextInt();
        int time = r.nextInt();
        int m = r.nextInt();
        int i;
        adj = new LinkedList[n+1];
        for(i=1;i<=n;i++) adj[i] = new LinkedList<Pair>();
        for(i=0;i<m;i++)
        {
            int x = r.nextInt();
            int y = r.nextInt();
            int w = r.nextInt();
            adj[y].add(new Pair(x, w));
        }
        int c = djikstra(n, exit, time);
        out.write(c+"\n");

        out.flush();
    }
    public static int djikstra(int n, int src, int time)
    {
        int weight[] = new int[n+1];
        Arrays.fill(weight, 10000000);
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(n, new PairComparator());
        pq.add(new Pair(src, 0));
        weight[src] = 0;
        while(pq.size()!=0)
        {
            Pair p = pq.remove();
            int x = p.edge;
            Iterator<Pair> i = adj[x].listIterator();
            while(i.hasNext())
            {
                p = i.next();
                int u = p.edge;
                int wt = p.weight;
                if(weight[u]>weight[x]+wt)
                {
                    weight[u] = weight[x]+wt;
                    pq.add(new Pair(u, weight[u]));
                }
            }
        }
        int j, count = 0;
        for(j=1;j<=n;j++) if(weight[j]<=time) count++;
        return count;
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