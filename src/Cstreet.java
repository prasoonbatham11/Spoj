import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Cstreet
{
    static int u[],v[],a[],s[];
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
            int c = r.nextInt();
            int n = r.nextInt();
            int m = r.nextInt();

            u = new int[m];
            v = new int[m];
            a = new int[m];
            s = new int[n];
            int i;
            for(i=0;i<m;i++) {u[i] = r.nextInt()-1; v[i] = r.nextInt()-1; a[i] = r.nextInt();}
            for(i=0;i<n;i++) s[i] = i;
            mergeSort(0,m-1);
            long ans =0;
            for(i=0;i<m;i++)
            {
                if(!find(u[i], v[i]))
                {
                    ans+=a[i];
                    union(u[i], v[i]);
                }
            }
            out.write(c*ans+"\n");
        }

        out.flush();
    }
    public static int root(int a)
    {
        while(s[a]!=a)
        {
            s[a] = s[s[a]];
            a = s[a];
        }
        return a;
    }
    public static void union(int x, int y)
    {
        s[root(x)] = root(y);
    }
    public static boolean find(int x, int y)
    {
        return root(x)==root(y);
    }
    public static void mergeSort(int left,int right)
    {
        int mid=(left+right)/2;
        if(left<right)
        {
            mergeSort(left,mid);
            mergeSort(mid+1,right);
            merge(left,mid,right);
        }
    }
    public static void merge(int left,int mid,int right)
    {
        int t[]=new int[right-left+1];
        int t1[] = new int[right-left+1];
        int t2[] = new int[right-left+1];
        int pos=0,lpos=left,rpos=mid+1;
        while(lpos<=mid&&rpos<=right)
        {
            if(a[lpos]<a[rpos])
            {t[pos]=a[lpos]; t1[pos]=u[lpos]; t2[pos]=v[lpos];pos++;lpos++;}
            else
            {t[pos]=a[rpos]; t1[pos]=u[rpos]; t2[pos]=v[rpos];pos++;rpos++;}
        }
        while(lpos<=mid) {t[pos]=a[lpos]; t1[pos]=u[lpos]; t2[pos]=v[lpos];pos++;lpos++;}
        while(rpos<=right) {t[pos]=a[rpos]; t1[pos]=u[rpos]; t2[pos]=v[rpos];pos++;rpos++;}
        while(--pos>=0) {a[pos+left]=t[pos]; u[pos+left] = t1[pos]; v[pos+left] = t2[pos];}
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