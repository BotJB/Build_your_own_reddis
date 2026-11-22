package main.commands;

public class EchoCommand implements IRedisCommands{
    @Override
    public String execute(String[] commandsAndArguments) {
        if(commandsAndArguments.length!=2){
           return "-Err no argument passed";
        }
        String valueToReturn=commandsAndArguments[1].trim();
        return "$"
               +"\r\n"
                + valueToReturn
                +"\r\n";

    }
}
