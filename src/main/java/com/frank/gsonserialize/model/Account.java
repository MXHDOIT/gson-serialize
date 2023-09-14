package com.frank.gsonserialize.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: maxinhang.
 * @version: 2023/9/13 17:26.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Since(1.0)
    @Expose
    private String name;
    @Since(1.0)
    private int age;
    @Since(1.0)
    @SerializedName(value = "gender",alternate = {"Gender","GENDER"})
    private String gender;

    @Since(1.1)
    private String area;
}
