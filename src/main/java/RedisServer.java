import java.net.ServerSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class RedisServer {

    private final int portNumber;

    public RedisServer(int portNumber){
        this.portNumber=portNumber;
    }
    //The method to start the Redis

    public void start(){
        try(ServerSocket serverSocket=new ServerSocket(portNumber)){
            serverSocket.setReuseAddress(true);
            Socket socket=serverSocket.accept();
            ClientHandler handler=new ClientHandler(socket);
            handler.handle();


        }
        catch(Exception e ){

        }
    }
}
