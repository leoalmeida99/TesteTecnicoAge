package br.com.soc.sistema.action.exame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameBusiness;
import br.com.soc.sistema.dao.examesrealizados.ExameRealizadoDao;
import br.com.soc.sistema.filter.ExameFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameRealizadoVo;
import br.com.soc.sistema.vo.ExameVo;

public class ExameAction extends Action {
	private List<ExameVo> exames = new ArrayList<>();
	private ExameBusiness business = new ExameBusiness();
	private ExameFilter filtrar = new ExameFilter();
	private ExameVo exameVo = new ExameVo();
	
	
	public String todos() {
		exames.addAll(business.trazerTodosOsExames());	

		return SUCCESS;
	}
	
	public String filtrar() {
		if(filtrar.isNullOpcoesCombo())
			return REDIRECT;
		
		exames = business.filtrarExames(filtrar);
		
		return SUCCESS;
	}
	
	
	public String novo() {
		if(exameVo.getNome() == null) {
			return INPUT;
		}
		//verificar se e um exame novo ou exame para editar

		if(exameVo.getRowid() != "") {
			System.out.println("editando exame");
			business.editarExame(exameVo);
		}else {
			System.out.println("salvando exame");
			business.salvarExame(exameVo);
		}
		
		return REDIRECT;
	}
	
	public String editar() {
		if(exameVo.getRowid() == null)
			return REDIRECT;
		
		exameVo = business.buscarExamePor(exameVo.getRowid());
//		 exame vo retorna o numero id

//		System.out.println(exameVo);
//		System.out.println("funfa n?");
		
		return INPUT;
	}
	
	public String excluir() {
		
		int idExame = Integer.parseInt(exameVo.getRowid());
		ExameRealizadoDao exameRealizadoDao = new ExameRealizadoDao();
//		System.out.println(exameRealizadoDao);

		if(exameVo.getRowid() == null || exameRealizadoDao.buscarPorExame(idExame) != null)
			return REDIRECT;
		
		business.excluirExame(exameVo);
		
		return REDIRECT;
	}
	
	
	public List<OpcoesComboBuscarExames> getListaOpcoesCombo(){
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}
	
	public List<ExameVo> getExames() {
		return exames;
	}

	public void setExames(List<ExameVo> exames) {
		this.exames = exames;
	}

	public ExameFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameVo getExameVo() {
		return exameVo;
	}

	public void setExameVo(ExameVo exameVo) {
		this.exameVo = exameVo;
	}
}
