import java.util.*;
class Cubes
{
    public static void main(String args[])
    {
        int a=6,b,c,d;
        while(a<=100)
        {
            for(b=2;b<=a-3;b++)
                for(c=b+1;c<=a-2;c++)
                    for(d=c+1;d<=a-1;d++)
                        if(cube(a)==cube(b)+cube(c)+cube(d))
                            System.out.println("Cube = "+a+", Triple = ("+b+","+c+","+d+")");
            a++;
        }
    }
    public static int cube(int n) {return n*n*n;}
}