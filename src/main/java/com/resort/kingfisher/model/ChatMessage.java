package com.resort.kingfisher.model;





import jakarta.persistence.*;
@Entity
@Table(name = "tbl_chatmessages")
public class ChatMessage {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String content;
	    
	    @ManyToOne
	    private Customer customerSender;
	    
	    @ManyToOne
	    private Owner ownerSender;
	    
	    @ManyToOne
	    private Customer customerReceiver;
	    
	    @ManyToOne
	    private Owner ownerReceiver;
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Customer getCustomerSender() {
			return customerSender;
		}

		public void setCustomerSender(Customer customerSender) {
			this.customerSender = customerSender;
		}

		public Owner getOwnerSender() {
			return ownerSender;
		}

		public void setOwnerSender(Owner ownerSender) {
			this.ownerSender = ownerSender;
		}

		public Customer getCustomerReceiver() {
			return customerReceiver;
		}

		public void setCustomerReceiver(Customer customerReceiver) {
			this.customerReceiver = customerReceiver;
		}

		public Owner getOwnerReceiver() {
			return ownerReceiver;
		}

		public void setOwnerReceiver(Owner ownerReceiver) {
			this.ownerReceiver = ownerReceiver;
		}

		public ChatMessage(String content, Customer customerSender, Owner ownerSender, Customer customerReceiver,
				Owner ownerReceiver) {
			super();
			this.content = content;
			this.customerSender = customerSender;
			this.ownerSender = ownerSender;
			this.customerReceiver = customerReceiver;
			this.ownerReceiver = ownerReceiver;
		}

		public ChatMessage() {
			
		}

		@Override
		public String toString() {
			return "ChatMessage [id=" + id + ", content=" + content + ", customerSender=" + customerSender
					+ ", ownerSender=" + ownerSender + ", customerReceiver=" + customerReceiver + ", ownerReceiver="
					+ ownerReceiver + "]";
		}
	
 
}