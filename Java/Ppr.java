import java.io.*;
import java.util.*;
class ppr {
    public static void main(String args[]) throws Exception {
	String names[] = new String[]{"Asad", "Foyj", "Juwel", "Mijan", "Tanmay"};
	BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
	long power[] = new long[32];
	power[0] = 1;
	for(int i = 1;i<32;i++) power[i] = power[i-1]*2;
	while(true) {
	    String x = br.readLine();
	    if(x==null) break;
	    long n = Long.parseLong(x)-1;
	    int i;
	    for(i=1;;i++) {
		long sum = 5*(power[i]-1);
		//System.out.println(i+" "+sum);
		if(sum>n) break;
	    }
	    long ss = 5*(power[i-1]-1);
	    n = n-ss;
	    //System.out.println("here "+n);
	    long j = power[i-1];
	    n = n/j;
	    //System.out.println("hello "+n);
	    System.out.println(names[(int)n]);
	}
    }
}
