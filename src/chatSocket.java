import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 熊义杰
 * 服务端,多线程
 */
public class chatSocket extends Thread{
    Socket socket;
    public chatSocket(Socket s){
        socket = s;
    }
    public void out(String msg){
        try{
            new DataOutputStream(socket.getOutputStream()).writeUTF(msg);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String line;
            while ((line = in.readUTF()) != (null)){
                System.out.println(line);
                String str[] = line.split(" ");
                String I_account = str[0];
                String Y_account = str[1];
                String msg = "";
                for(int i=2;i<str.length;i++){
                    if (str[i].indexOf("add") != -1){
                        msg = msg + "";
                    }else {
                        msg = msg + str[i] + " ";
                    }
                }
                if (I_account.equals("####")) {
                    chatManager.getCm().remove(Y_account);
                    chatManager.getCm().onOut(Y_account);
                    System.out.println(Y_account + "已退出!");
                }
                else if(I_account.equals("###@")){
                    chatManager.getCm().sendMsg(Y_account,msg,I_account);
                }
                else if(I_account.equals("##@@"))
                {
                    chatManager.getCm().sendMsg(Y_account,msg,I_account);
                }
                else if(str[2].indexOf("add") != -1){
                    chatManager.getCm().sendApply(I_account,Y_account,msg);
                }
                else {
                    if(msg.equals("####")||msg.equals("###@")||msg.equals("##@@")||msg.equals("#@@@"))
                        msg+=".";
                    chatManager.getCm().sendMsg(I_account, Y_account, msg);
                }
            }
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
