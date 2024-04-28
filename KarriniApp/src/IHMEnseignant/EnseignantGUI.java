package IHMEnseignant;


import Formation.AjoutCours;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class EnseignantGUI extends JFrame {

    EnseignantGUI(String nom, String prenom,int code) throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,700);
        this.setBackground(new Color(0x2979FF));
        this.setTitle("Enseignant");

        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome "+nom+" "+prenom);
        welcomePanel.add(welcomeLabel);

        JTabbedPane jtp= new JTabbedPane();
        JPanel coursePanel = new JPanel();
        JPanel classPanel = new JPanel();
        JPanel chatPanel = new JPanel();
        jtp.add(coursePanel,"Course");
        jtp.add(classPanel,"Class");
        jtp.add("Chat",chatPanel);

        CoursPanel cp=new CoursPanel(code);

//      ClassPanel ep=new ClassPanel(code);

        this.add(welcomePanel,BorderLayout.NORTH);
        this.add(jtp);

        // partie cours
        JButton update = new JButton("Update");

        JButton addCourseButton = new JButton("Add Course");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCourseButton);
        buttonPanel.add(update);
        coursePanel.add(buttonPanel);





        coursePanel.add(cp);

        //event handlers
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutCours ac=new AjoutCours();
                coursePanel.add(ac);

            }

        });
        update.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        //partie class
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE  );


        this.setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        EnseignantGUI egui=new EnseignantGUI("yassine","ghomriani",100);


    }

}

