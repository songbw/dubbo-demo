package com.flzc.rob.common;

/**
 * Created by iverson on 2015/10/14.
 */
public final class Constants {

    public static  final  Double EARTH_RADIUS = 6378.138; //地球半径
    public static  final  String LATITUDE_0 = "latitude0"; //
    public static  final  String LATITUDE_1 = "latitude1"; //
    public static  final  String LONGITUDE = "longitude"; //

    public static  final  int ACTIVITY_ANSWER = 39001; //答题
    public static  final  int ACTIVITY_CUSTOMIZATION = 39002;//定制
    public static  final  int ACTIVITY_AUCTION = 39003;//竞拍
    public static  final  int ACTIVITY_DIKOU = 39004;//红包抵扣
    
    public static  final  int DEFAULT_PAGESIZE = 10;//默认行数

    //根据经度，纬度计算距离公式
    public static  final String DISTANCE_FORMULA = " ROUND( 6378.138 * 2 * ASIN( SQRT( POW( SIN( ( :latitude0 * PI() / 180 - latitude * PI() / 180) / 2), 2) " +
            "+ COS(:latitude1 * PI() / 180) * COS(latitude * PI() / 180) * POW( SIN( ( :longitude * PI() / 180 - longitude * PI() / 180) / 2), 2))) * 1000) " +
            "AS distance" ;
}
