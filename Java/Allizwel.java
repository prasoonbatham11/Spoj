import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Allizwel
{
    public static int n, row, col;
    public static boolean vis[][];
    public static char all[], a[][];
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
        String temp[];
        n = 10;
        all = "ALLIZZWELL".toCharArray();
        while(t-->0)
        {
            String g = br.readLine();
            if(g.equals("")) {t++;continue;}
            temp = g.split(" ");
            row = Integer.parseInt(temp[0]);
            col = Integer.parseInt(temp[1]);
            vis = new boolean[row][col];
            a = new char[row][col];
            //System.out.println(row+" "+col);

            String str[] = new String[row];
            int i,j;
            for(i=0;i<row;i++) str[i] = br.readLine();

            int xy[][] = new int[row*col][2];
            int k = 0;

            for(i=0;i<row;i++) for(j=0;j<col;j++) {
                vis[i][j] = false;a[i][j] = str[i].charAt(j);
                if(a[i][j] == 'A')
                {
                    xy[k][0] = i;
                    xy[k][1] = j;
                    k++;
                }
            }
            boolean flag = false;

            for(i=0;i<k;i++)
                if(dfs(xy[i][0], xy[i][1], 0)) flag = true;

            if(flag) out.write("YES\n");
            else out.write("NO\n");



            /*if(solve(0,0,0)) out.write("YES\n");
            else
            {
                for(i=0;i<row;i++) for(j=0;j<col;j++) vis[i][j] = false;
                if(solve(row-1, col-1, 0)) out.write("YES\n");
                else out.write("NO\n");
            }*/
        }


        out.flush();
    }
    public static boolean valid(int i, int j)
    {
        return i>=0 && j>=0 && i<row && j<col;
    }
    public static boolean dfs(int i, int j, int pos)
    {
        if(!valid(i,j)) return false;
        if(vis[i][j]) return false;
        vis[i][j] = true;
        if(a[i][j] == all[pos])
        {
            int x,y;
            if(pos<n-1) {
                for (x = -1; x <= 1; x++)
                    for (y = -1; y <= 1; y++) {
                        if (x == 0 && y == 0) continue;
                        else if (solve(i + x, j + y, pos + 1)) return true;
                        if(valid(i+x, j+y)) vis[i+x][j+y] = false;
                    }
            }
            else return true;
        }
        else return false;
        return false;
    }
    public static boolean solve(int i, int j, int pos)
    {
        if(!valid(i,j)) return false;
        if(vis[i][j]) return false;
        vis[i][j] = true;
        if(a[i][j]==all[pos])
        {
            if(pos==0)
            {
                int x,y;
                for(x=-1;x<=1;x++)
                    for(y=-1;y<=1;y++)
                        if(x==0 && y==0) continue;
                        else if(solve(i+x, j+y, pos+1)) return true;
                for(x=-1;x<=1;x++)
                    for(y=-1;y<=1;y++)
                        if(x==0 && y==0) continue;
                        else if(solve(i+x, j+y, pos)) return true;
            }
            else if(pos<n-1)
            {
                int x,y;
                for(x=-1;x<=1;x++)
                    for(y=-1;y<=1;y++)
                        if(x==0 && y==0) continue;
                        else if(solve(i+x, j+y, pos+1)) return true;
            }
            else return true;
        }
        else if(pos==0)
        {
            int x,y;
            for(x=-1;x<=1;x++)
                for(y=-1;y<=1;y++)
                    if(x==0 && y==0) continue;
                    else if(solve(i+x, j+y, pos)) return true;
        }
        vis[i][j] = false;
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
