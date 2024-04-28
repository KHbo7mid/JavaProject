package chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ChatImplementation extends UnicastRemoteObject implements ChatRemote{
    ArrayList<Message> discussion;

    public ChatImplementation() throws RemoteException {
        discussion = new ArrayList<Message>();
    }

    public ArrayList<Message> getAllMsg() throws RemoteException {
        return discussion;
    }


    public void addMessage(Message ch) throws RemoteException {
        discussion.add(ch);
    }

    @Override
    public void deleteMsgs() throws RemoteException {
        discussion.clear();
    }
}
