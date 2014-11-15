package controledeentregas.model.to;

public class ProductsTO {

	private Integer id;
	private boolean ativo;
	private String cod;
	private String description;
	private Float value;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
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

	public Float getValue() {
		return value;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getCod() {
		return cod;
	}
}
