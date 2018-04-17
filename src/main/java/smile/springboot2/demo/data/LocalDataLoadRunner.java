package smile.springboot2.demo.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import smile.springboot2.demo.service.UserService;

/**
 * @Package: smile.springboot2.demo.data
 * @Description: 提前加载数据
 * @date: 2018/4/17 下午9:39
 * @author: liuxin
 */
@Component
public class LocalDataLoadRunner implements CommandLineRunner {
    @Autowired
    UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        userService.addUser("1", new User(12, "玩家1"));
        userService.addUser("2", new User(12, "玩家2"));
        userService.addUser("3", new User(12, "玩家3"));
        userService.addUser("4", new User(12, "玩家4"));
        userService.addUser("5", new User(12, "玩家5"));

    }
}
