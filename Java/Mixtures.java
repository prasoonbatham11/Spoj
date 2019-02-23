import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Mixtures
{
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        //Reader r = new Reader();
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        String line;
        int xx = 1;
        while((line=r.readLine())!=null)
        //while(xx<=2)
        {
            //xx++;
            int n = Integer.parseInt(line);
            //int n = r.nextInt();
            int a[] = new int[n+1];
            int m[][] = new int[n+1][n+1];
            int s[][] = new int[n+1][n+1];
            int i,j,l,k;
            StringTokenizer tokenizer = new StringTokenizer(r.readLine());
            for (i=1;i <= n; i++)
                a[i] = Integer.parseInt(tokenizer.nextToken());

            for(i=1;i<=n;i++) {m[i][i] = 0; s[i][i] = a[i];}

            for(l=2;l<=n;l++)
            {
                for(i=1;i<=n-l+1;i++)
                {
                    j = i+l-1;
                    m[i][j] = (int)Double.POSITIVE_INFINITY;
                    for(k=i;k<=j-1;k++)
                    {
                        int q = m[i][k]+m[k+1][j] + s[i][k]*s[k+1][j];
                        if(q<m[i][j])
                        {
                            m[i][j] = q;
                            s[i][j] = (s[i][k]+s[k+1][j])%100;
                        }
                    }
                }
            }
            //for(i=1;i<=n;i++) {for(j=1;j<=n;j++) out.write(m[i][j]+" ");out.write("\n");}
            //for(i=1;i<=n;i++) {for(j=1;j<=n;j++) out.write(s[i][j]+" ");out.write("\n");}
            out.write(m[1][n]+"\n");
        }

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