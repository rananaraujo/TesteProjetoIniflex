package principal;

import java.time.LocalDate;

//1– Classe Pessoa
public class Pessoa {

	public String name;
	public LocalDate data_nascimento; 
	
	public Pessoa() {
		// TODO Auto-generated constructor stub
		
	}

	public Pessoa(String name, LocalDate data_nascimento) {
		super();
		this.name = name;
		this.data_nascimento = data_nascimento;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

}
