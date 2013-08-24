package com.kull.android;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.kull.ObjectHelper;

public class RHelper {

	
	public enum RType{
		id(RHelper.id.class),attr(RHelper.attr.class),style(RHelper.style.class),
		layout(RHelper.layout.class),menu(RHelper.menu.class),string(RHelper.string.class),
		color(RHelper.color.class),dimen(RHelper.dimen.class),anim(RHelper.anim.class),
		drawable(RHelper.drawable.class),styleable(RHelper.styleable.class);
		
		public final Class rclass;
		RType(Class cls){
			rclass=cls;
		}
	}
	private static Map<String, Integer> CACHE_IDS=new HashMap<String, Integer>();
	private static Map<String, Integer[]> CACHE_IDARRAYS=new HashMap<String, Integer[]>();
	//private static Map<RType,Map<String, Integer>> IDS=new HashMap<RType,Map<String, Integer>>();
	private static Class CLASS_R=null;
	
	public static void init(Class clsR) throws Exception{
		if(CLASS_R!=null)return;
		
		if(!"R".equals(CLASS_R.getSimpleName())){
			throw new Exception("this class must be R");
		}
		CLASS_R=clsR;
		Class[] clss=  CLASS_R.getDeclaredClasses();
		for(RType rType :RType.values()){
			//IDS.put(rType, new HashMap<String, Integer>());
			
			for(Class cls :clss){
				String clsName=cls.getSimpleName();
				if(!clsName.equals(rType.name()))continue;
				Field[] fields=cls.getDeclaredFields();
				for(Field field :fields){
					    String fname=field.getName();
						String key=getKey(rType, fname);
						Type ft=field.getType();
				        if(Integer.class.equals(ft)){		
						  CACHE_IDS.put(key, field.getInt(cls));
				        }else if(Integer[].class.equals(ft)){
				        	CACHE_IDARRAYS.put(key, (Integer[])field.get(cls));
				        }
				        Field rf=rType.rclass.getField(fname);
				        if(rf!=null){
				        	rf.set(rType.rclass, field.get(cls));
				        }
				}
			}
			
		}
	}
	
	private static String getKey(RType rtype,String name){
		return rtype.name()+":"+name;
	}
	
	public static int getResourceIdByName(RType rtype,String name) throws NullPointerException{
		String key=getKey(rtype, name);
		if(CACHE_IDS.containsKey(key)){
			return CACHE_IDS.get(key);
		}else throw new NullPointerException(rtype.name()+":"+name+" is not exist");
		
		
	}
	
