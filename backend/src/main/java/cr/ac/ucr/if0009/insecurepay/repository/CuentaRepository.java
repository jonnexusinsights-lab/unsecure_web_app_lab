package cr.ac.ucr.if0009.insecurepay.repository;

import cr.ac.ucr.if0009.insecurepay.model.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaBancaria, Long> {
    List<CuentaBancaria> findByUsuarioId(Long usuarioId);
    Optional<CuentaBancaria> findByNumeroCuenta(String numeroCuenta);
}

