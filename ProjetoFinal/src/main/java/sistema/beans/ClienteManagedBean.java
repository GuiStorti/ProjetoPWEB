package sistema.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;


import sistema.modelos.Cliente;
import sistema.service.ClienteService;

@ManagedBean
@ViewScoped
public class ClienteManagedBean {

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes;
	private ClienteService service = new ClienteService();

	
	public void onRowEdit(RowEditEvent event) {

		Cliente c = ((Cliente) event.getObject());
		service.alterar(c);
	}

	public void salvar() {
		service.salvar(cliente);

		if (clientes != null)
			clientes.add(cliente);

		cliente = new Cliente();

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	public List<Cliente> getClientes() {
		if (clientes == null)
			clientes = service.getClientes();

		return clientes;
	}

	public void remover(Cliente cliente) {
		service.remover(cliente);
		clientes.remove(cliente);

	}
	
}
