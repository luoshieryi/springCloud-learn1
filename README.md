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
- eureka 等 netflix 的组件已过时