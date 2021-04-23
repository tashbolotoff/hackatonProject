package kg.hackaton.project.bootstrap;


import kg.hackaton.project.entities.Oblast;
import kg.hackaton.project.entities.Rayon;
import kg.hackaton.project.entities.User;
import kg.hackaton.project.entities.UserRole;
import kg.hackaton.project.repositories.OblastRepo;
import kg.hackaton.project.repositories.RayonRepo;
import kg.hackaton.project.repositories.UserRepo;
import kg.hackaton.project.repositories.UserRoleRepo;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OblastRepo oblastRepo;

    @Autowired
    private RayonRepo rayonRepo;

    @Value("${filespath}")
    private String filespath;

    @Override
    public void run(String... args) throws Exception {
        // ROLES
        UserRole adminRole = UserRole.builder()
                .name("ROLE_ADMIN")
                .build();
        userRoleRepo.save(adminRole);

        // USERS
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .name("Бакай")
                .surname("Кыдырбек уулу")
                .phone("0773508744")
                .email("bakaiks312@gmail.com")
                .dateOfBirth(new Date())
                .userRole(adminRole)
                .build();
        userRepo.save(admin);



        // OBLASTS
        Oblast bishkek = Oblast.builder()
                .name("г.Бишкек")
                .oblastCode(1)
                .build();
        oblastRepo.save(bishkek);
        Oblast osh = Oblast.builder()
                .name("г.Ош")
                .oblastCode(9)
                .build();
        oblastRepo.save(osh);
        Oblast chuy = Oblast.builder()
                .name("Чуйская область")
                .oblastCode(8)
                .build();
        oblastRepo.save(chuy);
        Oblast talas = Oblast.builder()
                .name("Таласская область")
                .oblastCode(7)
                .build();
        oblastRepo.save(talas);
        Oblast oshOblast = Oblast.builder()
                .name("Ошская область")
                .oblastCode(6)
                .build();
        oblastRepo.save(oshOblast);
        Oblast batken = Oblast.builder()
                .name("Баткенская область")
                .oblastCode(5)
                .build();
        oblastRepo.save(batken);
        Oblast naryn = Oblast.builder()
                .name("Нарынская область")
                .oblastCode(4)
                .build();
        oblastRepo.save(naryn);
        Oblast jalalabad = Oblast.builder()
                .name("Джалал-Абадская область")
                .oblastCode(3)
                .build();
        oblastRepo.save(jalalabad);
        Oblast issykkul = Oblast.builder()
                .name("Иссык-Кульская область")
                .oblastCode(2)
                .build();
        oblastRepo.save(issykkul);

        try (InputStream inp = new FileInputStream(filespath + "rayons.xls")) {
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i < 100; i++) {
                Row row = sheet.getRow(i);
                Cell name = row.getCell(2);
                Cell rayonCode = row.getCell(0);
                Cell oblId = row.getCell(1);
                if (name != null && oblId != null) {
                    String oblIdStr = String.valueOf(oblId);
                    String rayonCodeStr = String.valueOf(rayonCode);
                    float oblIdfl = Float.parseFloat(oblIdStr);
                    float rayonCodeFl = Float.parseFloat(rayonCodeStr);
                    Rayon rayon = Rayon.builder()
                            .name(String.valueOf(name))
                            .oblast(oblastRepo.getOblastByOblastCode((int) oblIdfl))
                            .build();
                    rayonRepo.save(rayon);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}