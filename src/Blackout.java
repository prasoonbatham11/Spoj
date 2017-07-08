import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Blackout
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
        int n = r.nextInt();
        int m = r.nextInt();
        int q = r.nextInt();
        int k = r.nextInt();

        int a[][] = new int[n+1][m+1];
        int i,j;
        for(i=1;i<=n;i++) for(j=1;j<=m;j++) a[i][j] = r.nextInt();

        int cum[][] = new int[n+1][m+1];

        cum[0][0] = a[0][0];
        for(i=1;i<=n;i++) cum[i][1] = cum[i-1][1]+a[i][1];
        for(j=1;j<=m;j++) cum[1][j] = cum[1][j-1]+a[1][j];

        for(i=2;i<=n;i++)
        {
            for(j=2;j<=m;j++)
            {
                cum[i][j] = a[i][j]+cum[i-1][j]+cum[i][j-1]-cum[i-1][j-1];
            }
        }

        /*for(i=1;i<=n;i++) {
            for (j = 1; j <= m; j++)
                System.out.print(cum[i][j]+" ");
            System.out.println();
        }*/

        int knap[][] = new int[q+1][k+1];

        for(i=1;i<=q;i++)
        {
            int w = r.nextInt();
            int x = r.nextInt();
            int y = r.nextInt();
            int z = r.nextInt();

            int weight = cum[y][z] - cum[w-1][z] - cum[y][x-1] + cum[w-1][x-1];

            int val = (z-x+1)*(y-w+1);

            //System.out.println(weight+" "+val);

            for(j=1;j<=k;j++)
            {
                if(j<weight) knap[i][j] = knap[i-1][j];
                else
                {
                    knap[i][j] = Math.max(knap[i-1][j], val+knap[i-1][j-weight]);
                }
            }
        }

        out.write(knap[q][k]+"\n");



        out.flush();
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