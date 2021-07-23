package com.mamta.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mamta.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class OnlineFood {

  public static void main(String[] args) {
    SpringApplication.run(OnlineFood.class, args);

  }

}
