package dcomp.es2.locadora.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Locacao {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	private Usuario usuario;
	
	private LocalDate dataLocacao;
	
	private LocalDate dataRetorno;

	private LocalDate dataDevolucao;
	
	private double valor;
	
	@ManyToMany
	private List<Filme> filmes = new ArrayList<>();
	
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public LocalDate getDataLocacao() {
		return dataLocacao;
	}
	
	public void setDataLocacao(LocalDate dataLocacao) {
		this.dataLocacao = dataLocacao;
	}
	
	public LocalDate getDataRetorno() {
		return dataRetorno;
	}
	
	public void setDataRetorno(LocalDate dataRetorno) {
		this.dataRetorno = dataRetorno;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	public List<Filme> getFilmes() {
		return filmes;
	}
	
	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	public LocalDate getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(LocalDate dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	
	
}