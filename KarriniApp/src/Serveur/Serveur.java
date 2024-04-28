package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class Serveur {

    public static void main(String[] args)
    {
        System.out.println("Lancement Serveur ........");







        try {
            ServerSocket serveur=new ServerSocket(9000);
            System.out.println("Serveur en ecoute");
            while (true)
            {
                Socket s=serveur.accept();

                System.out.println("un Client est accepté");
new Authentification(s).start();

            }
        } catch (IOException e) {
            System.out.println("Server is not connected: " + e);
        }

    }

}
