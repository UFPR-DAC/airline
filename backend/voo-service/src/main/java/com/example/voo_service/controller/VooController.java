package com.example.voo_service.controller;

import com.example.voo_service.dto.*;
import com.example.voo_service.model.Aeroporto;
import com.example.voo_service.model.Estado;
import com.example.voo_service.model.Voo;
import com.example.voo_service.repository.AeroportoRepository;
import com.example.voo_service.repository.VooRepository;
import com.example.voo_service.service.VooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

    @GetMapping("/{codigoVoo}")
    public ResponseEntity<Voo> buscarPorCodigo(@PathVariable("codigoVoo") String codigoVoo) {
        if (codigoVoo == null || codigoVoo.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return vooRepository.findById(codigoVoo)
                .map(voo -> ResponseEntity.ok(voo))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(params = {"inicio", "fim"})
    public ResponseEntity<?> listarProximosVoosPorData(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
                if (inicio == null || fim == null || inicio.isAfter(fim)) {
                    return ResponseEntity.badRequest().body("Intervalo de pesquisa inválido");
                }
        OffsetDateTime inicioDateTime = inicio.atStartOfDay().atOffset(ZoneOffset.of("-03:00"));
                OffsetDateTime fimDateTime = fim.atTime(LocalTime.MAX).atOffset(ZoneOffset.of("-03:00"));
                List<Voo> voos = vooRepository.findByDataBetween(inicioDateTime, fimDateTime);
                if (voos.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum voo encontrado para essas datas");
                }
                return ResponseEntity.ok(voos);
    }

    @GetMapping(params = {"data", "origem", "destino"})
    public ResponseEntity<?> listarProximosVoosPorOrigemDestino(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam String origem,
            @RequestParam String destino) {
        if (data == null) {
            return ResponseEntity.badRequest().body("Intervalo de pesquisa inválido");
        }
        OffsetDateTime inicio = data.atStartOfDay(ZoneOffset.ofHours(-3)).toOffsetDateTime();
        OffsetDateTime fim = data.plusDays(1).atStartOfDay(ZoneOffset.ofHours(-3)).toOffsetDateTime().minusNanos(1);
        List<Voo> voos = vooRepository.findByDataBetweenAndAeroportoOrigem_CodigoAndAeroportoDestino_Codigo(inicio, fim, origem, destino);
        if (voos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum voo encontrado para essas datas");
        }
        List<VooDTO> vooDTOs = voos.stream()
                .map(this::toVooDTO)
                .toList();

        VoosPorRotaDTO resposta = new VoosPorRotaDTO();
        resposta.setData(inicio);
        resposta.setOrigem(origem);
        resposta.setDestino(destino);
        resposta.setVoos(vooDTOs);

        return ResponseEntity.ok(resposta);
    }

    @PatchMapping("/{codigoVoo}/estado")
    public ResponseEntity<?> alterarEstadoVoo(@PathVariable("codigoVoo") String codigoVoo, @RequestBody AlterarEstadoVooDTO alterarEstadoVooDTO) {
        return vooRepository.findById(codigoVoo)
                .map(voo -> {
                    try {
                        Estado novoEstado = Estado.valueOf(alterarEstadoVooDTO.getEstado().toUpperCase());
                        voo.setEstado(novoEstado);
                        vooRepository.save(voo);

                        VooRespostaDTO resposta = new VooRespostaDTO(
                                voo.getCodigo(),
                                voo.getData(),
                                voo.getValorPassagem(),
                                voo.getQuantidadePoltronasTotal(),
                                voo.getQuantidadePoltronasOcupadas(),
                                voo.getEstado(),
                                voo.getAeroportoOrigem().getCodigo(),
                                voo.getAeroportoDestino().getCodigo()
                        );
                        return ResponseEntity.ok(resposta);
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body("Erro ao atualizar estado do voo para " + alterarEstadoVooDTO.getEstado());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private AeroportoDTO toAeroportoDTO(Aeroporto aeroporto) {
        if (aeroporto == null) return null;
        return new AeroportoDTO(
                aeroporto.getCodigo(),
                aeroporto.getNome(),
                aeroporto.getCidade(),
                aeroporto.getUf()
        );
    }

    private VooDTO toVooDTO(Voo voo) {
        VooDTO dto = new VooDTO();
        dto.setCodigo(voo.getCodigo());
        dto.setData(voo.getData());
        dto.setValorPassagem(voo.getValorPassagem());
        dto.setQuantidadePoltronasTotal(voo.getQuantidadePoltronasTotal());
        dto.setQuantidadePoltronasOcupadas(voo.getQuantidadePoltronasOcupadas());
        dto.setEstado(voo.getEstado());
        dto.setCodigoAeroportoOrigem(voo.getAeroportoOrigem().getCodigo());
        dto.setCodigoAeroportoDestino(voo.getAeroportoDestino().getCodigo());
        return dto;
    }

}
