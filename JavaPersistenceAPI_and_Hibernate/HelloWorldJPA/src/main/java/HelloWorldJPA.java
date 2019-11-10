import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

public class HelloWorldJPA {
    public static void main(String[] args){
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("HelloWorldPU");
        UserTransaction transaction = 
    }
}
