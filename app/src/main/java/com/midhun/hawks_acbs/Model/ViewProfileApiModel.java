package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.Profile;

import java.util.List;

public class ViewProfileApiModel {
private ProfileList data;

    public ViewProfileApiModel(ProfileList data) {
        this.data = data;
    }

    public ProfileList getdata() {
        return data;
    }

    public  List<Profile> getProList(){
        return getdata().pageData;
    }

    public void setdata(ProfileList data) {
        this.data = data;
    }

    class ProfileList {
    private List<Profile> pageData;

    public List<Profile> getPageData(){
        return pageData;
    }
    public void setPageData(List<Profile> images){
        this.pageData=pageData;
    }
}

}
