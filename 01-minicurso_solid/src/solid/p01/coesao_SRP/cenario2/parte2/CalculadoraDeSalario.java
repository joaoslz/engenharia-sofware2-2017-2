package solid.p01.coesao_SRP.cenario2.parte2;

public class CalculadoraDeSalario {


    public double calcula(Funcionario funcionario) {
    	
    	// return funcionario.getCargo().getRegra().calcula(funcionario);
    	return funcionario.calcula();
     
   
    }


  }



/*	if(Cargo.DESENVOLVEDOR.equals(funcionario.getCargo())) {
        return new RegraDezOuVintePorcento().calcula(funcionario);
    }

    if(Cargo.DBA.equals(funcionario.getCargo()) || Cargo.TESTER.equals(funcionario.getCargo())) {
        return new  RegraQuinzeOuVintePorcento().calcula(funcionario );
    }

    throw new RuntimeException("funcionario invalido");*/