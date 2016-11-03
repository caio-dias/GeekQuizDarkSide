package br.com.senac.caiodiasaula2.geekquizdarkside;

/**
 * Created by joaopaulo on 03/11/16.
 */

public class UsuarioStatus {
    private String nmParticipante;
    private Integer codParticipante;
    private int Status;

    public String getNome() {
        return nmParticipante;
    }

    public Integer codParticipante() {
        return codParticipante;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void codParticipante(Integer codParticipante) {
        this.codParticipante = codParticipante;
    }

    public void setNome(String nome) {
        nmParticipante = nome;
    }
}
