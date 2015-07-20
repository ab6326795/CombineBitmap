package com.pwdgame.combinebitmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.media.ThumbnailUtils;

/**
 * 群聊头像生成工具
 * 
 * @author xieyuan 2015.07.20
 */
public class CombineBitmapTools {

	public static Bitmap combimeBitmap(Context context, int combineWidth,
			int combineHeight, Bitmap... bitmaps) {
		if (bitmaps == null || bitmaps.length == 0 || bitmaps.length > 9 )
			return null;
		Bitmap resultBitmap = null;
		int len = bitmaps.length;
		
		// 绘制数据
		List<CombineBitmapEntity> combineBitmapEntities = CombineNineRect
				.generateCombineBitmapEntity(combineWidth, combineHeight, len);
		// 缩略图
		List<Bitmap> thumbnailBitmaps = new ArrayList<Bitmap>();
		for (int i = 0; i < len; i++) {
			thumbnailBitmaps.add(ThumbnailUtils.extractThumbnail(bitmaps[i],
					(int) combineBitmapEntities.get(i).width,
					(int) combineBitmapEntities.get(i).height));
		}
		// 合成
		resultBitmap = getCombineBitmaps(combineBitmapEntities,
				thumbnailBitmaps, combineWidth, combineHeight);

		return resultBitmap;
	}
	
	public static Bitmap combimeBitmap(Context context, int combineWidth,
			int combineHeight, List<Bitmap> bitmaps) {
		if (bitmaps == null || bitmaps.size() == 0 || bitmaps.size() > 9 )
			return null;
		Bitmap resultBitmap = null;
		int len = bitmaps.size();
		// 绘制数据
		List<CombineBitmapEntity> combineBitmapEntities = CombineNineRect
				.generateCombineBitmapEntity(combineWidth, combineHeight, len);
		// 缩略图
		List<Bitmap> thumbnailBitmaps = new ArrayList<Bitmap>();
		for (int i = 0; i < len; i++) {
			thumbnailBitmaps.add(ThumbnailUtils.extractThumbnail(bitmaps.get(i),
					(int) combineBitmapEntities.get(i).width,
					(int) combineBitmapEntities.get(i).height));
		}
		// 合成
		resultBitmap = getCombineBitmaps(combineBitmapEntities,
				thumbnailBitmaps, combineWidth, combineHeight);

		return resultBitmap;
	}


	private static Bitmap getCombineBitmaps(
			List<CombineBitmapEntity> mEntityList, List<Bitmap> bitmaps,
			int width, int height) {
		Bitmap newBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		for (int i = 0; i < mEntityList.size(); i++) {
			newBitmap = mixtureBitmap(newBitmap, bitmaps.get(i), new PointF(
					mEntityList.get(i).x, mEntityList.get(i).y));
		}
		return newBitmap;
	}

	/**
	 * 将图片混合绘制在一起
	 * 
	 * @param first
	 * @param second
	 * @param fromPoint
	 * @return
	 */
	private static Bitmap mixtureBitmap(Bitmap first, Bitmap second,
			PointF fromPoint) {
		if (first == null || second == null || fromPoint == null) {
			return null;
		}
		Bitmap newBitmap = Bitmap.createBitmap(first.getWidth(),
				first.getHeight(), Config.ARGB_8888);
		Canvas cv = new Canvas(newBitmap);
		cv.drawBitmap(first, 0, 0, null);
		cv.drawBitmap(second, fromPoint.x, fromPoint.y, null);
		cv.save(Canvas.ALL_SAVE_FLAG);
		cv.restore();

		if (first != null) {
			first.recycle();
			first = null;
		}
		if (second != null) {
			second.recycle();
			second = null;
		}

		return newBitmap;
	}

}
