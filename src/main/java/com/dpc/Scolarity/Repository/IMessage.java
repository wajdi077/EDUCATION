package com.dpc.Scolarity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.Scolarity.Domain.Message;



public interface IMessage extends JpaRepository<Message, Long> {

}
