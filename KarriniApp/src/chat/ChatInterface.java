package chat;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatInterface extends JPanel {
    String nomContact;
    JLabel lnomContact;
    JPanel pnomContact,penvoyerMessage;
    JTextArea message;
    JTextArea chatArea;
    JButton envoyer,supprimer;
    int idReseiver;
    int idSender;
    Remote chat;
    String User;





    public ChatInterface(ChatRemote chat, String nomContact, int idSender, int idReseiver,String user)

{

    setLayout(new BorderLayout());

  this.idReseiver=idReseiver;
  this.idSender=idSender;
  this.chat=chat;
  this.nomContact=nomContact;
  this.User=user;

  lnomContact=new JLabel(nomContact);
  lnomContact.setFont(new Font("Arial",Font.BOLD,25));
  lnomContact.setForeground(Color.WHITE);
  lnomContact.setBackground(Color.GRAY);
  lnomContact.setOpaque(true);
pnomContact=new JPanel(new FlowLayout(FlowLayout.LEFT,20,20));
    pnomContact.setBackground(Color.GRAY);
   pnomContact.setOpaque(true);
  pnomContact.add(lnomContact);
  add(pnomContact,BorderLayout.NORTH);
    chatArea=new JTextArea(20,20);
    chatArea.setEditable(false);
  add(chatArea,BorderLayout.CENTER);
  message=new JTextArea(3,30);
  penvoyerMessage=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
  penvoyerMessage.setBackground(Color.GRAY);
  penvoyerMessage.setOpaque(true);
  penvoyerMessage.add(message);
  envoyer=new JButton("Envoyer");
  penvoyerMessage.add(envoyer);
  new Thread(()->{
      while (true)
      {
          try {

             if (chat!=null)
             {
                 ArrayList<Message> messages=chat.getAllMsg();
                 chatArea.setText("");
                 for (Message message : messages) {


                           if(message.getIdReceiver()==idReseiver && message.getIdSender()==idSender||message.getIdSender()==idReseiver && message.getIdReceiver()==idSender)
                           {
                               chatArea.append(message.toString());
                           }



                 }
                 Thread.sleep(1000);
             }

          } catch (RemoteException | InterruptedException e) {
              throw new RuntimeException(e);
          }
      }
  }).start();
  supprimer=new JButton("Supprimer");
  penvoyerMessage.add(supprimer);
  envoyer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          Date date=new Date();


          String msg="\n"+User;
          msg+=" :"+message.getText();
          try {

             if(msg.equals(""))
             {
                 JOptionPane.showMessageDialog(null,"Vous pouvez saisir tout d'abord");
             }
             else {
                 chat.addMessage(new Message(idReseiver,idSender,msg,date));
                 message.setText("");
             }
          } catch (RemoteException ex) {
              throw new RuntimeException(ex);
          }
      }
  });
  supprimer.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          try {
              chat.deleteMsgs();
          } catch (RemoteException ex) {
              throw new RuntimeException(ex);
          }
      }
  });



  add(penvoyerMessage,BorderLayout.SOUTH);

}

}
