package main.commands;

import main.RedisStore;

import java.util.Arrays;
import java.util.List;

public class RPushCommand implements IRedisCommands{
    private final RedisStore store;

    public RPushCommand(RedisStore store){
        this.store=store;
    }
    @Override
    public String execute(String[] commandsAndArguments) {

        if (commandsAndArguments.length < 3) {
            return "-ERR wrong number of arguments\r\n";
        }

        String key = commandsAndArguments[1];

        List<String> values =
                Arrays.stream(
                        Arrays.copyOfRange(
                                commandsAndArguments,
                                2,
                                commandsAndArguments.length
                        )
                ).toList();

        int newSize = store.rpush(key, values);

        return ":" + newSize + "\r\n";
    }
}
