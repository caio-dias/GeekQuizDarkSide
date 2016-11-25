package br.com.senac.caiodiasaula2.geekquizdarkside;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaopaulo on 24/11/16.
 */

public class QuestoesEvento {
    @SerializedName("codEvento")
    private String codEvento;
    @SerializedName("codQuestao")
    private String codQuestao;

    public String getCodEvento() {
        return codEvento;
    }

    public void setCodEvento(String codEvento) {
        this.codEvento = codEvento;
    }

    public String getCodQuestao() {
        return codQuestao;
    }

    public void setCodQuestao(String codQuestao) {
        this.codQuestao = codQuestao;
    }
}
