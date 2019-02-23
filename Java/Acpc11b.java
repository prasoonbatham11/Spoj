import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Acpc11b
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
        while(t-->0)
        {
            int n = r.nextInt();
            long a[] = new long[n];

            int i;
            for(i=0;i<n;i++) a[i] = r.nextLong();
            int m = r.nextInt();long b[] = new long[m];
            for(i=0;i<m;i++) b[i] = r.nextLong();
            mergeSort(b,0,m-1);mergeSort(a,0,n-1);
            //System.out.println(Arrays.toString(a));
            //System.out.println(Arrays.toString(b));
            long count = 1000000000;
            int x,y;
            for(i=0;i<n;i++)
            {
                if(search(b, a[i])!=-1) {count = 0; break;}
                x = searchlower(b, a[i]); y = searchupper(b,a[i]);
                //System.out.println(a[i]+" "+x+" "+y);
                if(x!=-1 && a[i]-b[x]<count) count = a[i]-b[x];
                if(y!=-1 && b[y]-a[i]<count) count = b[y]-a[i];
            }
            out.write(count+"\n");

        }

        out.flush();
    }
    public static void mergeSort(long a[],int left,int right)
    {
        int mid=(left+right)/2;
        if(left<right)
        {
            mergeSort(a,left,mid);
            mergeSort(a,mid+1,right);
            merge(a,left,mid,right);
        }
    }
    public static void merge(long a[],int left,int mid,int right)
    {
        long t[]=new long[right-left+1];
        int pos=0,lpos=left,rpos=mid+1;
        while(lpos<=mid&&rpos<=right)
        {
            if(a[lpos]<a[rpos])
                t[pos++]=a[lpos++];
            else
                t[pos++]=a[rpos++];
        }
        while(lpos<=mid) t[pos++]=a[lpos++];
        while(rpos<=right) t[pos++]=a[rpos++];
        while(--pos>=0) a[pos+left]=t[pos];
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
    public static int searchlower(long a[], long k)
    {
        if(k<a[0]) return -1;
        if(k>a[a.length-1]) return a.length-1;
        int lo = 0, hi = a.length-1, mid;
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