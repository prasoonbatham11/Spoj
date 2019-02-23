import java.util.*;
import java.io.*;
import java.math.*;
import java.util.regex.*;
class Onezero
{
    static int a[][] = new int[1000000][2];
    public static void main(String args[])throws Exception
    {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new
                FileOutputStream(java.io.FileDescriptor.out), "ASCII"), 512);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //the code goes here
        int t = Integer.parseInt(br.readLine());
        while(t-->0)
        {
            int n = Integer.parseInt(br.readLine());
            for(int i = 0;i<n;i++)
            {
                a[i][0] = (i*10)%n;
                a[i][1] = (a[i][0]+1)%n;
            }
            out.write(bfs(n)+"\n");
        }
        out.flush();

    }
    public static String bfs(int n)
    {
        if(n==1) return "1";
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<String> qu = new LinkedList<>();
        boolean vis[] = new boolean[n];
        Arrays.fill(vis, false);
        queue.add(1); qu.add("1");
        int i;String s="";
        while(queue.size()>0)
        {
            i = queue.poll();
            s = qu.poll();
            if(i==0) return s;
            if(vis[i]) continue;
            vis[i] = true;
            if(a[i][0]!=i) {queue.add(a[i][0]); qu.add(s+"0");}
            if(a[i][1]!=i) {queue.add(a[i][1]); qu.add(s+"1");}
        }
        return s;
    }

}