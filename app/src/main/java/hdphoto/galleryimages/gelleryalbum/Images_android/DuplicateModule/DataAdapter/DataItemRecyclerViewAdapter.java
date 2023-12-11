package hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import hdphoto.galleryimages.gelleryalbum.R;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.DataModel.Duplicate;
import hdphoto.galleryimages.gelleryalbum.Images_android.DuplicateModule.Utils.Utils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.ArrayList;


public class DataItemRecyclerViewAdapter extends RecyclerView.Adapter<DataItemRecyclerViewAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Duplicate> mDuplicates;


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView image;
        ImageView imgPlay;
        ImageView img_selected;
        ImageView img_unselected;
        RelativeLayout rlCard;
        RelativeLayout rl_border;
        RelativeLayout rl_check;
        TextView txtItemLabel;
        TextView txtPath;
        TextView txtSize;

        public ItemViewHolder(View view) {
            super(view);
            this.txtItemLabel = (TextView) view.findViewById(R.id.name);
            this.txtSize = (TextView) view.findViewById(R.id.size);
            this.txtPath = (TextView) view.findViewById(R.id.path);
            this.image = (RoundedImageView) view.findViewById(R.id.image);
            this.imgPlay = (ImageView) view.findViewById(R.id.play);
            this.rlCard = (RelativeLayout) view.findViewById(R.id.rlCard);
            this.img_unselected = (ImageView) view.findViewById(R.id.img_unselected);
            this.img_selected = (ImageView) view.findViewById(R.id.img_selected);
            this.rl_check = (RelativeLayout) view.findViewById(R.id.rl_check);
            this.rl_border = (RelativeLayout) view.findViewById(R.id.rl_border);
        }
    }

    public DataItemRecyclerViewAdapter(Context context, ArrayList<Duplicate> arrayList) {
        this.context = context;
        this.mDuplicates = arrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_duplicate, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
        final Duplicate dMDuplicate = this.mDuplicates.get(i);
        itemViewHolder.txtItemLabel.setText(getFileName(dMDuplicate.getFile().getPath()));
        itemViewHolder.txtSize.setText(Utils.formatFileSize(dMDuplicate.getFile().length()));
        itemViewHolder.txtPath.setText(dMDuplicate.getFile().getPath());
        if (dMDuplicate.isChecked()) {
            itemViewHolder.img_selected.setVisibility(0);
            itemViewHolder.img_unselected.setVisibility(8);
            itemViewHolder.rl_border.setBackgroundResource(R.drawable.pink_borde);
        }
        switch (dMDuplicate.getTypeFile()) {
            case 0:
                try {
                    RequestManager with = Glide.with(this.context);
                    with.load("file://" + dMDuplicate.getFile().getPath()).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e) {
                    Context context = this.context;
                    Toast.makeText(context, "Exception: " + e.getMessage(), 0).show();
                    break;
                }
            case 1:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.drawable.audio)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e2) {
                    Context context2 = this.context;
                    Toast.makeText(context2, "Exception: " + e2.getMessage(), 0).show();
                    break;
                }
            case 2:
                itemViewHolder.imgPlay.setVisibility(0);
                try {
                    RequestManager with2 = Glide.with(this.context);
                    with2.load("file://" + dMDuplicate.getFile().getPath()).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e3) {
                    Context context3 = this.context;
                    Toast.makeText(context3, "Exception: " + e3.getMessage(), 0).show();
                    break;
                }
            case 3:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.drawable.document)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e4) {
                    Context context4 = this.context;
                    Toast.makeText(context4, "Exception: " + e4.getMessage(), 0).show();
                    break;
                }
            case 4:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.mipmap.ic_launcher)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e5) {
                    Context context5 = this.context;
                    Toast.makeText(context5, "Exception: " + e5.getMessage(), 0).show();
                    break;
                }
            case 5:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.drawable.vcf)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e6) {
                    Context context6 = this.context;
                    Toast.makeText(context6, "Exception: " + e6.getMessage(), 0).show();
                    break;
                }
            case 6:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.drawable.zip)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e7) {
                    Context context7 = this.context;
                    Toast.makeText(context7, "Exception: " + e7.getMessage(), 0).show();
                    break;
                }
            case 7:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.drawable.ic_pdf)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e8) {
                    Context context8 = this.context;
                    Toast.makeText(context8, "Exception: " + e8.getMessage(), 0).show();
                    break;
                }
            default:
                try {
                    Glide.with(this.context).load(Integer.valueOf((int) R.drawable.unknown)).diskCacheStrategy(DiskCacheStrategy.ALL).priority(Priority.HIGH).centerCrop().error(R.drawable.ic_error).into(itemViewHolder.image);
                    break;
                } catch (Exception e9) {
                    Context context9 = this.context;
                    Toast.makeText(context9, "Exception: " + e9.getMessage(), 0).show();
                    break;
                }
        }
        itemViewHolder.rl_check.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.DuplicateModule.DMAdapter.DMItemRecyclerViewAdapter.1
            @Override
            public void onClick(View view) {
                if (itemViewHolder.img_selected.getVisibility() == 8) {
                    dMDuplicate.setChecked(true);
                    itemViewHolder.img_selected.setVisibility(0);
                    itemViewHolder.img_unselected.setVisibility(8);
                    itemViewHolder.rl_border.setBackgroundResource(R.drawable.pink_borde);
                    return;
                }
                dMDuplicate.setChecked(false);
                itemViewHolder.img_selected.setVisibility(8);
                itemViewHolder.img_unselected.setVisibility(0);
                itemViewHolder.rl_border.setBackgroundResource(0);
            }
        });
        itemViewHolder.rlCard.setOnClickListener(new View.OnClickListener() { // from class: hdphoto.galleryimages.gelleryalbum.android.DuplicateModule.DMAdapter.DMItemRecyclerViewAdapter.2
            @Override
            public void onClick(View view) {
                try {
                    DataItemRecyclerViewAdapter.this.OpenFile(dMDuplicate);
                } catch (Exception unused) {
                }
            }
        });
    }

    public void OpenFile(Duplicate dMDuplicate) {
        Intent createChooser;
        try {
            File file = dMDuplicate.getFile();
            if (dMDuplicate.getTypeFile() == 1) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                if (file.exists()) {
                    if (Build.VERSION.SDK_INT < 24) {
                        intent.setDataAndType(Uri.fromFile(file), "audio/*");
                    } else {
                        Context context = this.context;
                        Uri uriForFile = FileProvider.getUriForFile(context, this.context.getPackageName() + ".provider", file);
                        Context context2 = this.context;
                        context2.grantUriPermission(context2.getPackageName(), uriForFile, 1);
                        intent.setDataAndType(uriForFile, "audio/*");
                        intent.setFlags(1);
                    }
                    this.context.startActivity(Intent.createChooser(intent, "Complete action using"));
                    return;
                }
                return;
            }
            if (dMDuplicate.getTypeFile() == 2) {
                if (Build.VERSION.SDK_INT < 24) {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setDataAndType(Uri.fromFile(dMDuplicate.getFile()), "video/*");
                    createChooser = Intent.createChooser(intent2, "Complete action using");
                } else {
                    Intent intent3 = new Intent("android.intent.action.VIEW");
                    Context context3 = this.context;
                    Uri uriForFile2 = FileProvider.getUriForFile(context3, this.context.getPackageName() + ".provider", file);
                    Context context4 = this.context;
                    context4.grantUriPermission(context4.getPackageName(), uriForFile2, 1);
                    intent3.setType("*/*");
                    if (Build.VERSION.SDK_INT < 24) {
                        uriForFile2 = Uri.fromFile(file);
                    }
                    intent3.setData(uriForFile2);
                    intent3.setFlags(1);
                    createChooser = Intent.createChooser(intent3, "Complete action using");
                }
            } else if (Build.VERSION.SDK_INT < 24) {
                Uri fromFile = Uri.fromFile(file);
                Intent intent4 = new Intent("android.intent.action.VIEW");
                MimeTypeMap singleton = MimeTypeMap.getSingleton();
                intent4.setDataAndType(fromFile, singleton.hasExtension(MimeTypeMap.getFileExtensionFromUrl(fromFile.toString())) ? singleton.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(fromFile.toString())) : "*/*");
                this.context.startActivity(intent4);
                return;
            } else {
                Intent intent5 = new Intent("android.intent.action.VIEW");
                Context context5 = this.context;
                Uri uriForFile3 = FileProvider.getUriForFile(context5, this.context.getPackageName() + ".provider", file);
                Context context6 = this.context;
                context6.grantUriPermission(context6.getPackageName(), uriForFile3, 1);
                intent5.setType("*/*");
                if (Build.VERSION.SDK_INT < 24) {
                    uriForFile3 = Uri.fromFile(file);
                }
                intent5.setData(uriForFile3);
                intent5.setFlags(1);
                createChooser = Intent.createChooser(intent5, "Complete action using");
            }
            this.context.startActivity(createChooser);
        } catch (Exception unused) {
        }
    }

    public String getFileName(String str) {
        return str.substring(str.lastIndexOf("/") + 1);
    }

    @Override
    public int getItemCount() {
        return this.mDuplicates.size();
    }
}
