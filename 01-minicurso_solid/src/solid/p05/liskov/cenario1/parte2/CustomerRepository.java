package solid.p05.liskov.cenario1.parte2;

public class CustomerRepository implements DataReadRepository, DataWriteRepository {

	@Override
	public void persist(Object objeto) {
		// TODO salva o objeto no banco de dados
	}

	@Override
	public Object read(long id) {
		// TODO lê o objeto do banco de dados
		return null;
	}


}
