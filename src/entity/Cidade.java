package entity;

public class Cidade {
    private String nome;
    private Integer codigo;
    private String uf;

    public Cidade() {
    }

    public Cidade(String nome, Integer codigo, String uf) {
        this.nome = nome;
        this.codigo = codigo;
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
