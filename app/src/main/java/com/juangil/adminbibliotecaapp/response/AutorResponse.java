package com.juangil.adminbibliotecaapp.response;

import java.util.List;

public class AutorResponse {
    private String code;
    private String mensaje;
    private List<DataAutor> data;

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

    public List<DataAutor> getData() {
        return data;
    }

    public void setData(List<DataAutor> data) {
        this.data = data;
    }
}
