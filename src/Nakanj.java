import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Nakanj
{
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        String temp[];
        while(t-->0)
        {
            temp = br.readLine().split(" ");
            int i = temp[0].charAt(0)-'a'+1;
            int j = temp[0].charAt(1)-'0';
            int g = temp[1].charAt(0)-'a'+1;
            int h = temp[1].charAt(1)-'0';
            out.write(solve(i,j,g,h)+"\n");
        }
        out.flush();
    }
    public static long solve(int p, int q, int r, int s)
    {
        //System.out.println(s+" "+t);

        boolean vis[][] = new boolean[9][9];
        long arr[][] = new long[9][9];
        int i,j,x,y;for(i=0;i<8;i++) for(j=0;j<8;j++) vis[i][j] = false;
        LinkedList<Integer> queue1 = new LinkedList<>(), queue2 = new LinkedList<>();
        queue1.add(p); queue2.add(q);
        vis[p][q] = true;
        while(queue1.size()!=0)
        {

            i = queue1.poll(); j = queue2.poll();
            //System.out.println(i+" "+j+" "+arr[i][j]);
            for(x = -2; x<=2; x++)
            {
                for(y=-2;y<=2;y++)
                {
                    if(Math.abs(x)+Math.abs(y)==3)
                    {
                        if(valid(i+x, j+y) && !vis[i+x][j+y])
                        {
                            vis[i+x][j+y] = true;
                            arr[i+x][j+y] = arr[i][j]+1;
                            queue1.add(i+x); queue2.add(j+y);
                        }
                    }
                }
            }
            if(vis[r][s]) break;
        }
        return arr[r][s];
    }
    public static boolean valid(int i, int j)
    {
        return (i>0 && j>0 && i<=8 && j<=8);
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