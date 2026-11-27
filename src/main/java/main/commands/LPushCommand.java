package main.commands;

import main.RedisStore;

import java.util.Arrays;
import java.util.List;

public class LPushCommand implements IRedisCommands{
    private final RedisStore store;

    public LPushCommand(RedisStore store){
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

        int newSize = store.listPushMethod(key, values,"RPUSH");

        return ":" + newSize + "\r\n";
    }
}
