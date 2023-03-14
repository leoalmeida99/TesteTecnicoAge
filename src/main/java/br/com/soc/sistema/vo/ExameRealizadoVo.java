package br.com.soc.sistema.vo;

public class ExameRealizadoVo { // tirar duvida se pode int nos atributos cd func e cd exame.
	private String rowid;
	private String rowidFuncionario;
	private String rowidExame;
	private String dataRealizacao;

	public ExameRealizadoVo() {
	}

	public ExameRealizadoVo(String rowid, String rowidFuncionario, String rowidExame, String dataRealizacao) {
		this.rowid = rowid;
		this.rowidFuncionario = rowidFuncionario;
		this.rowidExame = rowidExame;
		this.dataRealizacao = dataRealizacao;
	}

	public String getRowidFuncionario() {
		return rowidFuncionario;
	}

	public void setRowidFuncionario(String rowidFuncionario) {
		this.rowidFuncionario = rowidFuncionario;
	}

	public String getRowidExame() {
		return rowidExame;
	}

	public void setRowidExame(String rowidExame) {
		this.rowidExame = rowidExame;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}
//	public Date getDataRealizacao() {
//		return dataRealizacao;
//	}
//	public void setDataRealizacao(Date dataRealizacao) {
//		this.dataRealizacao = dataRealizacao;
//	}

	public String getRowid() {
		return rowid;
	}

	public void setRowid(String rowid) {
		this.rowid = rowid;
	}

	@Override
	public String toString() {
		return "ExameRealizadoVo [rowid=" + rowid + ", rowidFuncionario=" + rowidFuncionario + ", rowidExame="
				+ rowidExame + ", dataRealizacao=" + dataRealizacao + "]";
	}

	public void setDataRealizacao(String string) {
		this.dataRealizacao = string;

	}

	
}
