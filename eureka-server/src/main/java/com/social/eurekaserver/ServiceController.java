package com.social.profile.eurekaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ServiceController {

    @Autowired
    private DiscoveryClient discoveryClient;

    //http://localhost:8761/service-instances/EUREKASERVER
    @GetMapping(value = "/service-instances/{applicationName}", produces = "application/json")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName)
    {

        return this.discoveryClient.getInstances(applicationName);
    }
}
