import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Bat3
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
        int t = r.nextInt();
        int n,m,a[],first[],second[],third[],i,k,l,j,x;
        while(t-->0)
        {
            n = r.nextInt();
            m = r.nextInt();
            a = new int[n];
            first = new int[m+1];
            second = new int[n-m-1];
            third = new int[n-1];
            j = 0; k = 0; l = 0;
            for(i=0;i<n;i++)
            {
                a[i] = r.nextInt();
                if(i<=m) first[j++] = a[i];
                else second[k++] = a[i];
                if(i!=m) third[l++] = a[i];
            }
            x = lds(third, n-1);
            x = (int)Math.max(x, llds(first, m+1)+lds(second, n-m-1));
            //System.out.println(lds(third, n-1)+" "+lds(first, m+1)+" "+lds(second, n-m-1));
            out.write(x+"\n");

        }

        out.flush();
    }
    public static int llds(int a[], int n)
    {
        int t[] = new int[n];
        Arrays.fill(t,1);
        int i,j;
        for(i=1;i<n;i++)
        {
            for(j=0;j<i;j++)
            {
                if(a[j]>a[i] && t[j]+1>t[i])
                    t[i] = t[j]+1;
            }
        }
        return t[n-1];
    }
    public static int lds(int a[], int n)
    {
        int t[] = new int[n];
        Arrays.fill(t,1);
        int i,j;
        for(i=1;i<n;i++)
        {
            for(j=0;j<i;j++)
            {
                if(a[j]>a[i] && t[j]+1>t[i])
                    t[i] = t[j]+1;
            }
        }
        int max = 0;
        for(i=0;i<n;i++) max = (int)Math.max(max, t[i]);
        return max;
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