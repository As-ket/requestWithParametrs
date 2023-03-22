package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        userService.add(new User("Sergey", "Ivanov", "SI@mail.com", new Car("Tesla", 3)));
        userService.add(new User("Volodya", "Petrov", "VP@gmail.com", new Car("Lada", 2107)));
        userService.add(new User("Renat", "Sidorov", "RS@gmail.com", new Car("Hyundai", 1)));

//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car model = " + user.getUserCar().getModel() + " series = " + user.getUserCar().getSeries());
            System.out.println();
        }

        User user12 = userService.getUserByCar("Lada", 2107);
        System.out.println(user12.toString());

        context.close();
    }
}
