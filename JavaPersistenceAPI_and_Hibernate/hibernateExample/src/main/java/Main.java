import models.Operation;
import models.User;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user = new User("Masha");
        userService.saveUser(user);
        Operation operation = new Operation(15.9);
        operation.setUser(user);
        user.addOperation(operation);
        Operation operation2 = new Operation(105.9);
        operation2.setUser(user);
        user.addOperation(operation2);
        userService.updateUser(user);
    }
}
