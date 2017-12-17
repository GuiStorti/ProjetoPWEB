package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import org.primefaces.event.RowEditEvent;
import sistema.beans.datamodel.PedidoDataModel;
import sistema.modelos.Cliente;
import sistema.modelos.Cliente;
import sistema.modelos.Pedido;
import sistema.modelos.Produto;
import sistema.modelos.Vendedor;
import sistema.service.PedidoService;

@ManagedBean
@ViewScoped
public class PedidoManagedBean {

	private Pedido pedido = new Pedido();
	private Pedido pedidoSelecionado;
	private PedidoService servico = new PedidoService();
	private List<Pedido> pedidos;
	private List<Produto> produtos;
	private Cliente cliente;
	private Vendedor vendedor;
	private List<Cliente> clientes;
	private List<Vendedor> vendedores;

	/**
	 * @return the pedidoSelecionado
	 */
	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	/**
	 * @param pedidoSelecionado
	 *            the pedidoSelecionado to set
	 */
	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = servico.pesquisar(pedidoSelecionado);
	}

	public void salvar() {
		pedido = servico.salvar(pedido);

		if (pedidos != null)
			pedido.setVendedor(vendedor);
			pedido.setCliente(cliente);
			pedidos.add(pedido);

		pedido = new Pedido();

	}
	
	
	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public PedidoService getServico() {
		return servico;
	}

	public void setServico(PedidoService servico) {
		this.servico = servico;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
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

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public DataModel<Pedido> getPedidos() {
		if (pedidos == null)
			pedidos = servico.getPedidos();

		return new PedidoDataModel(pedidos);
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void remove(Pedido pedido) {
		if (servico.pesquisarProdutosPedido(pedido).size() > 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não é possível remover pedido",
					"Pedido possui produtos!"));
		} else {
			servico.remover(pedido);
			pedidos.remove(pedido);
		}
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Produto> getProdutosPedido() {
		if (pedidoSelecionado != null) {
			return servico.pesquisarProdutosPedido(pedidoSelecionado);
		} else
			return null;
	}

	public void onRowEdit(RowEditEvent event) {

		Pedido f = ((Pedido) event.getObject());
		servico.alterar(f);
	}

	
	
}
