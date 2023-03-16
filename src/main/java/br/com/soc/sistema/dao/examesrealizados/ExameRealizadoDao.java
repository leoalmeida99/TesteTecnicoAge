package br.com.soc.sistema.dao.examesrealizados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.soc.sistema.dao.Dao;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoDao extends Dao {

	public void insertExameRealizado(ExameRealizadoVo exameRealizadoVo) { // necessita fazer a query
		StringBuilder query = new StringBuilder("INSERT INTO exame_realizado (rowid_funcionario, rowid_exame, dt_realizacao) VALUES (?, ?, ?)");
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {

			int i = 1;
			ps.setString(i++, exameRealizadoVo.getRowidFuncionario());
			ps.setString(i++, exameRealizadoVo.getRowidExame());
			ps.setString(i++, exameRealizadoVo.getDataRealizacao());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("deu ruim aqui hein");
			e.printStackTrace();
		}
	}

	public List<ExameRealizadoVo> findAllExamesRealizados() {
		StringBuilder query = new StringBuilder("SELECT rowid id, rowid_funcionario idfuncionario, rowid_exame idexame, dt_realizacao dtrealizacao FROM exame_realizado");
		
		try (Connection con = getConexao();
				PreparedStatement ps = con.prepareStatement(query.toString());
				ResultSet rs = ps.executeQuery()) {

			ExameRealizadoVo vo = null;
			List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
			while (rs.next()) {
				vo = new ExameRealizadoVo();
				vo.setRowid(rs.getString("id"));
				vo.setRowidFuncionario(rs.getString("idfuncionario"));
				vo.setRowidExame(rs.getString("idexame"));
				vo.setDataRealizacao(rs.getString("dtrealizacao"));

				examesRealizados.add(vo);
			}
			return examesRealizados;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}
	
	// implementar isso depois de fazer o crud
//	public List<ExameRealizadoVo> findAllByRowidFuncionario(Integer codigo) {
//		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
//				.append("WHERE lower(nm_funcionario) like lower(?)");
//
//		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
//			int i = 1;
//
//			ps.setString(i, "%" + nome + "%");
//
//			try (ResultSet rs = ps.executeQuery()) {
//				FuncionarioVo vo = null;
//				List<FuncionarioVo> funcionarios = new ArrayList<>();
//
//				while (rs.next()) {
//					vo = new FuncionarioVo();
//					vo.setRowid(rs.getString("id"));
//					vo.setNome(rs.getString("nome"));
//
//					funcionarios.add(vo);
//				}
//				return funcionarios;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return Collections.emptyList();
//	}

	public ExameRealizadoVo findByCodigo(Integer codigo) {
//		StringBuilder query = new StringBuilder("SELECT rowid id, nm_funcionario nome FROM funcionario ")
//				.append("WHERE rowid = ?");

		StringBuilder query = new StringBuilder("SELECT rowid id, rowid_funcionario idfuncionario, rowid_exame idexame, dt_realizacao dtrealizacao ")
				 .append("FROM exame_realizado WHERE rowid = ?");
		
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				ExameRealizadoVo vo = null;

				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.setRowidFuncionario(rs.getString("idfuncionario"));
					vo.setRowidExame(rs.getString("idexame"));
					vo.setDataRealizacao(rs.getString("dtrealizacao"));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void excluirExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		StringBuilder query = new StringBuilder("delete FROM exame_realizado WHERE rowid = ?");
		
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			
			ps.setString(i, exameRealizadoVo.getRowid());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		StringBuilder query = new StringBuilder("update exame_realizado set ")
					.append("rowid_funcionario = ?, rowid_exame = ?, dt_realizacao = ? where rowid = ?");
		
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;
			
			ps.setString(i++, exameRealizadoVo.getRowidFuncionario());
			ps.setString(i++, exameRealizadoVo.getRowidExame());
			ps.setString(i++, exameRealizadoVo.getDataRealizacao());
			ps.setString(i, exameRealizadoVo.getRowid());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ExameRealizadoVo buscarPorExame(Integer codigo) {
		StringBuilder query = new StringBuilder("SELECT rowid id, rowid_funcionario idfuncionario, rowid_exame idexame, dt_realizacao dtrealizacao ")
				 .append("FROM exame_realizado WHERE rowid_exame = ?");
		
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i, codigo);

			try (ResultSet rs = ps.executeQuery()) {
				ExameRealizadoVo vo = null;

				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.setRowidFuncionario(rs.getString("idfuncionario"));
					vo.setRowidExame(rs.getString("idexame"));
					vo.setDataRealizacao(rs.getString("dtrealizacao"));
				}
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ExameRealizadoVo buscarExamesRealizadosPorFuncionarioExameEData(Integer idFuncionario, Integer idExame,String dataRealizacao) {
		StringBuilder query = new StringBuilder("SELECT rowid id, rowid_funcionario idfuncionario, rowid_exame idexame, dt_realizacao dtrealizacao ")
				 .append("FROM exame_realizado ")
				 .append("WHERE rowid_funcionario = ? AND rowid_exame = ? AND dt_realizacao = ?");
		
		try (Connection con = getConexao(); PreparedStatement ps = con.prepareStatement(query.toString())) {
			int i = 1;

			ps.setInt(i++, idFuncionario);
			ps.setInt(i++, idExame);
			ps.setString(i++, dataRealizacao);

			try (ResultSet rs = ps.executeQuery()) {
				ExameRealizadoVo vo = null;

				while (rs.next()) {
					vo = new ExameRealizadoVo();
					vo.setRowid(rs.getString("id"));
					vo.setRowidFuncionario(rs.getString("idfuncionario"));
					vo.setRowidExame(rs.getString("idexame"));
					vo.setDataRealizacao(rs.getString("dtrealizacao"));
				}
				System.out.println(vo);
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}


//	public List<ExameRealizadoVo> buscarExamesRealizadosPorFuncionarioExameEData() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}