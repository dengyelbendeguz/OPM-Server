package hu.opm.opm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
public class OpmServerApplication {
    public static void main(String[] args) throws UnsupportedEncodingException {
        SpringApplication.run(OpmServerApplication.class, args);
    }
}
