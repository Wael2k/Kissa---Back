package PFA.project.persistence.dto;

import lombok.Data;


import java.util.Date;
@Data
public class BaseDto {
    private Date created;



    private Date modified;


    private int version;
}
