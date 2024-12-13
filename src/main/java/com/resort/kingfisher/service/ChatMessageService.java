package com.resort.kingfisher.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.kingfisher.model.ChatMessage;
import com.resort.kingfisher.model.Customer;

import com.resort.kingfisher.model.Owner;
import com.resort.kingfisher.repo.ChatMessageRepository;


import java.util.List;
@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public List<ChatMessage> getChatMessages(Customer customer, Owner owner) {
        return chatMessageRepository.findByCustomerSenderAndOwnerSender(customer, owner);
    }

    public ChatMessage saveChatMessage(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

	public List<ChatMessage> getCustomerChatMessages(Owner owner) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
