package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Blue_Jay_Gallery;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.Rose_ads.AdInterGD;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Hertz_Activity.HiPhi_MainActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Duff_Lib.Duff_CollageActivity;
import hdphoto.galleryimages.gelleryalbum.Images_android.StatusModule.SMWp11.ConstantWP.SMUtilsWP;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Peyton_GalleryDataFragment extends Fragment {
    public static int MAX_COLLAGE = 9;
    public static int MAX_MIRROR = 1;
    static int MAX_SCRAPBOOK = 9;
    public static CollageGalleryListener collageGalleryListener;
    public static final List<Declan_Album.SubImage> selectedImages = new ArrayList();
    Jaxson_FolderAdapter folderAdapter;
    RecyclerView folderRCV;
    RecyclerView imageRCV;
    Hunter_ImagesAdapter imagesAdapter;
    ImageView imgBack;
    MirrorGalleryListener mirrorGalleryListener;
    Emery_SelectedImagesAdapter selectedImagesAdapter;
    RecyclerView selectionRCV;
    Animation slideInLeft;
    TextView txtMaxLimit;
    TextView txtSelectedLabel;
    TextView txt_next;
    int cllgImgLimitMaxMirror = 1;
    int cllgImgLimitMax = 9;
    int cllgImgLimitMin = 0;
    boolean collageSingleMode = false;
    boolean isScrapBook = false;
    boolean isShape = false;
    Activity activity;


    public interface CollageGalleryListener {
        void onGalleryCancel();

        void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z, boolean z2);

        void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr, boolean z, boolean z2);

        void onGalleryOkSingleImage(long j, int i, boolean z, boolean z2);
    }


    public interface MirrorGalleryListener {
        void onGalleryCancel();

        void onGalleryOkImageArray(long[] jArr, int[] iArr, boolean z, boolean z2, String[] strArr);

        void onGalleryOkImageArrayRemoveFragment(long[] jArr, int[] iArr, boolean z, boolean z2);

        void onGalleryOkSingleImage(long j, int i, boolean z, boolean z2);
    }
    View inflate;
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
         inflate = layoutInflater.inflate(R.layout.fragment_collage_gallery, viewGroup, false);
        this.activity = getActivity();

        this.txtMaxLimit = (TextView) inflate.findViewById(R.id.maxAllowed);
        if (HiPhi_MainActivity.fromActivity != null) {
            if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                this.txtMaxLimit.setText(String.format(getString(R.string.gallery_lib_max), Integer.valueOf(GetLimitMax())));
            } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                this.txtMaxLimit.setText(String.format(getString(R.string.gallery_lib_max), Integer.valueOf(GetLimitMaxMirror())));
            } else {
                this.txtMaxLimit.setText(String.format(getString(R.string.gallery_lib_max), Integer.valueOf(GetLimitMax())));
            }
        }
        this.slideInLeft = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_in_left);
        TextView textView = (TextView) inflate.findViewById(R.id.selectedLabel);
        this.txtSelectedLabel = textView;
        textView.setText(getString(R.string.selected_d, 0));
        SetClearBtn(inflate);
        SetAlbumsList(inflate);
        SetPhotosList(inflate);
        SetSelectionList(inflate);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.btnBack);
        this.imgBack = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                Peyton_GalleryDataFragment.this.OnBack();
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(R.id.txt_next);
        this.txt_next = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view) {
                AdInterGD.getInstance().showInter(getActivity(), new AdInterGD.MyCallback() {
                    @Override
                    public void callbackCall() {
                        if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                            Peyton_GalleryDataFragment.this.PhotosSelectCollage();
                        } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                            Peyton_GalleryDataFragment.this.PhotosSelectMirror();
                        } else {
                            Peyton_GalleryDataFragment.this.PhotosSelectCollage();
                        }
                    }
                });
            }

        });
        return inflate;
    }

    private void SetClearBtn(View view) {
        view.findViewById(R.id.clearBtn).setOnClickListener(new View.OnClickListener() {
            @Override 
            public void onClick(View view2) {
                Peyton_GalleryDataFragment.this.RemoveAll();
            }
        });
    }

    private void SetAlbumsList(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.albumsList);
        this.folderRCV = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        Jaxson_FolderAdapter cMFolderAdapter = new Jaxson_FolderAdapter(Collections.emptyList());
        this.folderAdapter = cMFolderAdapter;
        cMFolderAdapter.setOnAlbumSelectedListener(new Jaxson_FolderAdapter.OnAlbumSelectedListener() {
            @Override
            public void onAlbumSelected(Declan_Album cMAlbum) {
                Peyton_GalleryDataFragment.this.imagesAdapter.setItems(cMAlbum.subImages);
            }
        });
        this.folderRCV.setAdapter(this.folderAdapter);
    }

    private void SetPhotosList(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.photosList);
        this.imageRCV = recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        Hunter_ImagesAdapter cMImagesAdapter = new Hunter_ImagesAdapter(Collections.emptyList());
        this.imagesAdapter = cMImagesAdapter;
        cMImagesAdapter.setOnImageSelectedListener(new Hunter_ImagesAdapter.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Declan_Album.SubImage subImage) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                    if (Peyton_GalleryDataFragment.selectedImages.size() < Peyton_GalleryDataFragment.this.GetLimitMax()) {
                        Peyton_GalleryDataFragment.selectedImages.add(subImage);
                    } else {
                        Toast.makeText(Peyton_GalleryDataFragment.this.getContext(), "You Reached Maximum Photo Selection!", 0).show();
                    }
                } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                    if (Peyton_GalleryDataFragment.selectedImages.size() < Peyton_GalleryDataFragment.this.GetLimitMaxMirror()) {
                        Peyton_GalleryDataFragment.selectedImages.add(subImage);
                    } else {
                        Toast.makeText(Peyton_GalleryDataFragment.this.getContext(), "You Reached Maximum Photo Selection!", 0).show();
                    }
                } else if (Peyton_GalleryDataFragment.selectedImages.size() < Peyton_GalleryDataFragment.this.GetLimitMax()) {
                    Peyton_GalleryDataFragment.selectedImages.add(subImage);
                } else {
                    Toast.makeText(Peyton_GalleryDataFragment.this.getContext(), "You Reached Maximum Photo Selection!", 0).show();
                }
                Peyton_GalleryDataFragment.this.SelectedListChanged();
            }
        });
        this.imageRCV.setAdapter(this.imagesAdapter);
    }

    private void SetSelectionList(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.selectedList);
        this.selectionRCV = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        Emery_SelectedImagesAdapter cMSelectedImagesAdapter = new Emery_SelectedImagesAdapter(Collections.emptyList());
        this.selectedImagesAdapter = cMSelectedImagesAdapter;
        cMSelectedImagesAdapter.setOnImageRemovedListener(new Emery_SelectedImagesAdapter.ImageRemovedListener() {
            @Override
            public void onImageRemoved(Declan_Album.SubImage subImage, int i) {
                Peyton_GalleryDataFragment.selectedImages.remove(i);
                Peyton_GalleryDataFragment.this.SelectedListChanged();
            }
        });
        this.selectionRCV.setAdapter(this.selectedImagesAdapter);
    }

    public void SelectedListChanged() {
        Emery_SelectedImagesAdapter cMSelectedImagesAdapter = this.selectedImagesAdapter;
        if (cMSelectedImagesAdapter != null) {
            List<Declan_Album.SubImage> list = selectedImages;
            cMSelectedImagesAdapter.setItems(list);
            if (list.size() > 0 && this.collageSingleMode) {
                if (HiPhi_MainActivity.fromActivity.equals("CollageActivity") || HiPhi_MainActivity.fromActivity.equals("ScrapBookActivity")) {
                    PhotosSelectCollage();
                } else if (HiPhi_MainActivity.fromActivity.equals("MirrorActivity")) {
                    PhotosSelectMirror();
                } else {
                    PhotosSelectCollage();
                }
                this.collageSingleMode = false;
            }
            this.txtSelectedLabel.setText(getString(R.string.selected_d, Integer.valueOf(list.size())));
        }
    }

    public void setIsShape(boolean z) {
        this.isShape = z;
    }

    public void RemoveAll() {
        selectedImages.clear();
        SelectedListChanged();
    }

    public int GetLimitMax() {
        return this.cllgImgLimitMax;
    }

    public int GetLimitMaxMirror() {
        return this.cllgImgLimitMaxMirror;
    }

    public void setCollageGalleryListener(CollageGalleryListener collageGalleryListener2) {
        collageGalleryListener = collageGalleryListener2;
    }

    public void setMirrorGalleryListener(MirrorGalleryListener mirrorGalleryListener) {
        this.mirrorGalleryListener = mirrorGalleryListener;
    }

    public void isScrapbookCheck(boolean z) {
        this.isScrapBook = z;
        SetLimitMax(MAX_SCRAPBOOK);
        if (selectedImages.size() > this.cllgImgLimitMax) {
            RemoveAll();
        }
    }

    public void isMirrorCheck() {
        SetLimitMaxMirror(MAX_MIRROR);
        if (selectedImages.size() > this.cllgImgLimitMaxMirror) {
            RemoveAll();
        }
    }

    public void SetLimitMax(int i) {
        this.cllgImgLimitMax = i;
        TextView textView = this.txtMaxLimit;
        if (textView != null) {
            textView.setText(String.format(getString(R.string.gallery_lib_max), Integer.valueOf(this.cllgImgLimitMax)));
        }
    }

    public void SetLimitMaxMirror(int i) {
        this.cllgImgLimitMaxMirror = i;
        TextView textView = this.txtMaxLimit;
        if (textView != null) {
            textView.setText(String.format(getString(R.string.gallery_lib_max), Integer.valueOf(this.cllgImgLimitMaxMirror)));
        }
    }

    @Override 
    public void onResume() {
        super.onResume();
        this.folderAdapter.setItems(GalleryFoldersList());
    }

    public void setCollageSingleMode(boolean z) {
        this.collageSingleMode = z;
        if (z) {
            RemoveAll();
        }
    }

    @Override 
    public void onPause() {
        super.onPause();
    }

    @Override 
    public void onDestroy() {
        super.onDestroy();
    }

    @Override 
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override 
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.folderAdapter.setItems(GalleryFoldersList());
    }

    private List<Declan_Album> GalleryFoldersList() {
        Cursor query = requireContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", SMUtilsWP.mediaPath, "bucket_display_name", "bucket_id", "_id", "orientation"}, null, null, "date_modified DESC");
        if (query == null || !query.moveToFirst()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int columnIndex = query.getColumnIndex("bucket_display_name");
        int columnIndex2 = query.getColumnIndex("bucket_id");
        int columnIndex3 = query.getColumnIndex("_id");
        int columnIndex4 = query.getColumnIndex("orientation");
        int columnIndexOrThrow = query.getColumnIndexOrThrow(SMUtilsWP.mediaPath);
        do {
            int i = query.getInt(columnIndex2);
            long j = query.getLong(columnIndex3);
            String string = query.getString(columnIndexOrThrow);
            if (arrayList2.contains(Integer.valueOf(i))) {
                ((Declan_Album) arrayList.get(arrayList2.indexOf(Integer.valueOf(i)))).subImages.add(new Declan_Album.SubImage(j, query.getInt(columnIndex4), string));
            } else {
                Declan_Album cMAlbum = new Declan_Album();
                cMAlbum.ID = i;
                cMAlbum.name = query.getString(columnIndex);
                cMAlbum.imageIdForThumb = j;
                cMAlbum.subImages.add(new Declan_Album.SubImage(j, query.getInt(columnIndex4), string));
                arrayList2.add(Integer.valueOf(i));
                arrayList.add(cMAlbum);
            }
        } while (query.moveToNext());
        return arrayList;
    }

    public void onBackPressed() {
        OnBack();
    }

    public void OnBack() {
        if (collageGalleryListener != null) {
            RemoveAll();
            collageGalleryListener.onGalleryCancel();
        }
        if (this.mirrorGalleryListener != null) {
            RemoveAll();
            this.mirrorGalleryListener.onGalleryCancel();
        }
        AdInterGD.getInstance().showInter(getActivity(), new AdInterGD.MyCallback() {
            @Override
            public void callbackCall() {
                Peyton_GalleryDataFragment.this.activity.finish();
            }
        });
    }

    private int getLimitMin() {
        return this.cllgImgLimitMin;
    }

    public void PhotosSelectCollage() {
        int size = selectedImages.size();
        if (size <= this.cllgImgLimitMin) {
            Toast.makeText(requireContext(), String.format(getString(R.string.gallery_select_one), Integer.valueOf(getLimitMin() + 1)), 0).show();
            return;
        }
        long[] jArr = new long[size];
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            List<Declan_Album.SubImage> list = selectedImages;
            jArr[i] = list.get(i).id;
            iArr[i] = list.get(i).orientation;
        }
        CollageGalleryListener collageGalleryListener2 = collageGalleryListener;
        if (collageGalleryListener2 != null) {
            collageGalleryListener2.onGalleryOkImageArray(jArr, iArr, this.isScrapBook, this.isShape);
            return;
        }
        Intent intent = new Intent(getActivity(), Duff_CollageActivity.class);
        intent.putExtra("photo_id_list", jArr);
        intent.putExtra("photo_orientation_list", iArr);
        intent.putExtra("is_scrap_book", this.isScrapBook);
        intent.putExtra("is_shape", this.isShape);
        getActivity().startActivity(intent);
        new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.CollageModule.CMGallery.CMGalleryDataFragment.7
            @Override 
            public void run() {
                if (Peyton_GalleryDataFragment.selectedImages != null) {
                    Peyton_GalleryDataFragment.this.RemoveAll();
                }
            }
        }, 1000L);
    }

    public void PhotosSelectMirror() {
        int size = selectedImages.size();
        if (size <= this.cllgImgLimitMin) {
            Toast.makeText(requireContext(), String.format(getString(R.string.gallery_select_one), Integer.valueOf(getLimitMin() + 1)), 0).show();
            return;
        }
        long[] jArr = new long[size];
        int[] iArr = new int[size];
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            List<Declan_Album.SubImage> list = selectedImages;
            jArr[i] = list.get(i).id;
            iArr[i] = list.get(i).orientation;
            strArr[i] = list.get(i).imagePath;
        }
        MirrorGalleryListener mirrorGalleryListener = this.mirrorGalleryListener;
        if (mirrorGalleryListener != null) {
            mirrorGalleryListener.onGalleryOkImageArray(jArr, iArr, this.isScrapBook, this.isShape, strArr);
            return;
        }
        try {
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
