package com.example.voo_service.controller;

import com.example.voo_service.dto.VooDTO;
import com.example.voo_service.model.Aeroporto;
import com.example.voo_service.model.Estado;
import com.example.voo_service.model.Voo;
import com.example.voo_service.repository.AeroportoRepository;
import com.example.voo_service.repository.VooRepository;
import com.example.voo_service.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voos")
public class VooController {
    @Autowired
    private VooRepository vooRepository;
    @Autowired
    private AeroportoRepository aeroportoRepository;
    @Autowired
    private VooService vooService;

    @GetMapping
    public List<Voo> listar() {
        return vooRepository.findAll();
    }

    @PostMapping
    public Voo inserir(@RequestBody VooDTO input) {
        Aeroporto aeroportoOrigem = aeroportoRepository.findById(input.getCodigoAeroportoOrigem())
                .orElseThrow(() -> new RuntimeException("Aeroporto de origem não encontrado"));
        Aeroporto aeroportoDestino = aeroportoRepository.findById(input.getCodigoAeroportoDestino())
                .orElseThrow(() -> new RuntimeException("Aeroporto de destino não encontrado"));
        Voo voo = new Voo();
        voo.setCodigo(input.getCodigo());
        voo.setData(input.getData());
        voo.setValorPassagem(input.getValorPassagem());
        voo.setQuantidadePoltronasTotal(input.getQuantidadePoltronasTotal());
        voo.setQuantidadePoltronasOcupadas(input.getQuantidadePoltronasOcupadas());
        voo.setEstado(Estado.CONFIRMADO);
        voo.setAeroportoOrigem(aeroportoOrigem);
        voo.setAeroportoDestino(aeroportoDestino);
        return vooService.salvarVoo(voo);
    }
}
