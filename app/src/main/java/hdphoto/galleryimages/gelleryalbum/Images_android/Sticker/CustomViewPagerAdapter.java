package hdphoto.galleryimages.gelleryalbum.Images_android.Sticker;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.ArrayList;


public class CustomViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> listOfFragments1;
    private ArrayList<String> listOfTitles1;

    public CustomViewPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> arrayList, ArrayList<String> arrayList2) {
        super(fragmentManager);
        this.listOfFragments1 = new ArrayList<>();
        this.listOfTitles1 = new ArrayList<>();
        this.listOfFragments1 = arrayList;
        this.listOfTitles1 = arrayList2;
    }

    @Override
    public int getCount() {
        return this.listOfFragments1.size();
    }

    @Override
    public Fragment getItem(int i) {
        return this.listOfFragments1.get(i);
    }

    @Override
    public CharSequence getPageTitle(int i) {
        return this.listOfTitles1.get(i);
    }
}
