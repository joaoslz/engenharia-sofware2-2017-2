package solid.p01.coesao_SRP.cenario2.parte2;

public class RegraDezOuVintePorcento implements RegraDeCalculoDoSalario {

	@Override
	public double calcula(Funcionario funcionario) {
		
		if(funcionario.getSalarioBase() > 3000.0) {
            return funcionario.getSalarioBase() * 0.85;
        }
        else {
            return funcionario.getSalarioBase() * 0.9;
        }
	}

}
