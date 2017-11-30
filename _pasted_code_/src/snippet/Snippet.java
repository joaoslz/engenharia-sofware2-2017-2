package snippet;

public class Snippet {
	public static FilmeBuilder umFilme() {
		
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		builder.filme.setEstoque(2);
		builder.filme.setNome("Filme 1");
		builder.filme.setPrecoLocacao(4.0);
		
		return builder;
	}
}

