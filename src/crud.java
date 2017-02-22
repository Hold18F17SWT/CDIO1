import dal.IUserDAO;
import dal.UserDAODiscImpl;
import dal.UserDAOMemory;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class crud {
    private static IUserDAO dao;
    private static Scanner sc;
    public static void main(String[] args){
        dao = new UserDAOMemory();
        sc = new Scanner(System.in);
        while (true){
            // Udskriv default Menu:
            System.out.println("--HOVEDMENU--");
            System.out.println("1. Opret bruger");
            System.out.println("2. Se alle brugere");
            System.out.println("3. Find en bruger");
            System.out.println("4. Opdatér en bruger");
            System.out.println("5. Slet en bruger");
            System.out.println("6. Afslut program");
            System.out.println("Hvad vil du?");
            int choice = sc.nextInt();
            //
            switch(choice){
                case 1: createUserMenu();
                    break;
                case 2: listUsersMenu();
                    break;
                case 3: findUserMenu();
                    break;
                case 4: updateUserMenu();
                    break;
                case 5: deleteUserMenu();
                    break;
                case 6: System.exit(1);
                    break;
            }
        }
    }

    private static void deleteUserMenu() {
        System.out.println("--SLET BRUGER--");
        System.out.println("Hvad er id'et på den bruger du vil slette?");
        int id = sc.nextInt();

        try {
            dao.deleteUser(id);
            System.out.println("Bruger blev slettet");
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateUserMenu() {
        System.out.println("--OPDATÉR BRUGER--");
        System.out.println("Indtast ID for bruger, der skal ændres:");
        int id = sc.nextInt();

        try {
            UserDTO user = dao.getUser(id);
            System.out.println(user.toString());
            boolean loopDisShit = true;

            while (loopDisShit){
                System.out.println("--VÆLG --");
                System.out.println("1. Navn");
                System.out.println("2. Initialer");
                System.out.println("3. Cpr-nummer");
                System.out.println("4. Password");
                System.out.println("5. Tilføj rolle");
                System.out.println("6. Fjern rolle");
                System.out.println("7. Tilbage til menu");
                int choice = sc.nextInt();
                //
                switch(choice){
                    case 1: System.out.println("Indtast nyt navn:");
                        user.setUserName(sc.next());
                        break;
                    case 2: System.out.println("Indtast nye initialer:");
                        user.setIni(sc.next());
                        break;
                    case 3: System.out.println("Indtast nyt cpr-nummer:");
                        user.setCpr(sc.next());
                        break;
                    case 4: System.out.println("Indtast nyt password:");
                        user.setPassword(sc.next());
                        break;
                    case 5: System.out.println("Indtast ny rolle:");
                        user.addRole(sc.next());
                        break;
                    case 6: System.out.println("Indtast rolle, der skal fjernes:");
                        user.removeRole(sc.next());
                        break;
                    case 7: loopDisShit = false;
                        break;
                }
                dao.updateUser(user);
            }
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }

/*        System.out.println("Vælg rolle:");
        String role = roleMenu();
        System.out.println("Hvad er id'et på den bruger du vil opdatere?");
        int id = sc.nextInt();
        System.out.println("indtast nyt navn:");
        String name = sc.next();
        System.out.println("indtast nyt initial:");
        String ini = sc.next();
        System.out.println("indtast nyt cpr-nummer:");
        String cpr = sc.next();
        System.out.println("indtast nyt password:");
        String password = sc.next();

        try {
            UserDTO user = dao.getUser(id);
            user.setUserName(name);
            user.setIni(ini);
            user.setCpr(cpr);
            user.setPassword(password);
            dao.updateUser(user);
            System.out.println("Bruger blev opdateret");
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }*/
    }

    private static void findUserMenu() {
        System.out.println("--FIND BRUGER--");
        System.out.println("Hvad er id'et på den bruger du vil finde?");
        int id = sc.nextInt();

        try {
            UserDTO user = dao.getUser(id);
            System.out.println(user);
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listUsersMenu() {
        System.out.println("--SE ALLE BRUGERE--");
        List users = null;

        try {
            users = dao.getUserList();
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }

        for (Object user : users) {
            System.out.println(user);
        }
    }

    private static void createUserMenu(){
        System.out.println("--OPRET BRUGER--");
        System.out.println("indtast ønsket ID mellem 1 og 99:");
        int id = sc.nextInt();
        System.out.println("indtast navn:");
        String name = sc.next();
        System.out.println("indtast initial:");
        String ini = sc.next();
        System.out.println("indtast cpr-nummer:");
        String cpr = sc.next();
        System.out.println("Vælg rolle:");
        String role = roleMenu();

        try {
            dao.createUser(new UserDTO(id, name, ini, cpr, role));
            System.out.println("Bruger blev oprettet");
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String roleMenu() {
        System.out.println("1. Admin");
        System.out.println("2. Pharmacist");
        System.out.println("3. Foreman");
        System.out.println("4. Operator");

        int choice = sc.nextInt();

        switch(choice) {
            case 1: return "Admin";
            case 2: return "Pharmacist";
            case 3: return "Foreman";
            case 4: return "Operator";
        }
        return "UNKNOWN";
    }
}
