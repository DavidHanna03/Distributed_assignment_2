import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class TodoServiceImpl extends UnicastRemoteObject implements TodoService {
    private List<String> tasks;
    private List<Boolean> isComplete;

    protected TodoServiceImpl() throws RemoteException {
        super();
        tasks = new ArrayList<>();
        isComplete = new ArrayList<>();
    }

    @Override
    public void addTask(String task) throws RemoteException {
        tasks.add(task);
        isComplete.add(false);  // New task starts as incomplete
        System.out.println("Task added: " + task);
    }

    @Override
    public boolean markTaskComplete(String task) throws RemoteException {
        int index = tasks.indexOf(task);
        if (index != -1) {
            isComplete.set(index, true);  // Mark task as complete
            System.out.println("Task marked as complete: " + task);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTask(String task) throws RemoteException {
        int index = tasks.indexOf(task);
        if (index != -1) {
            tasks.remove(index);
            isComplete.remove(index);
            System.out.println("Task deleted: " + task);
            return true;
        }
        return false;
    }

    @Override
    public int getTaskCount() throws RemoteException {
        System.out.println("Getting task count.");
        return tasks.size();
    }

    @Override
    public List<String> searchTasks(String keyword) throws RemoteException {
        List<String> result = new ArrayList<>();
        for (String task : tasks) {
            if (task.contains(keyword)) {
                result.add(task);
            }
        }
        System.out.println("Search tasks with keyword: " + keyword);
        return result;
    }

    @Override
    public List<String> viewAllTasks() throws RemoteException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            String status = isComplete.get(i) ? " (Completed)" : " (Incomplete)";
            result.add(tasks.get(i) + status);
        }
        System.out.println("Viewing all tasks.");
        return result;
    }
}
