package entity;

public class Pessoa{
   private String nome;
   private Integer codigo;
   private Integer idCidade;

    public Pessoa() {
    }

    public Pessoa(String nome, Integer idCidade) {
        this.nome = nome;
        this.idCidade = idCidade;
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

    public Integer getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
}
