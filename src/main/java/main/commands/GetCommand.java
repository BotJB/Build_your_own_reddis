package main.commands;

import main.RedisStore;

public class GetCommand implements IRedisCommands{
    private final RedisStore store;
    public GetCommand(RedisStore store){
        this.store=store;
    }
    @Override
    public String execute(String[] commandsAndArguments) {
        if(commandsAndArguments.length!=2){
            return "-Err not suitbale number of args";
        }

        return "$"
                +store.getValue(commandsAndArguments[1]).length()
                +"\r\n"
                +store.getValue(commandsAndArguments[1])
                +"\r\n";
    }
}
