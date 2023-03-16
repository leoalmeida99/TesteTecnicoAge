package br.com.soc.sistema.action.examerealizado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.filter.ExameRealizadoFilter;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.infra.OpcoesComboBuscarExames;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoAction extends Action {
	private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
	private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
	private ExameRealizadoFilter filtrar = new ExameRealizadoFilter();
	private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();

	public String todos() {
		examesRealizados.addAll(business.trazerTodosOsExamesRealizados());

		return SUCCESS;
	}

	public String filtrar() {
		if (filtrar.isNullOpcoesCombo())
			return REDIRECT;

		examesRealizados = business.filtrarExamesRealizados(filtrar);

		return SUCCESS;
	}

	private boolean existeExameRealizado() {
        // buscar na base de dados se já existe um registro com os mesmos valores de funcionário, exame e data
//        List<ExameRealizadoVo> exames = business.buscarExamesRealizadosPorFuncionarioExameEData(idFuncionario, idExame, dataRealizacao);
        ExameRealizadoVo exames = business.buscarExamesRealizadosPorFuncionarioExameEData(exameRealizadoVo.getRowidFuncionario(), exameRealizadoVo.getRowidExame(), exameRealizadoVo.getDataRealizacao());
        // se existir algum registro, retorna true
        if(exames == null) {
        	return false;
        }
        
        return true;
    }
	
	public String novo() {
		if (exameRealizadoVo.getRowid() == null || existeExameRealizado()) {
			return INPUT;
		}
		// verificar se é um exame novo ou exame para editar

		if (exameRealizadoVo.getRowid() != "") { // pode ser que de problema nessa linha, tem que verificar
			System.out.println("editando exame realizado");
			business.editarExameRealizado(exameRealizadoVo);
		} else {
			System.out.println("salvando exame realizado");
			business.salvarExameRealizado(exameRealizadoVo);
		}

		return REDIRECT;
	}

	public String editar() {
		if (exameRealizadoVo.getRowid() == null)
			return REDIRECT;

		exameRealizadoVo = business.buscarExameRealizadoPor(exameRealizadoVo.getRowid());

		return INPUT;
	}

	public String excluir() {
		if (exameRealizadoVo.getRowid() == null)
			return REDIRECT;

		business.excluirExameRealizado(exameRealizadoVo);

		return REDIRECT;
	}

	public List<OpcoesComboBuscarExames> getListaOpcoesCombo() {
		return Arrays.asList(OpcoesComboBuscarExames.values());
	}

	public List<ExameRealizadoVo> getExamesRealizados() {
		return examesRealizados;
	}

	public void setExamesRealizados(List<ExameRealizadoVo> examesRealizados) {
		this.examesRealizados = examesRealizados;
	}

	public ExameRealizadoFilter getFiltrar() {
		return filtrar;
	}

	public void setFiltrar(ExameRealizadoFilter filtrar) {
		this.filtrar = filtrar;
	}

	public ExameRealizadoVo getExameRealizadoVo() {
		return exameRealizadoVo;
	}

	public void setExameVo(ExameRealizadoVo exameRealizadoVo) {
		this.exameRealizadoVo = exameRealizadoVo;
	}
}
