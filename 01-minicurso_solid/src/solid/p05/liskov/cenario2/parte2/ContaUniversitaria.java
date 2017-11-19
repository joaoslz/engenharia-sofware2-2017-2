package solid.p05.liskov.cenario2.parte2;

public class ContaUniversitaria {
	
	private MovimentacaoSaldo movimentacaoSaldo = new MovimentacaoSaldo();

    private int milhas;

    public void deposita(double valor) {
        movimentacaoSaldo.deposita(valor);
        this.milhas += (int)valor;
    }

     public void saca(double valor) {
		movimentacaoSaldo.saca(valor);
	}



	public double getSaldo() {
		return movimentacaoSaldo.getSaldo();
	}



	public int getMilhas() {
        return milhas;
    }
}

