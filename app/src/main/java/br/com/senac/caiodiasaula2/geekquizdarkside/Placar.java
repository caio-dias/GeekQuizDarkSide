package br.com.senac.caiodiasaula2.geekquizdarkside;

/**
 * Created by joaopaulo on 27/11/16.
 */

public class Placar {
    private String nmGrupo;
    private String tempoRespostaSec;
    private String totalRespostaCerta;

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public String getTempoRespostaSec() {
        return tempoRespostaSec;
    }

    public void setTempoRespostaSec(String tempoRespostaSec) {
        this.tempoRespostaSec = tempoRespostaSec;
    }

    public String getTotalRespostaCerta() {
        return totalRespostaCerta;
    }

    public void setTotalRespostaCerta(String totalRespostaCerta) {
        this.totalRespostaCerta = totalRespostaCerta;
    }
}
