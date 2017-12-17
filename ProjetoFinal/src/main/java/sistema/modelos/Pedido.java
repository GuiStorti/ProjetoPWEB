package sistema.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;

	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Vendedor vendedor;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="Item_Pedido",
	joinColumns={@JoinColumn(name="pedido_id")},
	inverseJoinColumns={@JoinColumn(name="produto_id")})
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public Pedido(long codigo) {
		super();
		this.codigo = codigo;
	}
	
	public Pedido() {
	}

	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void addProduto(Produto produto)
	{
		produtos.add(produto);
		
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return true;
	}

	
	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + "]";
	}

	

	
	
	
	
}
