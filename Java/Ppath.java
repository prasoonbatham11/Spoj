import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Ppath
{
    static LinkedList<Integer> adj[];
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        boolean seive[] = new boolean[10005];Arrays.fill(seive,true);
        seive[0] = seive[1] = false;
        int p,j,h;
        for(p=2;p*p<seive.length;p++) if(seive[p]) for(j=p*p;j<seive.length;j+=p) seive[j] = false;

        adj = new LinkedList[10005];
        for(p=0;p<adj.length;p++) adj[p] = new LinkedList<Integer>();
        for(p=1000;p<=9999;p++)
        {
            if(seive[p])
            {
                //System.out.print("\n"+p+" has ");
                for(j=0;j<=9;j++)
                {
                    h = (p/10)*10 + j;
                    if(h!=p && seive[h]) {addEdge(p, h);}
                }
                for(j=0;j<=9;j++)
                {
                    h = (p/100)*100 + j*10 + p%10;
                    if(h!=p && seive[h]) {addEdge(p, h);}
                }
                for(j=0;j<=9;j++)
                {
                    h = (p/1000)*1000 + j*100 + p%100;
                    if(h!=p && seive[h]) {addEdge(p, h);}
                }
                for(j=1;j<=9;j++)
                {
                    h = j*1000 + p%1000;
                    if(h!=p && seive[h]) {addEdge(p, h);}
                }
            }
        }


        int t = r.nextInt();
        while(t-->0)
        {
            int src = r.nextInt();
            int dst = r.nextInt();
            int c = bfs(src, dst);
            if(c==-1) out.write("Impossible\n");
            else
                out.write(c+"\n");
        }

        out.flush();
    }
    public static void addEdge(int i, int j)
    {
        adj[i].add(j);
        adj[j].add(i);
    }
    public static int bfs(int n, int m)
    {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int weight[] = new int[10005];
        boolean visited[] = new boolean[10005];
        Arrays.fill(visited, false);
        Arrays.fill(weight, 0);
        queue.addLast(n);
        weight[n] = 0;
        visited[n] = true;
        while(queue.size()!=0)
        {
            int u = queue.poll();
            //if(visited[u]) continue;
            //visited[u] = true;
            //System.out.println(u+" has ");
            if(u==m) return weight[u];
            Iterator<Integer> i = adj[u].listIterator();
            while(i.hasNext())
            {
                int x = i.next();

                if(visited[x] == false)
                {
                    queue.addLast(x);
                    weight[x] = 1+weight[u];
                    visited[x] = true;
                    //System.out.print(x+" "+weight[x]+" ");
                }
            }
            //System.out.println("\n");
        }
        return -1;
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