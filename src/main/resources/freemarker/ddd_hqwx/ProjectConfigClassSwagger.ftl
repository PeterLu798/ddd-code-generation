package ${_configCommand.packageMainPath}.${_configClass.configClassPackagePath}.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置, 使用 knife4j
 * create time: ${_currentTime}
*/
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo())
                .groupName("Restful").select()
                .apis(RequestHandlerSelectors.basePackage("${_configCommand.packageMainPath}.api")).paths(PathSelectors.any()).build();

        List<Parameter> paramList = new ArrayList<Parameter>();
        // jwt-token 请求头授权参数
        ParameterBuilder tokenParam1 = new ParameterBuilder();
        tokenParam1.name("jwt-token")
                .description("jwt-token 用于授权")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true);
        paramList.add(tokenParam1.build());

        docket.globalOperationParameters(paramList);

        return docket;
    }

    private ApiInfo buildApiInfo() {
        String groupName = "请修改服务名称";

        StringBuffer serviceDesc = new StringBuffer(200);
        serviceDesc.append(groupName).append("-API文档");
        serviceDesc.append("<br/><br/> 调用方式说明");
        serviceDesc.append("<br/> http://xxxapi.hqwx.com/xxx-common/ (测试环境前缀, 需要配置host), /xxx-common 是该服务名的前缀, 添加在接口地址前面");
        serviceDesc.append("<br/> http://xxxapi.hqwx.com/xxx-common/ (线上环境前缀, 正式)");

        return new ApiInfoBuilder().title(groupName + "-API文档")
                .description(serviceDesc.toString())
                .contact(new Contact("环球网校-共享业务平台", "adminapi.hqwx.com", ""))
                .version("1.0").build();
    }
}
