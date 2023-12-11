package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.stickers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import hdphoto.galleryimages.gelleryalbum.R;

import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.EdApiService.Injection;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerItemModel;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Jackal_StickerLayout.Ledl_StickerPack;

import java.util.ArrayList;
import java.util.List;


public class StickerDataActivity extends AppCompatActivity implements StickersDataContract.View {
    public static final String SELECTED_STICKERS_EXTRA_KEY = "imgSticker";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    RecyclerView navRecyclerView;
    NavigationDrawerAdapter navigationDrawerAdapter;
    StickersDataContract.Presenter presenter;
    ProgressDialog progressDialog;
    List<Ledl_StickerPack> stickerPacks = new ArrayList();
    RecyclerView stickerRecyclerView;
    TextView txtTitleText;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sticker_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.navRecylerView);
        this.navRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.presenter = new StickersDataPresenter(this, Injection.provideApiService());
        SetToolbar();
        this.txtTitleText = (TextView) findViewById(R.id.textView_header);
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.stickerList);
        this.stickerRecyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 4));
        SetDrawer();
        this.presenter.loadStickers();
    }

    @Override
    public void showStickers(List<Ledl_StickerPack> list) {
        this.stickerPacks.clear();
        this.stickerPacks.addAll(list);
        AddDrawerItem();
    }

    @Override
    public void showProgress() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.progressDialog = progressDialog;
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null || !progressDialog.isShowing()) {
            return;
        }
        this.progressDialog.dismiss();
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private void SetToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.backkk);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sticker_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            if (this.drawerLayout.isDrawerOpen(this.navRecyclerView)) {
                this.drawerLayout.closeDrawer(GravityCompat.START, true);
            } else {
                this.drawerLayout.openDrawer(GravityCompat.START, true);
            }
            return true;
        } else if (menuItem.getItemId() != R.id.mi_save) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (this.navigationDrawerAdapter.getSelectedStickerPack().size() > 0) {
                Intent intent = new Intent();
                intent.putExtra(SELECTED_STICKERS_EXTRA_KEY, this.navigationDrawerAdapter.getSelectedStickerPack());
                setResult(-1, intent);
                finish();
            } else {
                Toast.makeText(this, "Please select at least one sticker", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    }

    private void AddDrawerItem() {
        NavigationDrawerAdapter navigationDrawerAdapter = new NavigationDrawerAdapter(this, this.stickerPacks);
        this.navigationDrawerAdapter = navigationDrawerAdapter;
        this.navRecyclerView.setAdapter(navigationDrawerAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public final void run() {
                StickerDataActivity.this.ClickableItem();
            }
        }, 1L);
    }

    public void ClickableItem() {
        this.navRecyclerView.findViewHolderForAdapterPosition(0).itemView.performClick();
    }

    private void SetDrawer() {
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, this.drawerLayout, R.string.drawer_open, R.string.drawer_close) { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.stickers.CMStickerDataActivity.2
            @Override
            public void onDrawerOpened(View view) {
                super.onDrawerOpened(view);
                TextView textView = StickerDataActivity.this.txtTitleText;
                textView.setText(StickerDataActivity.this.navigationDrawerAdapter.getSelectedStickerPack().size() + " Items Selected");
                StickerDataActivity.this.invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                TextView textView = StickerDataActivity.this.txtTitleText;
                textView.setText(StickerDataActivity.this.navigationDrawerAdapter.getSelectedStickerPack().size() + " Items Selected");
                StickerDataActivity.this.invalidateOptionsMenu();
            }
        };
        this.drawerToggle = actionBarDrawerToggle;
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        this.drawerToggle.setHomeAsUpIndicator(R.drawable.img_back_white);
        this.drawerLayout.addDrawerListener(this.drawerToggle);
    }

    @Override // androidx.appcompat.app.AppCompat
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.drawerToggle.onConfigurationChanged(configuration);
    }

    @Override 
    public void onDestroy() {
        this.presenter.onDestroy();
        super.onDestroy();
    }


    public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.ViewHolder> {
        LayoutInflater layoutInflater;
        Ledl_StickerPack stickerPack;

        public StickerAdapter(Context context, Ledl_StickerPack cMStickerPack) {
            this.layoutInflater = LayoutInflater.from(context);
            this.stickerPack = cMStickerPack;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(this.layoutInflater.inflate(R.layout.layout_sticker_adapter, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            final Ledl_StickerItemModel cMStickerItemModel = this.stickerPack.getStickerItemModelArrayList().get(i);
            Glide.with((FragmentActivity) StickerDataActivity.this).load(cMStickerItemModel.getStickerUrl()).into(viewHolder.img_StickerItem);
            viewHolder.checkboxSelect.setChecked(cMStickerItemModel.isSelected());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StickerAdapter.this.StickerListAdapter(cMStickerItemModel, viewHolder, view);
                }
            });
        }

        public void StickerListAdapter(Ledl_StickerItemModel cMStickerItemModel, ViewHolder viewHolder, View view) {
            boolean z = !cMStickerItemModel.isSelected();
            cMStickerItemModel.setSelected(z);
            viewHolder.checkboxSelect.setChecked(z);
            NotifyItem();
        }

        public void NotifyItem() {
            if (StickerDataActivity.this.navigationDrawerAdapter != null) {
                TextView textView = StickerDataActivity.this.txtTitleText;
                textView.setText(StickerDataActivity.this.navigationDrawerAdapter.getSelectedStickerPack().size() + " Items Selected");
            }
        }

        @Override
        public int getItemCount() {
            return this.stickerPack.getStickerItemModelArrayList().size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            CheckBox checkboxSelect;
            ImageView img_StickerItem;

            ViewHolder(View view) {
                super(view);
                this.img_StickerItem = (ImageView) view.findViewById(R.id.stickerItem);
                this.checkboxSelect = (CheckBox) view.findViewById(R.id.checkboxSelect);
            }
        }

        public Ledl_StickerItemModel getItem(int i) {
            return this.stickerPack.getStickerItemModelArrayList().get(i);
        }
    }


    public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {
        List<Ledl_StickerPack> mData;
        LayoutInflater mLayoutInflater;

        NavigationDrawerAdapter(Context context, List<Ledl_StickerPack> list) {
            this.mLayoutInflater = LayoutInflater.from(context);
            this.mData = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(this.mLayoutInflater.inflate(R.layout.layout_navigation_drawer_adapter, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            final Ledl_StickerPack cMStickerPack = this.mData.get(i);
            Glide.with((FragmentActivity) StickerDataActivity.this).load(cMStickerPack.getIconUrl()).into(viewHolder.imgCategoryIcon);
            viewHolder.textCategoryName.setText(cMStickerPack.getPackName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMActivity.stickers.CMStickerDataActivity.NavigationDrawerAdapter.1
                @Override
                public void onClick(View view) {
                    NavigationDrawerAdapter.this.NavigationDrawerAdapter(cMStickerPack, view);
                }
            });
        }

        public void NavigationDrawerAdapter(Ledl_StickerPack cMStickerPack, View view) {
            StickerDataActivity.this.drawerLayout.closeDrawer(GravityCompat.START, true);
            StickerDataActivity.this.stickerRecyclerView.setAdapter(new StickerAdapter(StickerDataActivity.this, cMStickerPack));
        }

        public ArrayList<Ledl_StickerItemModel> getSelectedStickerPack() {
            ArrayList<Ledl_StickerItemModel> arrayList = new ArrayList<>();
            for (int i = 0; i < StickerDataActivity.this.stickerPacks.size(); i++) {
                for (int i2 = 0; i2 < StickerDataActivity.this.stickerPacks.get(i).getStickerItemModelArrayList().size(); i2++) {
                    if (StickerDataActivity.this.stickerPacks.get(i).getStickerItemModelArrayList().get(i2).isSelected()) {
                        arrayList.add(StickerDataActivity.this.stickerPacks.get(i).getStickerItemModelArrayList().get(i2));
                    }
                }
            }
            return arrayList;
        }

        @Override
        public int getItemCount() {
            return this.mData.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imgCategoryIcon;
            TextView textCategoryName;

            ViewHolder(View view) {
                super(view);
                this.imgCategoryIcon = (ImageView) view.findViewById(R.id.imgCategoriIcon);
                this.textCategoryName = (TextView) view.findViewById(R.id.textCategoryName);
            }
        }

        public Ledl_StickerPack getItem(int i) {
            return this.mData.get(i);
        }
    }
}
