package smile.springboot2.demo.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import smile.springboot2.demo.data.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @Package: smile.springboot2.demo
 * @Description:
 * @date: 2018/4/17 下午8:43
 * @author: liuxin
 */
@Service
public class UserService {

    private Map<String, User> userCache = new ConcurrentHashMap<>();

    public void addUser(String uid,User user){
        userCache.put(uid,user);
    }

    public Mono<User> findUserById(String uid) {
        User undine = userCache.getOrDefault(uid, new User(0, "默认角色"));
        Mono<User> userMono=Mono.just(undine);
        return userMono;
    }

    public Flux<User> findUserAsList() {
        List<User>users=new ArrayList<>();
        Set<Map.Entry<String, User>> entries = userCache.entrySet();
        for (Map.Entry<String,User> entry:entries){
            User value = entry.getValue();
            users.add(value);
        }
        Stream<User> userStream = users.stream();
        Flux<User> userFlux = Flux.fromStream(userStream);
        return userFlux;
    }
}


