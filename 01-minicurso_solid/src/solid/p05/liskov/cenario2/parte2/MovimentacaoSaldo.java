package solid.p05.liskov.cenario2.parte2;

class MovimentacaoSaldo {
	
    private double saldo;
    
    public MovimentacaoSaldo() {
    	this.saldo = 0;
	}
    
    public void deposita(double valor) {
        this.saldo += valor;
    }
    
    public void saca(double valor) {
        if (valor <= this.saldo) {
            this.saldo -= valor;
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public double getSaldo() {
        return saldo;
    }



}
