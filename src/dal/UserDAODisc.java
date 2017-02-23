package dal;

import dto.UserDTO;

import java.util.List;

// HER SKAL IMPLEMENTERES EN VERSION AF IUSERDAO DER BRUGER DISK
public class UserDAODisc implements IUserDAO {
    @Override
    public UserDTO getUser(int userId) throws DALException {
        return null;
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        return null;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {

    }

    @Override
    public void updateUser(UserDTO user) throws DALException {

    }

    @Override
    public void deleteUser(int userId) throws DALException {

    }
}
