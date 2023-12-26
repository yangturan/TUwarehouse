package com.example.warehouse.pojo.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRole {
    private int userId;
    private List<String> roleCheckList;
}
