package cr.ac.ucr.if0009.insecurepay.controller;

import cr.ac.ucr.if0009.insecurepay.dto.CuentaDto;
import cr.ac.ucr.if0009.insecurepay.service.CuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - Broken Access Control]
    // Expone todas las cuentas y balances del banco publicamente
    @GetMapping
    public ResponseEntity<List<CuentaDto>> listarTodas() {
        return ResponseEntity.ok(cuentaService.listarTodas());
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - IDOR]
    // Consulta de balance y numero de cuenta por ID sin autenticacion ni autorizacion
    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cuentaService.obtenerPorId(id));
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - BOLA]
    // Consulta todas las cuentas asociadas a un ID de usuario arbitrario
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CuentaDto>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(cuentaService.obtenerPorUsuario(usuarioId));
    }
}

