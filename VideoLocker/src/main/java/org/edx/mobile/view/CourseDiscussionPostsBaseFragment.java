package org.edx.mobile.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.inject.Inject;

import org.edx.mobile.R;
import org.edx.mobile.discussion.DiscussionThread;
import org.edx.mobile.model.api.EnrolledCoursesResponse;
import org.edx.mobile.task.SetThreadReadTask;
import org.edx.mobile.view.adapters.DiscussionPostsAdapter;
import org.edx.mobile.view.adapters.InfiniteScrollUtils;

import org.edx.mobile.base.BaseFragment;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;


public abstract class CourseDiscussionPostsBaseFragment extends BaseFragment implements InfiniteScrollUtils.PageLoader<DiscussionThread> {

    @InjectView(R.id.discussion_posts_listview)
    ListView discussionPostsListView;

    @InjectExtra(Router.EXTRA_COURSE_DATA)
    EnrolledCoursesResponse courseData;

    @Inject
    DiscussionPostsAdapter discussionPostsAdapter;

    @Inject
    Router router;

    InfiniteScrollUtils.InfiniteListController controller;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = InfiniteScrollUtils.configureListViewWithInfiniteList(discussionPostsListView, discussionPostsAdapter, this);
        discussionPostsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getContext();
                DiscussionThread thread = discussionPostsAdapter.getItem(position);
                router.showCourseDiscussionResponses(context, thread, courseData);
                if (!thread.isRead()) {
                    new SetThreadReadTask(context, thread, true).execute();
                    // Refresh the row to mark it as read immediately,
                    // pending the response from the server.
                    thread.setRead(true);
                    discussionPostsAdapter.getView(position, view, parent);
                }
            }
        });
    }
}
