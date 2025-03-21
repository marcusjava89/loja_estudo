package loja.estudo.spring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import loja.estudo.spring.enums.StatusContaPagar;

@Entity
@Table(name = "conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", allocationSize = 1, initialValue = 1)
public class ContaPagar implements Serializable{

	/*Minha conta a pagar e não do cliente.*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
	private Long id;
	
	@Column (nullable = false)
	private String descricao;
	
	@Column (nullable = false)
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;

	@Column (nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusContaPagar status;

	@Column (nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtVencimento; 
	
	@Temporal(TemporalType.DATE)
	private Date dtPagamento;

	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;

	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_forn_id", nullable = false,
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_forn_fk"))
	private Pessoa pessoa_fornecedor;

	private Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	private String getDescricao() {
		return descricao;
	}

	private void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	private BigDecimal getValorTotal() {
		return valorTotal;
	}

	private void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	private Date getDtVencimento() {
		return dtVencimento;
	}

	private void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	private Date getDtPagamento() {
		return dtPagamento;
	}

	private void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	private StatusContaPagar getStatus() {
		return status;
	}

	private void setStatus(StatusContaPagar status) {
		this.status = status;
	}

	private Pessoa getPessoa() {
		return pessoa;
	}

	private void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	private Pessoa getPessoa_fornecedor() {
		return pessoa_fornecedor;
	}

	private void setPessoa_fornecedor(Pessoa pessoa_fornecedor) {
		this.pessoa_fornecedor = pessoa_fornecedor;
	}
	
	private BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	private void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaPagar other = (ContaPagar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}