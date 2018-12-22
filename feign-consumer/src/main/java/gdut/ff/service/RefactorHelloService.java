package gdut.ff.service;

import gdut.ff.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends HelloService{

}
