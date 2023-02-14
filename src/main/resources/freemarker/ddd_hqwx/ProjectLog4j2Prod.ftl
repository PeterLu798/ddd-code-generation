<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="30">

    <!-- 控制台输出 -->
    <Properties>
        <Property name="Log_Pattern" value="%d{yyyy-MM-dd HH:mm:ss,SSS} [%X{ip}] [%thread] [%X{traceId}] %-5level %logger{50}:%L - %msg%n" />
        <Property name="log_file_path">/data/weblog/webapp/hqwx-${_projectName}</Property>
    </Properties>

    <!-- 控制台输出 -->
    <Appenders>
        <!-- 按照每天生成日志文件 -->
        <RollingRandomAccessFile name="ROLL_FILE_ROOT" fileName="${r"${"}log_file_path${r"}"}/service.log"
                      filePattern="${r"${"}log_file_path${r"}"}/service.%d{yyyy-MM-dd}-%i.log"
                      append="false">
            <PatternLayout pattern="${r"${"}Log_Pattern${r"}"}"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="500000KB" />
                <TimeBasedTriggeringPolicy modulate="true" />
            </Policies>
            <DefaultRolloverStrategy max="1000"/>
        </RollingRandomAccessFile>
    </Appenders>
    <Loggers>
        <AsyncLogger name="com.ibatis" level="debug">
            <AppenderRef ref="ROLL_FILE_ROOT" />
            <AppenderRef ref="STDOUT" />
        </AsyncLogger>

        <AsyncLogger name="com.mysql" level="WARN" additivity="false">
            <AppenderRef ref="ROLL_FILE_ROOT" />
        </AsyncLogger>
        <AsyncLogger name="com.duowan.udb" level="INFO" >
            <AppenderRef ref="ROLL_FILE_APP" />
        </AsyncLogger>
        <!-- <AsyncLogger name="java.sql.Connection" level="DEBUG" /> -->
        <!-- <AsyncLogger name="java.sql.PreparedStatement" level="DEBUG" /> -->
        <!-- udb应用日志 -->
        <AsyncLogger name="com.hqwx" level="INFO" />
        <AsyncLogger name="cn.huanju" level="INFO"  />

        <AsyncLogger name="com.duowan.udb" level="INFO" />
        <Root level="INFO">
            <AppenderRef ref="ROLL_FILE_ROOT" />
        </Root>
    </Loggers>
</Configuration>
