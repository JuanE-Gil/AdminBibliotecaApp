package com.juangil.adminbibliotecaapp.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DataAutor {
    @SerializedName("id_autor")
    private String idAutor;
    @SerializedName("nom_autor")
    private String nomAutor;

    public String getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(String idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomAutor() {
        return nomAutor;
    }

    public void setNomAutor(String nomAutor) {
        this.nomAutor = nomAutor;
    }

    @NonNull
    @Override
    public String toString() {
        return this.nomAutor;
    }

    public void resetData(){
        this.idAutor = "";
        this.nomAutor = "";
    }
}
