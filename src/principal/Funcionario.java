package principal;

import java.math.BigDecimal;
import java.time.LocalDate;

//2 – Classe Funcionário que estenda a classe Pessoa
public class Funcionario extends Pessoa {
	public BigDecimal salario;
	public String funcao;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(String name, LocalDate data_nascimento, BigDecimal salario, String funcao) {
		super(name, data_nascimento);
		this.salario = salario;
		this.funcao = funcao;

	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public void aumento_salarial(BigDecimal salario) {
		 BigDecimal aumento = salario.multiply(new BigDecimal("0.10"));
	     this.salario = salario.add(aumento); 
		
	}

}
