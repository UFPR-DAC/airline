package com.example.reserva_service.client;

import com.example.reserva_service.client.dto.VooDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class VooClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${voo.service.url}")
    private String vooServiceUrl;

    public Optional<VooDTO> buscarVooPorCodigo(String codigoVoo) {
        try {
            // Faz a chamada GET para: http://voo-service:8084/voos/{codigoVoo}
            String url = vooServiceUrl + "/" + codigoVoo;
            VooDTO voo = restTemplate.getForObject(url, VooDTO.class);
            return Optional.ofNullable(voo);
        } catch (HttpClientErrorException.NotFound e) {
            // Se o voo não for encontrado (404), retorna um Optional vazio
            return Optional.empty();
        }
    }
}