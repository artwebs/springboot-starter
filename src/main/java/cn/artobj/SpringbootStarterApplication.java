package cn.artobj;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.alibaba.druid.pool.DruidDataSource;

import cn.artdev.springboot.DB;
import cn.artdev.springboot.HomeWidget;
import cn.artdev.springboot.SysConver;
import cn.artdev.springboot.SysFreemarkerView;


@ServletComponentScan
@ComponentScan(basePackages={"cn.artdev.springboot","cn.artobj.home"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DB.class})
public class SpringbootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootStarterApplication.class, args);
	}
	
	@Configuration
	static class MvcConfigurer implements WebMvcConfigurer{
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			// TODO Auto-generated method stub
			registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("/manage/**")
			.excludePathPatterns("/manage/login","/manage/code");
		}

		@Override
		public void configurePathMatch(PathMatchConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addFormatters(FormatterRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureViewResolvers(ViewResolverRegistry registry) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Validator getValidator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public MessageCodesResolver getMessageCodesResolver() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	@Autowired
    private Environment env;
	
	/***
	 * 初始化数据库
	 * @return
	 */
	
	@Bean
    public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));//用户名
        dataSource.setPassword(env.getProperty("spring.datasource.password"));//密码
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(20);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(60000);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestWhileIdle(true);
        dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }
	
	
	/***
	 * 自定义视图及方法
	 * @param resolver
	 * @return
	 */
	@Bean  
	public CommandLineRunner customFreemarker(FreeMarkerViewResolver resolver) {  
	    return new CommandLineRunner() {  
	        @Override  
	        public void run(String... strings) throws Exception {  
	        	//增加视图  
	        	resolver.setViewClass(SysFreemarkerView.class);
	            //添加自定义解析器  
	            Map map = resolver.getAttributesMap();  
	            map.put("conver", new SysConver());  
//	            map.put("homeWidget", new HomeWidget());  
	        }  
	    };  
	}  
	
}
