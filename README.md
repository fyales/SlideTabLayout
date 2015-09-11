Google官方推出了许多扩展的UI公共组件库，在这里，我们介绍一个比较实用的组件SlidingTabLayout,效果图如下:

<img src="img/1.png" style="width:50%;height=50%"/>

另外网易新闻的栏目展示用的也是类似的组件。

### 用法
首先将该组件的源代码拷贝到你的项目中，SlidingTabLayout.java和SlidingTabStrip.java，[项目地址](https://developer.android.com/samples/SlidingTabsBasic/src/com.example.android.common/view/SlidingTabLayout.html)。

接下来就可以在MainActivity里面进行布局了:

``` xml

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context=".MainActivity">

        <com.fyales.slidetablayout.SlidingTabLayout
            android:id="@+id/sliding_tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

        <android.support.v4.view.ViewPager
            android:id="@+id/viewpager"
            android:layout_width="match_parent"
            android:layout_height="0px"
            android:layout_weight="1"
            android:background="@android:color/white" />
    </LinearLayout>

```
    
    
然后，写一个基本的Fragment，用于展示:

``` java

    package com.fyales.slidetablayout;
    
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;
    
    /**
     * @author fyales
     */
    public class BaseFragment extends Fragment {
        private static final String DATA = "data";
    
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment,container,false);
            TextView textView = (TextView)view.findViewById(R.id.text);
            textView.setText("Fragment#" + getArguments().getInt(DATA,0));
            return textView;
        }
    
        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        }
    
        public static Fragment newInstance(int type){
            Fragment fragment = new BaseFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(DATA,type);
            fragment.setArguments(bundle);
            return fragment;
        }
    }
    
```
    
定义Adapter(继承自FragmentPagerAdapter)，覆写getItem()方法


	package com.fyales.slidetablayout;

	import android.content.Context;
	import android.support.v4.app.Fragment;
	import android.support.v4.app.FragmentManager;
	import android.support.v4.app.FragmentPagerAdapter;

	/**
 	* 简单实例
 	* @author fyales
 	*/
	public class TabViewPagerAdapter extends FragmentPagerAdapter {

    	private String mTabTitle[] = new String[]{"朝代", "人物", "战争"};
    	private Context mContext;

    	public TabViewPagerAdapter(FragmentManager fm, Context context) {
        	super(fm);
        	this.mContext = context;
   	 }

    	@Override
    	public Fragment getItem(int position) {
        	return null;
   	 }

    	@Override
    	public int getCount() {
       	 return 3;
    	}

    	@Override
    	public CharSequence getPageTitle(int position) {
        	return mTabTitle[position];
    	}
	}

    
最后我们就可以在MainActivity.java中直接使用了


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
                
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        // slidingTabLayout.setDistributeEvenly(true); 是否填充满屏幕的宽度
        slidingTabLayout.setViewPager(viewPager);
    }

    
    
### 自定义样式和自定义下划线颜色
SlidingTabLayout同样有一些自定义的方法供你使用，你可以定义下划线的颜色或者自定义样式，通过以下方法调用



        //自定义下划线颜色
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer(){
            @Override
            public int getIndicatorColor(int position) {
                return Color.RED;
            }
        });
        
        //自定义展示样式
        slidingTabLayout.setCustomTabView(R.layout.your_custom_tab, 0);

