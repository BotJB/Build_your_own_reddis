package main.commands;

import main.RedisStore;

public interface IRedisCommands {
    public String execute(String[] commandsAndArguments);
}
