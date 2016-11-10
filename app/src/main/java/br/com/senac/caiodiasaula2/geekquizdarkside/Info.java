package br.com.senac.caiodiasaula2.geekquizdarkside;


public class Info {
    private static Info ourInstance = new Info();
    private String nome;
    private String codParticipante;
    private Integer Status;
    private String login;
    private String senha;

    public static Info getInstance() {
        return ourInstance;
    }

    private Info() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodParticipante() {
        return codParticipante;
    }

    public void setCodParticipante(String codParticipante) {
        this.codParticipante = codParticipante;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
