package com.juangil.adminbibliotecaapp.webservice;

import com.juangil.adminbibliotecaapp.response.AdminUsuarioResponse;
import com.juangil.adminbibliotecaapp.response.AutorResponse;
import com.juangil.adminbibliotecaapp.response.DataAdminUsuario;
import com.juangil.adminbibliotecaapp.response.DataAutor;
import com.juangil.adminbibliotecaapp.response.DataUsuario;
import com.juangil.adminbibliotecaapp.response.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebService {

    @POST("/adminlogin")
    Call<AdminUsuarioResponse> Login(
            @Body DataAdminUsuario usuario
    );

    //region usuarios
    @GET("/usuarios")
    Call<UsuarioResponse> obtenerUsuarios();

    @POST("/usuarios/add")
    Call<UsuarioResponse> agregarUsuario(
            @Body DataUsuario usuario
    );

    @POST("/usuarios/update")
    Call<UsuarioResponse> actualizaUsuario(
            @Body DataUsuario usuario
    );

    @POST("/usuarios/delete")
    Call<UsuarioResponse> borrarUsuario(
            @Body DataUsuario usuario
    );
    //endregion

    //region autores
    @GET("/autores")
    Call<AutorResponse> obtenerAutores();

    @POST("/autores/add")
    Call<AutorResponse> agregarAutor(
            @Body DataAutor autor
    );

    @POST("/autores/update")
    Call<AutorResponse> actualizarAutor(
            @Body DataAutor autor
    );

    @POST("/autores/delete")
    Call<AutorResponse> borrarAutor(
            @Body DataAutor autor
    );
    //endregion

}
