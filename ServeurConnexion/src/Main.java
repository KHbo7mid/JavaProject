

import chat.ChatImplementation;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Server is running...");
        String url = "rmi://localhost:9001/chat";
        try {
            ChatImplementation chat = new ChatImplementation();
            LocateRegistry.createRegistry(9001);
            Naming.rebind(url, chat);
            System.out.println("Server RUNNING...");

        } catch (Exception e) {
            System.out.println("Server is not connected: " + e);
        }
    }
}