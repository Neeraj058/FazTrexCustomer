package com.faztrex.customer.adapters.navdrawers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.faztrex.customer.R;
import com.faztrex.customer.databinding.ItemNavMenuChildBinding;
import com.faztrex.customer.databinding.ItemNavMenuHeaderBinding;
import com.faztrex.customer.models.navdrawer.NavigationItem;
import com.faztrex.customer.models.navdrawer.NavigationSubItem;

import java.util.List;

public class NavigationItemExpandableAdapter extends ExpandableRecyclerAdapter<NavigationItem, NavigationSubItem, NavigationItemExpandableAdapter.ItemParentViewHolder, NavigationItemExpandableAdapter.ItemChildViewHolder> {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;
    private final String TAG = this.getClass().getSimpleName();
    private final Context mContext;

    private final OnNavigationMenuItemClickListener navigationMenuItemClickListener;

    public NavigationItemExpandableAdapter(Context mContext, @NonNull List<NavigationItem> parentList, OnNavigationMenuItemClickListener navigationMenuItemClickListener) {
        super(parentList);
        this.mContext = mContext;
        this.navigationMenuItemClickListener = navigationMenuItemClickListener;
    }

    @NonNull
    @Override
    public ItemParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(parentViewGroup.getContext());
        ItemNavMenuHeaderBinding itemNavMenuHeaderBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_nav_menu_header, parentViewGroup, false);

        return new ItemParentViewHolder(itemNavMenuHeaderBinding);
    }

    @NonNull
    @Override
    public ItemChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(childViewGroup.getContext());
        ItemNavMenuChildBinding itemNavMenuChildBinding = DataBindingUtil.inflate(mLayoutInflater, R.layout.item_nav_menu_child, childViewGroup, false);

        return new ItemChildViewHolder(itemNavMenuChildBinding);
    }

    @Override
    public void onBindParentViewHolder(@NonNull ItemParentViewHolder parentViewHolder, int parentPosition, @NonNull NavigationItem navigationItem) {

        try {

            if (navigationItem.isVisible()) {

                parentViewHolder.itemNavMenuHeaderBinding.tvHeaderTitle.setText(navigationItem.getMenuName());
                parentViewHolder.itemNavMenuHeaderBinding.ivHeaderIcon.setImageDrawable(navigationItem.getMenuIcon());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    @Override
    public void onBindChildViewHolder(@NonNull ItemChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull NavigationSubItem navigationSubItem) {

        try {

            if (navigationSubItem.isVisible()) {

                childViewHolder.itemNavMenuChildBinding.tvChildTitle.setText(navigationSubItem.getSubMenuName());
                childViewHolder.itemNavMenuChildBinding.ivChildIcon.setImageDrawable(navigationSubItem.getSubMenuIcon());

                childViewHolder.itemView.setOnClickListener(v -> navigationMenuItemClickListener.onChildMenuClick(childViewHolder.getParentAdapterPosition(), childViewHolder.getChildAdapterPosition(), navigationSubItem));
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getLocalizedMessage());
        }
    }

    public interface OnNavigationMenuItemClickListener {

        void onChildMenuClick(int parentPosition, int childPosition, NavigationSubItem navigationSubItem);

    }

    class ItemParentViewHolder extends ParentViewHolder {

        private final ItemNavMenuHeaderBinding itemNavMenuHeaderBinding;

        ItemParentViewHolder(ItemNavMenuHeaderBinding itemNavMenuHeaderBinding) {

            super(itemNavMenuHeaderBinding.getRoot());

            this.itemNavMenuHeaderBinding = itemNavMenuHeaderBinding;
        }

        @SuppressLint("NewApi")
        @Override
        public void setExpanded(boolean expanded) {
            super.setExpanded(expanded);

            if (expanded) {
                itemNavMenuHeaderBinding.ivArrowExpandMenu.setRotation(ROTATED_POSITION);
            } else {
                itemNavMenuHeaderBinding.ivArrowExpandMenu.setRotation(INITIAL_POSITION);
            }
        }

        @Override
        public void onExpansionToggled(boolean expanded) {
            super.onExpansionToggled(expanded);

            RotateAnimation rotateAnimation;

            if (expanded) {

                // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);

            } else {

                // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(300);
            rotateAnimation.setFillAfter(false);
            itemNavMenuHeaderBinding.ivArrowExpandMenu.startAnimation(rotateAnimation);
        }
    }

    class ItemChildViewHolder extends ChildViewHolder {

        private final ItemNavMenuChildBinding itemNavMenuChildBinding;

        ItemChildViewHolder(ItemNavMenuChildBinding itemNavMenuChildBinding) {

            super(itemNavMenuChildBinding.getRoot());

            this.itemNavMenuChildBinding = itemNavMenuChildBinding;
        }
    }
}
