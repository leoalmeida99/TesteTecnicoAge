package br.com.soc.sistema.dao.funcionarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.FuncionarioVo;

public class FuncionarioDao extends Dao {

	public void insertFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder("INSERT INTO funcionario (nm_funcionario) values (?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setString(i++, funcionarioVo.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<FuncionarioVo> findAllFuncionarios() {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario");
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			FuncionarioVo vo = null;
			List<FuncionarioVo> funcionarios = new ArrayList<>();
			while (rs.next()) {
				vo = new FuncionarioVo();
				vo.setRowid(rs.getString("id"));
				vo.setNome(rs.getString("nome"));

				funcionarios.add(vo);
			}
			return funcionarios;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<FuncionarioVo> findAllByNome(String nome) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
				.append("WHERE lower(nm_funcionario) like lower(?)");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setString(i, "%" + nome + "%");

			try (ResultSet rs = ps.executeQuery()) {
				FuncionarioVo vo = null;
				List<FuncionarioVo> funcionarios = new ArrayList<>();

				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));

					funcionarios.add(vo);
				}
				return funcionarios;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	public FuncionarioVo findByCodigo(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
				.append("WHERE rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				FuncionarioVo vo = null;

				while (rs.next()) {
					vo = new FuncionarioVo();
					vo.setRowid(rs.getString("id"));
					vo.setNome(rs.getString("nome"));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluirFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder queryRemoveFuncionario = new StringBuilder("DELETE FROM funcionario WHERE rowid = ?");
		StringBuilder queryRemoveExamesRealizadoDoFuncionario = new StringBuilder("DELETE FROM exame_realizado WHERE rowid_funcionario = ?");

		try (Connection con = getConexao(); 
		PreparedStatement psFuncionario = con.prepareStatement(queryRemoveFuncionario.toString());
		PreparedStatement psExame = con.prepareStatement(queryRemoveExamesRealizadoDoFuncionario.toString())) {
			int i = 1;

			psFuncionario.setString(i, funcionarioVo.getRowid());
			psFuncionario.execute();
			
			psExame.setString(i, funcionarioVo.getRowid());
			psExame.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarFuncionario(FuncionarioVo funcionarioVo) {
		StringBuilder query = new StringBuilder("update funcionario set ").append("nm_funcionario = ? where rowid = ?");

		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setString(i++, funcionarioVo.getNome());
			ps.setString(i, funcionarioVo.getRowid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}