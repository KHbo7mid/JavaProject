package Enseignant;








import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AjoutEnseignant  extends JInternalFrame{
    JLabel ltitle,lnom,lprenom,lmail,lmatiere,lcode;
    JTextField tfnom,tfprenom,tfmail,tfmatiere,tfcode;

    JPanel ptitle,pnom,pprenom,pmail,pmatiere,pform,pbtn,pcode;
    JButton btn;
    EnseignantDAO enseignant;
    ArrayList<Enseignant> data=new ArrayList<Enseignant>();
    public AjoutEnseignant()
    {
        setTitle("Ajout d'un Enseignant ");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x2979FF));

        setSize(600,700);
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        ptitle=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        ltitle=new JLabel("Ajout d'un Enseignant ");

        ltitle.setFont(new Font("Inter",Font.BOLD,48));


        ltitle.setHorizontalAlignment(JLabel.CENTER);

        ptitle.add(ltitle);


        ptitle.setOpaque(false);
        lcode=new JLabel("Code :");
        lcode.setFont(new Font("Inter",Font.BOLD,20));
        tfcode=new JTextField(20);
        tfcode.setText("Code Prof:");
        tfcode.setFont(new Font("Arial", Font.PLAIN, 14));
        tfcode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfcode.getText().equals("Code Prof:")) {
                    tfcode.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfcode.getText().equals(""))
                {
                    tfcode.setText("Code Prof:");
                }

            }
        });
        pcode=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pcode.add(lcode);
        pcode.add(tfcode);
        pcode.setOpaque(false);

        lnom=new JLabel("Nom:");
        lnom.setFont(new Font("Inter",Font.BOLD,20));

        tfnom=new JTextField(20);
        tfnom.setText("Nom:");
        tfnom.setFont(new Font("Arial", Font.PLAIN, 14));

        tfnom.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfnom.getText().equals("Nom:")) {
                    tfnom.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfnom.getText().equals(""))
                {
                    tfnom.setText("Nom:");
                }

            }
        });
        pnom=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pnom.add(lnom);
        pnom.add(tfnom);
        pnom.setOpaque(false);
        lprenom=new JLabel("Prenom:");
        lprenom.setFont(new Font("Inter",Font.BOLD,20));

        tfprenom=new JTextField(20);
        tfprenom.setText("Prenom:");
        tfprenom.setFont(new Font("Arial", Font.PLAIN, 14));

        tfprenom.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfprenom.getText().equals("Prenom:")) {
                    tfprenom.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfprenom.getText().equals(""))
                {
                    tfprenom.setText("Prenom:");
                }

            }
        });
        pprenom=new JPanel(new FlowLayout(FlowLayout.CENTER,35,10));
        pprenom.add(lprenom);
        pprenom.add(tfprenom);
        pprenom.setOpaque(false);
        lmail=new JLabel("Email:");
        lmail.setFont(new Font("Inter",Font.BOLD,20));

        tfmail=new JTextField(20);
        tfmail.setText("Email:");
        tfmail.setFont(new Font("Arial", Font.PLAIN, 14));

        tfmail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfmail.getText().equals("Email:")) {
                    tfmail.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfmail.getText().equals(""))
                {
                    tfmail.setText("Email:");
                }

            }
        });
        pmail=new JPanel(new FlowLayout(FlowLayout.CENTER,35,10));
        pmail.add(lmail);
        pmail.add(tfmail);
        pmail.setOpaque(false);
        pmatiere=new JPanel(new FlowLayout(FlowLayout.CENTER,35,10));
        lmatiere=new JLabel("Matiere:");
        lmatiere.setFont(new Font("Inter",Font.BOLD,20));
        tfmatiere=new JTextField(20);
        tfmatiere.setText("Matiere:");
       tfmatiere.setFont(new Font("Arial", Font.PLAIN, 14));

        pmatiere.add(lmatiere);
        pmatiere.add(tfmatiere);
        tfmatiere.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfmatiere.getText().equals("Matiere:")) {
                    tfmatiere.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfmatiere.getText().equals(""))
                {
                    tfmatiere.setText("Matiere:");
                }

            }
        });
        pmatiere.setOpaque(false);
        pform=new JPanel(new FlowLayout(FlowLayout.CENTER,0,10));
        btn =new JButton("Ajouter");
        btn.setBackground(Color.white);
        btn.setForeground(new Color(0x2979FF));
        btn.setFont(new Font("Judson",Font.BOLD,20));
        pbtn=new JPanel(new FlowLayout(FlowLayout.CENTER,50,100));
        pbtn.add(btn);
        pbtn.setOpaque(false);
        pform.add(pcode);
        pform.add(pnom);
        pform.add(pprenom);
        pform.add(pmail);
        pform.add(pmatiere);
        pform.setOpaque(false);

        add(ptitle,BorderLayout.NORTH);
        add(pform,BorderLayout.CENTER);
        add(pbtn,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfnom.getText().isEmpty()||tfnom.getText().equals("Nom:")|| tfprenom.getText().isEmpty()||tfprenom.getText().equals("Prenom:") ||tfmail.getText().isEmpty()||tfmail.getText().equals("Email:") ||tfmatiere.getText().isEmpty()||tfmatiere.getText().equals("Matiere")) {
                    JOptionPane.showMessageDialog(null,"Veuillez remplir tous les champs");


                } else if (!tfmail.getText().contains("@")) {
                    JOptionPane.showMessageDialog(null,"Email Invalide","Erreur",JOptionPane.ERROR_MESSAGE);

                }
                else {
                    int code= Integer.parseInt(tfcode.getText());
                    String nom=tfnom.getText();
                    String prenom=tfprenom.getText();
                    String email=tfmail.getText();
                    String matiere=tfmatiere.getText();

                    enseignant=new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    int a= enseignant.ajouterEnseignant(code,nom,prenom,email,matiere);
                    if (a>0)
                    {
                        JOptionPane.showMessageDialog(null,"Ajoute effectué avec succes");
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Erreur");
                    }
                }

            }

        });
        setVisible(true);
    }


}
