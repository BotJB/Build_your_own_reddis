package main.commands;

import main.RedisStore;

public class SetCommand implements IRedisCommands {
    private final RedisStore store;
    public SetCommand(RedisStore store){
        this.store=store;
    }
    @Override
    public String execute(String[] commandsAndArguments) {
        if(commandsAndArguments.length!=3){
            return "-Err not suitbale number of args";
        }
        store.setValue(commandsAndArguments[1],commandsAndArguments[2]);
        return "+OK\\r\\n";
    }
}