	public static int getResourceIdByName(String name)throws NullPointerException{
		
		for(RType rtype :RType.values()){
			String key=getKey(rtype, name);
			if(CACHE_IDS.containsKey(key)){
				return CACHE_IDS.get(key);
			}
		}
		throw new NullPointerException(name+" is not exist");
		
	}
	
	
	public static int getResourceByKey(String key) throws NullPointerException{
		
		if(CACHE_IDS.containsKey(key)){
			return CACHE_IDS.get(key);
		}else throw new NullPointerException(key+" is not exist");
		
		
	}
	
	
	public static String getString(Context context, String name){
		String key=getKey(RType.string, name);
		if(CACHE_IDS.containsKey(key)){
			return context.getString(CACHE_IDS.get(key));
		}else throw new NullPointerException(name+" is not exist");
	}
	
	
	
	
	
	
	
	
	public static   class anim {
		public static   int  kull_grow_from_bottom = Integer.MIN_VALUE;
		public static   int  kull_grow_from_bottomleft_to_topright = Integer.MIN_VALUE;
		public static   int  kull_grow_from_bottomright_to_topleft = Integer.MIN_VALUE;
		public static   int  kull_grow_from_top = Integer.MIN_VALUE;
		public static   int  kull_grow_from_topleft_to_bottomright = Integer.MIN_VALUE;
		public static   int  kull_grow_from_topright_to_bottomleft = Integer.MIN_VALUE;
		public static   int  kull_rack = Integer.MIN_VALUE;
		public static   int  kull_shrink_from_bottom = Integer.MIN_VALUE;
		public static   int  kull_shrink_from_bottomleft_to_topright = Integer.MIN_VALUE;
		public static   int  kull_shrink_from_bottomright_to_topleft = Integer.MIN_VALUE;
		public static   int  kull_shrink_from_top = Integer.MIN_VALUE;
		public static   int  kull_shrink_from_topleft_to_bottomright = Integer.MIN_VALUE;
		public static   int  kull_shrink_from_topright_to_bottomleft = Integer.MIN_VALUE;
	}
	public static   class attr {
		public static   int activeDot = Integer.MIN_VALUE;
		public static   int defaultSrc = Integer.MIN_VALUE;
		public static   int dividerDrawable = Integer.MIN_VALUE;
		public static   int dividerWidth = Integer.MIN_VALUE;
		public static   int dotCount = Integer.MIN_VALUE;
		public static   int dotDrawable = Integer.MIN_VALUE;
		public static   int dotSpacing = Integer.MIN_VALUE;
		public static   int dotType = Integer.MIN_VALUE;
		public static   int drawable = Integer.MIN_VALUE;
		public static   int enabled = Integer.MIN_VALUE;
		public static   int kull_ActionBarApplicationDrawable = Integer.MIN_VALUE;
		public static   int kull_ActionBarBackground = Integer.MIN_VALUE;
		public static   int kull_ActionBarDividerDrawable = Integer.MIN_VALUE;
		public static   int kull_ActionBarDividerStyle = Integer.MIN_VALUE;
		public static   int kull_ActionBarDividerWidth = Integer.MIN_VALUE;
		public static   int kull_ActionBarHomeDrawable = Integer.MIN_VALUE;
		public static   int kull_ActionBarItemBackground = Integer.MIN_VALUE;
		public static   int kull_ActionBarItemColorAlt = Integer.MIN_VALUE;
		public static   int kull_ActionBarItemColorNormal = Integer.MIN_VALUE;
		public static   int kull_ActionBarItemStyle = Integer.MIN_VALUE;
		public static   int kull_ActionBarMaxItems = Integer.MIN_VALUE;
		public static   int kull_ActionBarStyle = Integer.MIN_VALUE;
		public static   int kull_ActionBarTitleColor = Integer.MIN_VALUE;
		public static   int kull_ActionBarTitleStyle = Integer.MIN_VALUE;
		public static   int kull_DescriptionItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_DrawableHeight = Integer.MIN_VALUE;
		public static   int kull_DrawableItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_DrawableItemViewStyleDrawable = Integer.MIN_VALUE;
		public static   int kull_DrawableItemViewStyleText = Integer.MIN_VALUE;
		public static   int kull_DrawableMargin = Integer.MIN_VALUE;
		public static   int kull_DrawableWidth = Integer.MIN_VALUE;
		public static   int kull_ItemViewPreferredHalfHeight = Integer.MIN_VALUE;
		public static   int kull_ItemViewPreferredHeight = Integer.MIN_VALUE;
		public static   int kull_ItemViewPreferredPaddingLeft = Integer.MIN_VALUE;
		public static   int kull_LongTextItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_PageIndicatorStyle = Integer.MIN_VALUE;
		public static   int kull_ProgressBarHeight = Integer.MIN_VALUE;
		public static   int kull_ProgressBarMargin = Integer.MIN_VALUE;
		public static   int kull_ProgressBarWidth = Integer.MIN_VALUE;
		public static   int kull_ProgressItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_ProgressItemViewStyleProgressBar = Integer.MIN_VALUE;
		public static   int kull_ProgressItemViewStyleText = Integer.MIN_VALUE;
		public static   int kull_QuickActionBarItemStyle = Integer.MIN_VALUE;
		public static   int kull_QuickActionBarStyle = Integer.MIN_VALUE;
		public static   int kull_QuickActionGridItemStyle = Integer.MIN_VALUE;
		public static   int kull_QuickActionGridStyle = Integer.MIN_VALUE;
		public static   int kull_SegmentBackground = Integer.MIN_VALUE;
		public static   int kull_SegmentCheckmark = Integer.MIN_VALUE;
		public static   int kull_SegmentTextColor = Integer.MIN_VALUE;
		public static   int kull_SegmentTextSize = Integer.MIN_VALUE;
		public static   int kull_SegmentedBarStyle = Integer.MIN_VALUE;
		public static   int kull_SegmentedHostStyle = Integer.MIN_VALUE;
		public static   int kull_SeparatorItemViewPreferredHeight = Integer.MIN_VALUE;
		public static   int kull_SeparatorItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_SubtextItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_SubtextItemViewStyleSubtext = Integer.MIN_VALUE;
		public static   int kull_SubtextItemViewStyleText = Integer.MIN_VALUE;
		public static   int kull_SubtitleItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_SubtitleItemViewStyleSubtitle = Integer.MIN_VALUE;
		public static   int kull_SubtitleItemViewStyleText = Integer.MIN_VALUE;
		public static   int kull_TabIndicatorBackground = Integer.MIN_VALUE;
		public static   int kull_TabIndicatorHeight = Integer.MIN_VALUE;
		public static   int kull_TabIndicatorStyle = Integer.MIN_VALUE;
		public static   int kull_TabIndicatorTextAppearance = Integer.MIN_VALUE;
		public static   int kull_TextAppearance = Integer.MIN_VALUE;
		public static   int kull_TextAppearanceLarge = Integer.MIN_VALUE;
		public static   int kull_TextAppearanceMedium = Integer.MIN_VALUE;
		public static   int kull_TextAppearanceSeparator = Integer.MIN_VALUE;
		public static   int kull_TextAppearanceSmall = Integer.MIN_VALUE;
		public static   int kull_TextItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_ThumbnailItemViewStyle = Integer.MIN_VALUE;
		public static   int kull_ThumbnailItemViewStyleSubtitle = Integer.MIN_VALUE;
		public static   int kull_ThumbnailItemViewStyleText = Integer.MIN_VALUE;
		public static   int kull_ThumbnailItemViewStyleThumbnail = Integer.MIN_VALUE;
		public static   int gravity = Integer.MIN_VALUE;
		public static   int homeDrawable = Integer.MIN_VALUE;
		public static   int inDensity = Integer.MIN_VALUE;
		public static   int isInProgress = Integer.MIN_VALUE;
		public static   int maxItems = Integer.MIN_VALUE;
		public static   int segmentedBar = Integer.MIN_VALUE;
		public static   int segmentedContentView = Integer.MIN_VALUE;
		public static   int subtext = Integer.MIN_VALUE;
		public static   int subtitle = Integer.MIN_VALUE;
		public static   int text = Integer.MIN_VALUE;
		public static   int thumbnail = Integer.MIN_VALUE;
		public static   int thumbnailURL = Integer.MIN_VALUE;
		public static   int title = Integer.MIN_VALUE;
		public static   int type = Integer.MIN_VALUE;
		public static   int url = Integer.MIN_VALUE;
	}
	public static   class color {
		public static   int  kull_action_bar_divider_tint = Integer.MIN_VALUE;
		public static   int  kull_action_bar_tint = Integer.MIN_VALUE;
	}
	public static   class dimen {
		public static   int  kull_action_bar_height = Integer.MIN_VALUE;
		public static   int  kull_arrow_offset = Integer.MIN_VALUE;
		public static   int  kull_drawable_height = Integer.MIN_VALUE;
		public static   int  kull_drawable_margin = Integer.MIN_VALUE;
		public static   int  kull_drawable_width = Integer.MIN_VALUE;
		public static   int  kull_item_view_half_height = Integer.MIN_VALUE;
		public static   int  kull_item_view_height = Integer.MIN_VALUE;
		public static   int  kull_item_view_padding_left = Integer.MIN_VALUE;
		public static   int  kull_progress_bar_height = Integer.MIN_VALUE;
		public static   int  kull_progress_bar_margin = Integer.MIN_VALUE;
		public static   int  kull_progress_bar_width = Integer.MIN_VALUE;
		public static   int  kull_separator_item_view_height = Integer.MIN_VALUE;
		public static   int  kull_separator_item_view_padding_left = Integer.MIN_VALUE;
	}
	public static   class drawable {
		public static   int  kull_action_bar_add = Integer.MIN_VALUE;
		public static   int  kull_action_bar_all_friends = Integer.MIN_VALUE;
		public static   int  kull_action_bar_compass = Integer.MIN_VALUE;
		public static   int  kull_action_bar_compose = Integer.MIN_VALUE;
		public static   int  kull_action_bar_edit = Integer.MIN_VALUE;
		public static   int  kull_action_bar_export = Integer.MIN_VALUE;
		public static   int  kull_action_bar_eye = Integer.MIN_VALUE;
		public static   int  kull_action_bar_gallery = Integer.MIN_VALUE;
		public static   int  kull_action_bar_group = Integer.MIN_VALUE;
		public static   int  kull_action_bar_help = Integer.MIN_VALUE;
		public static   int  kull_action_bar_home = Integer.MIN_VALUE;
		public static   int  kull_action_bar_info = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item_focused = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item_pressed = Integer.MIN_VALUE;
		public static   int  kull_action_bar_list = Integer.MIN_VALUE;
		public static   int  kull_action_bar_locate = Integer.MIN_VALUE;
		public static   int  kull_action_bar_locate_myself = Integer.MIN_VALUE;
		public static   int  kull_action_bar_mail = Integer.MIN_VALUE;
		public static   int  kull_action_bar_refresh = Integer.MIN_VALUE;
		public static   int  kull_action_bar_search = Integer.MIN_VALUE;
		public static   int  kull_action_bar_settings = Integer.MIN_VALUE;
		public static   int  kull_action_bar_share = Integer.MIN_VALUE;
		public static   int  kull_action_bar_slideshow = Integer.MIN_VALUE;
		public static   int  kull_action_bar_sort_alpha = Integer.MIN_VALUE;
		public static   int  kull_action_bar_sort_by_size = Integer.MIN_VALUE;
		public static   int  kull_action_bar_star = Integer.MIN_VALUE;
		public static   int  kull_action_bar_take_photo = Integer.MIN_VALUE;
		public static   int  kull_action_bar_talk = Integer.MIN_VALUE;
		public static   int  kull_action_bar_trashcan = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_focused = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_normal = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_normal_focused = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_normal_normal = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_normal_pressed = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_pressed = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_selected = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_selected_focused = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_selected_normal = Integer.MIN_VALUE;
		public static   int  kull_page_indicator_dot_selected_pressed = Integer.MIN_VALUE;
		public static   int  kull_quick_action_arrow_up = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_arrow_down = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_background = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_bottom_frame = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_grip_left = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_grip_right = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_item = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_item_normal = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_item_pressed = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_item_selected = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_arrow_down = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_bg = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_bottom_frame = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_selector = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_selector_focused = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_selector_pressed = Integer.MIN_VALUE;
		public static   int  kull_quick_action_top_frame = Integer.MIN_VALUE;
		public static   int  kull_segment_checkmark = Integer.MIN_VALUE;
		public static   int  kull_segment_checkmark_off = Integer.MIN_VALUE;
		public static   int  kull_segment_checkmark_on = Integer.MIN_VALUE;
		public static   int  kull_segment_divider = Integer.MIN_VALUE;
		public static   int  kull_segment_label = Integer.MIN_VALUE;
		public static   int  kull_segment_label_focused = Integer.MIN_VALUE;
		public static   int  kull_segment_label_normal = Integer.MIN_VALUE;
		public static   int  kull_segment_label_pressed = Integer.MIN_VALUE;
		public static   int  kull_shadow_bottom = Integer.MIN_VALUE;
		public static   int  kull_shadow_top = Integer.MIN_VALUE;
		public static   int  kull_tab_indicator = Integer.MIN_VALUE;
		public static   int  kull_tab_indicator_normal = Integer.MIN_VALUE;
		public static   int  kull_tab_indicator_pressed = Integer.MIN_VALUE;
		public static   int  kull_tab_indicator_selected = Integer.MIN_VALUE;
		public static   int  kull_tab_indicator_unselected = Integer.MIN_VALUE;
	}
	public  static  class id {
		public static   int bottom = Integer.MIN_VALUE;
		public static   int center = Integer.MIN_VALUE;
		public static   int center_horizontal = Integer.MIN_VALUE;
		public static   int center_vertical = Integer.MIN_VALUE;
		public static   int dashboard = Integer.MIN_VALUE;
		public static   int empty = Integer.MIN_VALUE;
		public static   int  kull_action_bar = Integer.MIN_VALUE;
		public static   int  kull_action_bar_content_view = Integer.MIN_VALUE;
		public static   int  kull_action_bar_home_item = Integer.MIN_VALUE;
		public static   int  kull_action_bar_host = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item_progress_bar = Integer.MIN_VALUE;
		public static   int  kull_action_bar_title = Integer.MIN_VALUE;
		public static   int  kull_description = Integer.MIN_VALUE;
		public static   int  kull_drawable = Integer.MIN_VALUE;
		public static   int  kull_progress_bar = Integer.MIN_VALUE;
		public static   int  kull_segmented_bar = Integer.MIN_VALUE;
		public static   int  kull_segmented_content_view = Integer.MIN_VALUE;
		public static   int  kull_separator_text = Integer.MIN_VALUE;
		public static   int  kull_subtext = Integer.MIN_VALUE;
		public static   int  kull_subtitle = Integer.MIN_VALUE;
		public static   int  kull_text = Integer.MIN_VALUE;
		public static   int  kull_thumbnail = Integer.MIN_VALUE;
		public static   int gdi_arrow_down = Integer.MIN_VALUE;
		public static   int gdi_arrow_up = Integer.MIN_VALUE;
		public static   int gdi_footer = Integer.MIN_VALUE;
		public static   int gdi_grid = Integer.MIN_VALUE;
		public static   int gdi_header = Integer.MIN_VALUE;
		public static   int gdi_quick_action_items = Integer.MIN_VALUE;
		public static   int gdi_rack = Integer.MIN_VALUE;
		public static   int gdi_scroll = Integer.MIN_VALUE;
		public static   int hdpi = Integer.MIN_VALUE;
		public static   int ldpi = Integer.MIN_VALUE;
		public static   int left = Integer.MIN_VALUE;
		public static   int mdpi = Integer.MIN_VALUE;
		public static   int multiple = Integer.MIN_VALUE;
		public static   int normal = Integer.MIN_VALUE;
		public static   int right = Integer.MIN_VALUE;
		public static   int single = Integer.MIN_VALUE;
		public static   int top = Integer.MIN_VALUE;
		public static   int xhdpi = Integer.MIN_VALUE;
	}
	public static   class layout {
		public static   int  kull_action_bar_dashboard = Integer.MIN_VALUE;
		public static   int  kull_action_bar_empty = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item_base = Integer.MIN_VALUE;
		public static   int  kull_action_bar_item_loader = Integer.MIN_VALUE;
		public static   int  kull_action_bar_normal = Integer.MIN_VALUE;
		public static   int  kull_content_dashboard = Integer.MIN_VALUE;
		public static   int  kull_content_empty = Integer.MIN_VALUE;
		public static   int  kull_content_normal = Integer.MIN_VALUE;
		public static   int  kull_description_item_view = Integer.MIN_VALUE;
		public static   int  kull_drawable_item_view = Integer.MIN_VALUE;
		public static   int  kull_expandable_list_content_dashboard = Integer.MIN_VALUE;
		public static   int  kull_expandable_list_content_empty = Integer.MIN_VALUE;
		public static   int  kull_expandable_list_content_normal = Integer.MIN_VALUE;
		public static   int  kull_list_content_dashboard = Integer.MIN_VALUE;
		public static   int  kull_list_content_empty = Integer.MIN_VALUE;
		public static   int  kull_list_content_normal = Integer.MIN_VALUE;
		public static   int  kull_long_text_item_view = Integer.MIN_VALUE;
		public static   int  kull_progress_item_view = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar = Integer.MIN_VALUE;
		public static   int  kull_quick_action_bar_item = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid = Integer.MIN_VALUE;
		public static   int  kull_quick_action_grid_item = Integer.MIN_VALUE;
		public static   int  kull_segment = Integer.MIN_VALUE;
		public static   int  kull_separator_item_view = Integer.MIN_VALUE;
		public static   int  kull_subtext_item_view = Integer.MIN_VALUE;
		public static   int  kull_subtitle_item_view = Integer.MIN_VALUE;
		public static   int  kull_tab_content = Integer.MIN_VALUE;
		public static   int  kull_tab_indicator = Integer.MIN_VALUE;
		public static   int  kull_text_item_view = Integer.MIN_VALUE;
		public static   int  kull_thumbnail_item_view = Integer.MIN_VALUE;
	}
	public static   class string {
		public static   int  kull_add = Integer.MIN_VALUE;
		public static   int  kull_all_friends = Integer.MIN_VALUE;
		public static   int  kull_compass = Integer.MIN_VALUE;
		public static   int  kull_compose = Integer.MIN_VALUE;
		public static   int  kull_edit = Integer.MIN_VALUE;
		public static   int  kull_export = Integer.MIN_VALUE;
		public static   int  kull_eye = Integer.MIN_VALUE;
		public static   int  kull_gallery = Integer.MIN_VALUE;
		public static   int  kull_go_home = Integer.MIN_VALUE;
		public static   int  kull_group = Integer.MIN_VALUE;
		public static   int  kull_help = Integer.MIN_VALUE;
		public static   int  kull_info = Integer.MIN_VALUE;
		public static   int  kull_list = Integer.MIN_VALUE;
		public static   int  kull_locate = Integer.MIN_VALUE;
		public static   int  kull_locate_myself = Integer.MIN_VALUE;
		public static   int  kull_mail = Integer.MIN_VALUE;
		public static   int  kull_pick_photo = Integer.MIN_VALUE;
		public static   int  kull_refresh = Integer.MIN_VALUE;
		public static   int  kull_search = Integer.MIN_VALUE;
		public static   int  kull_settings = Integer.MIN_VALUE;
		public static   int  kull_share = Integer.MIN_VALUE;
		public static   int  kull_slideshow = Integer.MIN_VALUE;
		public static   int  kull_sort_alpha = Integer.MIN_VALUE;
		public static   int  kull_sort_by_size = Integer.MIN_VALUE;
		public static   int  kull_star = Integer.MIN_VALUE;
		public static   int  kull_take_photo = Integer.MIN_VALUE;
		public static   int  kull_talk = Integer.MIN_VALUE;
		public static   int  kull_trashcan = Integer.MIN_VALUE;
	}
	public static   class style {
		public static   int GreenDroid = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopDown = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopDown_Center = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopDown_Left = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopDown_Right = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopUp = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopUp_Center = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopUp_Left = Integer.MIN_VALUE;
		public static   int GreenDroid_Animation_PopUp_Right = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ActionBar = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ActionBar_Dashboard = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ActionBar_Empty = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ActionBar_Item = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ActionBar_Title = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_DescriptionItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_DrawableItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_DrawableItemView_Drawable = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_DrawableItemView_Text = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_LongTextItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ProgressItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ProgressItemView_ProgressBar = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ProgressItemView_Text = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SeparatorItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SubtextItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SubtextItemView_Subtext = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SubtextItemView_Text = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SubtitleItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SubtitleItemView_Subtitle = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_SubtitleItemView_Text = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_TextItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ThumbnailItemView = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ThumbnailItemView_Subtitle = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ThumbnailItemView_Text = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_ItemView_ThumbnailItemView_Thumbnail = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_PageIndicator = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_QuickAction = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_QuickAction_Bar = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_QuickAction_Bar_Item = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_QuickAction_Grid = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_QuickAction_Grid_Item = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_QuickAction_Item = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_SegmentedBar = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_SegmentedHost = Integer.MIN_VALUE;
		public static   int GreenDroid_Widget_TabIndicator = Integer.MIN_VALUE;
		public static   int TextAppearance = Integer.MIN_VALUE;
		public static   int TextAppearance_Large = Integer.MIN_VALUE;
		public static   int TextAppearance_Medium = Integer.MIN_VALUE;
		public static   int TextAppearance_Separator = Integer.MIN_VALUE;
		public static   int TextAppearance_Small = Integer.MIN_VALUE;
		public static   int TextAppearance_TabIndicator = Integer.MIN_VALUE;
		public static   int Theme_GreenDroid = Integer.MIN_VALUE;
		public static   int Theme_GreenDroid_Light = Integer.MIN_VALUE;
		public static   int Theme_GreenDroid_Light_NoTitleBar = Integer.MIN_VALUE;
		public static   int Theme_GreenDroid_NoTitleBar = Integer.MIN_VALUE;
	}
	public static   class styleable {
		public static   int[] kull_ActionBar = { 0x7f010048, 0x7f010049, 0x7f01004c, 0x7f01004d, 0x7f01004e, 0x7f01004f };
		public static   int kull_ActionBar_dividerDrawable = 0;
		public static   int kull_ActionBar_dividerWidth = 1;
		public static   int kull_ActionBar_homeDrawable = 4;
		public static   int kull_ActionBar_maxItems = 5;
		public static   int kull_ActionBar_title = 2;
		public static   int kull_ActionBar_type = 3;
		public static   int[] kull_AsyncImageView = { 0x7f010050, 0x7f010051, 0x7f010052 };
		public static   int kull_AsyncImageView_defaultSrc = 0;
		public static   int kull_AsyncImageView_inDensity = 2;
		public static   int kull_AsyncImageView_url = 1;
		public static   int[] kull_DescriptionItem = { };
		public static   int[] kull_DrawableItem = { 0x7f010043 };
		public static   int kull_DrawableItem_drawable = 0;
		public static   int[] kull_Item = { 0x7f010040 };
		public static   int kull_Item_enabled = 0;
		public static   int[] kull_LongTextItem = { };
		public static   int[] kull_PageIndicator = { 0x7f010053, 0x7f010054, 0x7f010055, 0x7f010056, 0x7f010057, 0x7f010058 };
		public static   int kull_PageIndicator_activeDot = 1;
		public static   int kull_PageIndicator_dotCount = 0;
		public static   int kull_PageIndicator_dotDrawable = 2;
		public static   int kull_PageIndicator_dotSpacing = 3;
		public static   int kull_PageIndicator_dotType = 5;
		public static   int kull_PageIndicator_gravity = 4;
		public static   int[] kull_ProgressItem = { 0x7f010042 };
		public static   int kull_ProgressItem_isInProgress = 0;
		public static   int[] kull_SegmentedBar = { 0x7f010048, 0x7f010049 };
		public static   int kull_SegmentedBar_dividerDrawable = 0;
		public static   int kull_SegmentedBar_dividerWidth = 1;
		public static   int[] kull_SegmentedHost = { 0x7f01004a, 0x7f01004b };
		public static   int kull_SegmentedHost_segmentedBar = 0;
		public static   int kull_SegmentedHost_segmentedContentView = 1;
		public static   int[] kull_SeparatorItem = { };
		public static   int[] kull_SubtextItem = { 0x7f010045 };
		public static   int kull_SubtextItem_subtext = 0;
		public static   int[] kull_SubtitleItem = { 0x7f010044 };
		public static   int kull_SubtitleItem_subtitle = 0;
		public static   int[] kull_TextItem = { 0x7f010041 };
		public static   int kull_TextItem_text = 0;
		public static   int[] kull_ThumbnailItem = { 0x7f010046, 0x7f010047 };
		public static   int kull_ThumbnailItem_thumbnail = 0;
		public static   int kull_ThumbnailItem_thumbnailURL = 0;
	}
	
	public static   class menu {}
}
