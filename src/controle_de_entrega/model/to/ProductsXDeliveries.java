package controle_de_entrega.model.to;

public class ProductsXDeliveries {

	private Integer id;
	private Integer codeDelivery;
	private Integer idProduct;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodeDelivery() {
		return codeDelivery;
	}
	public void setCodeDelivery(Integer codeDelivery) {
		this.codeDelivery = codeDelivery;
	}
	public Integer getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}
}
