package kg.hackaton.project.bootstrap;


import kg.hackaton.project.entities.*;
import kg.hackaton.project.enums.UserStatus;
import kg.hackaton.project.repositories.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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

    @Autowired
    private SerieRepo serieRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private PermissionCategoryRepo permissionCategoryRepo;

    @Value("${filespath}")
    private String filespath;

    @Override
    public void run(String... args) throws Exception {

        Permission permissionAdmin = Permission.builder()
                .name("SUPER_ADMIN")
                .build();
        permissionRepo.save(permissionAdmin);

        PermissionCategory permissionCategoryUser = PermissionCategory.builder()
                .name("Users")
                .nameRu("Пользователи")
                .build();
        permissionCategoryRepo.save(permissionCategoryUser);

        Permission permissionUserCreate = Permission.builder()
                .name("USER_CREATE")
                .nameRu("Добавление пользователей")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserCreate);

        Permission permissionUserUpdate = Permission.builder()
                .name("USER_UPDATE")
                .nameRu("Изменение пользователей")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserUpdate);

        Permission permissionUserRead = Permission.builder()
                .name("USER_READ")
                .nameRu("Просмотр пользователей")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserRead);


        PermissionCategory permissionCategoryRayon = PermissionCategory.builder()
                .name("Rayons")
                .nameRu("Районы")
                .build();
        permissionCategoryRepo.save(permissionCategoryRayon);

        Permission permissionRayonCreate = Permission.builder()
                .name("RAYON_CREATE")
                .nameRu("Добавление районов")
                .permissionCategory(permissionCategoryRayon)
                .build();
        permissionRepo.save(permissionRayonCreate);

        Permission permissionRayonRead = Permission.builder()
                .name("RAYON_READ")
                .nameRu("Просмотр районов")
                .permissionCategory(permissionCategoryRayon)
                .build();
        permissionRepo.save(permissionRayonRead);

        Permission permissionRayonUpdate = Permission.builder()
                .name("RAYON_UPDATE")
                .nameRu("Изменение районов")
                .permissionCategory(permissionCategoryRayon)
                .build();
        permissionRepo.save(permissionRayonUpdate);

        // ROLES
        UserRole userRoleAdmin = UserRole.builder()
                .name("ROLE_ADMIN")
                .build();
        ArrayList<Permission> permissionArrayList = new ArrayList<>();
        permissionArrayList.add(permissionAdmin);
        userRoleAdmin.setPermissions(permissionArrayList);
        if (userRoleAdmin.getPermissions() != null) {
            permissionArrayList.addAll(userRoleAdmin.getPermissions());
        }
        userRoleRepo.save(userRoleAdmin);

        // USERS
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .userStatus(UserStatus.АКТИВИРОВАН)
                .name("Бакай")
                .surname("Кыдырбек уулу")
                .phone("0773508744")
                .email("bakaiks312@gmail.com")
                .dateOfBirth(new Date())
                .userRole(userRoleAdmin)
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

        //SERIES
        Serie first = Serie.builder()
                .name("102 серия")
                .build();
        serieRepo.save(first);

        Serie second = Serie.builder()
                .name("104 серия")
                .build();
        serieRepo.save(second);

        Serie third = Serie.builder()
                .name("104 серия улучшенная")
                .build();
        serieRepo.save(third);

        Serie fourth = Serie.builder()
                .name("105 серия")
                .build();
        serieRepo.save(fourth);

        Serie fifth = Serie.builder()
                .name("105 серия улучшенная")
                .build();
        serieRepo.save(fifth);

        Serie sixth = Serie.builder()
                .name("106 серия")
                .build();
        serieRepo.save(sixth);

        Serie seventh = Serie.builder()
                .name("106 серия улучшенная")
                .build();
        serieRepo.save(seventh);

        Serie eighth = Serie.builder()
                .name("сталинка")
                .build();
        serieRepo.save(eighth);

        Serie nineth = Serie.builder()
                .name("хрущевка")
                .build();
        serieRepo.save(nineth);

        Serie tenth = Serie.builder()
                .name("индивид. планировка")
                .build();
        serieRepo.save(tenth);

        Serie eleven = Serie.builder()
                .name("элитка")
                .build();
        serieRepo.save(eleven);

        Serie twelve = Serie.builder()
                .name("малосемейка")
                .build();
        serieRepo.save(twelve);

        Serie thirteen = Serie.builder()
                .name("пентхаус")
                .build();
        serieRepo.save(thirteen);

        Serie fourteen = Serie.builder()
                .name("108 серия")
                .build();
        serieRepo.save(fourteen);

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