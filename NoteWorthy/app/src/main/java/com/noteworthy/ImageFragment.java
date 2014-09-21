package com.noteworthy;
 
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
 
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
 
 
 
 
/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ImageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
 
    private int mImageNum;
 
    //CHANGED3
    private ImageView imageDisplay;
 
 
 
    private String mUrl;
 
    private ImageLoader imageLoader = ImageLoader.getInstance();
 
    
    DisplayImageOptions options;
 
    //LASTMINUTE
//    private removeUrlListener listener;
 
 
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(int position, String url)
    {
    	
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        args.putString(ARG_PARAM2, url);
        fragment.setArguments(args);
        return fragment;
    }
    public ImageFragment()
    {
    	
        // Required empty public constructor
    }
 
    //CHANGED4
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
//            listener = (removeUrlListener) activity; LASTMINUTE
        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
        }
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageNum = getArguments() != null ? getArguments().getInt(ARG_PARAM1) : -1;
        mUrl = getArguments() != null ? getArguments().getString(ARG_PARAM2) : null;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
        final View v = inflater.inflate(R.layout.fragment_image, container, false);
        //CHANGED3
        imageDisplay = (ImageView) v.findViewById(R.id.fragmentImageView);
 
        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .considerExifParams(true)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();
 
        setRetainInstance(true);
        return v;
 
 
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
 
 
        Log.e("wat", ""+imageLoader.isInited());
 
        imageLoader.displayImage(mUrl, imageDisplay, options, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                Log.d("testing", mUrl);
            }
 
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "Input/Output error";
                        break;
                    case DECODING_ERROR:
                        message = "Image can't be decoded";
                        break;
                    case NETWORK_DENIED:
                        message = "Downloads are denied";
                        break;
                    case OUT_OF_MEMORY:
                        message = "Out Of Memory error";
                        break;
                    case UNKNOWN:
                        message = "Unknown error";
                        break;
                }
 
                //CHANGED4
                //listener.removeUrlFromList(mImageNum);
 
            }
 
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            }
        });
 
    }
 
 
}