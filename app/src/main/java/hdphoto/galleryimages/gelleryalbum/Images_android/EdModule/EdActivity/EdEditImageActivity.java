package hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;

import hdphoto.galleryimages.gelleryalbum.Alofi_AdsAds.OnBackPressed;
import hdphoto.galleryimages.gelleryalbum.Images_android.ShapeBuilder;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.Constant.BitmapProcessing;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFilter.EdFilterListener;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFilter.EdFilterViewAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment.EdEmojiBSFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment.EdPropertiesBSFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment.EdShapeBSFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment.EdStickerBSFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdFragment.EdTextEditorDialogFragment;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.Tool.EMEditingToolsAdapter;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.Tool.EMToolType;
import hdphoto.galleryimages.gelleryalbum.Images_android.EdModule.EdUtils.EdFileSaveHelper;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.AppUtilsClass;
import hdphoto.galleryimages.gelleryalbum.Images_android.Utils.FolderPath;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.io.IOException;

import ja.burhanrashid52.photoeditor.OnPhotoEditorListener;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;
import ja.burhanrashid52.photoeditor.PhotoFilter;
import ja.burhanrashid52.photoeditor.SaveSettings;
import ja.burhanrashid52.photoeditor.TextStyleBuilder;
import ja.burhanrashid52.photoeditor.ViewType;

