import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Acmaker
{
    static int n,k;
    static String insig[],temp[],abbr,word[] = new String[1000];
    static int dp[][][] = new int[100][100][100];
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        Input in = new Input();
        Reader r = new Reader();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        int i,j,l;
        while(true)
        {
            n = Integer.parseInt(br.readLine());
            if(n==0) break;
            insig = new String[n];
            for(i=0;i<n;i++) insig[i] = br.readLine();
            Arrays.sort(insig);
            //System.out.println(Arrays.toString(insig));
            while(true)
            {
                temp = br.readLine().split(" ");
                if(temp[0].equals("LAST") && temp[1].equals("CASE")) break;

                abbr = temp[0];
                k = 0;
                for(i=1;i<temp.length;i++)
                {
                    if(search(insig, temp[i])==-1) word[k++] = temp[i];
                }
                for(i=0;i<100;i++) for(j=0;j<100;j++) for(l=0;l<100;l++) dp[i][j][l] = -1;
                int ans = solve(0,0,0);
                if(ans==0) out.write(abbr+" is not a valid abbreviation\n");
                else out.write(abbr+" can be formed in "+ans+" ways\n");
                //out.write(solve(0,0,0)+"\n");
                //System.out.println(Arrays.toString(word));
                //System.out.println(abbr+" "+k);
            }
        }

        out.flush();
    }
    public static int search(String a[], String k)
    {
        int lo = 0, hi = a.length-1, mid;
        while(lo<=hi)
        {
            mid = lo+(hi-lo)/2;
            if(a[mid].compareTo(k)<0) lo = mid+1;
            else if(a[mid].compareTo(k)>0) hi = mid-1;
            else return mid;
        }
        return -1;
    }
    public static int solve(int abb, int wrd, int from)
    {
        //System.out.println(abb+" "+wrd+" "+from);
        //System.out.println(abb+" "+wrd+" "+from+" "+dp[abb][wrd][from]);
        if(abb == abbr.length()) if(wrd>=k) return 1; else return 0;
        if(wrd==k) return 0;
        if(dp[abb][wrd][from]!=-1) {return dp[abb][wrd][from];}
        dp[abb][wrd][from] = 0;
        int l = word[wrd].length();
        for(int i=from;i<l;i++)
        {
            if((word[wrd].charAt(i)-'a') == (abbr.charAt(abb)-'A'))
            {
                dp[abb][wrd][from] += solve(abb+1, wrd, i+1) + solve(abb+1, wrd+1, 0);
            }
        }
        //System.out.println(dp[abb][wrd][from]);
        return dp[abb][wrd][from];
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
