package com.levil.mongo.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.*;

import java.util.Date;

/**
 * @author Levi Li
 * @since 03/15/2023
 */


@Data
public abstract class BaseEntity {

    @Id
    private String _id;

    @CreatedBy
    private Integer entryId;

    @CreatedDate
    private Date entryDatetime;

    @LastModifiedBy
    private Integer updateId;

    @LastModifiedDate
    private Date updateDatetime;

    private Integer deleteId;

    private Date deleteDatetime;

    @JsonIgnore
    @Version
    private Integer hVersion;
}
