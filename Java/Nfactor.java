import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Nfactor
{
    static int ff[][] = new int[1000001][11];
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        boolean seive[] = new boolean[1000001];Arrays.fill(seive,true);
        seive[0] = seive[1] = false;
        int p,j;
        for(p=2;p*p<seive.length;p++) if(seive[p]) for(j=p*p;j<seive.length;j+=p) seive[j] = false;

        int prime[] = new int[1000001];
        j = 0;
        for(p=2;p<seive.length;p++) if(seive[p]) prime[j++] = p;

        long sumfact[] = new long[1000001];
        sumfact[0] = 0;
        sumfact[1] = 0;
        int i,c;
        long sum;
        for(i=2;i<sumfact.length;i++)
        {
            j = i;sum = 1;
            c =0;
            for(p=0;prime[p]*prime[p]<=j;p++)
            {
                if(j%prime[p]==0) c++;
                while(j%prime[p]==0)
                {
                    j/=prime[p];
                }

            }
            if(j>1) c++;

            sumfact[i] = c;
            //if(i<=100) System.out.println(i+" "+c);
        }

        ff[0][(int)sumfact[0]] = 1;
        for(i=1;i<sumfact.length;i++)
        {
            for(j=0;j<=10;j++) ff[i][j] = ff[i-1][j];
            ff[i][(int)sumfact[i]]++;
            /*if(i<=100) {
            System.out.print(i+"\t\t");
            for(j=0;j<=10;j++) System.out.print(ff[i][j]+"\t");
            System.out.println();}*/
        }

        int t = r.nextInt();
        while(t-->0)
        {
            int a = r.nextInt(), b = r.nextInt(), cc = r.nextInt();
            out.write((ff[b][cc]-ff[a-1][cc])+"\n");
        }




        out.flush();
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