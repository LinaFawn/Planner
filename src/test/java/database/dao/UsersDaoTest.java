package database.dao;

import com.example.demo10.database.dao.RequestDao;
import com.example.demo10.database.model.Requests;
import com.example.demo10.database.model.Users;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

class UsersDaoTest extends RequestDao {
    @Test
    void selectByLoginTest() {
        Users user = selectUserByLogin("kvy");
        System.out.println(user);
    }

    @Test
    void selectAllUsersTest() {
        List<Users> users = selectAllUsers();
        System.out.println(users);
    }

    @Test
    void selectRequstsByUserIdTest() {
        List<Requests> requestsList = selectRequestsByUserId(7);

        for(Requests i: requestsList)
            System.out.println(i);
    }

    @Test
    void selectUserByRequestIdTest() {
       Users user = selectUserByRequestId(3);
        System.out.println(user);
    }

    @Test
    void findUserRoleTest() {
        String str = selectUserRoleByLogin("kvy");
        System.out.println(str);
    }

    @Test
    void updateRequestTest() {
        Requests requests = selectRequestById(4);
        requests.setStatus("started");
        requests.setComment("new Comment");
        System.out.println(requests);
        updateRequest(requests);
    }

    @Test
    void insertRequest() {
        Requests request = new Requests(
                54,
                "new",
                "sdsd",
                "sdsd",
                "sdsd",
                "sdsd",
                "sdsd",
                "sdsd",
                Date.valueOf("2015-03-31"),//date
                45,
                10000,
                "sdsd",
                null,
                null
        );
        int t = insertRequest(request);
        assertEquals(1, t);
    }

    @Test
    void insertRequestWithoudField() {
        Requests request = new Requests(
                23,
                "sdsd",
                "sdsd",
                "sdsd",
                "sdsd",
                null,
                "sdsd",
                "sdsd",
                Date.valueOf("2015-03-31"),//date
                45,
                10000,
                "sdsd",
                null,
                null
        );
        int t = insertRequest(request);
        assertEquals(0, t);
    }

    @Test
    void selectRequestBydateTest() {
        List<Requests> requestsList = selectRequestsByDate(Date.valueOf("2015-03-31"), Date.valueOf("2015-03-31"));

        for(Requests i: requestsList)
            System.out.println(i);
    }

    @Test
    public void jdbcallTest(){
        Connection conn = getConnection(); // Использование параметров для получения объекта подключения
        System.out.println ("«Соединитесь успешно!»");
        System.out.println(conn);
    }
}