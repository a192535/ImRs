package com.example.myim.IM;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tencent.imsdk.v2.V2TIMCreateGroupMemberInfo;
import com.tencent.imsdk.v2.V2TIMGroupInfo;
import com.tencent.imsdk.v2.V2TIMGroupManager;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMValueCallback;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Group {
    V2TIMGroupManager groupManager = V2TIMManager.getGroupManager();
    V2TIMGroupInfo groupInfo = new V2TIMGroupInfo();
    List<V2TIMCreateGroupMemberInfo> memberInfo = new List<V2TIMCreateGroupMemberInfo>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<V2TIMCreateGroupMemberInfo> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(V2TIMCreateGroupMemberInfo v2TIMCreateGroupMemberInfo) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends V2TIMCreateGroupMemberInfo> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends V2TIMCreateGroupMemberInfo> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public V2TIMCreateGroupMemberInfo get(int index) {
            return null;
        }

        @Override
        public V2TIMCreateGroupMemberInfo set(int index, V2TIMCreateGroupMemberInfo element) {
            return null;
        }

        @Override
        public void add(int index, V2TIMCreateGroupMemberInfo element) {

        }

        @Override
        public V2TIMCreateGroupMemberInfo remove(int index) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<V2TIMCreateGroupMemberInfo> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<V2TIMCreateGroupMemberInfo> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<V2TIMCreateGroupMemberInfo> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    /**
     * 创建群聊
     * @param groupId 必填
     * @param groupName 可以为null
     * @param groupType 必填，可选字段：V2TIMManager.GROUP_TYPE_XXX
     */
    public void createGrout(String groupId,String groupName,String groupType){
        groupInfo.setGroupID(groupId);
        if(groupName!=null){
            groupInfo.setGroupName(groupName);
        }
        groupInfo.setGroupType(groupType);
        groupManager.createGroup(groupInfo,memberInfo, new V2TIMValueCallback<String>() {
            @Override
            public void onError(int code, String desc) {

            }

            @Override
            public void onSuccess(String s) {

            }
        });
    }
}
