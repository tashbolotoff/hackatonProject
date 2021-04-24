package kg.hackaton.project.bootstrap;


import kg.hackaton.project.entities.*;
import kg.hackaton.project.enums.*;
import kg.hackaton.project.repositories.*;
import org.apache.catalina.LifecycleState;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.tuple.InMemoryValueGenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private ClientRepo clientRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Autowired
    private PermissionCategoryRepo permissionCategoryRepo;

    @Autowired
    private AppartmentRepo appartmentRepo;

    @Autowired
    private ImgPathRepo imgPathRepo;

    @Value("${filespath}")
    private String filespath;

    @Value("${image1}")
    private String image1;

    @Value("${image2}")
    private String image2;
    @Value("${image3}")
    private String image3;
    @Value("${image4}")
    private String image4;

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

        PermissionCategory permissionCategoryOblasts = PermissionCategory.builder()
                .name("Oblasts")
                .nameRu("Области")
                .build();
        permissionCategoryRepo.save(permissionCategoryOblasts);

        Permission permissionOblastsRead = Permission.builder()
                .name("OBLAST_READ")
                .nameRu("Просмотр областей")
                .permissionCategory(permissionCategoryOblasts)
                .build();
        permissionRepo.save(permissionOblastsRead);

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


        PermissionCategory permissionCategoryManufacturer = PermissionCategory.builder()
                .name("Manufacturer")
                .nameRu("Жилые комплексы")
                .build();
        permissionCategoryRepo.save(permissionCategoryManufacturer);

        Permission permissionManufacturerCreate = Permission.builder()
                .name("MANUFACTURER_CREATE")
                .nameRu("Добавление жилого комплекса")
                .permissionCategory(permissionCategoryManufacturer)
                .build();
        permissionRepo.save(permissionManufacturerCreate);

        Permission permissionManufacturerRead = Permission.builder()
                .name("MANUFACTURER_READ")
                .nameRu("Просмотр жилых комплексов")
                .permissionCategory(permissionCategoryManufacturer)
                .build();
        permissionRepo.save(permissionManufacturerRead);

        Permission permissionManufacturerUpdate = Permission.builder()
                .name("MANUFACTURER_UPDATE")
                .nameRu("Изменение жилых комплексов")
                .permissionCategory(permissionCategoryManufacturer)
                .build();
        permissionRepo.save(permissionManufacturerUpdate);


        PermissionCategory permissionCategoryClient = PermissionCategory.builder()
                .name("Clients")
                .nameRu("Клиенты")
                .build();
        permissionCategoryRepo.save(permissionCategoryClient);

        Permission permissionClientCreate = Permission.builder()
                .name("CLIENT_CREATE")
                .nameRu("Добавление клиентов")
                .permissionCategory(permissionCategoryClient)
                .build();
        permissionRepo.save(permissionClientCreate);

        Permission permissionClientRead = Permission.builder()
                .name("CLIENT_READ")
                .nameRu("Просмотр клиентов")
                .permissionCategory(permissionCategoryClient)
                .build();
        permissionRepo.save(permissionClientRead);

        Permission permissionClientUpdate = Permission.builder()
                .name("CLIENT_UPDATE")
                .nameRu("Изменение клиентов")
                .permissionCategory(permissionCategoryClient)
                .build();
        permissionRepo.save(permissionClientUpdate);


        PermissionCategory permissionCategorySerie = PermissionCategory.builder()
                .name("Series")
                .nameRu("Серии квартир")
                .build();
        permissionCategoryRepo.save(permissionCategorySerie);

        Permission permissionSerieCreate = Permission.builder()
                .name("SERIE_CREATE")
                .nameRu("Добавление серий квартир")
                .permissionCategory(permissionCategorySerie)
                .build();
        permissionRepo.save(permissionSerieCreate);

        Permission permissionSerieRead = Permission.builder()
                .name("SERIE_READ")
                .nameRu("Просмотр серий квартир")
                .permissionCategory(permissionCategorySerie)
                .build();
        permissionRepo.save(permissionSerieRead);

        Permission permissionSerieUpdate = Permission.builder()
                .name("SERIE_UPDATE")
                .nameRu("Изменение серий квартир")
                .permissionCategory(permissionCategorySerie)
                .build();
        permissionRepo.save(permissionSerieUpdate);


        PermissionCategory permissionCategoryContract = PermissionCategory.builder()
                .name("Contracts")
                .nameRu("Договора")
                .build();
        permissionCategoryRepo.save(permissionCategoryContract);

        Permission permissionContractCreate = Permission.builder()
                .name("CONTRACT_CREATE")
                .nameRu("Добавление договоров")
                .permissionCategory(permissionCategoryContract)
                .build();
        permissionRepo.save(permissionContractCreate);

        Permission permissionContractRead = Permission.builder()
                .name("CONTRACT_READ")
                .nameRu("Просмотр договоров")
                .permissionCategory(permissionCategoryContract)
                .build();
        permissionRepo.save(permissionContractRead);


        //for Appartments
        PermissionCategory permissionCategoryAppartment = PermissionCategory.builder()
                .name("Appartments")
                .nameRu("Квартиры/Дома")
                .build();
        permissionCategoryRepo.save(permissionCategoryAppartment);

        Permission permissionAppartmentCreate = Permission.builder()
                .name("APPARTMENT_CREATE")
                .nameRu("Добавление квартир/домов")
                .permissionCategory(permissionCategoryAppartment)
                .build();
        permissionRepo.save(permissionAppartmentCreate);

        Permission permissionAppartmentRead = Permission.builder()
                .name("APPARTMENT_READ")
                .nameRu("Просмотр квартир/домов")
                .permissionCategory(permissionCategoryAppartment)
                .build();
        permissionRepo.save(permissionAppartmentRead);

        Permission permissionAppartmentUpdate = Permission.builder()
                .name("APPARTMENT_UPDATE")
                .nameRu("Изменение квартир/домов")
                .permissionCategory(permissionCategoryAppartment)
                .build();
        permissionRepo.save(permissionAppartmentUpdate);

        //for MAP
        PermissionCategory permissionCategoryMap = PermissionCategory.builder()
                .name("Map")
                .nameRu("Карта")
                .build();
        permissionCategoryRepo.save(permissionCategoryMap);

        Permission permissionMapRead = Permission.builder()
                .name("MAP_READ")
                .nameRu("Просмотр карты")
                .permissionCategory(permissionCategoryMap)
                .build();
        permissionRepo.save(permissionMapRead);

        // ROLES
        UserRole userRoleAdmin = UserRole.builder()
                .name("ROLE_ADMIN")
                .build();
        ArrayList<Permission> permissionArrayListAdmin = new ArrayList<>();
        permissionArrayListAdmin.add(permissionAdmin);
        if (userRoleAdmin.getPermissions() != null) {
            permissionArrayListAdmin.addAll(userRoleAdmin.getPermissions());
        }
        userRoleAdmin.setPermissions(permissionArrayListAdmin);
        userRoleRepo.save(userRoleAdmin);

        UserRole userRoleManager = UserRole.builder()
                .name("ROLE_MANAGER")
                .build();
        ArrayList<Permission> permissionArrayListManager = new ArrayList<>();
        permissionArrayListManager.add(permissionClientRead);
        permissionArrayListManager.add(permissionClientCreate);
        permissionArrayListManager.add(permissionClientUpdate);
        permissionArrayListManager.add(permissionAppartmentCreate);
        permissionArrayListManager.add(permissionAppartmentRead);
        permissionArrayListManager.add(permissionAppartmentUpdate);
        if (userRoleManager.getPermissions() != null) {
            permissionArrayListManager.addAll(userRoleManager.getPermissions());
        }
        userRoleManager.setPermissions(permissionArrayListManager);
        userRoleRepo.save(userRoleManager);

        UserRole userRoleClient = UserRole.builder()
                .name("ROLE_CLIENT")
                .build();
        ArrayList<Permission> permissionArrayListClient = new ArrayList<>();
        permissionArrayListClient.add(permissionMapRead);
        if (userRoleClient.getPermissions() != null) {
            permissionArrayListClient.addAll(userRoleClient.getPermissions());
        }
        userRoleClient.setPermissions(permissionArrayListClient);
        userRoleRepo.save(userRoleClient);

        // USERS
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .userStatus(UserStatus.Активирован)
                .name("Администратор")
                .surname("Администратор")
                .phone("0773508744")
                .email("bakaiks312@gmail.com")
                .dateOfBirth(new Date())
                .userRole(userRoleAdmin)
                .build();
        userRepo.save(admin);

        User manager = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("manager"))
                .userStatus(UserStatus.Активирован)
                .name("Абдулло")
                .surname("Каримов")
                .middleName("Ахмадилаевич")
                .phone("0773508744")
                .email("obdul@gmail.com")
                .dateOfBirth(new Date())
                .userRole(userRoleManager)
                .build();
        userRepo.save(manager);
        UserRole userRole = UserRole.builder().name("ROLE_CLIENT").build();
        userRoleRepo.save(userRole);
        User client = User.builder()
                .username("client")
                .password("client")
                .userStatus(UserStatus.Активирован)
                .userRole(userRole)
                .build();
        userRepo.save(client);

        Client client1 = Client.builder()
                .name("Улук")
                .surname("Капарбеков")
                .middlename("Давлетович")
                .pin("21408199901306")
                .user(client)
                .build();
        clientRepo.save(client1);

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

        //MANUFACTURERS
        Manufacturer manufacturerItalia = Manufacturer.builder()
                .name("ЖК Итальянский квартал")
                .build();
        manufacturerRepo.save(manufacturerItalia);

        Manufacturer manufacturerNewYork = Manufacturer.builder()
                .name("ЖК Нью-Йорк")
                .build();
        manufacturerRepo.save(manufacturerNewYork);

        Manufacturer manufacturerAvenue = Manufacturer.builder()
                .name("ЖК 7 Авеню")
                .build();
        manufacturerRepo.save(manufacturerAvenue);

        Manufacturer manufacturerEnglish = Manufacturer.builder()
                .name("ЖК Английский квартал")
                .build();
        manufacturerRepo.save(manufacturerEnglish);

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
        List<ImgPath> imgPaths = new ArrayList<>();
        ImgPath imgPath = ImgPath.builder()
                .path(image1)
                .build();
        imgPathRepo.save(imgPath);
        imgPaths.add(imgPath);

        ImgPath imgPath2 = ImgPath.builder()
                .path(image2)
                .build();
        imgPathRepo.save(imgPath2);
        imgPaths.add(imgPath2);

        ImgPath imgPath3 = ImgPath.builder()
                .path(image3)
                .build();
        imgPathRepo.save(imgPath3);
        imgPaths.add(imgPath3);

        ImgPath imgPath4 = ImgPath.builder()
                .path(image4)
                .build();
        imgPathRepo.save(imgPath4);
        imgPaths.add(imgPath4);

        Appartment appartment = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(1)
                .stage(5)
                .price(new BigDecimal(8000))
                .address("ул. Боконбаева 39")
                .manufacturer(manufacturerRepo.save(Manufacturer.builder().name("ЖК Элит хаус").build()))
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Квартира)
                .typeOfSale(TypeOfSale.Аренда)
                .busyOrFreeStatus(BusyOrFreeStatus.Свободно)
                .latitude(42.832808)
                .longitude(74.616490)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment);

        Appartment appartment1 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(1)
                .stage(5)
                .price(new BigDecimal(60000))
                .address("ул. Исанова 30")
                .manufacturer(manufacturerRepo.save(Manufacturer.builder().name("ЖК Авангард").build()))
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Квартира)
                .typeOfSale(TypeOfSale.Продажа)
                .busyOrFreeStatus(BusyOrFreeStatus.Свободно)
                .latitude(42.8702373)
                .longitude(74.5904728)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment1);

        Appartment appartment2 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(3)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("ул. Асаналиева 45")
                .manufacturer(manufacturerRepo.save(Manufacturer.builder().name("ЖК Олимп").build()))
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Дом)
                .typeOfSale(TypeOfSale.Лизинг)
                .busyOrFreeStatus(BusyOrFreeStatus.Занято)
                .latitude(42.8661323)
                .longitude(74.569608)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment2);

        Appartment appartment3 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(4)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("ул. Асаналиева 45")
                .manufacturer(manufacturerItalia)
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Дом)
                .typeOfSale(TypeOfSale.Лизинг)
                .busyOrFreeStatus(BusyOrFreeStatus.Занято)
                .latitude(42.8661323)
                .longitude(74.569608)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment3);

        Appartment appartment4 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(2)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("ул. Асаналиева 45")
                .manufacturer(manufacturerNewYork)
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Дом)
                .typeOfSale(TypeOfSale.Аренда)
                .busyOrFreeStatus(BusyOrFreeStatus.Свободно)
                .latitude(42.8661323)
                .longitude(74.569608)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment4);

        Appartment appartment5 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(1)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("ул. Асаналиева 45")
                .manufacturer(manufacturerAvenue)
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Дом)
                .typeOfSale(TypeOfSale.Лизинг)
                .busyOrFreeStatus(BusyOrFreeStatus.Свободно)
                .latitude(42.8661323)
                .longitude(74.569608)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment5);

        Appartment appartment6 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(5)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("ул. Асаналиева 45")
                .manufacturer(manufacturerEnglish)
                .serie(eleven)
                .condition(Condition.Хорошее)
                .typeOfHouse(TypeOfHouse.Дом)
                .typeOfSale(TypeOfSale.Лизинг)
                .busyOrFreeStatus(BusyOrFreeStatus.Занято)
                .latitude(42.8661323)
                .longitude(74.569608)
                .imgPaths(imgPaths)
                .build();
        appartmentRepo.save(appartment6);
    }
}