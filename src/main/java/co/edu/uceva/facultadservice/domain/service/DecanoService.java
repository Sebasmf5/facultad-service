package co.edu.uceva.facultadservice.domain.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name="usuario-service")
public interface DecanoService {
    @GetMapping("/api/v1/usuario-service/usuarios/decanos")
    public ResponseEntity<Map<String, Object>> getDecano();
}

