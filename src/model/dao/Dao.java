package model.dao;

import entity.Cidade;
import entity.Pessoa;
import exception.ExceptionIntegrity;
import exception.Excessao;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    Connection cnn = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement prds = null;

    public Dao(){
        cnn = Conexao.getConnection();
    }

    //Listar
    public List<Cidade> listaCidade (){
        List<Cidade>lista = new ArrayList<>();

        try{

            String sql = "SELECT * FROM cidade;";
            st = cnn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                Cidade c = new Cidade();
                c.setNome(rs.getString("nome"));
                c.setCodigo(rs.getInt("codigo"));
                c.setUf(rs.getString("uf"));
                lista.add(c);
            }
            return lista;

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public List<Pessoa> listaPessoa(){
        List<Pessoa>lista = new ArrayList<>();

        try{
            String sql = "SELECT *FROM pessoa;";
            st = cnn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                Pessoa p = new Pessoa();
                p.setNome(rs.getString("nome"));
                p.setCodigo(rs.getInt("codigo"));
                p.setIdCidade(rs.getInt("idCidade"));
                lista.add(p);
            }

            return lista;
        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }

    //INSERIR
    public void inserirCidade(Cidade cidade){
        try{
            String sql = "INSERT INTO cidade (nome, uf, codigo)  VALUES (?,?,?);";
            prds = cnn.prepareStatement(sql);
            prds.setString(1, cidade.getNome());
            prds.setString(2, cidade.getUf());
            prds.setInt(3, cidade.getCodigo());
            prds.executeUpdate();


        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }


    }
    public void inserirPessoa(Pessoa pessoa){
        try{

            String sql = "INSERT INTO pessoa (nome, idcidade) VALUES (?,?);";
            prds = cnn.prepareStatement(sql);
            prds.setString(1, pessoa.getNome());
            prds.setInt(2, pessoa.getIdCidade());
            prds.executeUpdate();

        } catch (SQLException t) {
            throw new Excessao(t.getMessage());
        }
    }

    //DELETAR
    public void DeletarCidade(Integer id){
        try{

            String sql = "DELETE FROM Cidade WHERE codigo = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            prds.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionIntegrity(e.getMessage());
        }
    }
    public void deletarPessoa(Integer id){
        try{
            String sql = "DELETE FROM Pessoa WHERE codigo = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            prds.executeUpdate();

        } catch (SQLException e) {
            throw new ExceptionIntegrity(e.getMessage());
        }
    }

    //ALTERAR
    public void alterarCidade(Cidade cidade, Integer codCidade){
        try{
            String sql = "UPDATE  cidade SET nome=?, uf=?, codigo=? WHERE  codigo=?;";
            prds = cnn.prepareStatement(sql);
            prds.setString(1, cidade.getNome());
            prds.setString(2, cidade.getUf());
            prds.setInt(3, cidade.getCodigo());
            prds.setInt(4, codCidade);
            prds.executeUpdate();

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public void alterarPessoa(Pessoa pessoa, Integer codPessoa){
//
        try{
            String sql = "UPDATE Pessoa SET nome=? ,idCidade=?, codigo=? WHERE codigo=?;";
            prds = cnn.prepareStatement(sql);
            prds.setString(1, pessoa.getNome());
            prds.setInt(2, pessoa.getIdCidade());
            prds.setInt(3,pessoa.getCodigo());
            prds.setInt(4, codPessoa);
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    //REMOVER
    public void removerCidade(int id){
        try{
            String sql = "DELETE FROM cidade WHERE codigo = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public void removerPessoa(int id){
        try{
            String sql = "DELETE FROM pessoa WHERE codigo = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            prds.executeUpdate();

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }

    //CONSULTAR
    public Cidade consultarCidade(int id){
        Cidade cidade = new Cidade();
        try{
            String sql = "SELECT * FROM cidade WHERE codigo = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            rs = prds.executeQuery();
            if(rs.next()){
                cidade.setNome(rs.getString("nome"));
                cidade.setCodigo(rs.getInt("codigo"));
                cidade.setUf(rs.getString("uf"));

            }

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
        return cidade;
    }
    public Pessoa consultarPessoa(int id){
        Pessoa pessoa = new Pessoa();
        try{
            String sql = "SELECT * FROM pessoa WHERE codigo = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            rs = prds.executeQuery();
            if(rs.next()){
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCodigo(rs.getInt("codigo"));
                pessoa.setIdCidade(rs.getInt("idcidade"));
            }

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }

        return pessoa;
    }

    //FECHAR CONEXAO
    public void fecharConexao(){
        try{
            rs.close();
            st.close();
            prds.close();
            Conexao.closeConnection();

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }

}
