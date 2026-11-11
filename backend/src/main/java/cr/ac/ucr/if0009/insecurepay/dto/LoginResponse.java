package cr.ac.ucr.if0009.insecurepay.dto;

public class LoginResponse {
    private String token;
    private String username;
    private String nombreCompleto;
    private String rol;
    private Long usuarioId;

    public LoginResponse() {
    }

    public LoginResponse(String token, String username, String nombreCompleto, String rol, Long usuarioId) {
        this.token = token;
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.usuarioId = usuarioId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

