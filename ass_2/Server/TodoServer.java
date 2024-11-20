import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TodoServer {
    public static void main(String[] args) {
        try {
            TodoService service = new TodoServiceImpl();
            Registry registry = LocateRegistry.getRegistry(1099); // Connect to existing registry
            registry.rebind("TodoService", service);
            System.out.println("Todo Service is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
