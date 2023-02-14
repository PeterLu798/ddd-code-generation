<#assign __projectGroupId = "" />
<#assign __projectArtifactId = "" />
<#assign __projectName = "" />
<#assign __projectDesc = "" />
<#assign __projectJdkVersion = "" />
<#if _pomParamMap?exists && _pomParamMap?size gt 0>
    <#assign __projectGroupId = _pomParamMap["projectGroupId"] />
    <#assign __projectArtifactId = _pomParamMap["projectArtifactId"] />
    <#assign __projectName = _pomParamMap["projectName"] />
    <#assign __projectDesc = _pomParamMap["projectDesc"] />
    <#assign __projectJdkVersion = _pomParamMap["projectJdkVersion"] />
</#if>
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>cn.huanju.edu</groupId>
        <artifactId>edu-base-parent</artifactId>
        <version>1.0.6-SNAPSHOT</version>
    </parent>

    <groupId>${__projectGroupId}</groupId>
    <artifactId>${__projectArtifactId}</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>${__projectName}</name>
    <description>${__projectDesc}</description>

    <!-- create time: ${_currentTime} -->

    <properties>
        <java.version>${__projectJdkVersion}</java.version>
        <maven.compiler.source>${__projectJdkVersion}</maven.compiler.source>
        <maven.compiler.target>${__projectJdkVersion}</maven.compiler.target>

        <reflections.version>0.9.12</reflections.version>
        <jwt.version>0.9.0</jwt.version>
        <baomidou-mybatis-plus.version>3.5.0</baomidou-mybatis-plus.version>
        <baomidou-dynamic-datasource.version>3.5.0</baomidou-dynamic-datasource.version>
        <nacos-client.version>1.3.2</nacos-client.version>
        <metrics.version>2.6.9</metrics.version>
        <easypoi.version>4.1.3</easypoi.version>

        <jetcache.version>2.5.14</jetcache.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>2.0.3</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${r"${"}baomidou-mybatis-plus.version${r"}"}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>${r"${"}baomidou-dynamic-datasource.version${r"}"}</version>
        </dependency>

        <dependency>
            <groupId>cn.afterturn</groupId>
            <artifactId>easypoi-spring-boot-starter</artifactId>
            <version>${r"${"}easypoi.version${r"}"}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-loadbalancer</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-httpclient</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- clickhouse-jdbc 这个驱动版本太老了，我懒得换了 -->
        <!--
        <dependency>
            <groupId>ru.yandex.clickhouse</groupId>
            <artifactId>clickhouse-jdbc</artifactId>
            <version>0.2.4</version>
        </dependency>
        -->

        <dependency>
            <groupId>cn.huanju.edu</groupId>
            <artifactId>edu-base-model</artifactId>
        </dependency>
        <dependency>
            <groupId>cn.huanju.edu</groupId>
            <artifactId>edu-base</artifactId>
        </dependency>

        <dependency>
            <groupId>cn.huanju.edu100</groupId>
            <artifactId>bizplat-base</artifactId>
            <version>1.0.4-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>cn.huanju.edu</groupId>
            <artifactId>edu-rpc-swagger</artifactId>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>${r"${"}baomidou-mybatis-plus.version${r"}"}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>${r"${"}baomidou-dynamic-datasource.version${r"}"}</version>
        </dependency>

        <!-- kafka 默认不开启
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-kafka</artifactId>
        </dependency>
        -->

        <dependency>
            <groupId>org.reflections</groupId>
            <artifactId>reflections</artifactId>
            <version>${r"${"}reflections.version${r"}"}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <!-- local cache 20200819 -->

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>com.duowan</groupId>
            <artifactId>hawk.metrics.client</artifactId>
            <version>${r"${"}metrics.version${r"}"}</version>
            <exclusions>
                <exclusion>
                    <artifactId>log4j</artifactId>
                    <groupId>log4j</groupId>
                </exclusion>
                <exclusion>
                    <artifactId>slf4j-log4j12</artifactId>
                    <groupId>org.slf4j</groupId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>com.hqwx</groupId>
            <artifactId>message-client</artifactId>
            <version>1.0.5-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>com.hqwx</groupId>
            <artifactId>common-security</artifactId>
            <version>0.0.1-SNAPSHOT</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-web</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-data-redis</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>io.jsonwebtoken</groupId>
                    <artifactId>jjwt</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>com.hqwx</groupId>
            <artifactId>thrift-client</artifactId>
            <version>3.0.0.5-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.hqwx</groupId>
            <artifactId>thrift-nacos-discovery</artifactId>
            <version>1.0.2-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.hqwx</groupId>
            <artifactId>grpc-common</artifactId>
            <version>1.4.0.2-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>com.alicp.jetcache</groupId>
            <artifactId>jetcache-starter-redis</artifactId>
            <version>${r"${"}jetcache.version${r"}"}</version>
        </dependency>

        <!-- xxl-job -->
        <!--
        <dependency>
            <groupId>com.xuxueli</groupId>
            <artifactId>xxl-job-core</artifactId>
            <version>2.1.1</version>
        </dependency>
        -->

        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

            <!--
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${__projectJdkVersion}</source>
                    <target>${__projectJdkVersion}</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            -->
        </plugins>
    </build>

</project>
