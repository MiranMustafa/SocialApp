package interfaces;

import java.util.ArrayList;

import model.PostsInfo;

/**
 * Created by Hi-Tech on 10/19/2017.
 */

public interface PostsResponseCallback {
     void onPostsResponseCallback(ArrayList<PostsInfo> postsInfos);
}
