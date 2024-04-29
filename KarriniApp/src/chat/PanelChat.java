package chat;


import Enseignant.EnseignantDAO;
import Etudiant.EtudiantDAO;
import IHMEtudiant.DemandeDAO;
import chat.ChatInterface;
import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PanelChat extends JPanel {
    JSplitPane jsp;
    JList jl;
    static HashMap<Integer,String> map;
    static DefaultListModel<String> listContact;
    JTabbedPane jtp;
    int idUser;
    String User;
    EnseignantDAO enseignantDAO;
    EtudiantDAO etudiantDAO;


    ArrayList<String>contactOuvert;



    public PanelChat(int idUser,String User) {

        System.out.println("User is running...");
        String url = "rmi://127.0.0.1:9001/chat";
        try {
            ChatRemote chat = (ChatRemote) Naming.lookup(url);


        this.idUser = idUser;
        this.User=User;

        jsp = new JSplitPane();
        jsp.setBorder(BorderFactory.createTitledBorder("Contacts"));
        jl = new JList();
       listContact=new DefaultListModel<>();
        map=new HashMap<>();
      map.put(0,"Admin");
     if(idUser!=0)
     {
         listContact.addElement("Admin");
     }

      jl.setModel(listContact);
    contactOuvert=new ArrayList<>();


        jl.setPreferredSize(new Dimension(150, 550));
        jtp = new JTabbedPane();
        jtp.setPreferredSize(new Dimension(600, 100));
        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);
        add(jsp);
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2)
                {
                    if(!contactOuvert.contains(String.valueOf(jl.getSelectedIndex())))
                    {
                        int id=getKey((String) jl.getSelectedValue());



                        ChatInterface chatInterface=new ChatInterface(chat,(String)jl.getSelectedValue(),id,idUser,User);
                        jtp.addTab((String) jl.getSelectedValue(),chatInterface);
                        contactOuvert.add(String.valueOf(jl.getSelectedIndex()));
                    }

                }
                if(e.getButton()==MouseEvent.BUTTON3 )
                {
                    JPopupMenu popup=new JPopupMenu();
                    JMenuItem fermer=new JMenuItem("fermer");
                    popup.add(fermer);
                    popup.show(jl,e.getX(),e.getY());
                    fermer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jtp.remove(jtp.getSelectedIndex());
                            contactOuvert.remove(String.valueOf(jl.getSelectedIndex()));
                        }
                    });

                }

            }
        });
        } catch (Exception e) {
            System.out.println("Client is not connected: " + e);
        }
    }

    public static void addContact(int id,String person) {
       map.put(id,person);
        listContact.addElement(person);
    }
    public int getKey(String value)
    {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return  -1;
    }
    public static void ContactEnseignant(EnseignantDAO enseignantDAO) {
        try {
            String req = "select code,nom,prenom from enseignant;";
            ResultSet rs = enseignantDAO.selection(req);

            if (rs != null) {
                while (rs.next()) {
                    int code = rs.getInt("code");
                    String nomEnseignant = rs.getString("nom") + " " + rs.getString("prenom");
                    PanelChat.addContact(code, nomEnseignant);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }
    public static void ContactEtudiant(EtudiantDAO etudiantDAO) {
        try {
            String req = "select id,nom,prenom from etudiant;";
            ResultSet rs = etudiantDAO.selection(req);

            if (rs != null) {
                while (rs.next()) {
                    int code = rs.getInt("id");
                    String nomEtudiant = rs.getString("nom") + " " + rs.getString("prenom");
                    PanelChat.addContact(code, nomEtudiant);
                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    }


