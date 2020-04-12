//package com.zt.ebook.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())//添加拦截器
//                .addPathPatterns("CommentController/**","SecondCommentController/**","UserController/alter/userInfo","CollectionController/**") //拦截所有请求
//                .excludePathPatterns("UserController/user/register","UserController/user/login");//对应的不拦截的请求
//    }
//}