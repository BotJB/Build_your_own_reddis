package main;

public class CommandParser {
    public String processCommand(String[] commands){

        for(String command:commands) {
            switch (command) {
                case "PING":
                    return "+PONG\r\n";
                default:
                    return "Error unknown command ";
            }
        }
        return "Error unknown command ";
    }
}
