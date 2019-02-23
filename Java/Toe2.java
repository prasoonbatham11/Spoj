import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Toe2
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
        while(true)
        {
            String str = br.readLine();
            if(str.equals("end")) break;
            int k = 0,i,j;
            char game[][] = new char[3][3];
            int x=0, o=0, dot=0;
            for(i=0;i<3;i++) for(j=0;j<3;j++) {
                game[i][j] = str.charAt(k++);
                if(game[i][j] == 'X') x++;
                else if(game[i][j] == 'O') o++;
                else dot++;
            }
            if(x-o!=0 && x-o!=1) {
                out.write("invalid\n");
                continue;
            }
            int xw = checkGrid(game, 'X');
            int ow = checkGrid(game, 'O');
            if(dot==0 && x-o != 1) {out.write("invalid\n");}
            else if(xw>=1 && ow>=1) out.write("invalid\n");
            else if(xw>=1 && x-o!=1) out.write("invalid\n");
            else if(ow>=1 && x-o!=0) out.write("invalid\n");
            else if(ow==0 && xw==0 && x+o!=9) out.write("invalid\n");
            else out.write("valid\n");
        }

        out.flush();
    }
    public static int checkGrid(char[][] game, char k)
    {
        int c= 0;
        int i, j, count = 0;
        for(i=0;i<3;i++)
        {
            count = 0;
            for(j=0;j<3;j++)
            {
                if(game[i][j] == k) count++;
            }
            if(count==3) c++;
        }
        for(j=0;j<3;j++)
        {
            count = 0;
            for(i=0;i<3;i++)
            {
                if(game[i][j] == k) count++;
            }
            if(count==3) c++;
        }
        count = 0;
        for(i=0;i<3;i++)
        {
            if(game[i][i] == k) count++;
        }
        if(count == 3) c++;
        count = 0;
        for(i=0;i<3;i++)
        {
            if(game[i][2-i] == k) count++;
        }
        if(count == 3) c++;
        return c;

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