package kr.co.cgb.academycommunity.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.cgb.academycommunity.data.Post;
import kr.co.cgb.academycommunity.data.User;


/**
 * Created by the on 2017-10-16.
 */

public class GlobalData {

    public static List<Post> posts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();


    public static void initData() {
        posts.clear();
        posts.add(new Post(0, "안녕하세여", Calendar.getInstance(), users.get(0)));

        users.clear();
        users.add(new User(0, "test", "1234", "천고바", "010-1111-1111", 0, null, "내소개를시작하지..", Calendar.getInstance(), false));
    }
}
