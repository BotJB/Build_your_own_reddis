import java.net.ServerSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisServer {

    private final int portNumber;
    private final int maxThreads;
    public RedisServer(int portNumber,int maxThreads){
        this.portNumber=portNumber;
        this.maxThreads=maxThreads;
    }
    //The method to start the Redis

    public void start(){
        //Instantiating multiples thread to handle concurrency
        final ExecutorService executorService= Executors.newFixedThreadPool(maxThreads);
        try(ServerSocket serverSocket=new ServerSocket(portNumber)){
            System.out.println("We are in the redis Server class");
            //clean shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(()->{
                executorService.shutdown();
                try{serverSocket.close();}catch(IOException ignored){}
            }));
            serverSocket.setReuseAddress(true);
            while(true) {
                Socket socket = serverSocket.accept();
                executorService.execute(()->{
                    ClientHandler handler = new ClientHandler(socket);
                    handler.handle();
                });
            }

        }
        catch(Exception e ){

        }
    }
}
