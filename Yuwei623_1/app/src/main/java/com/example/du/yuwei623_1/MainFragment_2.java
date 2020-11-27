package com.example.du.yuwei623_1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by du on 2018/6/28.
 */

public class MainFragment_2 extends Fragment{
    public RecyclerView recyclerView;
    private List<Menu> menuList = new ArrayList<>();

    public static MainFragment_2 newInstance(String param1){
        MainFragment_2 fragment_2 = new MainFragment_2();
        Bundle args = new Bundle();
        args.putString("args1",param1);
        fragment_2.setArguments(args);
        return fragment_2;
    }

    public MainFragment_2(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.main_fragment_2,container,false);

        initMenu1();
        initMenu2();
        initMenu3();
        initMenu4();
        initMenu5();
        initMenu6();
        initMenu7();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerView.setAdapter(new MainFragment_2_ViewAdapter(getActivity(),menuList));

        return view;
    }

    public class Menu{
        private String menu_id;
        private String menu_name;
        private String imageId;

        public Menu() {
        }

        public Menu(String menu_id, String menu_name, String imageId) {
            this.menu_id = menu_id;
            this.menu_name = menu_name;
            this.imageId = imageId;
        }

        public String getMenu_id() {
            return menu_id;
        }

        public void setMenu_id(String menu_id) {
            this.menu_id = menu_id;
        }

        public String getMenu_name() {
            return menu_name;
        }

        public void setMenu_name(String menu_name) {
            this.menu_name = menu_name;
        }

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }
    }

    private void initMenu1() {
        Menu m1 = new Menu("37","早餐",url37);
        menuList.add(m1);
        Menu m2 = new Menu("1","家常菜",url1);
        menuList.add(m2);
        Menu m3 = new Menu("155","孕妇",url3);
        menuList.add(m3);
        Menu m4 = new Menu("6","烘焙",url4);
        menuList.add(m4);
    }

    private void initMenu2() {
        Menu m1 = new Menu("37","早餐",url37);
        menuList.add(m1);
        Menu m2 = new Menu("38","午餐",url38);
        menuList.add(m2);
        Menu m3 = new Menu("40","晚餐",url40);
        menuList.add(m3);
        Menu m4 = new Menu("41","宵夜",url41);
        menuList.add(m4);
    }

    private void initMenu3() {
        Menu m1 = new Menu("1","家常菜",url4);
        menuList.add(m1);
        Menu m2 = new Menu("2","快手菜",url2);
        menuList.add(m2);
        Menu m3 = new Menu("3","创意菜",url3);
        menuList.add(m3);
        Menu m4 = new Menu("4","素菜",url4);
        menuList.add(m4);
        Menu m5 = new Menu("5","凉菜",url5);
        menuList.add(m5);
        Menu m6 = new Menu("6","烘焙",url6);
        menuList.add(m6);
        Menu m7 = new Menu("7","面食",url7);
        menuList.add(m7);
        Menu m8 = new Menu("8","汤",url8);
        menuList.add(m8);
    }

    private void initMenu4() {
        Menu m1 = new Menu("10","川菜", url10);
        menuList.add(m1);
        Menu m2 = new Menu("11","粤菜", url11);
        menuList.add(m2);
        Menu m3 = new Menu("12","湘菜", url12);
        menuList.add(m3);
        Menu m4 = new Menu("13","鲁菜", url13);
        menuList.add(m4);
        Menu m5 = new Menu("105","徽菜", url105);
        menuList.add(m5);
        Menu m6 = new Menu("104","苏菜", url104);
        menuList.add(m6);
        Menu m7 = new Menu("102","浙菜", url102);
        menuList.add(m7);
        Menu m8 = new Menu("101","闽菜", url101);
        menuList.add(m8);
    }

    private void initMenu5() {
        Menu m1 = new Menu("73","蛋糕", url73);
        menuList.add(m1);
        Menu m2 = new Menu("74","面包", url74);
        menuList.add(m2);
        Menu m3 = new Menu("75","饼干", url75);
        menuList.add(m3);
        Menu m4 = new Menu("86","甜品", url86);
        menuList.add(m4);
        Menu m5 = new Menu("87","沙拉", url87);
        menuList.add(m5);
        Menu m6 = new Menu("88","饮品", url88);
        menuList.add(m6);
    }

    private void initMenu6() {
        Menu m1 = new Menu("155","孕妇", url155);
        menuList.add(m1);
        Menu m2 = new Menu("156","儿童", url156);
        menuList.add(m2);
        Menu m3 = new Menu("157","幼儿", url157);
        menuList.add(m3);
        Menu m4 = new Menu("158","老年人", url158);
        menuList.add(m4);
        Menu m5 = new Menu("159","考生", url159);
        menuList.add(m5);
        Menu m6 = new Menu("160","月子", url160);
    }

    private void initMenu7() {
        Menu m1 = new Menu("31","养胃", url31);
        menuList.add(m1);
        Menu m2 = new Menu("28","清肺", url28);
        menuList.add(m2);
        Menu m3 = new Menu("29","护肝", url29);
        menuList.add(m3);
        Menu m4 = new Menu("30","减肥", url30);
        menuList.add(m4);
        Menu m5 = new Menu("129","美容", url129);
        menuList.add(m5);
        Menu m6 = new Menu("33","排毒", url33);
        menuList.add(m6);
        Menu m7 = new Menu("34","补血", url34);
        menuList.add(m7);
        Menu m8 = new Menu("153","润肠", url153);
        menuList.add(m8);
    }

    //    String url = "https://www.baidu.com/img/bd_logo1.png";
    String url1 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/1/676_623772.jpg";
    String url2 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/2/1402_925753.jpg";
    String url3 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/2/1915_306893.jpg";
    String url4 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/3/2442_451139.jpg";
    String url5 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/4/3548_777775.jpg";
    String url6 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/5/4323_224573.jpg";
    String url7 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/6/5621_595752.jpg";
    String url8 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/7/6588_403614.jpg";
    String url10 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/8/7464_346208.jpg";
    String url11 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/8/7997_539205.jpg";
    String url12 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/9/8564_805753.jpg";
    String url13 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/9/8817_454863.jpg";
    String url28 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/16/15946_675623.jpg";
    String url29 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/17/16752_134173.jpg";
    String url30 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/10/9419_781236.jpg";
    String url31 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/18/18036_641851.jpg";
    String url33 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/20/19151_796419.jpg";
    String url34 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/14/13662_331023.jpg";
    String url37 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/21/20459_999312.jpg";
    String url38 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/8/7458_713648.jpg";
    String url40 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/23/22069_284419.jpg";
    String url41 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/23/22355_894925.jpg";
    String url73 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/5/4451_978266.jpg";
    String url74 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/38/37386_407870.jpg";
    String url75 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/38/37522_744447.jpg";
    String url86 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/20/19258_178355.jpg";
    String url87 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/41/40455_983947.jpg";
    String url88 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/22/21666_271166.jpg";
    String url101 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/42/41987_326624.jpg";
    String url102 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/25/24170_767509.jpg";
    String url104 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/15/14469_290824.jpg";
    String url105 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/25/24748_934702.jpg";
    String url129 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/11/10209_293599.jpg";
    String url153 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/7/6742_208615.jpg";
    String url155 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/45/44164_153107.jpg";
    String url156 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/21/20425_949966.jpg";
    String url157 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/2/1893_914287.jpg";
    String url158 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/47/46211_848139.jpg";
    String url159 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/47/46326_206550.jpg";
    String url160 = "http://juheimg.oss-cn-hangzhou.aliyuncs.com/cookbook/t/16/15393_450762.jpg";

}
