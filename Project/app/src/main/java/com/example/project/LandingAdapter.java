package com.example.project;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LandingAdapter extends FragmentStateAdapter {

        public LandingAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new LandingFragmentOne();
            } else if (position == 1){
                return new LandingFragmentTwo();
            } else {
                return new LandingFragmentThree();
            }
        }

        @Override
        public int getItemCount() {
            return 3; // Jumlah total halaman/fragmen
        }
}
