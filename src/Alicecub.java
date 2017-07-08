import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Alicecub
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
        Graph hypercube = new Graph(16);
        initializeHypercube(hypercube);

        int bit[] = new int[65536];

        bfs(bit, hypercube);

        int t = r.nextInt();
        int x = 1;
        while(t-->0)
        {
            int a[] = new int[16];
            int i;
            for(i=0;i<16;i++)
            {
                a[i] = r.nextInt();
            }

            int b = 0;
            for(i=0;i<16;i++)
            {
                b += a[i]*Math.pow(2, 15-i);
            }
            if(bit[b]<=3) out.write("Case #"+x+": "+bit[b]+"\n");
            else out.write("Case #"+x+": more\n");
            x++;
        }





        out.flush();
    }

    public static void bfs(int dist[], Graph hypercube)
    {
        boolean vis[] = new boolean[65536];
        Arrays.fill(vis, false);
        Arrays.fill(dist, Integer.MAX_VALUE);
        vis[255] = true;
        dist[255] = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(255);

        while(!queue.isEmpty())
        {
            int bit = queue.poll();
            //System.out.println(Integer.toBinaryString(bit)+" "+dist[bit]);
            if(dist[bit] == 3) continue;
            int i;
            for(i=0;i<16;i++)
            {
                Iterator<Integer> j = hypercube.adj[i].listIterator();
                while(j.hasNext())
                {
                    int x = j.next();

                    int iset = ((bit) & (1<<i));
                    int xset = ((bit) & (1<<x));

                    //System.out.println(i+" "+x+" "+iset+" "+xset);
                    if(iset==0 && xset>0)
                    {
                        int newbit = bit|(1<<i);
                        newbit = (newbit & (~(1<<x)));
                        if(!vis[newbit])
                        {
                            vis[newbit] = true;
                            dist[newbit] = dist[bit]+1;
                            queue.add(newbit);
                        }
                    }
                    else if(iset>0 && xset==0)
                    {
                        int newbit = bit|(1<<x);
                        newbit = (newbit & (~(1<<i)));
                        if(!vis[newbit])
                        {
                            vis[newbit] = true;
                            dist[newbit] = dist[bit]+1;
                            queue.add(newbit);
                        }
                    }

                }
            }
        }
    }

    public static void initializeHypercube(Graph h)
    {
        h.addEdge(0,1);h.addEdge(1,3);h.addEdge(3,2);h.addEdge(2,0);
        h.addEdge(4,5);h.addEdge(5,7);h.addEdge(7,6);h.addEdge(6,4);
        h.addEdge(12,13);h.addEdge(13,15);h.addEdge(15,14);h.addEdge(14,12);
        h.addEdge(8,9);h.addEdge(9,11);h.addEdge(11,10);h.addEdge(10,8);
        h.addEdge(0,4);h.addEdge(1,5);h.addEdge(2,6);h.addEdge(3,7);
        h.addEdge(4,12);h.addEdge(5,13);h.addEdge(7,15);h.addEdge(6,14);
        h.addEdge(8,12);h.addEdge(9,13);h.addEdge(11,15);h.addEdge(10,14);
        h.addEdge(0,8);h.addEdge(1,9);h.addEdge(3,11);h.addEdge(2,10);
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