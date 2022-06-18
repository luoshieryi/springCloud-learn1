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
```

- [更多路由条件 predicates](https://cloud.spring.io/spring-cloud-gateway/multi/multi_gateway-request-predicates-factories.html)