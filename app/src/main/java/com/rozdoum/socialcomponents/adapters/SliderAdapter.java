package com.rozdoum.socialcomponents.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rozdoum.socialcomponents.R;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images ={
            R.drawable.ic_for_you_50,
            R.drawable.ic_for_you_50,
            R.drawable.ic_search,
            R.drawable.ic_plus,
            R.drawable.ic_team

    };

    public String[] slide_headings ={
            "프레이리스트",
            "나의 프레이리스트",
            "동역자와 기도 찾기",
            "+프레이리스트",
            "동역자의 프레이리스트"
    };

    public String[] slide_descs ={
            "기도제목을 스마트폰에도 쓰고 \n서로의 기도제목을 묻고 기도할 수 있는 앱입니다",
            "이곳에서 여러분들의 프레이리스트를 만들어보세요",
            "새로운 동역자와 그들의 프레이리스트를 찾아보세요",
            "여러분들의 기도뿐만 아니라 \n동역자들의 기도도 리스트에 쓸수 있습니다",
            "동역하는 이웃의 새로운 프레이리스트 소식을 받아보세요"
    };



    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideHeading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slid_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        if(position==0){
            slideImageView.setVisibility(View.INVISIBLE);
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
