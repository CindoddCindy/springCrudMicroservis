package com.trymicroservice.mymicroservicetry.rest.validation;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.trymicroservice.mymicroservicetry.rest.dto.AuserDTO;

public class UserDtoValidator implements BaseValidator<AuserDTO> {

    private final ShelfDtoValidator shelfDtoValidator;

    @Autowired
    public UserDtoValidator(ShelfDtoValidator shelfDtoValidator) {
        this.shelfDtoValidator = shelfDtoValidator;
    }

    @Override
    public void validate(WarehouseDto obj) {
        validateUniqueShelfCodes(obj.getShelves());
    }

    private void validateUniqueShelfCodes(List<ShelfDto> shelfDtos) {
        Set<String> uniqueCodes = new HashSet<String>();
        for (ShelfDto shelfDto : shelfDtos) {
            String code = shelfDto.getCode();

            // Check if list contains two or more identical codes
            if (uniqueCodes.contains(code))
                throw new FieldNotUniqueException(code);
            else
                // Check if code is already committed
                shelfDtoValidator.validate(shelfDto);
            uniqueCodes.add(code);
        }
    }

}
