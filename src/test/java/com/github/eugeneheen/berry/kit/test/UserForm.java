package com.github.eugeneheen.berry.kit.test;

import lombok.Data;

/**
 * 新增用户信息Form实体
 *
 * @author Eugene
 * 2018-12-04 10:24
 */
@Data
public class UserForm {

    private String name;

    private String nickName;

    private Integer gendaer;

    private String identityCardNo;

    private String creator;

    private Long parentId;
}
