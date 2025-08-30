package principal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		// 3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
		List<Funcionario> list_funcionario = new ArrayList<>(Arrays.asList(
				new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
				new Funcionario("João", LocalDate.of(1990, 12, 5), new BigDecimal("2284.38"), "Operador"),
				new Funcionario("Caio", LocalDate.of(1961, 2, 5), new BigDecimal("9836.14"), "Coordenador"),
				new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
				new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
				new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
				new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
				new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
				new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
				new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")));

		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("\n" + "=".repeat(60));
			System.out.println("MENU DE FUNCIONALIDADES");
			System.out.println("=".repeat(60));
			System.out.println("1. Remover funcionário João");
			System.out.println("2. Imprimir todos os funcionários");
			System.out.println("3. Aplicar aumento de 10% nos salários");
			System.out.println("4. Agrupar funcionários por função");
			System.out.println("5. Imprimir funcionários agrupados por função");
			System.out.println("6. Imprimir aniversariantes dos meses 10 e 12");
			System.out.println("7. Imprimir funcionário com maior idade");
			System.out.println("8. Imprimir funcionários em ordem alfabética");
			System.out.println("9. Imprimir total dos salários");
			System.out.println("10. Imprimir salários em múltiplos do salário mínimo");
			System.out.println("0. Sair");
			System.out.println("=".repeat(60));
			System.out.print("Escolha uma opção: ");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				remove_joao(list_funcionario);
				System.out.println("João removido com sucesso!");
				break;

			case 2:
				imprimirFuncionarios(list_funcionario);
				break;

			case 3:
				aumento_de_salario(list_funcionario);
				System.out.println("Aumento aplicado com sucesso!");
				break;

			case 4:
				Map<String, List<Funcionario>> agrupado = agruparPorFuncao(list_funcionario);
				System.out.println("Agrupamento realizado com sucesso!");
				break;

			case 5:
				Map<String, List<Funcionario>> agrupadoPrint = agruparPorFuncao(list_funcionario);
				imprimirAgrupamento(agrupadoPrint);
				break;

			case 6:

				imprimir_aniversario(list_funcionario);
				break;

			case 7:
				imprimir_maior_idade(list_funcionario);
				break;

			case 8:

				imprimirPorOrdemAlfabetica(list_funcionario);
				break;

			case 9:

				imprimir_total_salario(list_funcionario);
				break;

			case 10:

				imprimirSalariosMinimos(list_funcionario);
				break;

			case 0:
				System.out.println("Saindo do sistema...");
				break;

			default:
				System.out.println("Opção inválida! Tente novamente.");
				break;
			}

			if (opcao != 0) {
				System.out.println("\nPressione Enter para continuar...");
				scanner.nextLine();
				scanner.nextLine();
			}

		} while (opcao != 0);

		scanner.close();

	}

	// 3.3 – Remover o funcionário “João” da lista.
	public static void remove_joao(List<Funcionario> list_funcionarios) {
		for (int i = 0; i < list_funcionarios.size(); i++) {
			if (list_funcionarios.get(i).getName().equalsIgnoreCase("João")) {
				list_funcionarios.remove(i);
				break;
			}
		}
	}

	// 3.4 – Imprimir todos os funcionários
	public static void imprimirFuncionarios(List<Funcionario> funcionarios) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

		for (Funcionario funcionario : funcionarios) {
			String salarioFormatado = nf.format(funcionario.getSalario());
			System.out.printf(" %-14s  %-12s  R$ %-9s  %-13s \n", funcionario.getName(),
					funcionario.getData_nascimento().format(formatter), salarioFormatado, funcionario.getFuncao());
		}

	}

	// 3.5 - Os funcionários receberam 10% de aumento de salário
	public static void aumento_de_salario(List<Funcionario> funcionarios) {
		for (Funcionario funcionario : funcionarios) {
			funcionario.aumento_salarial(funcionario.getSalario());
		}

	}

	// 3.6 - Agrupar os funcionários por função em um MAP
	public static Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
		return funcionarios.stream().collect(Collectors.groupingBy(func -> func.getFuncao()));
	}

	// 3.7 - Imprimir os funcionários, agrupados por função.
	public static void imprimirAgrupamento(Map<String, List<Funcionario>> agrupamento) {
		System.out.println("\nFUNCIONÁRIOS POR FUNÇÃO");
		agrupamento.forEach((funcao, lista) -> {
			System.out.println("\n" + funcao);
			lista.forEach(funcionario -> System.out.println(funcionario.getName()));
		});
	}

	// 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12.
	public static void imprimir_aniversario(List<Funcionario> funcionarios) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

		System.out.println("\nFUNCIONÁRIOS QUE FAZEM ANIVERSÁRIO NOS MESES 10 E 12");

		for (Funcionario funcionario : funcionarios) {
			int mesAniversario = funcionario.getData_nascimento().getMonthValue();

			if (mesAniversario == 10 || mesAniversario == 12) {
				String salarioFormatado = nf.format(funcionario.getSalario());
				System.out.printf(" %-14s  %-12s  R$ %-9s  %-13s\n", funcionario.getName(),
						funcionario.getData_nascimento().format(formatter), salarioFormatado, funcionario.getFuncao(),
						mesAniversario);
			}
		}
	}

	// 3.9 - Imprimir o funcionário com a maior idade, exibir os atributos: nome e
	// idade.
	public static void imprimir_maior_idade(List<Funcionario> funcionarios) {

		Funcionario funcionario_mais_velho = funcionarios.get(0);
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getData_nascimento().isBefore(funcionario_mais_velho.getData_nascimento())) {
				funcionario_mais_velho = funcionario;
			}
		}

		int idade = calcularIdade(funcionario_mais_velho.getData_nascimento());

		System.out.println("\nFUNCIONÁRIOS MAIS VELHO");
		System.out.printf(" %-14s  %-12s\n", funcionario_mais_velho.getName(), idade);

	}

	// Função para calcular idade
	public static int calcularIdade(LocalDate dataNascimento) {
		return Period.between(dataNascimento, LocalDate.now()).getYears();
	}

	// 3.10 - Imprimir a lista de funcionários por ordem alfabética
	public static void imprimirPorOrdemAlfabetica(List<Funcionario> funcionarios) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

		System.out.println("\nFUNCIONÁRIOS EM ORDEM ALFABÉTICA");

		List<Funcionario> ordenados = new ArrayList<>(funcionarios);
		ordenados.sort(Comparator.comparing(Funcionario::getName));

		for (Funcionario funcionario : ordenados) {
			String salarioFormatado = nf.format(funcionario.getSalario());
			System.out.printf(" %-14s  %-12s  R$ %-9s  %-13s\n", funcionario.getName(),
					funcionario.getData_nascimento().format(formatter), salarioFormatado, funcionario.getFuncao());
		}
	}

	// 3.11 – Imprimir o total dos salários dos funcionários.
	public static void imprimir_total_salario(List<Funcionario> funcionarios) {
		BigDecimal total = BigDecimal.ZERO;
		for (Funcionario func : funcionarios) {
			total = total.add(func.getSalario());
		}

		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		System.out.println("\nTOTAL DOS SALÁRIOS");
		System.out.printf("Total: %s\n", nf.format(total));

	}

	// 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando
	// que o salário mínimo é R$1212.00.
	public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
		BigDecimal salarioMinimo = new BigDecimal("1212.00");
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		System.out.println("\nSALÁRIOS EM MÚLTIPLOS DO SALÁRIO MÍNIMO");
		System.out.println("Salário Mínimo de referência: " + nf.format(salarioMinimo));

		funcionarios.forEach(func -> {
			BigDecimal qtdSalariosMinimos = func.getSalario().divide(salarioMinimo, 0, RoundingMode.DOWN);
			System.out.printf("%s: %.2f salários mínimos (%s)\n", func.getName(), qtdSalariosMinimos,
					nf.format(func.getSalario()));
		});
	}

}
