package controledeentregas.model.to;

public class ProductsResearched {

	private Integer id;
	private String code;
	private String description;
	private Float value;
	private Float quantity;
	private boolean select;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setCode(String cod) {
		this.code = cod;
	}

	public String getCode() {
		return code;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}
}
