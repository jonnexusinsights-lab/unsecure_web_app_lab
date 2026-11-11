package cr.ac.ucr.if0009.insecurepay.service;

import cr.ac.ucr.if0009.insecurepay.dto.LoginRequest;
import cr.ac.ucr.if0009.insecurepay.dto.LoginResponse;
import cr.ac.ucr.if0009.insecurepay.model.Usuario;
import cr.ac.ucr.if0009.insecurepay.repository.UsuarioRepository;
import cr.ac.ucr.if0009.insecurepay.util.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    // [VULNERABILIDAD INTENCIONADA: A07:2021 - Identification & Authentication Failures]
    // Comparacion directa de contrasenas en texto plano sin BCryptPasswordEncoder
    public LoginResponse autenticar(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado en InsecurePay: " + request.getUsername()));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Credenciales invalidas para el usuario: " + request.getUsername());
        }

        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getRol().name(), usuario.getId());

        return new LoginResponse(
                token,
                usuario.getUsername(),
                usuario.getNombreCompleto(),
                usuario.getRol().name(),
                usuario.getId()
        );
    }
}

