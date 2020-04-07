package com.liuying.jetpackproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.liuying.jetpackproject.R;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        //创建跳转方式一
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(textView).navigate(R.id.action_navigation_home_to_navigation_dashboard);
            }
        });
        //创建方式二
        Bundle bundle = new Bundle();
        bundle.putString("args", "args1");
        textView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_home_to_navigation_dashboard, bundle));


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                textView.setText(strings.get(0));
            }
        });
        return root;
    }
}
