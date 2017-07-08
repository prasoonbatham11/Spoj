import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Abcpath
{
    static int h, w;
    static String str[];
    static char a[][];
    static int v[][];
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();

        //the code goes here
        int x = 1;
        while(true)
        {
            int i,j;
            h = r.nextInt(); w = r.nextInt();
            if(h==0 && w==0) break;
            str = new String[h];
            for(i=0;i<h;i++) str[i] = r.readLine();
            a = new char[h][w];
            v = new int[h][w];
            for(i=0;i<h;i++)
                for(j=0;j<w;j++)
                {a[i][j] = str[i].  charAt(j);v[i][j] = -1;}
            int m = 0;
            for(i=0;i<h;i++)
                for(j=0;j<w;j++)
                    if(a[i][j]=='A')
                        m = (int)Math.max(m, count(i,j));
            /*for(i=0;i<h;i++)
                {for(j=0;j<w;j++)
                    out.write(v[i][j]+" ");
                out.write("\n");}*/
            out.write("Case "+x+": "+m+"\n");
            x++;
        }

        out.flush();
    }

    public static int count(int i, int j)
    {
        int max = 1;
        if(v[i][j]!=-1) return v[i][j];
        for(int x=i-1; x<=i+1;x++)
            for(int y=j-1; y<=j+1;y++)
                if(x==i && y==j) continue;
                else if(valid(x,y) && a[x][y]-a[i][j]==1)
                {
                    if(v[x][y]!=-1) max = (int)Math.max(max, 1+v[x][y]);
                    else max = (int)Math.max(max, 1+count(x,y));
                }
        /*if(valid(i-1,j) && a[i-1][j]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i-1,j));
        if(valid(i,j-1) && a[i][j-1]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i,j-1));
        if(valid(i+1,j) && a[i+1][j]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i+1,j));
        if(valid(i,j+1) && a[i][j+1]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i,j+1));
        if(valid(i-1,j-1) && a[i-1][j-1]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i-1,j-1));
        if(valid(i-1,j+1) && a[i-1][j+1]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i-1,j+1));
        if(valid(i+1,j-1) && a[i+1][j-1]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i+1,j-1));
        if(valid(i+1,j+1) && a[i+1][j+1]-a[i][j] == 1) max = (int)Math.max(max, 1+count(i+1,j+1));*/
        return v[i][j]=max;
    }
    public static boolean valid(int i, int j)
    {
        if(i>=0 && j>=0 && i<h && j<w) return true;
        return false;
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