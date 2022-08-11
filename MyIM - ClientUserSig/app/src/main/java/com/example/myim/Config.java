package com.example.myim;

import com.example.myim.IM_Activity.Contact;
import com.example.myim.IM_Activity.Conversation;
import com.example.myim.IM_Activity.Person;

/** 存放静态配置信息 */
public class Config {

    /** 腾讯云IM的APPID */
    public static final int SDKAPPID = 1400551195; // 您的 SDKAppID
    /** 腾讯云IM的SECRETKEY */
    public static final String SECRETKEY = "3780c6d3e752168dba355a9038936ee9860b0b2ef23567d11a0f7a65655a7e34";
    /** 腾讯云直播的licenceURL */
    public static final String licenceURL = "https://license.vod2.myqcloud.com/license/v1/6a3c46830e0140ac11d2a4781429acc4/TXLiveSDK.licence"; // 获取到的 licence url
    /** 腾讯云直播的licenceKey */
    public static final String licenceKey = "ee5cfd518b087595fafbf0be6d93a7c5"; // 获取到的 licence key
    /** IM底部菜单”tool“按钮 */
    public static int btnTools = R.id.btnMy;
    /** IM底部菜单“联系人”按钮 */
    public static int btnContact = R.id.btnContact;
    /** IM底部菜单“会话”按钮 */
    public static int btnMsg = R.id.btnMsg;
    /** 用户ID UerId */
    public static String userid = MainActivity.userid;
    /** 用户签名 UserSig */
    public static String usersig;
    /** 类 */
    public static Class<Conversation> a = Conversation.class;
    public static Class<Contact> b = Contact.class;
    public static Class<Person> c = Person.class;

}
