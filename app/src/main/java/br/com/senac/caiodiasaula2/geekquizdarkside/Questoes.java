package br.com.senac.caiodiasaula2.geekquizdarkside;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaopaulo on 23/11/16.
 */

public class Questoes {
    @SerializedName("textoquestao")
    private String textoquestao;
    @SerializedName("dificuldade")
    private String dificuldade;
    @SerializedName("codalternativa")
    private String codalternativa;
    @SerializedName("textoalternativa")
    private String textoalternativa;
    @SerializedName("assunto")
    private String assunto;
    @SerializedName("correta")
    private String correta;
    @SerializedName("codassunto")
    private String codassunto;
    @SerializedName("codquestao")
    private String codquestao;
    @SerializedName("area")
    private String area;
    @SerializedName("codevento")
    private String codevento;

    public String getTextoquestao() {
        return textoquestao;
    }

    public void setTextoquestao(String textoquestao) {
        this.textoquestao = textoquestao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getCodalternativa() {
        return codalternativa;
    }

    public void setCodalternativa(String codalternativa) {
        this.codalternativa = codalternativa;
    }

    public String getTextoalternativa() {
        return textoalternativa;
    }

    public void setTextoalternativa(String textoalternativa) {
        this.textoalternativa = textoalternativa;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorreta() {
        return correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

    public String getCodassunto() {
        return codassunto;
    }

    public void setCodassunto(String codassunto) {
        this.codassunto = codassunto;
    }

    public String getCodquestao() {
        return codquestao;
    }

    public void setCodquestao(String codquestao) {
        this.codquestao = codquestao;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCodevento() {
        return codevento;
    }

    public void setCodevento(String codevento) {
        this.codevento = codevento;
    }
}
