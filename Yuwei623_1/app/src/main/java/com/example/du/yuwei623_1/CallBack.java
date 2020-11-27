package com.example.du.yuwei623_1;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by du on 2018/6/28.
 */

public interface CallBack{
    void Call(String result);

}


/*
class Demo {
public void net (CallBack callBack) {
	new Thread( new Runnable() {
		@Override
		public void run() {
			System.out.println(" 请求");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			callBack.call();
		}
	}).start();
}


	public static void main(String[] args) {

		new Demo().net(new CallBack() {

			@Override
			public void call() {
				System.out.println("call");
			}
		});

	}

 static public interface CallBack{
	 public void  call();
}
}



 */
