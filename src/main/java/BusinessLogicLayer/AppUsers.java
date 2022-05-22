package BusinessLogicLayer;

import DataAccessLayer.Serializator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppUsers implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private List<User> users;

    public AppUsers(){
        users = new ArrayList<>();
    }

    public boolean addUser(User newUser)
    {
        for(User user: users)
        {
            if(user.getUsername().equals(newUser.getUsername()))
            {
                return false;
            }
        }
        users.add(newUser);
        return true;
    }

    public boolean doesUserExit(User user)
    {
        for(User usr: users)
        {
            if(usr.equals(user))
                return true;
        }
        return false;
    }

    public void saveUsers()
    {
        Serializator.serialize(this, "users.dat");
    }

    @Override
    public String toString() {
        return "AppUsers{" +
                "users=" + users +
                '}';
    }
}
