import main.CommandParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler {
    private final Socket clientSocket;
    private final CommandParser parser;

    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        parser=new CommandParser();
    }

    public void handle() {
        System.out.println("IT came here");
        try(clientSocket; BufferedReader reader=new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream(),
                        StandardCharsets.UTF_8
                        )

        )){
            //to write the response back to the client
            PrintWriter writer=new PrintWriter(clientSocket.getOutputStream(),true);
           String command;
           while((command=reader.readLine())!=null){
               System.out.println("Recieved:"+ command);
               String reponse=parser.processCommand(command);
               writer.println(reponse);


           }
        }
        catch(Exception e ){
            System.out.println("The error is "+ e.getMessage());
        }
    }
}
