package br.com.senac.caiodiasaula2.geekquizdarkside;

/**
 * Created by joaopaulo on 10/11/16.
 */

public class EventoStatus {
    private Integer idEvento;
    private String statusEvento;
    private String tipoJogo;

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        idEvento = idEvento;
    }

    public String getTipoJogo() {
        return tipoJogo;
    }

    public void setTipoJogo(String tipoJogo) {
        this.tipoJogo = tipoJogo;
    }

    public String getStatusEvento() {
        return statusEvento;
    }

    public void setStatusEvento(String statusEvento) {
        this.statusEvento = statusEvento;
    }
}
