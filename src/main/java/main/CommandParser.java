package main;

public class CommandParser {
    public String processCommand(String command){
        String[] commandsSeperated=command.trim().split("\\s+");
        //conver the commands in to high case
        String commandToExecute=commandsSeperated[0].toUpperCase();

        switch(commandToExecute){
            case "PING": return "+PONG\\r\\n";
            default:return "Error unknown command ";
        }
    }
}
