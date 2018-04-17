package smile.springboot2.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import smile.springboot2.demo.data.User;
import smile.springboot2.demo.service.UserService;

/**
 * 路由信息
 */
@Configuration
public class Routes {

    /**
     * 演示GET请求,单路由
     * @param userService
     * @return
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routersFunction(UserService userService) {
        return RouterFunctions.route(RequestPredicates.GET("/api/users/v1"), request -> {
            Flux<User> userAsList = userService.findUserAsList();
            return ServerResponse.ok().body(userAsList, User.class);
        });
    }

    /**
     * 绑定多个路由
     * GET和POST请求
     * @param userService
     * @return
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> routerFunction(UserService userService) {
        return RouterFunctions.route(RequestPredicates.GET("/api/users/v2"), request -> {
            Flux<User> userAsList = userService.findUserAsList();
            return ServerResponse.ok().body(userAsList, User.class);
        }).and(RouterFunctions.route(RequestPredicates.POST("/api/user/{uid}"),request->{
            String uid = request.pathVariable("uid");
            Mono<User> user = userService.findUserById(uid);
            return ServerResponse.ok().body(user,User.class);
        }));
    }
}

