package com.fullmanchiu.samples.todedatabinding.tasks;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.fullmanchiu.samples.todedatabinding.BR;
import com.fullmanchiu.samples.todedatabinding.R;

import static com.fullmanchiu.samples.todedatabinding.tasks.TasksFilterType.ACTIVE_TASKS;
import static com.fullmanchiu.samples.todedatabinding.tasks.TasksFilterType.ALL_TASKS;
import static com.fullmanchiu.samples.todedatabinding.tasks.TasksFilterType.COMPLETED_TASKS;

public class TasksViewModel extends BaseObservable {
    int mTaskListSize = 0;
    private final TasksContract.Presenter mPresenter;

    private Context mContext;

    public TasksViewModel(Context context, TasksContract.Presenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    @Bindable
    public String getCurrentFilteringLabel() {
        switch (mPresenter.getFiltering()) {
            case ALL_TASKS:
                return mContext.getResources().getString(R.string.label_all);
            case ACTIVE_TASKS:
                return mContext.getResources().getString(R.string.label_active);
            case COMPLETED_TASKS:
                return mContext.getResources().getString(R.string.label_completed);
        }
        return null;
    }

    @Bindable
    public String getNoTasksLabel() {
        switch (mPresenter.getFiltering()) {
            case ALL_TASKS:
                return mContext.getResources().getString(R.string.no_tasks_all);
            case ACTIVE_TASKS:
                return mContext.getResources().getString(R.string.no_tasks_active);
            case COMPLETED_TASKS:
                return mContext.getResources().getString(R.string.no_tasks_completed);
        }
        return null;
    }

    @Bindable
    public Drawable getNoTaskIconRes() {
        switch (mPresenter.getFiltering()) {
            case ALL_TASKS:
                return mContext.getResources().getDrawable(R.drawable.ic_assignment_turned_in_24dp);
            case ACTIVE_TASKS:
                return mContext.getResources().getDrawable(R.drawable.ic_check_circle_24dp);
            case COMPLETED_TASKS:
                return mContext.getResources().getDrawable(R.drawable.ic_verified_user_24dp);
        }
        return null;
    }

    @Bindable
    public boolean getTasksAddViewVisible() {
        return mPresenter.getFiltering() == ALL_TASKS;
    }

    @Bindable
    public boolean isNotEmpty() {
        return mTaskListSize > 0;
    }

    public void setTaskListSize(int taskListSize) {
        mTaskListSize = taskListSize;
        notifyPropertyChanged(BR.noTaskIconRes);
        notifyPropertyChanged(BR.noTasksLabel);
        notifyPropertyChanged(BR.currentFilteringLabel);
        notifyPropertyChanged(BR.notEmpty);
        notifyPropertyChanged(BR.tasksAddViewVisible);
    }
}
