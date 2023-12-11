package hdphoto.galleryimages.gelleryalbum.Images_android.Ibex_CollageModule.Luxgen_ShapeLayout;

import hdphoto.galleryimages.gelleryalbum.R;
import java.util.ArrayList;
import java.util.List;


public class Spyker_CollageShapeUtils {
    public static int[][] collageShapeIconsArray = {new int[]{R.drawable.collage_shape_1_0, R.drawable.collage_shape_1_1, R.drawable.collage_shape_1_2, R.drawable.collage_shape_1_3, R.drawable.collage_shape_1_4, R.drawable.collage_shape_1_5, R.drawable.collage_shape_1_6, R.drawable.collage_shape_1_7, R.drawable.collage_shape_1_8, R.drawable.collage_shape_1_9, R.drawable.collage_shape_1_10, R.drawable.collage_shape_1_11}, new int[]{R.drawable.collage_shape_2_0, R.drawable.collage_shape_2_1, R.drawable.collage_shape_2_2, R.drawable.collage_shape_2_3, R.drawable.collage_shape_2_4, R.drawable.collage_shape_2_5, R.drawable.collage_shape_2_6, R.drawable.collage_shape_2_7, R.drawable.collage_shape_2_8, R.drawable.collage_shape_2_9, R.drawable.collage_shape_2_10, R.drawable.collage_shape_2_11, R.drawable.collage_shape_2_12, R.drawable.collage_shape_2_13, R.drawable.collage_shape_2_14, R.drawable.collage_shape_2_15}, new int[]{R.drawable.collage_shape_3_0, R.drawable.collage_shape_3_1, R.drawable.collage_shape_3_2, R.drawable.collage_shape_3_3, R.drawable.collage_shape_3_4, R.drawable.collage_shape_3_5, R.drawable.collage_shape_3_6, R.drawable.collage_shape_3_7, R.drawable.collage_shape_3_8, R.drawable.collage_shape_3_9, R.drawable.collage_shape_3_10, R.drawable.collage_shape_3_11, R.drawable.collage_shape_3_12, R.drawable.collage_shape_3_13, R.drawable.collage_shape_3_14, R.drawable.collage_shape_3_15, R.drawable.collage_shape_3_16, R.drawable.collage_shape_3_17, R.drawable.collage_shape_3_18, R.drawable.collage_shape_3_19, R.drawable.collage_shape_3_20, R.drawable.collage_shape_3_21}, new int[]{R.drawable.collage_shape_4_0, R.drawable.collage_shape_4_1, R.drawable.collage_shape_4_2, R.drawable.collage_shape_4_3, R.drawable.collage_shape_4_4, R.drawable.collage_shape_4_5, R.drawable.collage_shape_4_6, R.drawable.collage_shape_4_7, R.drawable.collage_shape_4_8, R.drawable.collage_shape_4_9, R.drawable.collage_shape_4_10, R.drawable.collage_shape_4_11, R.drawable.collage_shape_4_12, R.drawable.collage_shape_4_13, R.drawable.collage_shape_4_14, R.drawable.collage_shape_4_15, R.drawable.collage_shape_4_16, R.drawable.collage_shape_4_17}, new int[]{R.drawable.collage_shape_5_0, R.drawable.collage_shape_5_1, R.drawable.collage_shape_5_2, R.drawable.collage_shape_5_3, R.drawable.collage_shape_5_4, R.drawable.collage_shape_5_5, R.drawable.collage_shape_5_6, R.drawable.collage_shape_5_7, R.drawable.collage_shape_5_8, R.drawable.collage_shape_5_9, R.drawable.collage_shape_5_10, R.drawable.collage_shape_5_11, R.drawable.collage_shape_5_12, R.drawable.collage_shape_5_13, R.drawable.collage_shape_5_14, R.drawable.collage_shape_5_15, R.drawable.collage_shape_5_16, R.drawable.collage_shape_5_17, R.drawable.collage_shape_5_18, R.drawable.collage_shape_5_19, R.drawable.collage_shape_5_20}, new int[]{R.drawable.collage_shape_6_0, R.drawable.collage_shape_6_1, R.drawable.collage_shape_6_2, R.drawable.collage_shape_6_3, R.drawable.collage_shape_6_4, R.drawable.collage_shape_6_5, R.drawable.collage_shape_6_6, R.drawable.collage_shape_6_7, R.drawable.collage_shape_6_8, R.drawable.collage_shape_6_9, R.drawable.collage_shape_6_10, R.drawable.collage_shape_6_11, R.drawable.collage_shape_6_12}, new int[]{R.drawable.collage_shape_7_0, R.drawable.collage_shape_7_1, R.drawable.collage_shape_7_2, R.drawable.collage_shape_7_3, R.drawable.collage_shape_7_4, R.drawable.collage_shape_7_5, R.drawable.collage_shape_7_6, R.drawable.collage_shape_7_7, R.drawable.collage_shape_7_8, R.drawable.collage_shape_7_9}, new int[]{R.drawable.collage_shape_8_0, R.drawable.collage_shape_8_1, R.drawable.collage_shape_8_2, R.drawable.collage_shape_8_3, R.drawable.collage_shape_8_4, R.drawable.collage_shape_8_5, R.drawable.collage_shape_8_6, R.drawable.collage_shape_8_7, R.drawable.collage_shape_8_8, R.drawable.collage_shape_8_9, R.drawable.collage_shape_8_10, R.drawable.collage_shape_8_11, R.drawable.collage_shape_8_12, R.drawable.collage_shape_8_13, R.drawable.collage_shape_8_14, R.drawable.collage_shape_8_15}, new int[]{R.drawable.collage_shape_9_0, R.drawable.collage_shape_9_1, R.drawable.collage_shape_9_2, R.drawable.collage_shape_9_3, R.drawable.collage_shape_9_4, R.drawable.collage_shape_9_5, R.drawable.collage_shape_9_6, R.drawable.collage_shape_9_7, R.drawable.collage_shape_9_8}};
    public static float scrapBookShapeScale = 1.0f;
    public List collageLayoutList;

