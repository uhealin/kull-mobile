package org.pccpa.adapter;

import java.util.List;

import org.pccpa.R;
import org.pccpa.api.Client;
import org.pccpa.api.EmployeeItem;
import org.pccpa.widget.MenuActionGrid;

import greendroid.image.ChainImageProcessor;
import greendroid.image.ImageProcessor;
import greendroid.image.MaskImageProcessor;
import greendroid.image.ScaleImageProcessor;
import greendroid.widget.AsyncImageView;
import greendroid.widget.QuickAction;
import greendroid.widget.QuickActionBar;
import greendroid.widget.QuickActionWidget;
import greendroid.widget.QuickActionWidget.OnQuickActionClickListener;





import com.kull.StringHelper;
import com.kull.android.ContextHelper;
import com.kull.android.widget.ContextAdapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class EmployeeItemAdapter extends ContextAdapter {
	private QuickActionWidget mBar;
	
	static class ViewHolder{
		public AsyncImageView imageView;
		public TextView textView;
		public StringBuilder texBuilder=new StringBuilder();
		public Button btnMore;
	}
	private final String mImageForPosition="pccpa";
    private static final StringBuilder BUILDER = new StringBuilder();
	private LayoutInflater _inflater;
	private ImageProcessor _imageProcessor;
	private List<EmployeeItem> _ems;
	public EmployeeItemAdapter(Context context,List<EmployeeItem> ems) {
		super(context);
		
		_inflater=LayoutInflater.from(_context);
		this._ems=ems;
		 mBar = new QuickActionBar(_context);
	     mBar.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_talk, R.string.action_dotel));
	      mBar.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_mail, R.string.action_sms));
	      mBar.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_info, R.string.action_eminfo));
	      mBar.addQuickAction(new MyQuickAction(_context, R.drawable.gd_action_bar_add, R.string.action_addtocontact));
	      mBar.setOnQuickActionClickListener(mActionListener);    
		// TODO Auto-generated constructor stub
	}

	
	 private OnQuickActionClickListener mActionListener = new OnQuickActionClickListener() {
	        public void onQuickActionClicked(QuickActionWidget widget, int position) {
	        	 EmployeeItem selectedEm=(EmployeeItem)getItem(position);
	        	 String number=StringHelper.firstNotBlank(selectedEm.getEMobile()
	        			 ,selectedEm.getEMobileShort(),selectedEm.getETelWork(),selectedEm.getETelWorkShort());
	        	 switch (position) {
				case 0:
					_contextHelper.toCallTel(number);
					break;
                case 1:
                	try{
                		_contextHelper.toSendSms(number,"");
                	}catch(Exception ex){
                		Toast.makeText(_context, "貌似平板不能发短信", 5000).show();
                	}
                
					break;
                case 2:
                	Toast.makeText(_context, "功能未实现", 5000).show();
                	break;
                case 3:
                	Toast.makeText(_context, "功能未实现", 5000).show();
                	break;
				default:
					break;
				}
	        }
	    };
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this._ems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this._ems.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return Long.parseLong(this._ems.get(arg0).getEID());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	     ViewHolder holder;
         EmployeeItem emItem=(EmployeeItem)this.getItem(position);
         if (convertView == null) {
             convertView = _inflater.inflate(R.layout.image_item_view, parent, false);
             holder = new ViewHolder();
             holder.imageView = (AsyncImageView) convertView.findViewById(R.id.async_image);
             holder.imageView.setImageProcessor(_imageProcessor);
             holder.textView = (TextView) convertView.findViewById(R.id.text);
             holder.btnMore=(Button)convertView.findViewById(R.id.button_more);
             holder.btnMore.setText(R.string.more);
             holder.btnMore.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						//attemptLogin();
						mBar.show(view);
					}
				})
			;
             convertView.setTag(holder);
         } else {
             holder = (ViewHolder) convertView.getTag();
         }

         //BUILDER.setLength(0);
         //BUILDER.append(BASE_URL_PREFIX);
         //BUILDER.append(position);
         //BUILDER.append(BASE_URL_SUFFIX);
         String url=Client.urlEmployeePhoto(this._ems.get(position).getEID());
         holder.imageView.setUrl(url);

         //final StringBuilder textBuilder = holder.texBuilder;
         //textBuilder.setLength(0);
         //textBuilder.append(mImageForPosition);
         //textBuilder.append(position);
         holder.textView.setText(emItem.getEUserName()+" "+StringHelper.firstNotBlank(emItem.getEMobile(),emItem.getETelWork()));

         return convertView;
	}
	
	
	 private void prepareImageProcessor(Context context) {
         
         final int thumbnailSize = context.getResources().getDimensionPixelSize(R.dimen.thumbnail_size);
         final int thumbnailRadius = context.getResources().getDimensionPixelSize(R.dimen.thumbnail_radius);

         if (Math.random() >= 0.5f||1==1) {
             //@formatter:off
             _imageProcessor = new ChainImageProcessor(
                     new ScaleImageProcessor(thumbnailSize, thumbnailSize, ScaleType.FIT_XY),
                     new MaskImageProcessor(thumbnailRadius));
             //@formatter:on
         } else {
             
             Path path = new Path();
             path.moveTo(thumbnailRadius, 0);
             
             path.lineTo(thumbnailSize - thumbnailRadius, 0);
             path.lineTo(thumbnailSize, thumbnailRadius);
             path.lineTo(thumbnailSize, thumbnailSize - thumbnailRadius);
             path.lineTo(thumbnailSize - thumbnailRadius, thumbnailSize);
             path.lineTo(thumbnailRadius, thumbnailSize);
             path.lineTo(0, thumbnailSize - thumbnailRadius);
             path.lineTo(0, thumbnailRadius);
             
             path.close();
             
             Bitmap mask = Bitmap.createBitmap(thumbnailSize, thumbnailSize, Config.ARGB_8888);
             Canvas canvas = new Canvas(mask);
             
             Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
             paint.setStyle(Style.FILL_AND_STROKE);
             paint.setColor(Color.RED);
             
             canvas.drawPath(path, paint);
             
             //@formatter:off
             _imageProcessor = new ChainImageProcessor(
                     new ScaleImageProcessor(thumbnailSize, thumbnailSize, ScaleType.FIT_XY),
                     new MaskImageProcessor(mask));
             //@formatter:on
         }
     }

	 private static class MyQuickAction extends QuickAction {
	        
	        private static final ColorFilter BLACK_CF = new LightingColorFilter(Color.BLACK, Color.BLACK);

	        public MyQuickAction(Context ctx, int drawableId, int titleId) {
	            super(ctx, buildDrawable(ctx, drawableId), titleId);
	        }
	        
	        private static Drawable buildDrawable(Context ctx, int drawableId) {
	            Drawable d = ctx.getResources().getDrawable(drawableId);
	            d.setColorFilter(BLACK_CF);
	            return d;
	        }
	        
	    }
}
