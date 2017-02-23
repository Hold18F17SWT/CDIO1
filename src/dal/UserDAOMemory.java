package dal;

import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

// HER SKAL IMPLEMENTERES EN VERSION AF IUSERDAO DER BRUGER MEMORY (non persistent)
public class UserDAOMemory implements IUserDAO {
    ArrayList<UserDTO> userList = new ArrayList<>();

    @Override
    public UserDTO getUser(int userId) throws DALException {
        for (UserDTO userDTO : userList) {
            if (userDTO.getUserId()==userId) {
                return userDTO;
            }
        }
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return userList;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        userList.add(user);
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserId()==user.getUserId()){
                userList.set(i, user);
            }
        }
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        userList.remove(getUser(userId));
    }
}
