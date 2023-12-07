package com.juangil.adminbibliotecaapp.response;

import java.util.List;

public class UsuarioResponse {
    private String code;
    private String mensaje;
    private List<DataUsuario> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<DataUsuario> getData() {
        return data;
    }

    public void setData(List<DataUsuario> data) {
        this.data = data;
    }
}
