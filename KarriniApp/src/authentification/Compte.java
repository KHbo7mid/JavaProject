package authentification;





import Etudiant.Etudiant;
import Etudiant.EtudiantDAO;
import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Compte extends JFrame {
    JLabel ltitle,lnom,lprenom,lmail,lmdp;
    JTextField tfnom,tfprenom,tfmail;
    JPasswordField mdp;
    JPanel ptitle,pnom,pprenom,pmail,pmdp,pform,pbtn;
    JButton btn;
    EtudiantDAO etudiant;

    ArrayList<Etudiant>data=new ArrayList<Etudiant>();
    public Compte(){
        setTitle("Creer un Compte ");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x2979FF));

        setSize(600,700);
        setLocationRelativeTo(null);
        ptitle=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        ltitle=new JLabel("Creer un Compte");

        ltitle.setFont(new Font("Inter",Font.BOLD,48));


        ltitle.setHorizontalAlignment(JLabel.CENTER);

        ptitle.add(ltitle);


        ptitle.setOpaque(false);


        lnom=new JLabel("Nom:");
        lnom.setFont(new Font("Inter",Font.BOLD,20));
        lnom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lnom.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lnom.setForeground(Color.BLACK);
            }
        });
        tfnom=new JTextField(20);
        tfnom.setText("Saisir Votre Nom:");
        tfnom.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfnom.getText().equals("Saisir Votre Nom:")) {
                    tfnom.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfnom.getText().equals(""))
                {
                    tfnom.setText("Saisir Votre Nom:");
                }

            }
        });
        pnom=new JPanel(new FlowLayout(FlowLayout.CENTER,50,15));
        pnom.add(lnom);
        pnom.add(tfnom);
        pnom.setOpaque(false);
        lprenom=new JLabel("Prenom:");
        lprenom.setFont(new Font("Inter",Font.BOLD,20));
        lprenom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lprenom.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lprenom.setForeground(Color.BLACK);
            }
        });
        tfprenom=new JTextField(20);
        tfprenom.setText("Saisir Votre Prenom:");
        tfprenom.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfprenom.getText().equals("Saisir Votre Prenom:")) {
                    tfprenom.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfprenom.getText().equals(""))
                {
                    tfprenom.setText("Saisir Votre Prenom:");
                }

            }
        });
        pprenom=new JPanel(new FlowLayout(FlowLayout.CENTER,35,15));
        pprenom.add(lprenom);
        pprenom.add(tfprenom);
        pprenom.setOpaque(false);
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

        pmail=new JPanel(new FlowLayout(FlowLayout.CENTER,50,15));
        pmail.add(lmail);
        pmail.add(tfmail);
        pmail.setOpaque(false);
        lmdp=new JLabel("Mot de Passe:");
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
        mdp=new JPasswordField(20);
        mdp.setText("Saisir Votre mot de passe:");
        mdp.setEchoChar((char) 0);
        mdp.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Arrays.equals(mdp.getPassword(), "Saisir Votre mot de passe:".toCharArray())) {
                    mdp.setText("");
                    mdp.setEchoChar('*'); // Or whatever your echo character is
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (mdp.getPassword().length == 0) {
                    mdp.setText("Saisir Votre mot de passe:");
                    mdp.setEchoChar((char) 0);
                }
            }
        });
        pmdp=new JPanel(new FlowLayout(FlowLayout.CENTER,5,15));
        pmdp.add(lmdp);
        pmdp.add(mdp);
        pmdp.setOpaque(false);
        pform=new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
        btn =new JButton("Valider");
        btn.setBackground(Color.white);
        btn.setForeground(new Color(0x2979FF));
        btn.setFont(new Font("Judson",Font.BOLD,20));
        pbtn=new JPanel(new FlowLayout(FlowLayout.CENTER,50,100));
        pbtn.add(btn);
        pbtn.setOpaque(false);
        pform.add(pnom);
        pform.add(pprenom);
        pform.add(pmail);
        pform.add(pmdp);
        pform.setOpaque(false);

        add(ptitle,BorderLayout.NORTH);
        add(pform,BorderLayout.CENTER);
        add(pbtn,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(exist(mdp.getPassword()))
                {
                    JOptionPane.showMessageDialog(null,"le mot de passe existe deja ","Erreur",JOptionPane.ERROR_MESSAGE);

                } else if (tfnom.getText().isEmpty()||tfnom.getText().equals("Saisir Votre Nom:")|| tfprenom.getText().isEmpty()||tfprenom.getText().equals("Saisir Votre Prenom:") ||tfmail.getText().isEmpty()||tfmail.getText().equals("Saisir Votre Email:") ||mdp.getPassword().equals("")||mdp.getPassword().equals("Saisir Votre mot de passe:")) {
                    JOptionPane.showMessageDialog(null,"Veuillez remplir tous les champs");


                } else if (!tfmail.getText().contains("@")) {
                    JOptionPane.showMessageDialog(null,"Email Invalide","Erreur",JOptionPane.ERROR_MESSAGE);

                }
                else {
                    String nom=tfnom.getText();
                    String prenom=tfprenom.getText();
                    String email=tfmail.getText();
                    char[] password= mdp.getPassword();
                    etudiant=new EtudiantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    int a= etudiant.ajouterEtudiant(nom,prenom,email,password);
                    if (a>0)
                    {
                        JOptionPane.showMessageDialog(null,"Compte cree avec succes");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Erreur");
                    }
                }

            }
            public boolean exist(char[] password)
            {
                for (Etudiant etudiant:data)
                {
                    if (etudiant.getPassword().equals(password))
                    {
                        return true;
                    }
                }
                return false;
            }
        });
        setVisible(true);
    }
}
