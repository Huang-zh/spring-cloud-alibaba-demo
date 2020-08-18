package com.huang.geteway.filters;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Huang.zh
 * @date 2020/8/18 17:15
 * @Description: 全局鉴权过滤器
 */
@Component
public class GlobalAuthorationFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isBlank(token)){
            //鉴权失败，未携带token
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //继续执行调用链
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        //最优先被Spring加载，优先级数字越小，优先级越高
        return 0;
    }
}
