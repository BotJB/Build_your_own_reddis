package main.commands;

import main.RedisStore;
import main.redis.IRedisValue;
import main.redis.StringValue;

public class GetCommand implements IRedisCommands{
    private final RedisStore store;
    public GetCommand(RedisStore store){
        this.store=store;
    }
    @Override
    public String execute(String[] commandsAndArguments) {

        if (commandsAndArguments.length != 2) {
            return "-ERR wrong number of arguments\r\n";
        }

        IRedisValue value = store.getValue(commandsAndArguments[1]);

        if (value == null) {
            return "$-1\r\n";
        }

        if(value instanceof StringValue v){
            return  "$"
                    +v.getSize()
                    + "\r\n"
                    + v.getValue()
                    + "\r\n";
        }

        throw new IllegalArgumentException();
    }
}
