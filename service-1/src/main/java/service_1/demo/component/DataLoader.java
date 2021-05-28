package service_1.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import service_1.demo.entity.Role;
import service_1.demo.entity.User;
import service_1.demo.entity.enums.RoleName;
import service_1.demo.repository.RoleRepository;
import service_1.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String val;
    @Override
    public void run(String... args) throws Exception {
        if (val.equals("create")){
            Role admin=new Role(RoleName.ROLE_ADMIN);
            List <Role> roles=new ArrayList<>();
            roles.add(roleRepository.save(admin));
            User user=new User("admin",passwordEncoder.encode("admin"), new HashSet<>(roles));
            User save = userRepository.save(user);
            System.out.println(save);
            List<User> users=new ArrayList<>();
            users.add(new User("AA1275655","Sattorov Abdusattor","Toshkent shahar, Yunsobod tumani, 12/55"));
            users.add(new User("AA8552255","Abdusattorov Sayfi","Toshkent shahar, Yashnobot tumani, aviasozlar-4/55"));
            users.add(new User("AA1234567","Samadov Vasliddin","Toshkent shahar, Shayhontohur tumani, Labzak ko'chasi 10/53"));
            users.add(new User("AA8975642","Alimov Bobur","Toshkent shahar, Sergili tumani, 8-hudud 81/27"));
            users.forEach(user1 -> {
                userRepository.save(user1);
            });
        }
    }
}
