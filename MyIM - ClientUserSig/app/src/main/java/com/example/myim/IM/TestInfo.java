package com.example.myim.IM;

import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;

public class TestInfo {
    V2TIMManager manager = V2TIMManager.getInstance();
    V2TIMUserFullInfo myInfo = new V2TIMUserFullInfo();
    V2TIMCallback callback = new V2TIMCallback() {
        @Override
        public void onError(int code, String desc) {

        }

        @Override
        public void onSuccess() {

        }
    };
    private TestInfo(){}

    /**
     * 单例，需调用getInstance获取实例
     * @return
     */
    public static TestInfo getInstance(){
        return new TestInfo();

    }
    public void setMyInfo(){
        manager.setSelfInfo(myInfo,callback);
    }
    public void setFaceUrl(String st){
        myInfo.setFaceUrl(st);
        manager.setSelfInfo(myInfo,callback);
    }
    public void setNickName(String st){
        myInfo.setNickname(st);
        manager.setSelfInfo(myInfo,callback);
    }
    /**设置性别
     * @param nt V2TIM_GENDER_UNKNOWN, V2TIM_GENDER_MALE, V2TIM_GENDER_FEMALE
     */
    public void setGender(int nt){
        myInfo.setGender(nt);
    }
    public String getAllInfo(){
        return myInfo.getUserID()+myInfo.getNickName()+myInfo.getAllowType()+myInfo.getGender();

    }
    public String getUserId(){
        return myInfo.getUserID();
    }
    public String getNickName(){
        return myInfo.getNickName();
    }
    public String getSignature(){
        return myInfo.getSelfSignature();
    }
    public String getAllowType(){
        if(myInfo.getAllowType()==0) {
            return "允许任何人添加好友";
        }else if (myInfo.getAllowType()==1){
            return "添加好友需要验证";
        }else if (myInfo.getAllowType()==2){
            return "拒绝任何人添加好友";
        }
        return  null;
    }

}
