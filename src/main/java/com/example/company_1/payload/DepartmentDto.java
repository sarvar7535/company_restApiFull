package com.example.company_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class DepartmentDto {

    @NotNull(message = "name bo`sh bo`lmasligi kerak")
    private String name;
    @NotNull(message = "companyId bo`sh bo`lmasligi kerak")
    private Integer companyId;
}
