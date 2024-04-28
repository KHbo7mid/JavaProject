package authentification;






import Enseignant.Enseignant;
import Enseignant.EnseignantDAO;
import Etudiant.Etudiant;
import Etudiant.EtudiantDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Connecter extends JFrame {
    JLabel ltitle,lmail,lmdp,lradioEtud,lradioEn;
    JTextField tfmail;
    JPasswordField passf;
    JPanel ptitle,pmail,pmdp,pform,pbtn,petudiant,penseignant;
    JButton btn;
    EnseignantDAO dataEnseignant;
    EtudiantDAO dataEtudiant;
    Enseignant enseignantRech;
     Etudiant etudiantRech;
     private Socket socket;
     private PrintWriter out;
     private BufferedReader in ;
     JRadioButton etudiant,enseingant;
    ButtonGroup buttonGroup;

    public Connecter(){
        try {
            socket=new Socket("127.0.0.1",9000);
            out=new PrintWriter(socket.getOutputStream(),true);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error connecting to the server", "Error", JOptionPane.ERROR_MESSAGE);
        }
        setTitle("Se Connecter ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x2979FF));
        setSize(600,700);

        setLocationRelativeTo(null);

        ptitle=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        ltitle=new JLabel("Se Connecter");

        ltitle.setFont(new Font("Inter",Font.BOLD,48));


        ltitle.setHorizontalAlignment(JLabel.CENTER);
        ptitle.add(ltitle);
        ptitle.setOpaque(false);
        lmail=new JLabel("E-mail:");
        lmail.setFont(new Font("Inter",Font.BOLD,20));
        lmail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lmail.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lmail.setForeground(Color.BLACK);
            }
        });
        tfmail=new JTextField(20);
        tfmail.setText("Saisir Votre Email:");
        tfmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfmail.getText().equals("Saisir Votre Email:")) {
                    tfmail.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
               if (tfmail.getText().equals(""))
               {
                   tfmail.setText("Saisir Votre Email:");
               }

            }
        });
        pmail=new JPanel(new FlowLayout(FlowLayout.CENTER,50,20));
        pmail.add(lmail);
        pmail.add(tfmail);
        pmail.setOpaque(false);
        lmdp=new JLabel("Mot de passe:");
        lmdp.setFont(new Font("Inter",Font.BOLD,15));
        lmdp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lmdp.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lmdp.setForeground(Color.BLACK);
            }
        });
        passf=new JPasswordField(20);
        passf.setText("Saisir Votre mot de passe:");
        passf.setEchoChar((char) 0);
        passf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Arrays.equals(passf.getPassword(), "Saisir Votre mot de passe:".toCharArray())) {
                    passf.setText("");
                    passf.setEchoChar('*'); // Or whatever your echo character is
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passf.getPassword().length == 0) {
                    passf.setText("Saisir Votre mot de passe:");
                    passf.setEchoChar((char) 0);
                }
            }
        });
        pmdp=new JPanel(new FlowLayout(FlowLayout.CENTER,5,20));
        pmdp.add(lmdp);
        pmdp.add(passf);
        pmdp.setOpaque(false);
        penseignant=new JPanel(new FlowLayout(FlowLayout.CENTER,5,20));
        lradioEn=new JLabel("Enseignant");
        lradioEn.setOpaque(false);

        enseingant=new JRadioButton();
        enseingant.setOpaque(false);
        penseignant.add(enseingant);
        penseignant.add(lradioEn);
        penseignant.setOpaque(false);
        petudiant=new JPanel(new FlowLayout(FlowLayout.CENTER,5,20));
        lradioEtud=new JLabel("Etudiant");
        lradioEtud.setOpaque(false);
        etudiant=new JRadioButton();
        etudiant.setOpaque(false);
        petudiant.add(etudiant);
        petudiant.add(lradioEtud);
        petudiant.setOpaque(false);
        buttonGroup=new ButtonGroup();
        buttonGroup.add(enseingant);
        buttonGroup.add(etudiant);
        pform=new JPanel(new FlowLayout(FlowLayout.CENTER,180,20));
        btn =new JButton("Valider");
        btn.setBackground(Color.white);
        btn.setForeground(new Color(0x2979FF));
        btn.setFont(new Font("Judson",Font.BOLD,20));
        pbtn=new JPanel(new FlowLayout(FlowLayout.CENTER,50,100));
        pbtn.add(btn);
        pbtn.setOpaque(false);
        add(ptitle,BorderLayout.NORTH);
        pform.add(pmail);
        pform.add(pmdp);
        pform.add(penseignant);
        pform.add(petudiant);
        pform.setOpaque(false);


        add(pform,BorderLayout.CENTER);
        add(pbtn,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                      String email = tfmail.getText().trim();
                                      String password = new String(passf.getPassword()).trim();
                                      String role = "";
                                      if (enseingant.isSelected()) {
                                          role = "enseignant";

                                      } else if (etudiant.isSelected()) {
                                          role = "etudiant";

                                      } else {
                                          JOptionPane.showMessageDialog(null, "Veuillez sélectionner un rôle", "Erreur", JOptionPane.ERROR_MESSAGE);
                                          return;
                                      }
                                      if (email.isEmpty() || password.isEmpty()) {
                                          JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs");
                                      } else if (!email.contains("@")) {
                                          JOptionPane.showMessageDialog(null, "Email invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
                                      } else {

                                              out.println(email);
                                              out.flush();
                                              out.println(password);
                                              out.flush();
                                              out.println(role);
                                              out.flush();




                                          try {
                                              String authentificationResult = in.readLine();
                                              if (authentificationResult.equals("SUCCESS")) {
                                                  JOptionPane.showMessageDialog(null, "Authentification réussie");

                                              } else {
                                                  JOptionPane.showMessageDialog(null, "Authentification échouée", "Erreur", JOptionPane.ERROR_MESSAGE);

                                              }
                                          } catch (IOException ex) {
                                              throw new RuntimeException(ex);
                                          }


                                      }

                                      }




                              });


        setVisible(true);
    }
}
