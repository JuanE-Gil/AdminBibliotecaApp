package com.juangil.adminbibliotecaapp.response;

import java.util.List;

public class AdminUsuarioResponse {

    private String code;
    private String mensaje;
    private List<DataAdminUsuario> data;



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

    public List<DataAdminUsuario> getData() {
        return data;
    }

    public void setData(List<DataAdminUsuario> data) {
        this.data = data;
    }
}
