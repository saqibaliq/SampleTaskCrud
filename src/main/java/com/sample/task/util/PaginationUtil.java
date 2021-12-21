package com.sample.task.util;


import io.swagger.annotations.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@ApiModel(value = "Page DTO")
public class PaginationUtil {

    @NotNull(message = "Current Page can not be Null")
    @NotBlank(message = "Current page can not be blank")
    @ApiModelProperty(value = "Current page", notes = "Current page can not be Null", required = true)
    private Integer currentPage;

    @NotNull(message = "Items per Pages can not be Null")
    @NotBlank(message = "Items per Pages can not be blank")
    @ApiModelProperty(value = "Items per Pages", notes = "Items per Pages can not be Null", required = true)
    private Integer itemsPerPage;

    @NotNull(message = "Sort By can not be Null")
    @NotBlank(message = "Sort By can not be blank")
    @ApiModelProperty(value = "Sort By", notes = "Sort By can not be Null")
    private String sortBy;

    @NotNull(message = "Direction can not be Null")
    @NotBlank(message = "Direction can not be blank")
    @ApiModelProperty(value = "Direction", notes = "Direction can not be Null")
    private String direction;

    @Override
    public String toString() {
        return "PaginationUtil{" +
                "currentPage=" + currentPage +
                ", itemsPerPages=" + itemsPerPage +
                ", sortBy='" + sortBy + '\'' +
                ", direction='" + direction + '\'' +
                '}';
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
