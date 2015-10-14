package com.mikes.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.support.RequestDataValueProcessor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mikes.editorsupport.EditorRegestrar;
@EnableWebSecurity
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.mikes")
public class SpringContextConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public CommonsMultipartResolver multipartResolver() {

		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setMaxUploadSize(100000);
		return commonsMultipartResolver;
	}
	/*
	 * Configure View Resolver 
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	/*
	 * Configure MessageSource to provide internationalized messages
	 * 
	 */
	
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
	    return messageSource;
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
	 * 
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}
	@Bean
	public static  CustomEditorConfigurer customEditorConfigurer() {
		CustomEditorConfigurer customEditorConfigurer  = new CustomEditorConfigurer();
		customEditorConfigurer.setPropertyEditorRegistrars(getPropertyEditorRegistrar());
		return customEditorConfigurer;
	}
	private static PropertyEditorRegistrar[] getPropertyEditorRegistrar() {
		List<PropertyEditorRegistrar> propertyEditors = new ArrayList<PropertyEditorRegistrar>();
		propertyEditors.add(new EditorRegestrar());
		return propertyEditors.toArray(new PropertyEditorRegistrar[propertyEditors.size()]);
	}
	@Bean 
    public RequestDataValueProcessor requestDataValueProcessor() {
        return new CsrfRequestDataValueProcessor(); 
    } 
}