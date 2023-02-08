package com.example.MPM;

import com.example.MPM.contract.MyPagePath;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//-------------------------------------------------------------------------------------------------------------
//Версия для разработки

@SpringBootApplication
public class MpmApplication implements WebMvcConfigurer{

	MyPagePath path = new MyPagePath(); //объявление словаря путей HTML страниц

	public static void main(String[] args) { //точка входа для запуска приложения
		SpringApplication.run(MpmApplication.class, args);
	}


	@Override //добавление контроллеров для простых листингов без логики
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(path.MAIN_PAGE);
		registry.addViewController("/login").setViewName(path.LOGIN);
	}
}

//-------------------------------------------------------------------------------------------------------------
//Версия для развёртывания

//@SpringBootApplication
//public class MpmApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
//	public MpmApplication() {
//	}
//
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return super.configure(builder);
//	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("index");
//		registry.addViewController("/login").setViewName("login");
//	}
//
//}