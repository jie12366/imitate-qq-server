import javax.swing.text.Style;
import java.util.HashMap;
import java.util.Map;

/**
 * 熊义杰
 * 客户端请求管理类
 */
public class chatManager {
    public chatManager(){

    }
    private static final chatManager cm = new chatManager();
    public static chatManager getCm(){
        return cm;
    }
    Map<String,chatSocket> map = new HashMap<>();  //账号映射客户端请求
    public void add(String account,chatSocket chatsocket){
        map.put(account,chatsocket);
    }
    public void remove(String account){
        map.remove(account);
    }

    /**
     * 发消息的方法（用entry遍历map）
     * @param from 来自哪里
     * @param to 发送到哪里
     * @param msg 消息
     */
    public void sendMsg(String from,String to,String msg){
        for (Map.Entry<String,chatSocket> entry : map.entrySet()){
            chatSocket socket = entry.getValue();
            if(entry.getKey().equals(to)){
                socket.out(from + " " + to + " " +msg);
            }
        }
    }

    /**
     * 用户上线
     * @param dialogName 用户账号名
     */
    public void onLine(String dialogName){
        for(Map.Entry<String,chatSocket> entry:map.entrySet()){
            chatSocket socket = entry.getValue();
            if(!entry.getKey().equals(dialogName)){
                socket.out(dialogName+" #### #@@@");
            }
        }

    }
    /**
     * 用户下线
     * @param name 用户账号名
     */
    public void onOut(String name) {

        for (Map.Entry<String, chatSocket> entry : map.entrySet()) {
            chatSocket socket = entry.getValue();
            if (!entry.getKey().equals(name)) {
                socket.out(name + " #### @@@@");
            }
        }
    }

    /**
     * 发添加好友申请的方法（用entry遍历map）
     * @param from 来自哪里
     * @param to 发送到哪里
     * @param msg 消息
     */
    public void sendApply(String from,String to,String msg){
        for (Map.Entry<String,chatSocket> entry : map.entrySet()){
            chatSocket socket = entry.getValue();
            if(entry.getKey().equals(to)){
                socket.out(from + " " + to + " " + "add" + msg);
            }
        }
    }

    /**
     * 接受申请
     * @param name 用户账号名
     */
    public void accept(String name) {

        for (Map.Entry<String, chatSocket> entry : map.entrySet()) {
            chatSocket socket = entry.getValue();
            if (!entry.getKey().equals(name)) {
                socket.out(name + " #### @@@@");
            }
        }
    }
}
