package jdev.mentoria.lojavirtual.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica  extends Pessoa{	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String cpf;
	
	@Temporal(TemporalType.DATE) /*Anotação quando trabalhar com data*/
	private Date dataNascimento;

	private String getCpf() {
		return cpf;
	}

	private void setCpf(String cpf) {
		this.cpf = cpf;
	}

	private Date getDataNascimento() {
		return dataNascimento;
	}

	private void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



}