package com.resort.kingfisher.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resort.kingfisher.model.ChatMessage;
import com.resort.kingfisher.model.Customer;
import com.resort.kingfisher.model.Owner;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByCustomerSenderAndOwnerSender(Customer customerSender, Owner ownerSender);

	}

