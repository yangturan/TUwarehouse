package com.example.warehouse.pojo.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleAuthUtil {
    private Integer roleId;
    private List<Integer> authIds;
}
