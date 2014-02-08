package controle_de_entrega.model.to;

public class ProductReportDelivery {

	private Integer id;
	private String code;
	private String description;
	private Float value;
	private Float quantidy = 0f;
	private Float valueTotal = 0f;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	public Float getQuantidy() {
		return quantidy;
	}
	public void setQuantidy(Float quantidy) {
		this.quantidy = quantidy;
	}
	public Float getValueTotal() {
		return valueTotal;
	}
	public void setValueTotal(Float valueTotal) {
		this.valueTotal = valueTotal;
	}
	
	
}
