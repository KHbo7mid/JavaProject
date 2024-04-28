package Formation;





import Enseignant.EnseignantDAO;

import Enseignant.Enseignant;
import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class AjoutCours  extends JFrame {
    JLabel ltitle,lnom,ldescription,lenseignant,lcode;
    JTextField tfnom,tfenseignant,tfcode;
    JTextArea tfdescription;
    JScrollPane scrollPane;

    JPanel ptitle,pnom,pdescription,penseignant,pform,pbtn,pcode;
    JButton btn;
    
    Enseignant enseignant;
    ArrayList<Formation> data=new ArrayList<Formation>();
    MyTableModel moddel;
    public AjoutCours()
    {
        setTitle("Ajout de Formation ");
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(0x2979FF));

        setSize(600,700);

        this.setResizable(true);

        ptitle=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        ltitle=new JLabel("Ajout de Formation ");

        ltitle.setFont(new Font("Inter",Font.BOLD,48));


        ltitle.setHorizontalAlignment(JLabel.CENTER);

        ptitle.add(ltitle);


        ptitle.setOpaque(false);
        lcode=new JLabel("Code de la Formation:");
        lcode.setFont(new Font("Inter",Font.BOLD,16));

        tfcode=new JTextField(20);
        tfcode.setText("Code:");
        tfcode.setFont(new Font("Arial", Font.PLAIN, 14));

        tfcode.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfcode.getText().equals("Code:")) {
                    tfcode.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfcode.getText().equals(""))
                {
                    tfcode.setText("Code:");
                }

            }
        });
        pcode=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        pcode.add(lcode);
        pcode.add(tfcode);
        pcode.setOpaque(false);


        lnom=new JLabel("Nom de la Formation:");
        lnom.setFont(new Font("Inter",Font.BOLD,16));

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
        ldescription=new JLabel("Description de la Formation:");
        ldescription.setFont(new Font("Inter",Font.BOLD,16));

        tfdescription=new JTextArea(5,20);
        tfdescription.setText("Description:");
        scrollPane=new JScrollPane(tfdescription);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tfdescription.setBounds(50, 50, 200, 30);
        tfdescription.setFont(new Font("Arial", Font.PLAIN, 14));

        tfdescription.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfdescription.getText().equals("Description:")) {
                    tfdescription.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfdescription.getText().equals(""))
                {
                    tfdescription.setText("Description:");
                }

            }
        });
        pdescription=new JPanel(new FlowLayout(FlowLayout.CENTER,35,10));
        pdescription.add(ldescription);
        pdescription.add(scrollPane);
        pdescription.setOpaque(false);

            penseignant=new JPanel(new FlowLayout(FlowLayout.CENTER,35,10));
        lenseignant=new JLabel("ID Enseignant:");
        lenseignant.setFont(new Font("Inter",Font.BOLD,20));
        tfenseignant=new JTextField(20);
        tfenseignant.setText("ID Enseignant:");
        tfenseignant.setFont(new Font("Arial", Font.PLAIN, 14));

        penseignant.add(lenseignant);
        penseignant.add(tfenseignant);
        tfenseignant.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfenseignant.getText().equals("ID Enseignant:")) {
                    tfenseignant.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfenseignant.getText().equals(""))
                {
                    tfenseignant.setText("ID Enseignant:");
                }

            }
        });
        penseignant.setOpaque(false);
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
        pform.add(pdescription);
        pform.add(penseignant);

        pform.setOpaque(false);

        add(ptitle,BorderLayout.NORTH);
        add(pform,BorderLayout.CENTER);
        add(pbtn,BorderLayout.SOUTH);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfcode.getText().isEmpty()||tfcode.getText().equals("Code:")||tfnom.getText().isEmpty()||tfnom.getText().equals("Nom:")|| tfdescription.getText().isEmpty()||tfdescription.getText().equals("Description:") ||tfenseignant.getText().isEmpty()||tfenseignant.getText().equals("ID Enseignant:") ) {
                    JOptionPane.showMessageDialog(null,"Veuillez remplir tous les champs");


                }
                else {
                    int code= Integer.parseInt(tfcode.getText());
                    String nom=tfnom.getText();
                    String description=tfdescription.getText();
                   int id_enseignant= Integer.parseInt(tfenseignant.getText());

                    CoursDAO formation = new CoursDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    EnseignantDAO  enseignantDAO=new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
                    enseignant=enseignantDAO.Recherche(id_enseignant);
                    if(enseignant==null)
                    {
                        JOptionPane.showMessageDialog(null,"Enseignant n'existe pas");
                    }
                    else {
                        int a= formation.ajouterCours(code,nom,description,id_enseignant);
                        if (a>0)
                        {
                            JOptionPane.showMessageDialog(null,"Ajout de Formation effectué avec succes");
                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Erreur");
                        }
                    }

                }

            }

        });
        setVisible(true);
    }
}

