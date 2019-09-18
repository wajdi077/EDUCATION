package com.dpc.Scolarity.Domain;

import java.util.List;

import javax.persistence.Column;

import lombok.Data;
@Data
public class Mail {
	
	@Column( nullable = false)
    private String mailFrom;
	
	@Column( nullable = false)
    private String mailTo;
	
	@Column( nullable = true)
    private String mailCc;
	
	@Column( nullable = false)
    private String mailSubject;
    
    @Column( nullable = false)
    private String mailContent;
    
    @Column( nullable = true)
    private String contentType;
 
    private List < Object > attachments;
 
    public Mail() {
      //  contentType = "text/plain";
    }

}