public class EdEditImageActivity extends EdBaseActivity implements OnPhotoEditorListener, EdPropertiesBSFragment.Properties, EdShapeBSFragment.Properties, EdEmojiBSFragment.EmojiListener, EdStickerBSFragment.StickerListener, EMEditingToolsAdapter.OnItemSelected, EdFilterListener {
    public static final String ACTION_NEXTGEN_EDIT = "action_nextgen_edit";
    static final int CAMERA_REQUEST = 52;
    static final int PICK_REQUEST = 53;
    public static final String PINCH_TEXT_SCALABLE_INTENT_KEY = "PINCH_TEXT_SCALABLE";
    static final String TAG = "EMEditImageActivity";
    Activity activity;
    Bitmap bitmap1;
    Bitmap bmmain;
    CropImageView cropImageView;
    File filePath;
    EdEmojiBSFragment gEmojiBSFragment;
    boolean gIsFilterVisible;
    PhotoEditor gPhotoEditor;
    PhotoEditorView gPhotoEditorView;
    EdPropertiesBSFragment gPropertiesBSFragment;
    ConstraintLayout gRootView;
    RecyclerView gRvFilters;
    RecyclerView gRvTools;
    EdFileSaveHelper gSaveFileHelper;
    Uri gSaveImageUri;
    EdShapeBSFragment gShapeBSFragment;
    ShapeBuilder gShapeBuilder;
    EdStickerBSFragment gStickerBSFragment;
    TextView gTxtCurrentTool;
    ImageView imgCamera;
    ImageView imgClose;
    ImageView imgCrop;
    ImageView imgGallery;
    ImageView imgRedo;
    ImageView imgShare;
    ImageView imgUndo;
    ImageView img_back;
    Uri mainuri;
    TextView txtSave;
    EMEditingToolsAdapter gEditingToolsAdapter = new EMEditingToolsAdapter(this);
    EdFilterViewAdapter gFilterViewAdapter = new EdFilterViewAdapter(this);
    ConstraintSet gConstraintSet = new ConstraintSet();
    String stringExtra = null;
    private final boolean lockAspectRatio = false;
    private final boolean setBitmapMaxWidthHeight = false;
    private final int ASPECT_RATIO_X = 16;
    private final int ASPECT_RATIO_Y = 9;
    private final int bitmapMaxWidth = 1000;
    private final int bitmapMaxHeight = 1000;
    private final int IMAGE_COMPRESSION = 80;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CreateFullScreen();
        setContentView(R.layout.activity_single_edit_image);
        this.activity = this;
        ID_Binding();
        File file = new File(FolderPath.SAVE_IMAGE_PATH);
        this.filePath = file;
        if (!file.exists()) {
            this.filePath.mkdirs();
        }
        HandleIntentImage(this.gPhotoEditorView.getSource());
        this.gPropertiesBSFragment = new EdPropertiesBSFragment();
        this.gEmojiBSFragment = new EdEmojiBSFragment();
        this.gStickerBSFragment = new EdStickerBSFragment();
        this.gShapeBSFragment = new EdShapeBSFragment();
        this.gStickerBSFragment.setStickerListener(this);
        this.gEmojiBSFragment.setEmojiListener(this);
        this.gPropertiesBSFragment.setPropertiesChangeListener(this);
        this.gShapeBSFragment.setPropertiesChangeListener(this);
        this.gRvTools.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.gRvTools.setAdapter(this.gEditingToolsAdapter);
        this.gRvFilters.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.gRvFilters.setAdapter(this.gFilterViewAdapter);
        PhotoEditor build = new PhotoEditor.Builder(this, this.gPhotoEditorView).setPinchTextScalable(getIntent().getBooleanExtra(PINCH_TEXT_SCALABLE_INTENT_KEY, true)).build();
        this.gPhotoEditor = build;
        build.setOnPhotoEditorListener(this);
        String stringExtra = getIntent().getStringExtra("imagepath");
        this.stringExtra = stringExtra;
        if (stringExtra != null) {
            this.gPhotoEditorView.getSource().setImageURI(Uri.parse(this.stringExtra));
        }
        this.gSaveFileHelper = new EdFileSaveHelper(this);
    }

    private void HandleIntentImage(ImageView imageView) {
        Uri data;
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        if ("android.intent.action.EDIT".equals(intent.getAction()) || ACTION_NEXTGEN_EDIT.equals(intent.getAction())) {
            try {
                imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), intent.getData()));
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        String type = intent.getType();
        if (type == null || !type.startsWith("image/") || (data = intent.getData()) == null) {
            return;
        }
        imageView.setImageURI(data);
    }

    private void ID_Binding() {
        this.gPhotoEditorView = (PhotoEditorView) findViewById(R.id.photoEditorView);
        this.gTxtCurrentTool = (TextView) findViewById(R.id.txtCurrentTool);
        this.gRvTools = (RecyclerView) findViewById(R.id.rvConstraintTools);
        this.gRvFilters = (RecyclerView) findViewById(R.id.rvFilterView);
        this.gRootView = (ConstraintLayout) findViewById(R.id.rootView);
        this.img_back = (ImageView) findViewById(R.id.img_back);
        this.imgUndo = (ImageView) findViewById(R.id.imgUndo);
        this.imgRedo = (ImageView) findViewById(R.id.imgRedo);
        this.imgCamera = (ImageView) findViewById(R.id.imgCamera);
        this.imgGallery = (ImageView) findViewById(R.id.imgGallery);
        this.txtSave = findViewById(R.id.txtSave);
        this.imgClose = (ImageView) findViewById(R.id.imgClose);
        this.imgShare = (ImageView) findViewById(R.id.imgShare);
        this.imgCrop = (ImageView) findViewById(R.id.imgCrop);
        Click_Event();
    }

    private void Click_Event() {
        this.cropImageView = (CropImageView) findViewById(R.id.cropImageView);
        this.img_back.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.1
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.onBackPressed();
            }
        });
        this.imgUndo.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.2
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.gPhotoEditor.undo();
            }
        });
        this.imgRedo.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.3
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.gPhotoEditor.redo();
            }
        });
        this.imgCamera.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.4
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.activity.startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 52);
            }
        });
        this.imgGallery.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.5
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.activity.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI), 53);
            }
        });
        this.txtSave.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.6
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.SaveImage();
            }
        });
        this.imgClose.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.7
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.onBackPressed();
            }
        });
        this.imgShare.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.8
            @Override 
            public void onClick(View view) {
                if (EdEditImageActivity.this.gSaveImageUri == null) {
                    EdEditImageActivity.this.SaveImage();
                    new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.8.1
                        @Override 
                        public void run() {
                            EdEditImageActivity.this.ShareImage();
                        }
                    }, 1900L);
                    return;
                }
                EdEditImageActivity.this.ShareImage();
            }
        });
        this.imgCrop.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.9
            @Override 
            public void onClick(View view) {
                EdEditImageActivity.this.cropImage(Uri.fromFile(new File(EdEditImageActivity.this.stringExtra)));
            }
        });
    }

    @Override // ja.burhanrashid52.photoeditor.OnPhotoEditorListener
    public void onEditTextChangeListener(final View view, String str, int i) {
        EdTextEditorDialogFragment.show(this, str, i).setOnTextEditorListener(new EdTextEditorDialogFragment.TextEditor() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.10
            @Override // hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMFragment.EMTextEditorDialogFragment.TextEditor
            public final void onDone(String str2, int i2) {
                EdEditImageActivity.this.lambda$onEditTextChangeListener$0$EMEditImageActivity(view, str2, i2);
            }
        });
    }

    public void lambda$onEditTextChangeListener$0$EMEditImageActivity(View view, String str, int i) {
        TextStyleBuilder textStyleBuilder = new TextStyleBuilder();
        textStyleBuilder.withTextColor(i);
        this.gPhotoEditor.editText(view, str, textStyleBuilder);
        this.gTxtCurrentTool.setText(R.string.label_text);
    }

    @Override // ja.burhanrashid52.photoeditor.OnPhotoEditorListener
    public void onAddViewListener(ViewType viewType, int i) {
        Log.d(TAG, "onAddViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + i + "]");
    }

    @Override // ja.burhanrashid52.photoeditor.OnPhotoEditorListener
    public void onRemoveViewListener(ViewType viewType, int i) {
        Log.d(TAG, "onRemoveViewListener() called with: viewType = [" + viewType + "], numberOfAddedViews = [" + i + "]");
    }

    @Override // ja.burhanrashid52.photoeditor.OnPhotoEditorListener
    public void onStartViewChangeListener(ViewType viewType) {
        Log.d(TAG, "onStartViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    @Override // ja.burhanrashid52.photoeditor.OnPhotoEditorListener
    public void onStopViewChangeListener(ViewType viewType) {
        Log.d(TAG, "onStopViewChangeListener() called with: viewType = [" + viewType + "]");
    }

    public void ShareImage() {
        if (this.gSaveImageUri == null) {
            ShowSnackBar(getString(R.string.msg_save_image_to_share));
            return;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", buildFileProviderUri(this.gSaveImageUri));
        startActivity(Intent.createChooser(intent, getString(R.string.msg_share_image)));
    }

    private Uri buildFileProviderUri(Uri uri) {
        return FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", new File(uri.getPath()));
    }

    public void SaveImage() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            showLoading("Saving...");
            File file = new File(this.filePath + File.separator + System.currentTimeMillis() + ".png");
            try {
                file.createNewFile();
                this.gPhotoEditor.saveAsFile(file.getAbsolutePath(), new SaveSettings.Builder().setClearViewsEnabled(true).setTransparencyEnabled(true).build(), new PhotoEditor.OnSaveListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.11
                    @Override // ja.burhanrashid52.photoeditor.PhotoEditor.OnSaveListener
                    public void onSuccess(String str) {
                        EdEditImageActivity.this.HideLoading();
                        EdEditImageActivity.this.ShowSnackBar("Image Saved Successfully");
                        EdEditImageActivity.this.gSaveImageUri = Uri.fromFile(new File(str));
                        EdEditImageActivity.this.gPhotoEditorView.getSource().setImageURI(EdEditImageActivity.this.gSaveImageUri);
                        AppUtilsClass.insertUri(EdEditImageActivity.this, new File(EdEditImageActivity.this.gSaveImageUri.getPath()));
                        new Handler().postDelayed(new Runnable() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.11.1
                            @Override 
                            public void run() {
                                AppUtilsClass.RefreshMoment(EdEditImageActivity.this);
                                AppUtilsClass.RefreshImageAlbum(EdEditImageActivity.this);
                            }
                        }, 500L);
                    }

                    @Override // ja.burhanrashid52.photoeditor.PhotoEditor.OnSaveListener
                    public void onFailure(Exception exc) {
                        EdEditImageActivity.this.HideLoading();
                        EdEditImageActivity.this.ShowSnackBar("Failed to save Image");
                    }
                });
                return;
            } catch (IOException e) {
                e.printStackTrace();
                HideLoading();
                ShowSnackBar(e.getMessage());
                return;
            }
        }
        requestPermission("android.permission.WRITE_EXTERNAL_STORAGE");
    }

    @Override
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 52) {
                this.gPhotoEditor.clearAllViews();
                this.gPhotoEditorView.getSource().setImageBitmap((Bitmap) intent.getExtras().get("data"));
            } else if (i == 53) {
                try {
                    this.gPhotoEditor.clearAllViews();
                    this.gPhotoEditorView.getSource().setImageBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), intent.getData()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (i == 69) {
            if (i2 == -1) {
                handleUCropResult(intent);
            } else {
                setResultCancelled();
            }
        } else if (i != 96) {
            setResultCancelled();
        } else {
            UCrop.getError(intent);
            setResultCancelled();
        }
    }

    @Override
    public void onColorChanged(int i) {
        this.gTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onOpacityChanged(int i) {
        this.gTxtCurrentTool.setText(R.string.label_brush);
    }

    @Override
    public void onShapeSizeChanged(int i) {
        this.gTxtCurrentTool.setText(R.string.label_brush);
    }

//    @Override
//    public void onShapePicked(ShapeType shapeType) {
//    }

    @Override
    public void onEmojiClick(String str) {
        this.gPhotoEditor.addEmoji(str);
        this.gTxtCurrentTool.setText(R.string.label_emoji);
    }

    @Override
    public void onStickerClick(Bitmap bitmap) {
        this.gPhotoEditor.addImage(bitmap);
        this.gTxtCurrentTool.setText(R.string.label_sticker);
    }

    @Override
    public void isPermissionGranted(boolean z, String str) {
        if (z) {
            SaveImage();
        }
    }

    @Override
    public void onFilterSelected(PhotoFilter photoFilter) {
        this.gPhotoEditor.setFilterEffect(photoFilter);
    }

    static class C0070AnonymousClass12 {
        static final int[] $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType;

        C0070AnonymousClass12() {
        }

        static {
            int[] iArr = new int[EMToolType.values().length];
            $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType = iArr;
            try {
                iArr[EMToolType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType[EMToolType.TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType[EMToolType.ERASER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType[EMToolType.FILTER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType[EMToolType.EMOJI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType[EMToolType.STICKER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Override // hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMTool.EMEditingToolsAdapter.OnItemSelected
    public void onToolSelected(EMToolType eMToolType) {
        switch (C0070AnonymousClass12.$SwitchMap$gallerynew$newapp$android$EditorModule$EMTool$EMToolType[eMToolType.ordinal()]) {
            case 1:
                this.gPhotoEditor.setBrushDrawingMode(true);
                ShapeBuilder shapeBuilder = new ShapeBuilder();
                this.gShapeBuilder = shapeBuilder;
//                this.gPhotoEditor.setShape(shapeBuilder);
                this.gTxtCurrentTool.setText(R.string.label_shape);
                ShowBSDialogFragment(this.gShapeBSFragment);
                return;
            case 2:
                EdTextEditorDialogFragment.show(this).setOnTextEditorListener(new EdTextEditorDialogFragment.TextEditor() { // from class: hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMActivity.EMEditImageActivity.12
                    @Override // hdphoto.galleryimages.gelleryalbum.android.EditorModule.EMFragment.EMTextEditorDialogFragment.TextEditor
                    public final void onDone(String str, int i) {
                        EdEditImageActivity.this.lambda$onToolSelected$1$EMEditImageActivity(str, i);
                    }
                });
                return;
            case 3:
                if (!this.gPhotoEditor.isCacheEmpty()) {
                    this.gPhotoEditor.brushEraser();
                    this.gTxtCurrentTool.setText(R.string.label_eraser_mode);
                    return;
                }
                Toast.makeText(this.activity, "First Draw Something", 0).show();
                return;
            case 4:
                this.gTxtCurrentTool.setText(R.string.label_filter);
                ShowFilter(true);
                return;
            case 5:
                ShowBSDialogFragment(this.gEmojiBSFragment);
                return;
            case 6:
                ShowBSDialogFragment(this.gStickerBSFragment);
                return;
            default:
                return;
        }
    }

    public void lambda$onToolSelected$1$EMEditImageActivity(String str, int i) {
        TextStyleBuilder textStyleBuilder = new TextStyleBuilder();
        textStyleBuilder.withTextColor(i);
        this.gPhotoEditor.addText(str, textStyleBuilder);
        this.gTxtCurrentTool.setText(R.string.label_text);
    }

    private void ShowBSDialogFragment(BottomSheetDialogFragment bottomSheetDialogFragment) {
        if (bottomSheetDialogFragment == null || bottomSheetDialogFragment.isAdded()) {
            return;
        }
        bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
    }

    void ShowFilter(boolean z) {
        this.gIsFilterVisible = z;
        this.gConstraintSet.clone(this.gRootView);
        if (z) {
            this.gConstraintSet.clear(this.gRvFilters.getId(), 6);
            this.gConstraintSet.connect(this.gRvFilters.getId(), 6, 0, 6);
            this.gConstraintSet.connect(this.gRvFilters.getId(), 7, 0, 7);
        } else {
            this.gConstraintSet.connect(this.gRvFilters.getId(), 6, 0, 7);
            this.gConstraintSet.clear(this.gRvFilters.getId(), 7);
        }
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(350L);
        changeBounds.setInterpolator(new AnticipateOvershootInterpolator(1.0f));
        TransitionManager.beginDelayedTransition(this.gRootView, changeBounds);
        this.gConstraintSet.applyTo(this.gRootView);
    }

    @Override 
    public void onBackPressed() {
                if (EdEditImageActivity.this.gIsFilterVisible) {
                    EdEditImageActivity.this.ShowFilter(false);
                    EdEditImageActivity.this.gTxtCurrentTool.setText(R.string.app_name);
                    return;
                }
        OnBackPressed.onBackPressed(this,true);


    }

    public void cropImage(Uri uri) {
        Uri fromFile = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString(), new File(uri.toString()).getName()));
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(80);
        options.setToolbarColor(ContextCompat.getColor(this, R.color.black));
        options.setToolbarWidgetColor(ContextCompat.getColor(this, R.color.white));
        options.setToolbarTitle("Crop Image");
        options.setActiveControlsWidgetColor(ContextCompat.getColor(this, R.color.yellow_theme));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.black));
        options.setCropFrameColor(ContextCompat.getColor(this, R.color.yellow_theme));
        options.setCropGridColor(ContextCompat.getColor(this, R.color.yellow_theme));
        UCrop.of(uri, fromFile).withOptions(options).start(this);
    }

    private void handleUCropResult(Intent intent) {
        if (intent == null) {
            setResultCancelled();
            return;
        }
        Uri output = UCrop.getOutput(intent);
        try {
            this.mainuri = output;
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), output);
            this.bitmap1 = bitmap;
            this.bmmain = bitmap;
            if (bitmap == null) {
                try {
                    Toast.makeText(this, "Couldn't handle this image, It has large size!", 0).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                this.bmmain = BitmapProcessing.resizeBitmap(bitmap, bitmap.getWidth(), (this.bmmain.getHeight() * this.bitmap1.getWidth()) / this.bmmain.getWidth());
                this.gPhotoEditorView.getSource().setImageBitmap(this.bmmain);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            Log.d("hgjj", " e " + e2.getMessage());
        }
    }

    private void setResultOk(Uri uri) {
        Intent intent = new Intent();
        intent.putExtra("path", uri);
        setResult(-1, intent);
        finish();
    }

    private void setResultCancelled() {
        setResult(0, new Intent());
    }
}
