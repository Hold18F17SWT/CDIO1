import dal.IUserDAO;
import dal.UserDAODiscImpl;
import dto.UserDTO;

import java.util.List;
import java.util.Scanner;

/**
 * Created by emilbonnekristiansen on 20/02/2017.
 */
public class crud {
    private static UserDAODiscImpl dao;
    private static Scanner sc;
    public static void main(String[] args){
        dao = new UserDAODiscImpl();
        sc = new Scanner(System.in);
        while (true){
            // Udskriv default menu:
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
        // ikke implementeret
    }

    private static void updateUserMenu() {
        System.out.println("--OPDATÉR BRUGER--");
        // ikke implementeret
    }

    private static void findUserMenu() {
        System.out.println("--FIND BRUGER--");
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
