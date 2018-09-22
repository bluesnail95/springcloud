package gdut.ff;

import gdut.ff.domain.User;
import gdut.ff.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    public void test() {
        User user = new User();
        user.setUsername("liuffei");
        user.setPassword("123");
        userService.insert(user);

        User loginUser = userService.findOneUser("liuffei","123");
        System.out.println(loginUser);
    }

}
