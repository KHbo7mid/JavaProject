package chat;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ChatRemote extends Remote {
    public ArrayList<Message> getAllMsg() throws RemoteException;
    public void addMessage(Message msg) throws RemoteException;
    public void deleteMsgs() throws RemoteException;
}
