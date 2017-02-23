package test;

//UTIL IMPORT
import java.util.List;

//CLASSES IMPORT
import dal.IUserDAO.DALException;
import dal.UserDAOMemory;
import dto.UserDTO;

//JUNIT4 IMPORTS
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;


public class DBTester {
	private UserDTO User1;
	private UserDTO User2_Admin;
	private UserDTO User3;

	private UserDAOMemory iDAO = new UserDAOMemory();

	@Before
	public void setUp() throws Exception {
	    User1 = new UserDTO(1,"","","");
        User2_Admin = new UserDTO(1,"","","");
        User3 = new UserDTO(1,"","","");

        iDAO.createUser(User1);
        iDAO.createUser(User2_Admin);
        iDAO.createUser(User3);

        User1.setIni("test");
		User1.setUserId(0);
		User1.setUserName("Bjarne");
		User1.setCpr("12345678-9012");

		User2_Admin.setIni("test");
		User2_Admin.addRole("Admin");
		User2_Admin.setUserId(1);
		User2_Admin.setCpr("12345678-9013");
		User2_Admin.setUserName("AdminMan");

		User3.setIni("test");
		User3.setUserId(2);
		User3.setUserName("Bjarne1");
		User3.setCpr("12345678-9014");

	}

	@Test
	public void testEntities(){
		Assert.assertNotNull(this.User1);
		Assert.assertNotNull(this.User2_Admin);
		Assert.assertNotNull(this.User3);

		printUsers(iDAO);
		System.out.println("All users are instantiated");
	}

	@Test(expected = DALException.class)
	public void testUserAlreadyExsits() {
        System.out.print("\n");
        printUsers(iDAO);
		try {
            iDAO.createUser(User1);
            System.out.println("Making new player");
            System.out.print("\n");
            printUsers(iDAO);
            System.out.println("TODO");
            System.out.println("DOES NOT STOP THE SAME PLAYER FROM BEING CREATED");
        } catch (DALException e1){
        Assert.assertTrue(e1 instanceof DALException && e1.getMessage().equals("User already exists!"));
		}
	}

	@Test
    public void testDeleteUser(){
        System.out.print("\n");
        try {
            System.out.println("Trying to delete userID 0");
            iDAO.deleteUser(0);
            printUsers(iDAO);
            System.out.println("Deleted userID 0");
        } catch (DALException e){
            System.out.println("UserID 0 does not exists.");
        }
    }

    @Test
    public void testChangeUser(){
        System.out.print("\n");
        System.out.println("Default list of users");
        printUsers(iDAO);
        User1.setUserName("Birke");
        User1.setPassword("password123");
        System.out.print("\n");
        try {
            iDAO.updateUser(User1);
            System.out.println("Update username and password for userID 0");
        } catch (DALException e){

        }
        printUsers(iDAO);
        System.out.println("User1 has been updated");

    }
	//Change to new arrayList
	private static void printUsers(UserDAOMemory iDAO) {
		try {
			System.out.println("Printing users...");
			List<UserDTO> userList = iDAO.getUserList();
			for (UserDTO userDTO : userList) {
				System.out.println(userDTO);
			}

		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
