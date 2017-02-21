import dal.IUserDAO;
import dal.UserDAODiscImpl;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

/**
 * Created by emilbonnekristiansen on 20/02/2017.
 */
public class crud {
    private static IUserDAO dao;
    private static Scanner sc;
    public static void main(String[] args){
        dao = new UserDAODiscImpl();
        sc = new Scanner(System.in);
        while (true){
            // Udskriv default Menu:
            System.out.println("--HOVEDMENU--");
            System.out.println("1. Opret bruger");
            System.out.println("2. Se alle brugere");
            System.out.println("3. Find en bruger");
            System.out.println("4. Opdatér en bruger");
            System.out.println("5. Slet en bruger");
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
        }
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

    public static void createUserMenu(){
        System.out.println("--OPRET BRUGER--");
        System.out.println("indtast ønsket id mellem 11 og 99:");
        int id = sc.nextInt();
        System.out.println("indtast navn:");
        String name = sc.next();
        System.out.println("indtast initial:");
        String ini = sc.next();
        System.out.println("indtast cpr-nummer:");
        String cpr = sc.next();


        UserDTO newUser = new UserDTO(id, name, ini, cpr);

        try {
            dao.createUser(newUser);
            System.out.println("Bruger blev oprettet");
        } catch (IUserDAO.DALException e) {
            System.out.println(e.getMessage());
        }

    }
}
