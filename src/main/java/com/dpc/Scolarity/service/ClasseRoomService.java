package com.dpc.Scolarity.service;

import com.dpc.Scolarity.Domain.Classes;
import com.dpc.Scolarity.Dto.ClasseRoomDTO;


/**
 * Service Interface for managing ClasseRoom.
 */
public interface ClasseRoomService {

  
    ClasseRoomDTO save(ClasseRoomDTO classeRoomDTO);

  
    ClasseRoomDTO findOne(Long id);

  
    void delete(Long id);
}
