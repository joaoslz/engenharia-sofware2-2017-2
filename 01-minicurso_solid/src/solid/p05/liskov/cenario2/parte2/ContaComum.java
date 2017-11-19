package solid.p05.liskov.cenario2.parte2;

public class ContaComum implements Investimento {
	
    private MovimentacaoSaldo movimentacaoSaldo;

    public ContaComum() {
        this.movimentacaoSaldo = new MovimentacaoSaldo();
    }

	public void deposita(double valor) {
		// código ...
		movimentacaoSaldo.deposita(valor);
		// código ...
	}

	public void saca(double valor) {
		movimentacaoSaldo.saca(valor);
	}

	/* (non-Javadoc)
	 * @see solid.p05.liskov.cenario2.parte2.Investimento#somaInvestimento()
	 */
	@Override
	public void somaInvestimento() {
		double saldo = movimentacaoSaldo.getSaldo();
        movimentacaoSaldo.deposita( saldo * 0.01 );
	}

	public double getSaldo() {
		return movimentacaoSaldo.getSaldo();
	}
	
	
    
    

 
}