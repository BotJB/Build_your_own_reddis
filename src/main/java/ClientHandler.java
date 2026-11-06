import java.net.Socket;

public class ClientHandler {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    public void handle(){
        try(clientSocket){
            System.out.println("CLient is connected");
        }
        catch(Exception e ){
            System.out.println("The error is "+ e.getMessage());
        }
    }
}
