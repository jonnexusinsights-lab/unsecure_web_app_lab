package cr.ac.ucr.if0009.insecurepay.service;

import cr.ac.ucr.if0009.insecurepay.dto.CuentaDto;
import cr.ac.ucr.if0009.insecurepay.model.CuentaBancaria;
import cr.ac.ucr.if0009.insecurepay.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - Broken Access Control]
    // Retorna todas las cuentas del banco sin verificar privilegios de administrador
    public List<CuentaDto> listarTodas() {
        return cuentaRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - IDOR (Insecure Direct Object Reference)]
    // Permite consultar los fondos y datos de cualquier cuenta bancaria alterando el {id}
    public CuentaDto obtenerPorId(Long id) {
        CuentaBancaria cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta bancaria no encontrada con ID: " + id));
        return convertirADto(cuenta);
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - BOLA]
    // Permite listar todas las cuentas de cualquier cliente alterando el usuarioId
    public List<CuentaDto> obtenerPorUsuario(Long usuarioId) {
        return cuentaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    private CuentaDto convertirADto(CuentaBancaria cuenta) {
        CuentaDto dto = new CuentaDto();
        dto.setId(cuenta.getId());
        dto.setUsuarioId(cuenta.getUsuario().getId());
        dto.setTitular(cuenta.getUsuario().getNombreCompleto());
        dto.setNumeroCuenta(cuenta.getNumeroCuenta());
        dto.setTipoCuenta(cuenta.getTipoCuenta().name());
        dto.setSaldo(cuenta.getSaldo());
        dto.setMoneda(cuenta.getMoneda());
        dto.setActiva(cuenta.getActiva());
        return dto;
    }
}

