import java.net.*;
import java.io.*;
public class MultiThreadedReverseEchoServer extends Thread{
    Socket skt;
    MultiThreadedReverseEchoServer(Socket skt){
        this.skt=skt;
    }
    public void run(){
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(skt.getInputStream()));
            PrintStream ps=new PrintStream(skt.getOutputStream());
            String msg;
            StringBuffer b;
            do{
                msg=br.readLine();
               b=new StringBuffer(msg);
               b.reverse();
               msg=b.toString();
               ps.println(msg);
            }while(!msg.equals("dne"));
            skt.close();
        }
        catch(Exception e){}
    }
    public static void main(String[] args) throws Exception {
        ServerSocket ss=new ServerSocket(2000);
        Socket skt;
        int ct=1;
        
        do{
            skt=ss.accept();
            new MultiThreadedReverseEchoServer(skt).start();
            System.out.println("No Clients Connected : "+ct++);

        }while(true);
    }
}
class Clients{
    public static void main(String []ar) throws Exception{
        Socket skt=new Socket("localhost",2000);
        BufferedReader keybr=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br=new BufferedReader(new InputStreamReader(skt.getInputStream()));
        PrintStream ps =new PrintStream(skt.getOutputStream());
        String msg;
        do{
            msg=keybr.readLine();
            ps.println(msg);
            msg=br.readLine();
            System.out.println("From Server : "+msg);
        }while(! msg.equals("dne"));
        skt.close();
    }
}