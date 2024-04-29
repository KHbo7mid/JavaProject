package Serveur;





import Enseignant.Enseignant;
import Enseignant.EnseignantDAO;
import Etudiant.Etudiant;
import Etudiant.EtudiantDAO;
import IHMEnseignant.EnseignantGUI;
import IHMEtudiant.HomePage;
import db_config.Config;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;

public class Authentification extends Thread {
    PrintWriter out = null;
    BufferedReader in = null;
    private Socket client;

    public Authentification(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String email = in.readLine();
                String password = in.readLine();
                String role = in.readLine();
                if(role.equals("etudiant"))
                {
                    boolean isAuthenticated = authenticateEtudiant(email, password);
                    if (isAuthenticated)
                    {
                        openInterfaceEtudiant(email,password);
                    }
                    else {
                        out.println("FAILURE");
                        out.flush();

                    }
                }
                if(role.equals("enseignant"))
                {
                    boolean isAuthenticated = authenticateEnseignant(email, password);
                    if (isAuthenticated)
                    {
                        openInterfaceEnseignant(email,password);
                    }
                    else {
                        out.println("FAILURE");
                        out.flush();
                    }
                }



            }
        } catch (SocketException e) {

            System.err.println("Client connection reset: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'authentification", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }


    private boolean authenticateEtudiant(String email, String password) {

            EtudiantDAO dataEtudiant = new EtudiantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
            Etudiant etudiantRech = dataEtudiant.Recherche(password.toCharArray(),email);
          return  etudiantRech!= null;

    }
    private boolean authenticateEnseignant(String email, String password) {

        EnseignantDAO dataenseignant = new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        Enseignant enseignant = dataenseignant.RechercheAuthentification(email,password);
        return  enseignant!= null;

    }

    private void openInterfaceEtudiant(String email,String password)
    {

            EtudiantDAO dataEtudiant = new EtudiantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
            Etudiant etudiantRech = dataEtudiant.Recherche(password.toCharArray(),email);
            if (etudiantRech!=null)
            {
                HomePage homePage=new HomePage(etudiantRech.getId(),etudiantRech.getNom(),etudiantRech.getPrenom());
               out.println("SUCCESS");
               out.flush();
            }
        out.println("FAILURE");
        out.flush();
        }
    private void openInterfaceEnseignant(String email,String password)
    {

        EnseignantDAO dataenseignant = new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        Enseignant enseignant = dataenseignant.RechercheAuthentification(email,password);
        if (enseignant!=null)
        {
            try {
                EnseignantGUI enseignantGUI=new EnseignantGUI(enseignant.getNom(),enseignant.getPrenom(),enseignant.getCode());
                out.println("SUCCESS");
                out.flush();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        out.println("FAILURE");
        out.flush();
    }


    }


