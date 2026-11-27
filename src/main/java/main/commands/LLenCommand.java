package main.commands;

import main.RedisStore;

public class LLenCommand implements IRedisCommands{
    private final RedisStore store;
   public LLenCommand(RedisStore store){
       this.store=store;
   }
    @Override
    public String execute(String[] commandsAndArguments) {
        if(commandsAndArguments.length!=2) return "-ERR wrong number of arguments\r\n";

        return  ":"
                +String.valueOf(store.listLength(commandsAndArguments[1]))
                +"\r\n";
    }
}
