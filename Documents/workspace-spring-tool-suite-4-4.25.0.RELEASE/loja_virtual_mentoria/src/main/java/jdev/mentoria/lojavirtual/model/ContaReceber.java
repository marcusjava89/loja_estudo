package jdev.mentoria.lojavirtual.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jdev.mentoria.lojavirtual.enums.StatusContaReceber;

@Entity
@Table(name = "conta_receber")
@SequenceGenerator(name = "seq_conta_receber", sequenceName = "seq_conta_receber", allocationSize = 1, initialValue = 1)
public class ContaReceber  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_receber")
	private Long id;
	
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private StatusContaReceber status;
	
	@Temporal(TemporalType.DATE)
	private Date dtVencimento;
	
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;

	/*ContaReceber tem relacionamento com Pessoa. Muitas contas a receber para uma pessoa.*/		
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false, 
	foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;

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

	private StatusContaReceber getStatus() {
		return status;
	}

	private void setStatus(StatusContaReceber status) {
		this.status = status;
	}

	private Date getDtVencimento() {
		return dtVencimento;
	}

	private void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	private BigDecimal getValorTotal() {
		return valorTotal;
	}

	private void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	private BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	private void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	private Pessoa getPessoa() {
		return pessoa;
	}

	private void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		ContaReceber other = (ContaReceber) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}