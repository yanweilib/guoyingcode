package com.yw.firstlib.util.sharedpreference;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by YSB on 2017/2/25.
 */

public class LoginParentModel {

    /**
     * errorCode : 200
     * returnResult : {"age":28,"area":"平定","birthday":1484668800000,"city":"阳泉","classid":1,"classname":"小一班","createtime":1484706545000,"email":"496512474@qq.com","emailisactive":1,"id":1,"idcard":"140321198811021230","islock":"n","lifestatus":1,"nickimg":"","nickname":"至尊宝","offset":0,"pageSize":10,"pagerSize":0,"password":"e10adc3949ba59abbe56e057f20f883e","province":"山西","realname":"张鹏超","sex":1,"tel":"18801449982","total":0,"updatetime":1484706550000,"usercode":"0909010","username":"18801449982"}
     * showMessage : 登录成功
     */

    private String errorCode;
    private LoginParentModel.ReturnResultBean returnResult;
    private String showMessage;

    public static LoginParentModel objectFromData(String str) {

        return new Gson().fromJson(str, LoginParentModel.class);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public LoginParentModel.ReturnResultBean getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(LoginParentModel.ReturnResultBean returnResult) {
        this.returnResult = returnResult;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }

    public static class ReturnResultBean implements Parcelable {
        /**
         * age : 28
         * area : 平定
         * birthday : 1484668800000
         * city : 阳泉
         * classid : 1
         * classname : 小一班
         * createtime : 1484706545000
         * email : 496512474@qq.com
         * emailisactive : 1
         * id : 1
         * idcard : 140321198811021230
         * islock : n
         * lifestatus : 1
         * nickimg :
         * nickname : 至尊宝
         * offset : 0
         * pageSize : 10
         * pagerSize : 0
         * password : e10adc3949ba59abbe56e057f20f883e
         * province : 山西
         * realname : 张鹏超
         * sex : 1
         * tel : 18801449982
         * total : 0
         * updatetime : 1484706550000
         * usercode : 0909010
         * username : 18801449982
         */

        private String age;
        private String area;
        private String birthday;
        private String city;
        private String classid;
        private String classname;
        private String createtime;
        private String email;
        private String emailisactive;
        private String id;
        private String idcard;
        private String islock;
        private String lifestatus;
        private String nickimg;
        private String nickname;
        private String offset;
        private String pageSize;
        private String pagerSize;
        private String password;
        private String province;
        private String realname;
        private String sex;
        private String tel;
        private String total;
        private String updatetime;
        private String usercode;
        private String username;
        private String kcode;

        private boolean autologin;//添加，是否自动登录

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getBirthday() {
            if(null==birthday)
            {
                birthday=new Date().getTime()+"";
            }
            if("null".equals(birthday))
            {
                birthday=new Date().getTime()+"";
            }
            if("".equals(birthday))
            {
                birthday=new Date().getTime()+"";
            }
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getClassid() {
            return classid;
        }

        public void setClassid(String classid) {
            this.classid = classid;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmailisactive() {
            return emailisactive;
        }

        public void setEmailisactive(String emailisactive) {
            this.emailisactive = emailisactive;
        }

        public String getId() {
            if(null==id)
            {
                id="0";
            }
            if("null".equals(id))
            {
                id="0";
            }
            if("".equals(id))
            {
                id="0";
            }
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getIslock() {
            return islock;
        }

        public void setIslock(String islock) {
            this.islock = islock;
        }

        public String getLifestatus() {
            return lifestatus;
        }

        public void setLifestatus(String lifestatus) {
            this.lifestatus = lifestatus;
        }

        public String getNickimg() {
            return nickimg;
        }

        public void setNickimg(String nickimg) {
            this.nickimg = nickimg;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOffset() {
            return offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public String getPagerSize() {
            return pagerSize;
        }

        public void setPagerSize(String pagerSize) {
            this.pagerSize = pagerSize;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getSex() {
            if(null==sex)
            {
                sex="0";
            }
            if("null".equals(sex))
            {
                sex="0";
            }
            if("".equals(sex))
            {
                sex="0";
            }

            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getUsercode() {
            return usercode;
        }

        public void setUsercode(String usercode) {
            this.usercode = usercode;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isAutologin() {
            return autologin;
        }

        public void setAutologin(boolean autologin) {
            this.autologin = autologin;
        }

        public String getKcode() {
            return kcode;
        }

        public void setKcode(String kcode) {
            this.kcode = kcode;
        }

        public ReturnResultBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.age);
            dest.writeString(this.area);
            dest.writeString(this.birthday);
            dest.writeString(this.city);
            dest.writeString(this.classid);
            dest.writeString(this.classname);
            dest.writeString(this.createtime);
            dest.writeString(this.email);
            dest.writeString(this.emailisactive);
            dest.writeString(this.id);
            dest.writeString(this.idcard);
            dest.writeString(this.islock);
            dest.writeString(this.lifestatus);
            dest.writeString(this.nickimg);
            dest.writeString(this.nickname);
            dest.writeString(this.offset);
            dest.writeString(this.pageSize);
            dest.writeString(this.pagerSize);
            dest.writeString(this.password);
            dest.writeString(this.province);
            dest.writeString(this.realname);
            dest.writeString(this.sex);
            dest.writeString(this.tel);
            dest.writeString(this.total);
            dest.writeString(this.updatetime);
            dest.writeString(this.usercode);
            dest.writeString(this.username);
            dest.writeString(this.kcode);
            dest.writeByte(this.autologin ? (byte) 1 : (byte) 0);
        }

        protected ReturnResultBean(Parcel in) {
            this.age = in.readString();
            this.area = in.readString();
            this.birthday = in.readString();
            this.city = in.readString();
            this.classid = in.readString();
            this.classname = in.readString();
            this.createtime = in.readString();
            this.email = in.readString();
            this.emailisactive = in.readString();
            this.id = in.readString();
            this.idcard = in.readString();
            this.islock = in.readString();
            this.lifestatus = in.readString();
            this.nickimg = in.readString();
            this.nickname = in.readString();
            this.offset = in.readString();
            this.pageSize = in.readString();
            this.pagerSize = in.readString();
            this.password = in.readString();
            this.province = in.readString();
            this.realname = in.readString();
            this.sex = in.readString();
            this.tel = in.readString();
            this.total = in.readString();
            this.updatetime = in.readString();
            this.usercode = in.readString();
            this.username = in.readString();
            this.kcode = in.readString();
            this.autologin = in.readByte() != 0;
        }

        public static final Creator<ReturnResultBean> CREATOR = new Creator<ReturnResultBean>() {
            @Override
            public ReturnResultBean createFromParcel(Parcel source) {
                return new ReturnResultBean(source);
            }

            @Override
            public ReturnResultBean[] newArray(int size) {
                return new ReturnResultBean[size];
            }
        };
    }
}
