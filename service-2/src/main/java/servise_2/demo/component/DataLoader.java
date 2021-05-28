package servise_2.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import servise_2.demo.entity.Credit;
import servise_2.demo.repository.CreditRepository;

@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String val;

    @Autowired
    CreditRepository cr;
    @Override
    public void run(String... args) throws Exception {
        if (val.equals("create")){
            Credit credit=new Credit("Iste'mol kriditi",250000.0,20000000.0,25.5);
            cr.save(credit);
            credit=new Credit("Ta'lim kriditi",2500000.0,15000000.0,25.5);
            cr.save(credit);
            credit=new Credit("Biznes kriditi",25000000.0,150000000.0,25.5);
            cr.save(credit);
        }
    }
}
