package com.midhun.hawks_acbs.Model;

import com.midhun.hawks_acbs.View.ProductById;

import java.util.List;

public class ViewProductsApiModel {
private ImagesList data;

    public ViewProductsApiModel(ImagesList data) {
        this.data = data;
    }

    public ImagesList getdata() {
        return data;
    }

    public  List<ProductById> getIList(){
        return getdata().images;
    }

    public void setdata(ImagesList data) {
        this.data = data;
    }

    class ImagesList {
    private List<ProductById> images;

    public List<ProductById> getImages(){
        return images;
    }
    public void setImages(List<ProductById> images){
        this.images=images;
    }
}

}
