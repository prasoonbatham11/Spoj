import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Lisa
{
    static long dp[][];
    static int p[];
    static char op[];
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        int t = Integer.parseInt(br.readLine());
        while(t-->0)
        {
            String s = br.readLine();
            int n = s.length();
            int x = (n+1)/2;
            p = new int[(n+1)/2];
            op = new char[n/2];
            int i,j=0,k=0;
            for(i=0;i<n;i++)
            {
                if(s.charAt(i)=='*' || s.charAt(i)=='+') op[k++] = s.charAt(i);
                else p[j++] = s.charAt(i)-'0';
            }
            dp = new long[x][x];
            for(i=0;i<x;i++) for(j=0;j<x;j++) dp[i][j] = -1;
            long ans = mcm(0,p.length-1);
            //  long ans = 0;
            for(i=0;i<x;i++) for(j=0;j<x;j++) dp[i][j] = -1;
            long ans2 = mcm2(0,p.length-1);
            out.write(ans2+" "+ans+"\n");




        }

        out.flush();
    }
    public static long mcm(int i, int j)
    {
        if(dp[i][j]!=-1) return dp[i][j];
        if(i==j) return dp[i][j] = p[i];

        int k;
        dp[i][j] = (long)Double.POSITIVE_INFINITY;
        for(k=i;k<j;k++)
        {
            if(op[k]=='+')
                dp[i][j] = (long)Math.min(dp[i][j],mcm(i,k)+mcm(k+1,j));
            else
                dp[i][j] = (long)Math.min(dp[i][j],mcm(i,k)*mcm(k+1,j));
        }
        return dp[i][j];
    }
    public static long mcm2(int i, int j)
    {
        if(dp[i][j]!=-1) return dp[i][j];
        if(i==j) return dp[i][j] = p[i];
        //System.out.println(i+" "+j);

        int k;
        dp[i][j] = (long)Double.NEGATIVE_INFINITY;
        for(k=i;k<j;k++)
        {
            long f=0;
            if(op[k]=='+')
            {f = mcm2(i,k)+mcm2(k+1,j);
                dp[i][j] = (long)Math.max(dp[i][j],f);
            }
            else
            {
                f = mcm2(i,k)*mcm2(k+1,j);
                dp[i][j] = (long)Math.max(dp[i][j],f);
            }
            //System.out.print(k+" "+f+" ");
        }
        //System.out.println(dp[i][j]);
        return dp[i][j];
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