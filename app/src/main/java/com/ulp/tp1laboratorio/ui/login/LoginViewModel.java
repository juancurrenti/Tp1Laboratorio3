package com.ulp.tp1laboratorio.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ulp.tp1laboratorio.model.Usuario;
import com.ulp.tp1laboratorio.request.SharedPreferencesManager;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> usuarioMutableLiveData;

    public LoginViewModel(Application application) {
        super(application);
        usuarioMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<Usuario> getUsuarioMutableLiveData() {
        return usuarioMutableLiveData;
    }

    public void login(String email, String password) {
        Usuario usuario = SharedPreferencesManager.login(getApplication(), email, password);
        usuarioMutableLiveData.postValue(usuario);
    }

}
