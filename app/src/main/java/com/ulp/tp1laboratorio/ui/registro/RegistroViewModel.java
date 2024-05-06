package com.ulp.tp1laboratorio.ui.registro;


import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.tp1laboratorio.model.Usuario;
import com.ulp.tp1laboratorio.request.SharedPreferencesManager;


public class RegistroViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> usuarioGuardado;

    public RegistroViewModel(Application application) {
        super(application);
        usuarioGuardado = new MutableLiveData<>();
    }

    public MutableLiveData<Boolean> getUsuarioGuardado() {
        return usuarioGuardado;
    }

    public void registrarUsuario(Usuario usuario) {
        SharedPreferencesManager.guardar(getApplication(), usuario);
        usuarioGuardado.postValue(true);
    }
}

