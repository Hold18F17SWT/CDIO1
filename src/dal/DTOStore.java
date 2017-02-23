package dal;

import dto.UserDTO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DTOStore implements Serializable {
    private static final long serialVersionUID = 1337081329834096215L;
    private Map<Integer, UserDTO> users = new HashMap();

    public DTOStore() {
    }

    public Map<Integer, UserDTO> getUsers() {
        return this.users;
    }

    public UserDTO getUser(int userID) {
        return this.users.get(userID);
    }

    public void addUser(UserDTO user) throws UserAlreadyExistsException {
        if (!this.users.containsKey(user.getUserId()))
            this.users.put(user.getUserId(), user);
        else
            throw new UserAlreadyExistsException();
    }

    public void setUsers(Map<Integer, UserDTO> users) {
        this.users = users;
    }

    public class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException() {

        }
    }
}
