package solid.p01.coesao_SRP.cenario2.parte2;

public enum Cargo {
	
    DESENVOLVEDOR (new RegraDezOuVintePorcento() ),
    DBA (new RegraQuinzeOuVintePorcento() ),
    TESTER (new RegraQuinzeOuVintePorcento() ), 
    ANALISTA( new RegraQuinzeOuVintePorcento() );
    
    private RegraDeCalculoDoSalario regra;

	Cargo(RegraDeCalculoDoSalario regra) {
    	this.regra = regra;
    }
    
	
	public RegraDeCalculoDoSalario getRegra() {
		return regra;
	}
}

