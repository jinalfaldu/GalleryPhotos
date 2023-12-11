package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;


public class CustomDrawerToggle extends AppCompatButton implements DrawerLayout.DrawerListener {
    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerClosed(View view) {
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerOpened(View view) {
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerSlide(View view, float f) {
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
    public void onDrawerStateChanged(int i) {
    }

    public CustomDrawerToggle(Context context) {
        super(context);
    }

    public CustomDrawerToggle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomDrawerToggle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
