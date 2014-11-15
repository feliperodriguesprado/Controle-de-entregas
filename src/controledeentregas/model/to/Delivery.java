package controledeentregas.model.to;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Delivery {

	private Integer code;
	
	private Date dateCreate;
	private String stringDateCreate;
	
	private Date datePrevision;
	private String stringDatePrevision;
	
	private Date dateDelivery;
	private String stringDateDelivery;
	
	private String description;
	private Float value = 0f;
	
	private Integer idSituation;
	private String situation;

	private SimpleDateFormat simpleDateFormat;

	public Delivery() {
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDateCreateString() {
		stringDateCreate = simpleDateFormat.format(dateCreate);
		return stringDateCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	
	public void setDateCreate(String dateCreate) {
		try {
			this.dateCreate = simpleDateFormat.parse(dateCreate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getDatePrevisionString() {
		stringDatePrevision = simpleDateFormat.format(this.datePrevision);
		return stringDatePrevision;
	}

	public Date getDatePrevision() {
		return datePrevision;
	}

	public void setDatePrevision(Date datePrevision) {
		this.datePrevision = datePrevision;
	}
	
	public void setDatePrevision(String datePrevision) {
		try {
			this.datePrevision = simpleDateFormat.parse(datePrevision);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getDateDeliveryString() {
		if (dateDelivery != null) {
			stringDateDelivery = simpleDateFormat.format(dateDelivery);
						
		} else if (dateDelivery == null) {
			stringDateDelivery = "";
		}
		return stringDateDelivery;
		
	}

	public Date getDateDelivery() {
		return dateDelivery;
	}

	public void setDateDelivery(Date dateDelivery) {
		this.dateDelivery = dateDelivery;
	}
	
	public void setDateDelivery(String dateDelivery) {
		try {
			this.dateDelivery = simpleDateFormat.parse(dateDelivery);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public String getSituation() {
		return situation;
	}
	
	public Integer getIdSituation() {
		return idSituation;
	}

	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	public void setSituation(String situation) {
		this.situation = situation;
	}
	
}
