package com.mealkey.core.web;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class User
{
    @NotNull
     private String name;
}
