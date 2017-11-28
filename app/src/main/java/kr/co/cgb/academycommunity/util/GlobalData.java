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

    public static User loginUserData = null;

    public static List<Post> posts = new ArrayList<>();
    public static List<User> users = new ArrayList<>();


    public static void initData() {



        loginUserData = new User(0, "test", "1234", "천고바", "010-1111-1111", 0,
                null, "내소개를시작하지..", Calendar.getInstance(), false);

        users.clear();
        users.add(new User(0, "test", "1234", "천고바", "010-1111-1111", 0,
                null, "내소개를시작하지..", Calendar.getInstance(), false));

        users.add(new User(1, "test1", "1234", "고바형", "010-2222-2222", 0,
                null, "안녕기모찌..", Calendar.getInstance(), false));

        posts.clear();
        posts.add(new Post(0, "타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~타이완 남바완~", 4, users.get(0)));
        posts.add(new Post(1, "아 왜..", 5, users.get(1)));

    }
}
