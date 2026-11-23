package main;

import main.CommandResgistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  private static final int PORT=6379;
  private static final int MAX_THREADS=50;
  public static void main(String[] args){
    System.out.println("starting the reddis server");
    //Do basic setup
    RedisStore store=new RedisStore();
    CommandResgistry registry=new CommandResgistry(store);
    RedisServer server=new RedisServer(PORT,MAX_THREADS,registry);
    server.start();
  }
}
