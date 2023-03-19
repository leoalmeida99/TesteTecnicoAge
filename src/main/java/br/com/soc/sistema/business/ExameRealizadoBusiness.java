package br.com.soc.sistema.business;

import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.dao.examesrealizados.ExameRealizadoDao;
import br.com.soc.sistema.exception.BusinessException;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoBusiness {

	private static final String FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO = "Foi informado um caracter no lugar de um numero";
	private static final String NAO_FOI_POSSIVEL_REALIZAR_A_BUSCA = "Não foi possivel realizar a busca";
	private ExameRealizadoDao dao;
	
	public ExameRealizadoBusiness() {
		this.dao = new ExameRealizadoDao();
	}
	
//	public List<ExameRealizadoVo> buscarExamesRealizadosPorFuncionarioExameEData(){
//		return dao.buscarExamesRealizadosPorFuncionarioExameEData();
//	}
	
	public List<ExameRealizadoVo> trazerTodosOsExamesRealizados(){
		return dao.findAllExamesRealizados();
	}
	
	public void excluirExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		dao.excluirExameRealizado(exameRealizadoVo);
	}
	
	public void editarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		dao.editarExameRealizado(exameRealizadoVo);
	}
	
	public void salvarExameRealizado(ExameRealizadoVo exameRealizadoVo) {
		try {
			if(exameRealizadoVo.getRowidExame().isEmpty())
				throw new BusinessException("id do exame nao pode ser em branco");
			if(exameRealizadoVo.getRowidFuncionario().isEmpty())
				throw new IllegalArgumentException("id do funcionario nao pode ser em branco");
			if(((CharSequence) exameRealizadoVo.getDataRealizacao()).isEmpty())
				throw new IllegalArgumentException("id do funcionario nao pode ser em branco");
			
			dao.insertExameRealizado(exameRealizadoVo);
		} catch (Exception e) {
			throw new BusinessException("Nao foi possivel realizar a inclusao");
		}
		
	}	
	
	public List<ExameRealizadoVo> filtrarExamesRealizados(ExameRealizadoFilter filter){
		List<ExameRealizadoVo> exameRealizado = new ArrayList<>();
		
		switch (filter.getOpcoesCombo()) {
			case ID:
				try {
					Integer codigo = Integer.parseInt(filter.getValorBusca());
					exameRealizado.add(dao.findByCodigo(codigo));
				}catch (NumberFormatException e) {
					throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
				}
			break;
		default:
			break;
		}
		return exameRealizado;
	}
	
	public ExameRealizadoVo buscarExameRealizadoPor(String codigo) {
		try {
			Integer cod = Integer.parseInt(codigo);
			return dao.findByCodigo(cod);
		}catch (NumberFormatException e) {
			throw new BusinessException(FOI_INFORMADO_CARACTER_NO_LUGAR_DE_UM_NUMERO);
		}
	}

	public ExameRealizadoVo buscarExamesRealizadosPorFuncionarioExameEData(String rowidFuncionario, String rowidExame,
			String dataRealizacao) {
		try {
			Integer idFuncionario = Integer.parseInt(rowidFuncionario);
			Integer idExame = Integer.parseInt(rowidExame);
			return dao.buscarExamesRealizadosPorFuncionarioExameEData(idFuncionario,idExame,dataRealizacao);
		}catch (NumberFormatException e) {
			throw new BusinessException(NAO_FOI_POSSIVEL_REALIZAR_A_BUSCA);
		}
		
		
	}

}
