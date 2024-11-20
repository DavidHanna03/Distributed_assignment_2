import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TodoService extends Remote {
    void addTask(String task) throws RemoteException;
    boolean markTaskComplete(String task) throws RemoteException;
    boolean deleteTask(String task) throws RemoteException;
    int getTaskCount() throws RemoteException;
    List<String> searchTasks(String keyword) throws RemoteException;
    List<String> viewAllTasks() throws RemoteException; // New feature to view all tasks
}
