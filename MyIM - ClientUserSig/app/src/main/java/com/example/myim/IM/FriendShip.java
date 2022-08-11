package com.example.myim.IM;

import android.app.Application;
import android.util.Log;

import com.tencent.imsdk.v2.V2TIMFriendAddApplication;
import com.tencent.imsdk.v2.V2TIMFriendApplication;
import com.tencent.imsdk.v2.V2TIMFriendApplicationResult;
import com.tencent.imsdk.v2.V2TIMFriendInfo;
import com.tencent.imsdk.v2.V2TIMFriendOperationResult;
import com.tencent.imsdk.v2.V2TIMFriendshipListener;
import com.tencent.imsdk.v2.V2TIMFriendshipManager;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;

import java.util.ArrayList;
import java.util.List;

public class FriendShip extends Application {
    V2TIMFriendshipManager manager = V2TIMManager.getFriendshipManager();
    V2TIMFriendshipListener listener = new V2TIMFriendshipListener() {
        @Override
        public void onFriendApplicationListAdded(List<V2TIMFriendApplication> applicationList) {
            Log.e("1234","收到新的好友申请");
            super.onFriendApplicationListAdded(applicationList); }};
    V2TIMValueCallback<V2TIMFriendOperationResult> callback = new V2TIMValueCallback<V2TIMFriendOperationResult>() {
        @Override
        public void onError(int code, String desc) {
            Log.e("1234","错误"+(code)+desc);
        }

        @Override
        public void onSuccess(V2TIMFriendOperationResult v2TIMFriendOperationResult) {
            int code = v2TIMFriendOperationResult.getResultCode();
            String  info = v2TIMFriendOperationResult.getResultInfo();
            Log.e("1234","回调成功" + code+info);
        }
    };

    public void addFriend(String userID){
        manager.setFriendListener(listener);
        V2TIMFriendAddApplication friendAdd = new V2TIMFriendAddApplication(userID);
        friendAdd.setAddType(V2TIMFriendInfo.V2TIM_FRIEND_TYPE_BOTH);
        friendAdd.setUserID(userID);
        manager.addFriend(friendAdd,callback);
    }
    public void deleteFriend(String userid){
        ArrayList<String> userIdArray = new ArrayList<>();
        userIdArray.add(userid);
        manager.deleteFromFriendList(userIdArray,V2TIMFriendInfo.V2TIM_FRIEND_TYPE_BOTH, new V2TIMValueCallback<List<V2TIMFriendOperationResult>>() {
            @Override
            public void onError(int code, String desc) {

            }

            @Override
            public void onSuccess(List<V2TIMFriendOperationResult> v2TIMFriendOperationResults) {

            }
        });

    }
    List<V2TIMFriendApplication> applyList;
    public List<V2TIMFriendApplication> getApplyList() {
        V2TIMValueCallback<V2TIMFriendApplicationResult> callback = new V2TIMValueCallback<V2TIMFriendApplicationResult>() {
            @Override
            public void onError(int code, String desc) {
                Log.e("1234","获取好友申请错误，错误码"+code);

            }

            @Override
            public void onSuccess(V2TIMFriendApplicationResult v2TIMFriendApplicationResult) {
                applyList = v2TIMFriendApplicationResult.getFriendApplicationList();

            }
        };
        manager.getFriendApplicationList(callback);

        return applyList;
    }
    public void acceptFriend(){
        V2TIMFriendApplication friendApplication = new V2TIMFriendApplication();
        manager.acceptFriendApplication(friendApplication,V2TIMFriendInfo.V2TIM_FRIEND_TYPE_BOTH,callback);
    };


}
