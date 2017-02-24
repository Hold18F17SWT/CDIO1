package dal;

import dto.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserDAODisk implements IUserDAO {
    private static String currentPath = System.getProperty("user.dir");
    private String fileName = currentPath + "/data.dat";

    public void setFileName(String newFileName) {
        // For testing purposes... for now
        this.fileName = currentPath + newFileName;
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        DTOStore users = this.loadUsers();
        UserDTO user = users.getUser(userId);

            if(user != null)
                return user;
            else
                throw new DALException("No user exists for that ID!");
    }

    @Override
    public List<UserDTO> getUserList() throws DALException {
        DTOStore users = this.loadUsers();

        if(users != null)
            return new ArrayList<>(users.getUsers().values());
        else
            throw new DALException("File not found!");
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        DTOStore users = this.loadUsers();

        try {
            users.addUser(user);
            this.saveUsers(users);
        }
        catch (DTOStore.UserAlreadyExistsException e) {
            throw new DALException("User already exists");
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        DTOStore users = this.loadUsers();
        UserDTO userOLD = users.getUser(user.getUserId());

        if (userOLD != null) {
            Map mappedUsers = users.getUsers();
            mappedUsers.put(user.getUserId(), user);
            users.setUsers(mappedUsers);
            this.saveUsers(users);
        }
        else
            throw new DALException("User doesn't exist");
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        DTOStore users = this.loadUsers();
        Map mappedUsers = users.getUsers();

        if (mappedUsers.containsKey(userId)) {
            mappedUsers.remove(userId);
            users.setUsers(mappedUsers);
            this.saveUsers(users);
        }
        else
            throw new DALException("User doesn't exist");
    }

    public void clearDataBase() throws DALException {
        DTOStore users = this.loadUsers();

            users.getUsers().clear();
            this.saveUsers(users);
    }

    private DTOStore loadUsers() throws DALException {
        DTOStore users = new DTOStore();
        ObjectInputStream oIS = null;
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            oIS = new ObjectInputStream(fIS);
            Object inObj = oIS.readObject();
            if (inObj instanceof DTOStore){
                users = (DTOStore) inObj;
            } else {
                throw new DALException("Wrong object in file");
            }
        } catch (FileNotFoundException e) {
            // Return empty DTOStore
        } catch (IOException e) {
            throw new DALException("Error while reading disk!", e);
        } catch (ClassNotFoundException e) {
            throw new DALException("Error while reading file - Class not found!", e);
        } finally {
            if (oIS != null){
                try {
                    oIS.close();
                } catch (IOException e) {
                    throw new DALException("Unable to close ObjectStream!", e);
                }
            }
        }
        return users;
    }

    private void saveUsers(DTOStore users) throws DALException {
        ObjectOutputStream oOS = null;
        try {
            FileOutputStream fOS = new FileOutputStream(fileName);
            oOS = new ObjectOutputStream(fOS);
            oOS.writeObject(users);
        } catch (FileNotFoundException e) {
            throw new DALException("Error locating file", e);
        } catch (IOException e) {
            throw new DALException("Error writing to disk", e);
        } finally {
            if (oOS != null) {
                try {
                    oOS.close();
                } catch (IOException e) {
                    throw new DALException("Unable to close ObjectStream!", e);
                }
            }
        }
    }

}
