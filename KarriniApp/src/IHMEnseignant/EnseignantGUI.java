package IHMEnseignant;


import Formation.AjoutCours;
import chat.PanelChat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class EnseignantGUI extends JFrame {
    JPanel welcomePanel,coursePanel,classPanel,chatPanel;
    int code;
    String nom,prenom,user;

    public EnseignantGUI(String nom, String prenom, int code) throws SQLException {
        this.code=code;
        this.nom=nom;
        this.prenom=prenom;
        user=nom+" "+prenom;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,700);
        this.setBackground(new Color(0x2979FF));
        this.setTitle("Enseignant");

         welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome "+nom+" "+prenom);
        welcomePanel.setBackground(Color.blue);
        welcomePanel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel);
        welcomePanel.setSize(900,200);

        JTabbedPane jtp= new JTabbedPane();
        coursePanel = new JPanel(new BorderLayout());
         classPanel = new JPanel();
        chatPanel = new PanelChat(code,user);
        jtp.add(coursePanel,"Course");
        jtp.add(classPanel,"Class");
        jtp.add("Chat",chatPanel);

        CoursPanel cp=new CoursPanel(code);

      ClassPanel ep=new ClassPanel(code);

        this.add(welcomePanel,BorderLayout.NORTH);
        this.add(jtp);

        // partie cours


        JButton addCourseButton = new JButton("Add Course");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCourseButton);

        coursePanel.add(buttonPanel,BorderLayout.NORTH);

        buttonPanel.setOpaque(false);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Font size 16, bold
        welcomeLabel.setForeground(Color.white); // Blue color



        coursePanel.add(cp);
        classPanel.add(ep);

        //event handlers
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutCours ac=new AjoutCours();
                coursePanel.add(ac);
                coursePanel.add(ac, BorderLayout.CENTER); // Add AjoutCours to the center of coursePanel
                coursePanel.revalidate(); // Revalidate the panel to reflect the changes
                coursePanel.repaint(); // Repaint the panel

            }

        });
       coursePanel.setBackground(new Color(0x2979FF));
       classPanel.setBackground(new Color(0x2979FF));
        //partie class


        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
       // EnseignantGUI egui=new EnseignantGUI("yassine","ghomriani",100);


    }

}

