package main.commands;

public class PingCommand implements IRedisCommands{
    @Override
    public String execute(String[] commandsAndArguments) {
        if(commandsAndArguments.length==0){
            return "-Err not a valid command";
        }
        return "+PONG\r\n";
    }
}
