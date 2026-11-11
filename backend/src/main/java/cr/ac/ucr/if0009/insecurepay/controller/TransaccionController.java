package cr.ac.ucr.if0009.insecurepay.controller;

import cr.ac.ucr.if0009.insecurepay.dto.TransaccionDto;
import cr.ac.ucr.if0009.insecurepay.dto.TransferenciaDto;
import cr.ac.ucr.if0009.insecurepay.service.TransaccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @GetMapping
    public ResponseEntity<List<TransaccionDto>> listarTodas() {
        return ResponseEntity.ok(transaccionService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transaccionService.obtenerPorId(id));
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<TransaccionDto>> obtenerPorCuenta(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(transaccionService.obtenerPorCuenta(cuentaId));
    }

    // [VULNERABILIDAD INTENCIONADA: A03:2021 - SQL Injection]
    // Endpoint para buscar transacciones vulnerable a inyeccion SQL en el parametro 'termino'
    @GetMapping("/buscar")
    public ResponseEntity<List<TransaccionDto>> buscarPorConcepto(@RequestParam("termino") String termino) {
        return ResponseEntity.ok(transaccionService.buscarPorConcepto(termino));
    }

    // [VULNERABILIDAD INTENCIONADA: A01:2021 - IDOR en Transferencias]
    // [VULNERABILIDAD INTENCIONADA: A03:2021 - Stored XSS]
    @PostMapping("/transferir")
    public ResponseEntity<TransaccionDto> transferir(@RequestBody TransferenciaDto dto) {
        TransaccionDto resultado = transaccionService.transferir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
}

