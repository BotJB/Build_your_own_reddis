package main.commands;

import main.RedisStore;

public class SetCommand implements IRedisCommands {
    private final RedisStore store;

    public SetCommand(RedisStore store){
        this.store=store;
    }
    @Override
    public String execute(String[] commandsAndArguments) {
        if(commandsAndArguments.length!=3&&commandsAndArguments.length!=5){
            return "-Err not suitbale number of args";
        }
        long timeToExpireCalcualted=-1;
        if(commandsAndArguments.length>2){
            timeToExpireCalcualted=Long.parseLong(commandsAndArguments[4]);
        switch(commandsAndArguments[3]){
            case "EX":timeToExpireCalcualted*=1000;
            break;
            case "PX":
                break;
        }}
        store.setValue(commandsAndArguments[1],commandsAndArguments[2],timeToExpireCalcualted);
        return "+OK\r\n";
    }
}
