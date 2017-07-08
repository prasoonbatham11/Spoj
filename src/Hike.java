import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Hike
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
        while (true)
        {
            String s = br.readLine();
            if(s.equals("0")) break;
            String[] temp = s.split(" ");
            int n = Integer.parseInt(temp[0]);
            int a = Integer.parseInt(temp[1]);
            int b = Integer.parseInt(temp[2]);
            int c = Integer.parseInt(temp[3]);

            char arr[][] = new char[n+1][n+1];

            for(int i = 1;i<=n;i++)
            {
                temp = br.readLine().split(" ");
                for(int j=1;j<=n;j++)
                {
                    arr[i][j] = temp[j-1].charAt(0);
                    //System.out.print(arr[i][j]+" ");
                }
                //System.out.println();
            }

            int h = bfs(n,a,b,c,arr);
            if(h==Integer.MAX_VALUE-100)
                out.write("impossible\n");
            else out.write(h+"\n");




        }

        out.flush();
    }
    public static int bfs(int n,int a, int b, int c, char[][] arr)
    {
        int dp[][][] = new int[n+1][n+1][n+1];
        LinkedList<Integer> q = new LinkedList<>();
        int i,j,k;
        for(i=1;i<=n;i++) for(j=1;j<=n;j++) for(k=1;k<=n;k++) dp[i][j][k] = Integer.MAX_VALUE-100;

        dp[a][b][c]= 0;
        q.add(a); q.add(b); q.add(c);
        while(!q.isEmpty())
        {
            i = q.poll();
            j = q.poll();
            k = q.poll();

            //System.out.println(i+" "+j+" "+k+" "+dp[i][j][k]);


            for(int p = 1;p<=n;p++)
            {
                if(i!=p && arr[i][p]==arr[j][k] && dp[p][j][k]>dp[i][j][k]+1)
                {
                    dp[p][j][k] = dp[i][j][k]+1;
                    //System.out.println(p+" "+j+" "+k+" "+dp[p][j][k]);
                    q.add(p); q.add(j); q.add(k);
                }
                if(j!=p && arr[j][p]==arr[i][k] && dp[i][p][k]>dp[i][j][k]+1)
                {
                    dp[i][p][k] = dp[i][j][k]+1;
                    //System.out.println(i+" "+p+" "+k+" "+dp[i][p][k]);
                    q.add(i); q.add(p); q.add(k);
                }
                if(k!=p && arr[k][p]==arr[i][j] && dp[i][j][p]>dp[i][j][k]+1)
                {
                    dp[i][j][p] = dp[i][j][k]+1;
                    //System.out.println(i+" "+j+" "+p+" "+dp[i][j][p]);
                    q.add(i); q.add(j); q.add(p);
                }
            }
        }

        int min = Integer.MIN_VALUE-100;
        for(i=1;i<=n;i++)
        {
            if(dp[i][i][i]<min) min = dp[i][i][i];
        }
        return min;
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