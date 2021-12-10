package com.bridgelabz.atmsystem.service;

import com.bridgelabz.atmsystem.dto.AtmDto;
import com.bridgelabz.atmsystem.entity.AtmEntity;
import com.bridgelabz.atmsystem.exception.AtmCustomException;
import com.bridgelabz.atmsystem.repository.AtmRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose : To implement AtmService class in atm-system Program.
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
@Service
public class AtmService {

    private static final String ATM_ADDED_SUCCESSFULLY = "Atm Added Successfully!";
    private static final String ATM_UPDATED_SUCCESSFULLY = "Atm Updated Successfully!";
    private static final String ATM_DELETED_SUCCESSFULLY = "Atm Deleted Successfully!";
    private static Logger logger = LoggerFactory.getLogger(AtmService.class);

    @Autowired
    private AtmRepo atmRepo;

    private ModelMapper modelMapper = new ModelMapper();

    /**
     * Purpose : To get the list of all card details.
     *
     * @return : All the atm card details .
     */
    public List<AtmDto> getAllAtm() {
        return atmRepo.findAll().stream()
                .map(atmEntity -> modelMapper.map(atmEntity, AtmDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Purpose : To Add card details in the atmSystem .
     *
     * @param atmDto : atmDto is used to add card details as per regex pattern.
     * @return : message that is atm card is added successfully!
     */
    public String addAtm(AtmDto atmDto) {
        AtmEntity atmEntity = modelMapper.map(atmDto, AtmEntity.class);
        atmRepo.save(atmEntity);
        logger.info(ATM_ADDED_SUCCESSFULLY);
        return ATM_ADDED_SUCCESSFULLY;
    }

    /**
     * Purpose : To get atm details by id.
     *
     * @param id : As per the id we get card details.
     * @return : atmEntity that is found by id.
     * @throws : AtmCustomException with message Invalid ATM Id.
     */
    private AtmEntity getAtmById(int id) {
        AtmEntity atmAppEntity = atmRepo.findById(id)
                .orElseThrow(() -> new AtmCustomException(
                        "Invalid ATM Id -> " + id));
        return atmAppEntity;
    }

    /**
     * Purpose : To Update card details in the atmSystem.
     *
     * @param id     : As per id card details will be updated.
     * @param atmDto : atmDto is used to update card details as per regex pattern.
     * @return : message that is atm updated successfully.
     */
    public String updateAtm(int id, AtmDto atmDto) {
        AtmEntity atmEntity = getAtmById(id);
        modelMapper.map(atmDto, atmEntity);
        atmRepo.save(atmEntity);
        logger.info(ATM_UPDATED_SUCCESSFULLY);
        return ATM_UPDATED_SUCCESSFULLY;
    }

    /**
     * Purpose : To Delete card details in the data.
     *
     * @param id : As per id card details will be deleted.
     * @return : message that is atm card is deleted successfully!
     */
    public String delete(int id) {
        AtmEntity atmEntity = getAtmById(id);
        atmRepo.delete(atmEntity);
        logger.info(ATM_DELETED_SUCCESSFULLY);
        return ATM_DELETED_SUCCESSFULLY;
    }
}

