// IMyAidlInterface.aidl
package cn.zdh.myaidl;

// Declare any non-default types here with import statements
//使用AIDL需要注意下面几点

//1：同包也要导包
//2: 支持基础类型数据传递，自定义类需要实现parcelable序列化
//3：自定义的类名和包名必须和 AIDL的类名包名一致
//4：in 服务端 不能修改客服端的入参数数据（bean），但是可以获取参数数据
//5：out 服务端 能修改客服端的入参数据（bean），但是并不可以获取参数数据
//6：inout 服务 能修改客服端的入参数据（bean），也可以获取参数数据 ，但是消耗性能最大
//7：注意 使用out inout Java的bean对象需要写 readFromParcel(Parcel reply){}方法，在方法里面把字段读取出来
//8：Java是service 是在子线程
import cn.zdh.myaidl.Bean;

interface IMyAidlInterface {

void login(in Bean bean);


}
