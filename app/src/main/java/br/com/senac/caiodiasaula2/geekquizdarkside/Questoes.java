package br.com.senac.caiodiasaula2.geekquizdarkside;

import com.google.gson.annotations.SerializedName;

/**
 * Created by joaopaulo on 23/11/16.
 */

public class Questoes {
    @SerializedName("CODEVENTO")
    private String CODEVENTO;
    @SerializedName("CODQUESTAO")
    private String CODQUESTAO;
    @SerializedName("TEXTOQUESTAO")
    private String TEXTOQUESTAO;
    @SerializedName("CODASSUNTO")
    private String CODASSUNTO;
    @SerializedName("AREA")
    private String AREA;
    @SerializedName("ASSUNTO")
    private String ASSUNTO;
    @SerializedName("DIFICULDADE")
    private String DIFICULDADE;
    @SerializedName("CODALTERNATIVA")
    private String CODALTERNATIVA;
    @SerializedName("TEXTOALTERNATIVA")
    private String TEXTOALTERNATIVA;
    @SerializedName("CORRETA")
    private String CORRETA;

    public String getCODEVENTO() {
        return CODEVENTO;
    }

    public void setCODEVENTO(String CODEVENTO) {
        this.CODEVENTO = CODEVENTO;
    }

    public String getCODQUESTAO() {
        return CODQUESTAO;
    }

    public void setCODQUESTAO(String CODQUESTAO) {
        this.CODQUESTAO = CODQUESTAO;
    }

    public String getTEXTOQUESTAO() {
        return TEXTOQUESTAO;
    }

    public void setTEXTOQUESTAO(String TEXTOQUESTAO) {
        this.TEXTOQUESTAO = TEXTOQUESTAO;
    }

    public String getCODASSUNTO() {
        return CODASSUNTO;
    }

    public void setCODASSUNTO(String CODASSUNTO) {
        this.CODASSUNTO = CODASSUNTO;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public String getASSUNTO() {
        return ASSUNTO;
    }

    public void setASSUNTO(String ASSUNTO) {
        this.ASSUNTO = ASSUNTO;
    }

    public String getDIFICULDADE() {
        return DIFICULDADE;
    }

    public void setDIFICULDADE(String DIFICULDADE) {
        this.DIFICULDADE = DIFICULDADE;
    }

    public String getCODALTERNATIVA() {
        return CODALTERNATIVA;
    }

    public void setCODALTERNATIVA(String CODALTERNATIVA) {
        this.CODALTERNATIVA = CODALTERNATIVA;
    }

    public String getTEXTOALTERNATIVA() {
        return TEXTOALTERNATIVA;
    }

    public void setTEXTOALTERNATIVA(String TEXTOALTERNATIVA) {
        this.TEXTOALTERNATIVA = TEXTOALTERNATIVA;
    }

    public String getCORRETA() {
        return CORRETA;
    }

    public void setCORRETA(String CORRETA) {
        this.CORRETA = CORRETA;
    }
}
