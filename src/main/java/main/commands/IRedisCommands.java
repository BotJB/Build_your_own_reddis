package main.commands;

public interface IRedisCommands {
    public String execute(String[] commandsAndArguments);
}
