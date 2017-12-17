package sistema.beans.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sistema.modelos.Pedido;
import sistema.service.PedidoService;



@FacesConverter("converterPedido")
public class PedidoConverter implements Converter {

	private PedidoService servico = new PedidoService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {

		
		if (value != null && !value.isEmpty()) {
			
			  for(Pedido f : servico.getPedidos())
				 if(f.getNome().equals(value))
				  	return f;
					
		}

		return null;

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object pedido) {
		if (pedido == null || pedido.equals("")) {
			return null;
		} else {
			return ((Pedido) pedido).getNome();

		}
	}

}
