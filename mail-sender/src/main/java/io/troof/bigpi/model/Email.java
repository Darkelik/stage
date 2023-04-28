package io.troof.bigpi.model;

public class Email {
	
	private String id;
    private String adress;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id) {
		this.id = id;
	}
    
    public String getAdress() {
    	return adress;
    }
    
    public void setAdress(String adress) {
		this.adress = adress;
	}
    
    public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

    public Email() {
    	adress = null;
    	cc = null;
    	bcc = null;
    	subject = null;
    	body = null;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((cc == null) ? 0 : cc.hashCode());
		result = prime * result + ((bcc == null) ? 0 : bcc.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		return result;
	}
}
