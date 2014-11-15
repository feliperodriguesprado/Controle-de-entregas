package controledeentregas.model.to;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeliveryTO {

	private Integer code;
	
	private Integer idSituation;
	
	private Date dateCreate;
	private String stringDateCreate;
	
	private Date datePrevision;
	private String stringDatePrevision;
	
	private Date dateDelivery;
	private String stringDateDelivery;
	
	private String description;
	private Float value = 0f;

	private SimpleDateFormat simpleDateFormat;

	public DeliveryTO() {
		simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getIdSituation() {
		return idSituation;
	}

	public void setIdSituation(Integer idSituation) {
		this.idSituation = idSituation;
	}

	public String getDateCreateString() {
		if (dateCreate != null) {
			stringDateCreate = simpleDateFormat.format(dateCreate);			
		} else if (dateCreate == null) {
			stringDateCreate = "";
		}
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
		
		if (datePrevision != null) {
			stringDatePrevision = simpleDateFormat.format(this.datePrevision);
		} else if (datePrevision == null) {
			stringDatePrevision = "";
		}
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
		
		if (dateDelivery.equals("")) {
			this.dateDelivery = null;
		} else {
			try {
				this.dateDelivery = simpleDateFormat.parse(dateDelivery);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
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

}