    public static Spyker_CollageShapeUtils CreateCollage(int i, int i2, int i3, boolean z) {
        if (z) {
            return CreateScrapLayout(i, i2, i3);
        }
        if (i == 1) {
            return new Rabbit_ClgShape1(i2, i3);
        }
        if (i == 2) {
            return new Rabbit_ClgShape2(i2, i3);
        }
        if (i == 3) {
            return new Rabbit_ClgShape3(i2, i3);
        }
        if (i == 4) {
            return new Rabbit_ClgShape4(i2, i3);
        }
        if (i == 5) {
            return new Rabbit_ClgShape5(i2, i3);
        }
        if (i == 6) {
            return new Rabbit_ClgShape6(i2, i3);
        }
        if (i == 7) {
            return new Rabbit_ClgShape7(i2, i3);
        }
        if (i == 8) {
            return new Rabbit_ClgShape8(i2, i3);
        }
        if (i == 9) {
            return new Rabbit_ClgShape9(i2, i3);
        }
        if (i == 10) {
            return new Spyker_CollageShape10(i2, i3);
        }
        return null;
    }

    public static Spyker_CollageShapeUtils CreateScrapLayout(int i, int i2, int i3) {
        Spyker_CollageShapeUtils cMCollageShapeUtils = new Spyker_CollageShapeUtils();
        cMCollageShapeUtils.collageLayoutList = new ArrayList();
        Rabbit_CollageScrapBook cMCollageScrapBook = new Rabbit_CollageScrapBook(i2, i3);
        if (i == 1) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(0));
            scrapBookShapeScale = 0.7f;
        }
        if (i == 2) {
            if (i3 > i2) {
                cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(2));
            } else {
                cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(1));
            }
            scrapBookShapeScale = 1.0f;
        } else if (i == 3) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(3));
            scrapBookShapeScale = 0.95f;
            return cMCollageShapeUtils;
        } else if (i == 4) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(4));
            scrapBookShapeScale = 1.0f;
            return cMCollageShapeUtils;
        } else if (i == 5) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(5));
            scrapBookShapeScale = 0.95f;
            return cMCollageShapeUtils;
        } else if (i == 6) {
            if (i3 > i2) {
                cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(6));
            } else {
                cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(7));
            }
            scrapBookShapeScale = 1.0f;
            return cMCollageShapeUtils;
        } else if (i == 7) {
            if (i3 > i2) {
                cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(8));
            } else {
                cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(9));
            }
            scrapBookShapeScale = 1.0f;
            return cMCollageShapeUtils;
        } else if (i == 8) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(10));
            scrapBookShapeScale = 1.0f;
            return cMCollageShapeUtils;
        } else if (i == 9) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(11));
            scrapBookShapeScale = 1.0f;
            return cMCollageShapeUtils;
        } else if (i == 10) {
            cMCollageShapeUtils.collageLayoutList.add((Rabbit_CollageLayout) cMCollageScrapBook.collageLayoutList.get(12));
            scrapBookShapeScale = 1.0f;
        }
        return cMCollageShapeUtils;
    }
}
