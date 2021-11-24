package transferencia;

public class Cliente {
    //nome, cpf, rg

    private String nome;
    private String cpf;
    private String rg;

//CONSTRUTORES
    public Cliente(String nome, String cpf, String rg) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
    }

//METODOS
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }
}
