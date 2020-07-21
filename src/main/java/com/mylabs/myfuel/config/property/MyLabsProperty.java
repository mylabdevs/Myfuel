package com.mylabs.myfuel.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mylabs")
public class MyLabsProperty {

    private String originPermitida = "http://localhost:8080";

    private final Seguranca seguranca = new Seguranca();

    public String getOriginPermitida() {
        return originPermitida;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public static class Seguranca {
        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }
}
