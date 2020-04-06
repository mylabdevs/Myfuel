package com.mylabs.myfuel.api.excepition;

import java.util.ArrayList;
import java.util.List;

public class ApiErros {

    List<String> erros;

    public void addErros(List<String> erros) {
        if (this.erros == null) {
            this.erros = new ArrayList<>();
        }

        this.erros.addAll(erros);
    }

    public void addError(String erros) {
        if (this.erros == null) {
            this.erros = new ArrayList<>();
        }

        this.erros.add(erros);
    }

    public List<String> getErros() {
        return erros;
    }
}
