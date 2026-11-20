import main.CommandParser;
import main.RespParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler {
    private final Socket clientSocket;
    private final CommandParser parser;
    private final RespParser  respParser;
    public ClientHandler(Socket clientSocket){
        this.clientSocket=clientSocket;
        parser=new CommandParser();
        respParser=new RespParser();
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
               String[] commands=respParser.parseRespCommand(reader);
               String reponse=parser.processCommand(commands);
               writer.print(reponse);
               writer.flush();



        }
        catch(Exception e ){
            System.out.println("The error is "+ e.getMessage());
        }
    }
}
