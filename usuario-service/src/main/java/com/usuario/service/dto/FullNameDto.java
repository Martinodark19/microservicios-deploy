package com.usuario.service.dto;

@Data
public class FullNameDto 
{
    private String name;
    private String lastName;

    public FullNameDto(String name,String lastName)
    {
        this.name = name;
        this.lastName=lastName;
    }


}
