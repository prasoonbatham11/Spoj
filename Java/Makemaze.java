import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Makemaze
{
    static int n, m, si, sj, di, dj;
    static char a[][];
    static boolean vis[][];
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
            String temp[] = br.readLine().split(" ");
            n = Integer.parseInt(temp[0]);
            m = Integer.parseInt(temp[1]);
            String str[] = new String[n];
            int i,j,count = 0;
            for(i=0;i<n;i++) str[i] = br.readLine();
            a = new char[n][m];
            vis = new boolean[n][m];
            for(i=0;i<n;i++) for(j=0;j<m;j++) {
                a[i][j] = str[i].charAt(j);
                vis[i][j] = false;
                if(i==0 || i==n-1 || j==0 || j==m-1)
                {
                    if(a[i][j]=='.')
                    {
                        count++;
                        if(count == 1) {si = i; sj = j;}
                        if(count == 2) {di = i; dj = j;}
                    }
                }
            }

            if(count!=2) out.write("invalid\n");
            else
            {
                if(dfs(si, sj)) out.write("valid\n");
                else out.write("invalid\n");
            }
        }

        out.flush();
    }
    public static boolean valid(int i, int j)
    {
        return i>=0 && j>=0 && i<n && j<m;
    }
    public static boolean dfs(int i, int j)
    {
        vis[i][j] = true;
        //System.out.println(i+" "+j);
        if(i==di && j==dj) return true;
        if(valid(i-1, j) && a[i-1][j]=='.' && !vis[i-1][j] && dfs(i-1, j)) return true;
        if(valid(i+1, j) && a[i+1][j]=='.' && !vis[i+1][j] && dfs(i+1, j)) return true;
        if(valid(i, j-1) && a[i][j-1]=='.' && !vis[i][j-1] && dfs(i, j-1)) return true;
        if(valid(i, j+1) && a[i][j+1]=='.' && !vis[i][j+1] && dfs(i, j+1)) return true;
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