package com.example.voo_service.service;

import com.example.voo_service.model.Voo;
import com.example.voo_service.repository.VooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VooService {
    @Autowired
    private VooRepository vooRepository;

    public String gerarCodigoVoo() {
        long count = vooRepository.count() + 1;
        return String.format("TAD%04d", count);
    }

    public Voo salvarVoo(Voo voo) {
        voo.setCodigo(gerarCodigoVoo());
        return vooRepository.save(voo);
    }
}
