spring:
  profiles:
    active: dev

# Nacos集群支持配置, 也可以将下面的配置移到统一的 feign-xxx.yml 中去
hqwx-${_projectName}:
  ribbon:
    NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule

# create time: ${_currentTime} 
# 注: 请根据项目实际情况对配置项进行修改，可以手动删除原有的 application.yml、application.properties 等文件

---
spring:
  config:
    activate:
      on-profile: dev
  application:
    name: hqwx-${_projectName}
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      config:
        server-addr: 123.56.3.61:8848
        namespace: 0322cc9b-b9a4-4529-b996-1466f2a8c500
        file-extension: yml
        shared-configs:
          - actuator-${r"${"}spring.profiles.active${r"}"}.yml
          - service-discovery-${r"${"}spring.profiles.active${r"}"}.yml
          # - kafka-${r"${"}spring.profiles.active${r"}"}.yml
          - feign-${r"${"}spring.profiles.active${r"}"}.yml
          - redis-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
          - mysql-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
logging:
  config: classpath:log4j2-dev.xml

---
spring:
  config:
    activate:
      on-profile: test
  application:
    name: hqwx-${_projectName}
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      config:
        server-addr: 123.56.3.61:8848
        namespace: 0ad9485c-a5ce-41a8-b1f6-3a3b0f5108c9
        file-extension: yml
        shared-configs:
          - actuator-${r"${"}spring.profiles.active${r"}"}.yml
          - service-discovery-${r"${"}spring.profiles.active${r"}"}.yml
          # - kafka-${r"${"}spring.profiles.active${r"}"}.yml
          - feign-${r"${"}spring.profiles.active${r"}"}.yml
          - redis-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
          - mysql-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
logging:
  config: classpath:log4j2-dev.xml

---
spring:
  config:
    activate:
      on-profile: uat
  application:
    name: hqwx-${_projectName}
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      config:
        server-addr: hqwx-nacos-pro.hq-edu.self.yydevops.com:8848
        namespace: d6c72197-b56b-4d8a-a0b3-93f4b9cf3459
        file-extension: yml
        shared-configs:
          - actuator-${r"${"}spring.profiles.active${r"}"}.yml
          - service-discovery-${r"${"}spring.profiles.active${r"}"}.yml
          # - kafka-${r"${"}spring.profiles.active${r"}"}.yml
          - feign-${r"${"}spring.profiles.active${r"}"}.yml
          - redis-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
          - mysql-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
logging:
  config: classpath:log4j2-prod.xml

---
spring:
  config:
    activate:
      on-profile: prod
  application:
    name: hqwx-${_projectName}
  main:
    allow-bean-definition-overriding: true
  cloud:
    nacos:
      config:
        server-addr: hqwx-nacos-pro.hq-edu.self.yydevops.com:8848
        namespace: 79e9c76b-3093-4352-8c1f-a6bede3b9f97
        file-extension: yml
        shared-configs:
          - actuator-${r"${"}spring.profiles.active${r"}"}.yml
          - service-discovery-${r"${"}spring.profiles.active${r"}"}.yml
          # - kafka-${r"${"}spring.profiles.active${r"}"}.yml
          - feign-${r"${"}spring.profiles.active${r"}"}.yml
          - redis-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
          - mysql-${r"${"}spring.profiles.active${r"}"}-${r"${"}app.zoneId:default${r"}"}.yml
logging:
  config: classpath:log4j2-prod.xml
