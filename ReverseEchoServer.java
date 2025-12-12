import java.net.*;
import java.io.*;

// Class For Server
public class ReverseEchoServer {

    public static void main(String []ar) throws Exception{
        ServerSocket ss=new ServerSocket(2000);
        Socket stk=ss.accept();

        BufferedReader br=new BufferedReader(new InputStreamReader(stk.getInputStream()));

        PrintStream ps=new PrintStream(stk.getOutputStream());

        String msg;
        StringBuffer b;
        do{
            msg=br.readLine();
            b=new StringBuffer(msg);
            b.reverse();
            msg=b.toString();
            ps.println(msg);

        }while(! msg.equals("dne"));
        ss.close();

    }
}

class Client{
    public static void main(String[] args) throws Exception{
        Socket stk=new Socket("localhost",2000);

        BufferedReader keyb=new BufferedReader(new InputStreamReader(System.in));

        BufferedReader br=new BufferedReader(new InputStreamReader(stk.getInputStream()));

        PrintStream ps=new PrintStream(stk.getOutputStream());
        String msg;
        do{
            msg=keyb.readLine();
            ps.println(msg);
            msg=br.readLine();
            System.out.println("From Server : "+msg);
        }while(! msg.equals("dne"));

        stk.close();
    }
}
