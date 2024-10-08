package com.social.user.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer
{

	private static final long MAX_AGE_SECS = 3600;

	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
	{
	
		final ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		final List<MediaType> list = new ArrayList<>();
		list.add(MediaType.IMAGE_JPEG);
		list.add(MediaType.APPLICATION_OCTET_STREAM);
		arrayHttpMessageConverter.setSupportedMediaTypes(list);
		converters.add(arrayHttpMessageConverter);
	
		WebMvcConfigurer.super.configureMessageConverters(converters);
	}*/

	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
			.maxAge(MAX_AGE_SECS);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
	{
		// argumentResolvers.add(new SpecificationArgumentResolver());
	}

	/*@Bean
	public FilterRegistrationBean authFilter()
	{
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setOrder(1);
		registration.setFilter(ServiceContextFilter);
	
		return registration;
	}*/

	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("index");
	}

	@Bean
	public ClassLoaderTemplateResolver templateResolver()
	{

		var templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine()
	{
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver()
	{
		var viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

}
