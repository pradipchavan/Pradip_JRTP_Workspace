package com.pradip.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Setter
@Getter
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	
	private String name;

    private String email;

    private String password;

    private Long phoneNo;

    private String pwdUpdated;

    @CreationTimestamp
    @Column(name = "date_created", updatable = false)//when record updated dateCreated should not become null
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    @Column(name = "last_updated", insertable = false)//when record insert lastUpdated should not be inserted
    private LocalDateTime lastUpdated;

}
