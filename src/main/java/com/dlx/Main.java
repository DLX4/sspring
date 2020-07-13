package com.dlx;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public Main() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ApplicationContext applicationContext = null;
        try {
            applicationContext = new ApplicationContext("com.dlx");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        applicationContext.close();

        BeanFactory beanFactory = new BeanFactory();
        beanFactory.addPostProcessor(new CustomPostProcessor());
        beanFactory.instantiate("com.dlx");
        beanFactory.populateProperties();
        beanFactory.injectBeanNames();
        beanFactory.initializeBeans();


        ProductService productService = (ProductService) beanFactory.getBean("ProductService");
        PromotionsService promotionsService = productService.getPromotionsService();
        System.out.println("BeanName " + promotionsService.getBeanName());

        System.out.println(promotionsService);
    }

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        new Main();
    }

    void testContext() throws ReflectiveOperationException {
        ApplicationContext applicationContext = new ApplicationContext("com.kciray");
        applicationContext.close();
    }

}
