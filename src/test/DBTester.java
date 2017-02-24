package test;

//UTIL IMPORT
import java.util.List;
import java.util.zip.DataFormatException;

//CLASSES IMPORT
import dal.DTOStore;
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.UserDAODisk;
//import dal.UserDAOMemory;
import dto.UserDTO;

//JUNIT4 IMPORTS
import org.junit.*;
//import org.junit.After;


public class DBTester {
    private UserDTO User1;
    private UserDTO User2_Admin;
    private UserDTO User3;

    private UserDAODisk iDAO = new UserDAODisk();

    @Before
    public void setUp() throws Exception {

        iDAO.setFileName("test.data");

        User1 = new UserDTO(11,"","","", "");
        User2_Admin = new UserDTO(22,"","","", "");
        User3 = new UserDTO(33,"","","", "");

        iDAO.createUser(User1);
        iDAO.createUser(User2_Admin);
        iDAO.createUser(User3);

        User1.setIni("test");
        User1.setUserName("Bjarne");
        User1.setCpr("12345678-9012");
        iDAO.updateUser(User1);

        User2_Admin.setIni("test");
        User2_Admin.addRole("Admin");
        User2_Admin.setCpr("12345678-9013");
        User2_Admin.setUserName("AdminMan");
        iDAO.updateUser(User2_Admin);

        User3.setIni("test");
        User3.setUserName("Bjarne1");
        User3.setCpr("12345678-9014");
        iDAO.updateUser(User3);
    }

    @After
    public void tearDown() throws Exception {
        iDAO.clearDataBase();
    }

    @Test
    public void testEntities(){
        Assert.assertNotNull(User1);
        Assert.assertNotNull(User2_Admin);
        Assert.assertNotNull(User3);

        printUsers(iDAO);
        System.out.println("All users were instantiated");
    }

    @Test
    public void testUserAlreadyExists() {
        System.out.print("\n");
        printUsers(iDAO);
        boolean thrown = false;
        try {
            iDAO.createUser(User1);
            System.out.println("Making new instance of user that already exists");
            System.out.print("\n");
            printUsers(iDAO);
        }
        catch (DALException e) {
            System.out.println(e.getMessage());
            thrown = true;
        }
        Assert.assertTrue(thrown);
    }

    @Test
    public void testDeleteUser(){
        System.out.print("\n");
        try {
            System.out.println("Trying to delete userID 22");
            iDAO.deleteUser(22);
            printUsers(iDAO);
            Assert.assertNull(iDAO.getUser(22));
            System.out.println("Deleted userID 22");
        }
        catch (DALException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testChangeUserDetails(){
        System.out.print("\n");
        System.out.println("Default list of users");
        printUsers(iDAO);
        User1.setUserName("Birke");
        User1.setPassword("password123");
        System.out.print("\n");
        try {
            iDAO.updateUser(User1);
            System.out.println("Update username and password for userID 11");
        }
        catch (DALException e){
            System.out.println(e.getMessage());
        }
        printUsers(iDAO);
        System.out.println("User1 has been updated");
    }

    private static void printUsers(UserDAODisk iDAO) {
        try {
            System.out.println("Printing users...");
            List<UserDTO> userList = iDAO.getUserList();
            for (UserDTO userDTO : userList) {
                System.out.println(userDTO);
            }
        }
        catch (DALException e) {
            e.printStackTrace();
        }
    }
}
