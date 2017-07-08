import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Chmaze
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
        int t = 1;
        while(true)
        {
            String tex = br.readLine();
            if(tex.equals("")) continue;
            String temp[] = tex.split(" ");
            int row = Integer.parseInt(temp[0]);
            int col = Integer.parseInt(temp[1]);
            int maze = Integer.parseInt(temp[2]);
            if(row==0) break;

            int a[][][] = new int[maze][row][col];

            String str;

            int i,j,k;
            for(i=0;i<maze;i++)
            {
                for(j=0;j<row;j++)
                {
                    str = br.readLine();
                    if(str.equals(""))
                    {
                        j--;continue;
                    }
                    for(k=0;k<col;k++)
                        a[i][j][k] = str.charAt(k)-'0';
                }
            }

            int ans = bfs(row, col, maze, a);

            if(ans==-1)
            {
                out.write("Case "+t+": Luke and Leia cannot escape.\n");
            }
            else out.write("Case "+t+": Luke and Leia can escape in "+ans+" steps.\n");
            t++;

        }

        out.flush();
    }
    public static int bfs(int row, int col, int maze, int a[][][])
    {
        boolean vis[][][] = new boolean[row][col][maze];
        int dist[][][] = new int[row][col][maze];
        int i,j,k;
        for(i=0;i<row;i++) for(j=0;j<col;j++) for(k=0;k<maze;k++) vis[i][j][k] = false;

        LinkedList<Integer> q = new LinkedList<>();
        q.add(0);q.add(0);q.add(0);

        int tx[] = {0, -1, 0, 1,0};
        int ty[] = {-1, 0, 1, 0,0};

        while(!q.isEmpty())
        {
            int x = q.poll();
            int y = q.poll();
            int m = q.poll();
            if(vis[x][y][m]) continue;
            vis[x][y][m] = true;

            //System.out.println("MAZE FOR YOU "+x+" "+y+" "+m);

            if(x==row-1 && y==col-1) return dist[x][y][m];

            for(int p = 0; p<5;p++)
            {
                //System.out.println(valid(x+tx[p], y+ty[p], row, col));
                if(valid(x+tx[p], y+ty[p], row, col) ) {
                    //System.out.println(valid(x+tx[p], y+ty[p], row, col)+" jdajkl");
                    for (int h = 1; h <=1; h++) {
                        //System.out.println((x + tx[p]) + " " + (y + ty[p]) + " " + ((m + h) % maze) + " " +
                        //      dist[x + tx[p]][y + ty[p]][(m + h) % maze]);
                        if (!vis[x + tx[p]][y + ty[p]][(m + h) % maze] && a[(m + h) % maze][x + tx[p]][y + ty[p]] == 0) {
                            q.add(x + tx[p]);
                            q.add(y + ty[p]);
                            q.add((m + h) % maze);
                            dist[x + tx[p]][y + ty[p]][(m + h) % maze] = dist[x][y][m] + h;
                            //System.out.println((x + tx[p]) + " " + (y + ty[p]) + " " + ((m + h) % maze) + " " +
                            //      dist[x + tx[p]][y + ty[p]][(m + h) % maze]);
                        }
                    }
                }
            }
        }
        return -1;

    }
    public static boolean valid(int i, int j, int r, int c)
    {
        return i>=0 && i<r && j>=0 && j<c;
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