package com.example.warehouse.pojo.Auth;


import java.util.Date;
import java.util.List;

/**
* 权限表
* @TableName auth_info
*/
public class AuthInfo{

    private Integer authId;

    private Integer parentId;

    private String authName;

    private String authDesc;

    private Integer authGrade;

    private String authType;

    private String authUrl;

    private String authCode;

    private Integer authOrder;

    private String authState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

    private List<AuthInfo> ChildAuth;

    public List<AuthInfo> getChildAuth() {
        return ChildAuth;
    }

    public void setChildAuth(List<AuthInfo> childAuth) {
        ChildAuth = childAuth;
    }

    public void setAuthId(Integer authId){
    this.authId = authId;
    }


    public void setParentId(Integer parentId){
    this.parentId = parentId;
    }


    public void setAuthName(String authName){
    this.authName = authName;
    }

    public void setAuthDesc(String authDesc){
    this.authDesc = authDesc;
    }

    public void setAuthGrade(Integer authGrade){
    this.authGrade = authGrade;
    }

    public void setAuthType(String authType){
    this.authType = authType;
    }

    public void setAuthUrl(String authUrl){
    this.authUrl = authUrl;
    }

    public void setAuthCode(String authCode){
    this.authCode = authCode;
    }

    public void setAuthOrder(Integer authOrder){
    this.authOrder = authOrder;
    }

    public void setAuthState(String authState){
    this.authState = authState;
    }

    public void setCreateBy(Integer createBy){
    this.createBy = createBy;
    }

    public void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    public void setUpdateBy(Integer updateBy){
    this.updateBy = updateBy;
    }

    public void setUpdateTime(Date updateTime){
    this.updateTime = updateTime;
    }


    public Integer getAuthId(){
    return this.authId;
    }

    public Integer getParentId(){
    return this.parentId;
    }

    public String getAuthName(){
    return this.authName;
    }

    public String getAuthDesc(){
    return this.authDesc;
    }

    public Integer getAuthGrade(){
    return this.authGrade;
    }

    public String getAuthType(){
    return this.authType;
    }

    public String getAuthUrl(){
    return this.authUrl;
    }

    public String getAuthCode(){
    return this.authCode;
    }

    public Integer getAuthOrder(){
    return this.authOrder;
    }

    public String getAuthState(){
    return this.authState;
    }

    public Integer getCreateBy(){
    return this.createBy;
    }

    public Date getCreateTime(){
    return this.createTime;
    }

    public Integer getUpdateBy(){
    return this.updateBy;
    }

    public Date getUpdateTime(){
    return this.updateTime;
    }

}
