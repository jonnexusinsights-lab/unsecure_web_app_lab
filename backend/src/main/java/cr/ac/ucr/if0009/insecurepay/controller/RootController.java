package cr.ac.ucr.if0009.insecurepay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping(value = {"/", "/api"})
    public ResponseEntity<Map<String, Object>> root() {
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("aplicacion", "InsecurePay REST API - Laboratorio de Ciberseguridad");
        resp.put("estado", "ONLINE");
        resp.put("version", "1.0.0");
        resp.put("descripcion", "Servidor REST API para el Laboratorio 17 (IF0009 - UCR)");
        resp.put("h2Console", "http://localhost:8080/h2-console");
        resp.put("frontendUrl", "http://localhost:4200");

        Map<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("login", "POST /api/auth/login");
        endpoints.put("cuentasUsuario", "GET /api/cuentas/usuario/{usuarioId}");
        endpoints.put("cuentaDetalle", "GET /api/cuentas/{id}");
        endpoints.put("cuentaSaldo", "GET /api/cuentas/{id}/saldo");
        endpoints.put("transferir", "POST /api/transacciones/transferir");
        endpoints.put("buscarTransacciones", "GET /api/transacciones/buscar?termino=pago");
        resp.put("endpointsDisponibles", endpoints);

        return ResponseEntity.ok(resp);
    }
}

