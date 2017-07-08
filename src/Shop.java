import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Shop
{
    public static class Pair
    {
        int x,y,weight;
        Pair(int i,int j, int w)
        {
            x = i;
            y = j;
            weight = w;
        }
    }
    public static class PairComparator implements Comparator<Pair>
    {
        public int compare(Pair f, Pair s)
        {
            return f.weight - s.weight;
        }
    }
    public static boolean valid(int i, int j, int n, int m)
    {
        return i>=0 && j>=0 && i<n && j<m;
    }
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        while(true)
        {
            String temp[] = br.readLine().split(" ");
            int m = Integer.parseInt(temp[0]);
            int n = Integer.parseInt(temp[1]);
            if(n==0 && m==0) break;
            String str[] = new String[n];

            char a[][] = new char[n][m];
            int i,j,ii=0,jj=0,di=0,dj=0;

            for(i=0;i<n;i++) str[i] = br.readLine();
            br.readLine();

            for(i=0;i<n;i++) for(j=0;j<m;j++) {
                a[i][j] = str[i].charAt(j);
                if(a[i][j] == 'S') {
                    ii = i; jj = j;
                }
                if(a[i][j] == 'D') {
                    di = i; dj = j;
                }
            }
            a[ii][jj] = '0';
            int ans = djikstra(ii, jj, di, dj, n, m, a);
            out.write(ans+"\n");




        }

        out.flush();
    }

    public static int djikstra(int si, int sj,int di, int dj, int n, int m, char a[][])
    {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(19, new PairComparator());
        pq.add(new Pair(si, sj, 0));
        //System.out.println("hh");
        int weight[][] = new int[n][m];
        int ii, jj;
        for(ii=0;ii<n;ii++) for(jj=0;jj<m;jj++) weight[ii][jj] = 1000000;
        weight[si][sj] = 0;
        while(pq.size()!=0)
        {
            Pair p = pq.remove();
            int i = p.x;
            int j = p.y;
            int wt = p.weight;
            //System.out.println(i+" "+j+" "+wt);
            if(valid(i-1, j, n, m) && a[i-1][j]!='X')
            {
                if(a[i-1][j]=='D' && weight[i-1][j]>weight[i][j])
                    weight[i-1][j] = weight[i][j];
                else if(a[i-1][j]!='D' && weight[i-1][j]>weight[i][j]+a[i-1][j]-'0')
                {
                    weight[i-1][j] = weight[i][j] + a[i-1][j] - '0';
                    pq.add(new Pair(i-1, j, weight[i-1][j]));
                }
            }
            if(valid(i+1, j, n, m) && a[i+1][j]!='X')
            {
                if(a[i+1][j]=='D' && weight[i+1][j]>weight[i][j])
                    weight[i+1][j] = weight[i][j];
                else if(a[i+1][j]!='D' && weight[i+1][j]>weight[i][j]+a[i+1][j]-'0')
                {
                    weight[i+1][j] = weight[i][j] + a[i+1][j] - '0';
                    pq.add(new Pair(i+1, j, weight[i+1][j]));
                }
            }
            if(valid(i, j-1, n, m) && a[i][j-1]!='X')
            {
                if(a[i][j-1]=='D' && weight[i][j-1]>weight[i][j])
                    weight[i][j-1] = weight[i][j];
                else if(a[i][j-1]!='D' && weight[i][j-1]>weight[i][j]+a[i][j-1]-'0')
                {
                    weight[i][j-1] = weight[i][j] + a[i][j-1] - '0';
                    pq.add(new Pair(i, j-1, weight[i][j-1]));
                }
            }
            if(valid(i, j+1, n, m) && a[i][j+1]!='X')
            {
                if(a[i][j+1]=='D' && weight[i][j+1]>weight[i][j])
                    weight[i][j+1] = weight[i][j];
                else if(a[i][j+1]!='D' && weight[i][j+1]>weight[i][j]+a[i][j+1]-'0')
                {
                    weight[i][j+1] = weight[i][j] + a[i][j+1] - '0';
                    pq.add(new Pair(i, j+1, weight[i][j+1]));
                }
            }
        }
        return weight[di][dj];
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