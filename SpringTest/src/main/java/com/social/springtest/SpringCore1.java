package com.social.springtest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

public class SpringCore1 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Company company = context.getBean(Company.class);
        System.out.println(company);
        company = context.getBean(Company.class);
        System.out.println(company);

       // Address address = context.getBean(Address.class);
    }
}

@Component
class Company
{
    //field based DI
    @Autowired
    @Qualifier("addressIndia")
    private  Address address;

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Component
class Address
{
    private String name;

}

@Configuration
@ComponentScan(basePackages = "com.social.springtest")
class Config
{
    @Bean
    @Primary
    @Order(2)
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
     Address addressIndia()
    {
        System.out.println("Address India bean is created .... ");
        return new Address("India");
    }

    @Bean
    @Order(1)
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    Address addressUSA()
    {
        System.out.println("AddressUSA bean is created .... ");
        return new Address("USA");
    }

    @Bean()
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
     Company company()
    {
        System.out.println("Company bean is created .... ");
        return new Company();
    }


}