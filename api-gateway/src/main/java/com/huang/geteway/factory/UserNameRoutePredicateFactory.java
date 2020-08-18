package com.huang.geteway.factory;

import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Huang.zh
 * @date 2020/8/18 16:26
 * @Description: service-order订单微服务实例鉴权工厂
 */
@Component
public class UserNameRoutePredicateFactory extends AbstractRoutePredicateFactory<UserNameRoutePredicateFactory.Config> {

    public UserNameRoutePredicateFactory() {
        super(UserNameRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            String userName = serverWebExchange.getRequest().getQueryParams().getFirst("userName");
            if(StringUtils.isNotBlank(userName)){
                return userName.equals(config.getUserName());
            }
            return false;
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userName");
    }

    /**
     * @Author Huang.zh
     * @Description 断言配置类，内部成员属性就是定义在yml中用于鉴权的参数名，注意：配置类必须为静态类
     * @Date 2020/8/18 17:05
     */
    @Data
    public static class Config{
        private String userName;
    }
}
