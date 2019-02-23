import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Bat1
{
    static int n,m,k,batchcost[], itemcost[][], rate[][], dp[][][][];
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
            n = r.nextInt(); m = r.nextInt(); k = r.nextInt();
            batchcost = new int[n];
            int i,j,x,y;
            for(i=0;i<n;i++) batchcost[i] = r.nextInt();
            itemcost = new int[n][m];
            for(i=0;i<n;i++) for(j=0;j<m;j++) itemcost[i][j] = r.nextInt();
            rate = new int[n][m];
            for(i=0;i<n;i++) for(j=0;j<m;j++) rate[i][j] = r.nextInt();
            dp = new int[n][m][k+1][2];
            for(i=0;i<n;i++) for(j=0;j<m;j++) for(x=0;x<=k;x++) for(y=0;y<2;y++) dp[i][j][x][y] = -1;
            out.write(solve(0,0,k,0)+"\n");
        }


        out.flush();
    }
    public static int solve(int batch, int item, int cost, int status)
    {

        if(cost==0) return dp[batch][item][cost][status]=0;
        if(item == m || batch == n) return 0;
        if(cost<0) return -1000000;
        if(dp[batch][item][cost][status]!=-1) return dp[batch][item][cost][status];

        int ans = 0;
        if(status == 0)
        {
            ans = (int)Math.max(solve(batch+1, item, cost, 0), solve(batch, 0, cost-batchcost[batch], 1));
        }
        else
        {
            if(cost-itemcost[batch][item]>=0)
                ans = (int)Math.max(ans, rate[batch][item] + solve(batch, item, cost-itemcost[batch][item], 1));
            ans = (int)Math.max(ans, solve(batch, item+1, cost, 1));
            ans = (int)Math.max(ans, solve(batch+1, 0, cost, 0));
        }
        return dp[batch][item][cost][status] = ans;
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