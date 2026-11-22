package main.handlers;

import main.CommandResgistry;
import main.parsers.CommandParser;
import main.parsers.RespParser;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ClientHandler {
    private final Socket clientSocket;
    private final CommandParser parser;
    private final RespParser  respParser;
    private final CommandResgistry registry;
    public ClientHandler(Socket clientSocket,CommandResgistry registry){
        this.clientSocket=clientSocket;
        parser=new CommandParser();
        respParser=new RespParser();
        this.registry=registry;
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
            //loop to keep it listing
            while(true) {
                String[] commands = respParser.parseRespCommand(reader);
                if (commands == null) {
                    return;
                }
                System.out.println(
                        "Parsed: " + Arrays.toString(commands)
                );
                String reponse = parser.processCommand(commands,registry);
                writer.print(reponse);
                writer.flush();
            }


        }
        catch(Exception e ){
            System.out.println("The error is "+ e.getMessage());
        }
    }
}
