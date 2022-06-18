# springCloud-learn1
SpringCloud learned from https://www.bilibili.com/video/BV1LQ4y127n4

stack:

- 注册中心: eureka -> nacos
- 配置中心: nacos
- 远程调用: restTemplate -> feign
- 网关: spring-cloud-gateway
- 数据库: mysql
- ORM: mybatis

remark:

- 最近几个版本中, spring 已经移除了 ribbon, 使用 loadbalancer 实现负载均衡
  - ribbon 自动添加在相关依赖内, loadbalancer 需要手动添加
- eureka 等 netflix 的组件已过时

## notes:

### 网关

- 身份认证, 权限校验
- 服务路由, 负载均衡
- 请求限流

#### spring-cloud-gateway:

配置项:

```yaml
spring:
    gateway:
      routes:
        - id: # 路由唯一标识
          uri: # 路由目标地址 -- http 固定地址, 或者 lb 根据服务名负载均衡, e.g. lb://service-name
          predicates: # 路由条件, 支持 Path, Header, Method, Query等
          filters: # 路由过滤器, 对请求/响应进行处理
      default-filters: # 默认过滤器, 对请求/响应进行处理
```

全局过滤器: implements GlobalFilter

```java
@Order(1)
@Component
public class DemoAuthFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> params = request.getQueryParams();

        String auth = params.getFirst("auth");

        if ("admin".equals(auth)) {
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

}
```

过滤器执行顺序: 

1. 根据 order 排值序
2. order 值一样时, default -> route -> global

网关CORS配置:
```yaml
spring:
  cloud:
    gateway:
      globalcors: # 全局的跨域处理
        add-to-simple-url-handler-mapping: true # 解决options请求被拦截问题
        corsConfigurations:
          '[/**]':
            allowedOrigins: # 允许哪些网站的跨域请求
              - "http://localhost:8090"
              - "http://www.leyou.com"
            allowedMethods: # 允许的跨域ajax的请求方式
              - "GET"
              - "POST"
              - "DELETE"
              - "PUT"
              - "OPTIONS"
            allowedHeaders: "*" # 允许在请求中携带的头信息
            allowCredentials: true # 是否允许携带cookie
            maxAge: 360000 # 这次跨域检测的有效期
```

- allowCreditentials: true
  - 允许携带cookie
  - 与 allowedOrigins: * 冲突
    - 可以使用 allowedOriginPatterns: * 解决该问题

- [Route Predicate Factories](https://cloud.spring.io/spring-cloud-gateway/multi/multi_gateway-request-predicates-factories.html)
- [GatewayFilter Factories](https://cloud.spring.io/spring-cloud-gateway/multi/multi__gatewayfilter_factories.html)