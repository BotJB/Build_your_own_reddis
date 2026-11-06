import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args){
    int portNumber=6379;
    RedisServer server=new RedisServer(portNumber);
    server.start();
  }
}
