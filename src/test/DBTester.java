package test;

//UTIL IMPORT
import java.util.List;

//CLASSES IMPORT
import dal.IUserDAO;
import dal.IUserDAO.DALException;
import dal.UserDAODiscImpl;
import dto.UserDTO;

//JUNIT4 IMPORTS
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;


public class DBTester {
	private UserDTO User1 = new UserDTO(1,"", "", "");
	private UserDTO User2_Admin = new UserDTO(1,"", "", "");
	private UserDTO User3 = new UserDTO(1,"", "", "");

	private IUserDAO iDAO = new UserDAODiscImpl();



	@Before
	public void setUp() throws Exception {
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

		iDAO.createUser(User1);
		iDAO.createUser(User2_Admin);
		iDAO.createUser(User3);
	}

	@Test
	public void testEntities(){

		Assert.assertNotNull(this.User1);
		Assert.assertNotNull(this.User2_Admin);
		Assert.assertNotNull(this.User3);

		printUsers(iDAO);

	}
	@Test(expected = DALException.class)
	public void testUserAlreadyExsits() {
		try {
			iDAO.createUser(User1);
		} catch (DALException e1){
		Assert.assertTrue(e1.getMessage().equals("User already exists!"));
		}
	}

/*
	public static void main(String[] args) {
		//TODO test new fields...

		//throw error if user already exsists
		try {
			iDAO.createUser(newUser);
		} catch (DALException e1) {
			System.out.println("User already existed - OK");
		}

		//create new user
		newUser.setUserId(1);
		newUser.setUserName("2ND user");
		try {
			iDAO.createUser(newUser);
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		printUsers(iDAO);
		newUser.setUserId(0);
		newUser.setUserName("ModifiedName");
		try {
			iDAO.updateUser(newUser);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		printUsers(iDAO);
		
		try {
			iDAO.deleteUser(1);
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		printUsers(iDAO);

	}
*/
	//Change to new arrayList
	private static void printUsers(IUserDAO iDAO) {
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

	@After
public void tearDown() throws Exception{

	}

}
