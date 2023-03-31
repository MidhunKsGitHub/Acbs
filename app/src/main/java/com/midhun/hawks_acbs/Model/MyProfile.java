package com.midhun.hawks_acbs.Model;

public class MyProfile {
   String name;
   String email;
   String mobile;
   String type;
   String id;
   String device_id;
   String image;
   String pass;
   String user_id;
   String created;
   String updated;
   String status;
   String delete_status;
   String deleted_at;
   String deleted_by;
   String address;
   String latitude;
   String longitude;
   String location;
   String rememberme;
   String error;
   String username;
   String password;

//   public MyProfile(String error,String type) {
//      this.error = error; this.type = type;
//   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public MyProfile(String name, String email, String mobile, String type, String id, String device_id,
                    String image, String pass, String user_id, String created, String updated, String status, String delete_status, String deleted_at, String deleted_by,
                    String address, String latitude, String longitude, String location, String rememberme, String username, String password) {
     // this.error = error;
      this.name = name;
      this.email = email;
      this.mobile = mobile;
      this.type = type;
      this.id = id;
      this.device_id = device_id;
      this.image = image;
      this.pass = pass;
      this.user_id = user_id;
      this.created = created;
      this.updated = updated;
      this.status = status;
      this.delete_status = delete_status;
      this.deleted_at = deleted_at;
      this.deleted_by = deleted_by;
      this.address = address;
      this.latitude = latitude;
      this.longitude = longitude;
      this.location = location;
      this.rememberme = rememberme;
      this.username=username;
      this.password=password;
   }

   public String getName() {
      return name;
   }
   public String getError() {
      return error;
   }
   public void setError(String error) {
      this.error = error;
   }
   public void setName(String name) {
      this.name = name;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getMobile() {
      return mobile;
   }

   public void setMobile(String mobile) {
      this.mobile = mobile;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getDevice_id() {
      return device_id;
   }

   public void setDevice_id(String device_id) {
      this.device_id = device_id;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   public String getUser_id() {
      return user_id;
   }

   public void setUser_id(String user_id) {
      this.user_id = user_id;
   }

   public String getCreated() {
      return created;
   }

   public void setCreated(String created) {
      this.created = created;
   }

   public String getUpdated() {
      return updated;
   }

   public void setUpdated(String updated) {
      this.updated = updated;
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getDelete_status() {
      return delete_status;
   }

   public void setDelete_status(String delete_status) {
      this.delete_status = delete_status;
   }

   public String getDeleted_at() {
      return deleted_at;
   }

   public void setDeleted_at(String deleted_at) {
      this.deleted_at = deleted_at;
   }

   public String getDeleted_by() {
      return deleted_by;
   }

   public void setDeleted_by(String deleted_by) {
      this.deleted_by = deleted_by;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getLatitude() {
      return latitude;
   }

   public void setLatitude(String latitude) {
      this.latitude = latitude;
   }

   public String getLongitude() {
      return longitude;
   }

   public void setLongitude(String longitude) {
      this.longitude = longitude;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public String getRememberme() {
      return rememberme;
   }

   public void setRememberme(String rememberme) {
      this.rememberme = rememberme;
   }
// [{"device_id":null,"id":50,"type":"user","image":"","username":"9947708952","mobile":"9947708952","email":"lekshmi@gmail.com","password":"$2y$10$zOZGwwbxsmy3ECHOKVMQk.ywTijkgqyCQCIhuw9nsG6Efv2niJa\/.","pass":null,"user_id":null,"name":"Lekshmi","created":"2022-10-06","updated":"2022-10-05 22:04:50","status":0,"delete_status":1,"deleted_at":null,"deleted_by":null,"address":"9947708952","latitude":null,"longitude":null,"location":"sdgfg","rememberme":null}]
}
