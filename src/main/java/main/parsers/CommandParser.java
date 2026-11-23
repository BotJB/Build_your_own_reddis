package main.parsers;

import main.CommandResgistry;
import main.RedisStore;
import main.commands.IRedisCommands;

public class CommandParser {

    private CommandResgistry registry;
    public String processCommand(String[] commandsAndArguments,CommandResgistry registry){

        IRedisCommands command=registry.commands.get(commandsAndArguments[0].toUpperCase());
        return command.execute(commandsAndArguments);
    }
}
