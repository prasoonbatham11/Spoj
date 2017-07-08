import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Busyman
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
        while (t-->0)
        {
            int n = r.nextInt();
            int i;
            int a[] = new int[n];
            int b[] = new int[n];

            for(i=0;i<n;i++)
            {
                a[i] = r.nextInt();
                b[i] = r.nextInt();
            }

            mergeSort(b,a, 0, n-1);

            //System.out.println(Arrays.toString(a));
            //System.out.println(Arrays.toString(b));

            int max = -1;
            int count = 0;
            for(i=0;i<n;i++)
            {
                if(a[i]>=max)
                {
                    max = b[i];
                    count++;
                }
            }
            //System.out.println(Arrays.toString(dp));

            out.write(count+"\n");




        }


        out.flush();
    }

    public static int search(long a[], long k)
    {
        int lo = 0, hi = a.length-1, mid;
        while(lo<=hi)
        {
            mid = lo+(hi-lo)/2;
            if(a[mid]<k) lo = mid+1;
            else if(a[mid]>k) hi = mid-1;
            else return mid;
        }
        return -1;
    }
    public static int searchlower(int a[],int l, int h, int k)
    {
        if(k<a[l]) return l;
        if(k>a[h]) return -1;
        int lo = l, hi = h, mid;
        while(lo<=hi)
        {
            mid = lo+(hi-lo)/2;
            if(k<=a[mid]) hi = mid-1;
            else lo = mid+1;
        }
        if(a[lo]!=k) lo--;
        return lo;
    }
    public static int searchupper(long a[], long k)
    {
        if(k>a[a.length-1]) return -1;
        if(k==a[a.length-1]) return a.length;
        int lo = 0, hi = a.length-1, mid;
        while(lo<hi)
        {
            mid = lo+(hi-lo)/2;
            if(a[mid]<=k) lo = mid+1;
            else hi = mid;
        }
        return lo;
    }


    public static void mergeSort(int a[],int b[], int left,int right)
    {
        int mid=(left+right)/2;
        if(left<right)
        {
            mergeSort(a,b,left,mid);
            mergeSort(a,b,mid+1,right);
            merge(a,b,left,mid,right);
        }
    }
    public static void merge(int a[],int b[], int left,int mid,int right)
    {
        int t[]=new int[right-left+1];
        int g[] = new int[right-left+1];

        int pos=0,lpos=left,rpos=mid+1;
        while(lpos<=mid&&rpos<=right)
        {
            if(a[lpos]<a[rpos])
            {
                t[pos]=a[lpos];
                g[pos++] = b[lpos++];
            }

            else if(a[lpos]>a[rpos])
            {
                t[pos]=a[rpos];
                g[pos++] = b[rpos++];
            }
            else if(b[lpos]<b[rpos])
            {
                t[pos] = a[lpos];
                g[pos++] = b[lpos++];
            }
            else {
                t[pos] = a[rpos];
                g[pos++] = b[rpos++];
            }
        }
        while(lpos<=mid) {g[pos] = b[lpos];t[pos++]=a[lpos++];}
        while(rpos<=right) {g[pos] = b[rpos];t[pos++]=a[rpos++];}
        while(--pos>=0) {
            b[pos+left] = g[pos];
            a[pos+left]=t[pos];
        }
    }
    public static class Pair
    {
        int first, second;
        Pair(int first, int second)
        {
            this.first = first;
            this.second = second;
        }
    }
    public static class PairComp implements Comparator<Pair>
    {
        public int compare(Pair a, Pair b)
        {
            if(a.first!=b.first) return a.first-b.first;
            else return a.second-b.second;
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