package main;

public class CommandParser {
    public String processCommand(String[] commands){


        switch(commands[0]){
            case "PING": return "+PONG\r\n";
            default:return "Error unknown command ";
        }
    }
}
