package Ejb;

import models.User;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Remote
public class UserBean {

    @PersistenceContext
    private EntityManager em;


    @SuppressWarnings({"unchecked"})
    public User authenticate(User user) throws Exception{

        if (user == null || StringUtils.isBlank(user.getName()) || StringUtils.isBlank(user.getPassword()))
            throw new Exception("Invalid user details.");

        List<User> users = em.createNamedQuery(User.USER_FIND_BY_NAME_PWD)
                .setParameter("name", user.getName())
                .setParameter("pwd", user.getPassword())
                .getResultList();

        if (users.isEmpty())
            throw new Exception("Invalid username or password.");

        return users.get(0);

    }
}
