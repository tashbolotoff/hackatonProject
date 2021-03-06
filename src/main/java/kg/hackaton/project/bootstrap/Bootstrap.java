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
                .nameRu("????????????????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryUser);

        Permission permissionUserCreate = Permission.builder()
                .name("USER_CREATE")
                .nameRu("???????????????????? ??????????????????????????")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserCreate);

        Permission permissionUserUpdate = Permission.builder()
                .name("USER_UPDATE")
                .nameRu("?????????????????? ??????????????????????????")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserUpdate);

        Permission permissionUserRead = Permission.builder()
                .name("USER_READ")
                .nameRu("???????????????? ??????????????????????????")
                .permissionCategory(permissionCategoryUser)
                .build();
        permissionRepo.save(permissionUserRead);

        PermissionCategory permissionCategoryOblasts = PermissionCategory.builder()
                .name("Oblasts")
                .nameRu("??????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryOblasts);

        Permission permissionOblastsRead = Permission.builder()
                .name("OBLAST_READ")
                .nameRu("???????????????? ????????????????")
                .permissionCategory(permissionCategoryOblasts)
                .build();
        permissionRepo.save(permissionOblastsRead);

        PermissionCategory permissionCategoryRayon = PermissionCategory.builder()
                .name("Rayons")
                .nameRu("????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryRayon);

        Permission permissionRayonCreate = Permission.builder()
                .name("RAYON_CREATE")
                .nameRu("???????????????????? ??????????????")
                .permissionCategory(permissionCategoryRayon)
                .build();
        permissionRepo.save(permissionRayonCreate);

        Permission permissionRayonRead = Permission.builder()
                .name("RAYON_READ")
                .nameRu("???????????????? ??????????????")
                .permissionCategory(permissionCategoryRayon)
                .build();
        permissionRepo.save(permissionRayonRead);

        Permission permissionRayonUpdate = Permission.builder()
                .name("RAYON_UPDATE")
                .nameRu("?????????????????? ??????????????")
                .permissionCategory(permissionCategoryRayon)
                .build();
        permissionRepo.save(permissionRayonUpdate);


        PermissionCategory permissionCategoryManufacturer = PermissionCategory.builder()
                .name("Manufacturer")
                .nameRu("?????????? ??????????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryManufacturer);

        Permission permissionManufacturerCreate = Permission.builder()
                .name("MANUFACTURER_CREATE")
                .nameRu("???????????????????? ???????????? ??????????????????")
                .permissionCategory(permissionCategoryManufacturer)
                .build();
        permissionRepo.save(permissionManufacturerCreate);

        Permission permissionManufacturerRead = Permission.builder()
                .name("MANUFACTURER_READ")
                .nameRu("???????????????? ?????????? ????????????????????")
                .permissionCategory(permissionCategoryManufacturer)
                .build();
        permissionRepo.save(permissionManufacturerRead);

        Permission permissionManufacturerUpdate = Permission.builder()
                .name("MANUFACTURER_UPDATE")
                .nameRu("?????????????????? ?????????? ????????????????????")
                .permissionCategory(permissionCategoryManufacturer)
                .build();
        permissionRepo.save(permissionManufacturerUpdate);


        PermissionCategory permissionCategoryClient = PermissionCategory.builder()
                .name("Clients")
                .nameRu("??????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryClient);

        Permission permissionClientCreate = Permission.builder()
                .name("CLIENT_CREATE")
                .nameRu("???????????????????? ????????????????")
                .permissionCategory(permissionCategoryClient)
                .build();
        permissionRepo.save(permissionClientCreate);

        Permission permissionClientRead = Permission.builder()
                .name("CLIENT_READ")
                .nameRu("???????????????? ????????????????")
                .permissionCategory(permissionCategoryClient)
                .build();
        permissionRepo.save(permissionClientRead);

        Permission permissionClientUpdate = Permission.builder()
                .name("CLIENT_UPDATE")
                .nameRu("?????????????????? ????????????????")
                .permissionCategory(permissionCategoryClient)
                .build();
        permissionRepo.save(permissionClientUpdate);


        PermissionCategory permissionCategorySerie = PermissionCategory.builder()
                .name("Series")
                .nameRu("?????????? ??????????????")
                .build();
        permissionCategoryRepo.save(permissionCategorySerie);

        Permission permissionSerieCreate = Permission.builder()
                .name("SERIE_CREATE")
                .nameRu("???????????????????? ?????????? ??????????????")
                .permissionCategory(permissionCategorySerie)
                .build();
        permissionRepo.save(permissionSerieCreate);

        Permission permissionSerieRead = Permission.builder()
                .name("SERIE_READ")
                .nameRu("???????????????? ?????????? ??????????????")
                .permissionCategory(permissionCategorySerie)
                .build();
        permissionRepo.save(permissionSerieRead);

        Permission permissionSerieUpdate = Permission.builder()
                .name("SERIE_UPDATE")
                .nameRu("?????????????????? ?????????? ??????????????")
                .permissionCategory(permissionCategorySerie)
                .build();
        permissionRepo.save(permissionSerieUpdate);


        PermissionCategory permissionCategoryContract = PermissionCategory.builder()
                .name("Contracts")
                .nameRu("????????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryContract);

        Permission permissionContractCreate = Permission.builder()
                .name("CONTRACT_CREATE")
                .nameRu("???????????????????? ??????????????????")
                .permissionCategory(permissionCategoryContract)
                .build();
        permissionRepo.save(permissionContractCreate);

        Permission permissionContractRead = Permission.builder()
                .name("CONTRACT_READ")
                .nameRu("???????????????? ??????????????????")
                .permissionCategory(permissionCategoryContract)
                .build();
        permissionRepo.save(permissionContractRead);


        //for Appartments
        PermissionCategory permissionCategoryAppartment = PermissionCategory.builder()
                .name("Appartments")
                .nameRu("????????????????/????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryAppartment);

        Permission permissionAppartmentCreate = Permission.builder()
                .name("APPARTMENT_CREATE")
                .nameRu("???????????????????? ??????????????/??????????")
                .permissionCategory(permissionCategoryAppartment)
                .build();
        permissionRepo.save(permissionAppartmentCreate);

        Permission permissionAppartmentRead = Permission.builder()
                .name("APPARTMENT_READ")
                .nameRu("???????????????? ??????????????/??????????")
                .permissionCategory(permissionCategoryAppartment)
                .build();
        permissionRepo.save(permissionAppartmentRead);

        Permission permissionAppartmentUpdate = Permission.builder()
                .name("APPARTMENT_UPDATE")
                .nameRu("?????????????????? ??????????????/??????????")
                .permissionCategory(permissionCategoryAppartment)
                .build();
        permissionRepo.save(permissionAppartmentUpdate);

        //for MAP
        PermissionCategory permissionCategoryMap = PermissionCategory.builder()
                .name("Map")
                .nameRu("??????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryMap);

        Permission permissionMapRead = Permission.builder()
                .name("MAP_READ")
                .nameRu("???????????????? ??????????")
                .permissionCategory(permissionCategoryMap)
                .build();
        permissionRepo.save(permissionMapRead);

        //for Chart
        PermissionCategory permissionCategoryChart = PermissionCategory.builder()
                .name("Chart")
                .nameRu("??????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryChart);

        Permission permissionChartsRead = Permission.builder()
                .name("CHART_READ")
                .nameRu("???????????????? ????????????")
                .permissionCategory(permissionCategoryChart)
                .build();
        permissionRepo.save(permissionChartsRead);

        //for Otchet
        PermissionCategory permissionCategoryOtchet = PermissionCategory.builder()
                .name("Otchet")
                .nameRu("????????????")
                .build();
        permissionCategoryRepo.save(permissionCategoryOtchet);

        Permission permissionOtchetsRead = Permission.builder()
                .name("OTCHET_READ")
                .nameRu("???????????????? ??????????????")
                .permissionCategory(permissionCategoryOtchet)
                .build();
        permissionRepo.save(permissionOtchetsRead);

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
                .userStatus(UserStatus.??????????????????????)
                .name("??????????????????????????")
                .surname("??????????????????????????")
                .phone("0773508744")
                .email("bakaiks312@gmail.com")
                .dateOfBirth(new Date())
                .userRole(userRoleAdmin)
                .build();
        userRepo.save(admin);

        User manager = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("manager"))
                .userStatus(UserStatus.??????????????????????)
                .name("??????????????")
                .surname("??????????????")
                .middleName("????????????????????????")
                .phone("0773508744")
                .email("obdul@gmail.com")
                .dateOfBirth(new Date())
                .userRole(userRoleManager)
                .build();
        userRepo.save(manager);

        User client = User.builder()
                .name("????????")
                .surname("????????????????????")
                .middleName("????????????????????")
                .username("client")
                .phone("0555211308")
                .email("uluk@gmail.com")
                .dateOfBirth(new Date())
                .password("client")
                .userStatus(UserStatus.??????????????????????)
                .userRole(userRoleClient)
                .build();
        userRepo.save(client);

        Client client1 = Client.builder()
                .name("????????")
                .surname("????????????????????")
                .middlename("????????????????????")
                .pin("21408199901306")
                .user(client)
                .build();
        clientRepo.save(client1);

        // OBLASTS
        Oblast bishkek = Oblast.builder()
                .name("??.????????????")
                .oblastCode(1)
                .build();
        oblastRepo.save(bishkek);
        Oblast osh = Oblast.builder()
                .name("??.????")
                .oblastCode(9)
                .build();
        oblastRepo.save(osh);
        Oblast chuy = Oblast.builder()
                .name("?????????????? ??????????????")
                .oblastCode(8)
                .build();
        oblastRepo.save(chuy);
        Oblast talas = Oblast.builder()
                .name("?????????????????? ??????????????")
                .oblastCode(7)
                .build();
        oblastRepo.save(talas);
        Oblast oshOblast = Oblast.builder()
                .name("???????????? ??????????????")
                .oblastCode(6)
                .build();
        oblastRepo.save(oshOblast);
        Oblast batken = Oblast.builder()
                .name("???????????????????? ??????????????")
                .oblastCode(5)
                .build();
        oblastRepo.save(batken);
        Oblast naryn = Oblast.builder()
                .name("?????????????????? ??????????????")
                .oblastCode(4)
                .build();
        oblastRepo.save(naryn);
        Oblast jalalabad = Oblast.builder()
                .name("????????????-???????????????? ??????????????")
                .oblastCode(3)
                .build();
        oblastRepo.save(jalalabad);
        Oblast issykkul = Oblast.builder()
                .name("??????????-???????????????? ??????????????")
                .oblastCode(2)
                .build();
        oblastRepo.save(issykkul);

        //SERIES
        Serie first = Serie.builder()
                .name("102 ??????????")
                .build();
        serieRepo.save(first);

        Serie second = Serie.builder()
                .name("104 ??????????")
                .build();
        serieRepo.save(second);

        Serie third = Serie.builder()
                .name("104 ?????????? ????????????????????")
                .build();
        serieRepo.save(third);

        Serie fourth = Serie.builder()
                .name("105 ??????????")
                .build();
        serieRepo.save(fourth);

        Serie fifth = Serie.builder()
                .name("105 ?????????? ????????????????????")
                .build();
        serieRepo.save(fifth);

        Serie sixth = Serie.builder()
                .name("106 ??????????")
                .build();
        serieRepo.save(sixth);

        Serie seventh = Serie.builder()
                .name("106 ?????????? ????????????????????")
                .build();
        serieRepo.save(seventh);

        Serie eighth = Serie.builder()
                .name("????????????????")
                .build();
        serieRepo.save(eighth);

        Serie nineth = Serie.builder()
                .name("????????????????")
                .build();
        serieRepo.save(nineth);

        Serie tenth = Serie.builder()
                .name("??????????????. ????????????????????")
                .build();
        serieRepo.save(tenth);

        Serie eleven = Serie.builder()
                .name("????????????")
                .build();
        serieRepo.save(eleven);

        Serie twelve = Serie.builder()
                .name("??????????????????????")
                .build();
        serieRepo.save(twelve);

        Serie thirteen = Serie.builder()
                .name("????????????????")
                .build();
        serieRepo.save(thirteen);

        Serie fourteen = Serie.builder()
                .name("108 ??????????")
                .build();
        serieRepo.save(fourteen);

        //MANUFACTURERS
        Manufacturer manufacturerItalia = Manufacturer.builder()
                .name("???? ?????????????????????? ??????????????")
                .build();
        manufacturerRepo.save(manufacturerItalia);

        Manufacturer manufacturerNewYork = Manufacturer.builder()
                .name("???? ??????-????????")
                .build();
        manufacturerRepo.save(manufacturerNewYork);

        Manufacturer manufacturerAvenue = Manufacturer.builder()
                .name("???? 7 ??????????")
                .build();
        manufacturerRepo.save(manufacturerAvenue);

        Manufacturer manufacturerEnglish = Manufacturer.builder()
                .name("???? ???????????????????? ??????????????")
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
        List<ImgPath> imgPaths1 = new ArrayList<>();
        List<ImgPath> imgPaths2 = new ArrayList<>();
        List<ImgPath> imgPaths3 = new ArrayList<>();
        List<ImgPath> imgPaths4 = new ArrayList<>();
        List<ImgPath> imgPaths5 = new ArrayList<>();
        List<ImgPath> imgPaths6 = new ArrayList<>();
        List<ImgPath> imgPaths7 = new ArrayList<>();
        ImgPath imgPath = ImgPath.builder()
                .path(image1)
                .build();
        imgPathRepo.save(imgPath);
        imgPaths1.add(imgPath);

        ImgPath imgPath2 = ImgPath.builder()
                .path(image2)
                .build();
        imgPathRepo.save(imgPath2);
        imgPaths2.add(imgPath2);

        ImgPath imgPath3 = ImgPath.builder()
                .path(image3)
                .build();
        imgPathRepo.save(imgPath3);
        imgPaths3.add(imgPath3);

        ImgPath imgPath4 = ImgPath.builder()
                .path(image4)
                .build();
        imgPathRepo.save(imgPath4);
        imgPaths4.add(imgPath4);

        imgPaths5.add(imgPath);
        imgPaths6.add(imgPath2);
        imgPaths7.add(imgPath3);
        Appartment appartment = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(1)
                .stage(5)
                .price(new BigDecimal(8000))
                .address("????. ???????????????????? 39")
                .manufacturer(manufacturerRepo.save(Manufacturer.builder().name("???? ???????? ????????").build()))
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.????????????????)
                .typeOfSale(TypeOfSale.????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????????)
                .latitude(42.832808)
                .longitude(74.616490)
                .imgPaths(imgPaths1)
                .build();
        appartmentRepo.save(appartment);

        Appartment appartment1 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(1)
                .stage(5)
                .price(new BigDecimal(60000))
                .address("????. ?????????????? 30")
                .manufacturer(manufacturerRepo.save(Manufacturer.builder().name("???? ????????????????").build()))
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.????????????????)
                .typeOfSale(TypeOfSale.??????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????????)
                .latitude(42.8298142)
                .longitude(74.6120214)
                .imgPaths(imgPaths2)
                .build();
        appartmentRepo.save(appartment1);

        Appartment appartment2 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(3)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("????. ???????????????????? 45")
                .manufacturer(manufacturerRepo.save(Manufacturer.builder().name("???? ??????????").build()))
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.??????)
                .typeOfSale(TypeOfSale.????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????)
                .latitude(42.8505576)
                .longitude(74.5798596)
                .imgPaths(imgPaths3)
                .build();
        appartmentRepo.save(appartment2);

        Appartment appartment3 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(4)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("????. ???????????????????? 45")
                .manufacturer(manufacturerItalia)
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.??????)
                .typeOfSale(TypeOfSale.????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????)
                .latitude(42.903959)
                .longitude(74.844725)
                .imgPaths(imgPaths4)
                .build();
        appartmentRepo.save(appartment3);

        Appartment appartment4 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(2)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("????. ???????????????????? 45")
                .manufacturer(manufacturerNewYork)
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.??????)
                .typeOfSale(TypeOfSale.????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????????)
                .latitude(42.875228)
                .longitude(74.56206)
                .imgPaths(imgPaths5)
                .build();
        appartmentRepo.save(appartment4);

        Appartment appartment5 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(1)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("????. ???????????????????? 45")
                .manufacturer(manufacturerAvenue)
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.??????)
                .typeOfSale(TypeOfSale.????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????????)
                .latitude(42.862427)
                .longitude(74.5425)
                .imgPaths(imgPaths6)
                .build();
        appartmentRepo.save(appartment5);

        Appartment appartment6 = Appartment.builder()
                .rayon(rayonRepo.getOne(67L))
                .countOfRooms(5)
                .stage(5)
                .price(new BigDecimal(120000))
                .address("????. ???????????????????? 45")
                .manufacturer(manufacturerEnglish)
                .serie(eleven)
                .condition(Condition.??????????????)
                .typeOfHouse(TypeOfHouse.??????)
                .typeOfSale(TypeOfSale.????????????)
                .busyOrFreeStatus(BusyOrFreeStatus.????????????)
                .latitude(42.871466)
                .longitude(74.572623)
                .imgPaths(imgPaths7)
                .build();
        appartmentRepo.save(appartment6);
    }
}