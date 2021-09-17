package com.zkzy.portal.common.utils;

/**
 * Created by admin on 2017/10/25.
 */
public class WaterQualityUtil {
    //I类codmn
    private static final double Istandardcodmn=2.0;
    //I类氨氮
    private static final double Istandarammonianitrogen=0.15;
    //I类总磷
    private static final double Istandartotalphosphorus=0.02;
    //I类总磷（湖、库）
    private static final double IstandarLakeAndRetotalphosphorus=0.01;

    //II类codmn
    private static final double IIstandardcodmn=4.0;
    //II类氨氮
    private static final double IIstandarammonianitrogen=0.5;
    //II类总磷
    private static final double IIstandartotalphosphorus=0.1;
    //II类总磷（湖、库）
    private static final double IIstandarLakeAndRetotalphosphorus=0.025;

    //III类codmn
    private static final double IIIstandardcodmn=6.0;
    //III类氨氮
    private static final double IIIstandarammonianitrogen=1.0;
    //III类总磷
    private static final double IIIstandartotalphosphorus=0.2;
    //III类总磷（湖、库）
    private static final double IIIstandarLakeAndRetotalphosphorus=0.05;

    //Ⅳ类codmn
    private static final double Ⅳstandardcodmn=10.0;
    //Ⅳ类氨氮
    private static final double Ⅳstandarammonianitrogen=1.5;
    //Ⅳ类总磷
    private static final double Ⅳstandartotalphosphorus=0.3;
    //Ⅳ类总磷（湖、库）
    private static final double ⅣstandarLakeAndRetotalphosphorus=0.1;

    //Ⅴ类codmn
    private static final double Ⅴstandardcodmn=15.0;
    //Ⅴ类氨氮
    private static final double Ⅴstandarammonianitrogen=2.0;
    //Ⅴ类总磷
    private static final double Ⅴstandartotalphosphorus=0.4;
    //Ⅴ类总磷（湖、库）
    private static final double ⅤstandarLakeAndRetotalphosphorus=0.2;

    //三指标传入三个指标和点名称
    public static  String getCurrentWaterQuality(double codmn,double ammonianitrogen,double totalphosphorus,double mytargetcodmn,double mytargetammonianitrogen,double mytargettotalphosphorus){
       boolean isTrue= getLakeOrku( mytargetcodmn, mytargetammonianitrogen, mytargettotalphosphorus);
        if(isTrue){
            if(codmn<=Istandardcodmn&&ammonianitrogen<=Istandarammonianitrogen&&totalphosphorus<=IstandarLakeAndRetotalphosphorus){
           return "Ⅰ类";
            }else if(codmn<=IIstandardcodmn&&ammonianitrogen<=IIstandarammonianitrogen&&totalphosphorus<=IIstandarLakeAndRetotalphosphorus){
                return "Ⅱ类";
            }else if(codmn<=IIIstandardcodmn&&ammonianitrogen<=IIIstandarammonianitrogen&&totalphosphorus<=IIIstandarLakeAndRetotalphosphorus){
                return "Ⅲ类";
            }else if(codmn<=Ⅳstandardcodmn&&ammonianitrogen<=Ⅳstandarammonianitrogen&&totalphosphorus<=ⅣstandarLakeAndRetotalphosphorus){
                return "Ⅳ类";
            }else if(codmn<=Ⅴstandardcodmn&&ammonianitrogen<=Ⅴstandarammonianitrogen&&totalphosphorus<=ⅤstandarLakeAndRetotalphosphorus){
                return "Ⅴ类";
            }else {
                return "劣V类";
            }
        }else{
            if(codmn<=Istandardcodmn&&ammonianitrogen<=Istandarammonianitrogen&&totalphosphorus<=Istandartotalphosphorus){
                return "Ⅰ类";
            }else if(codmn<=IIstandardcodmn&&ammonianitrogen<=IIstandarammonianitrogen&&totalphosphorus<=IIstandartotalphosphorus){
                return "Ⅱ类";
            }else if(codmn<=IIIstandardcodmn&&ammonianitrogen<=IIIstandarammonianitrogen&&totalphosphorus<=IIIstandartotalphosphorus){
                return "Ⅲ类";
            }else if(codmn<=Ⅳstandardcodmn&&ammonianitrogen<=Ⅳstandarammonianitrogen&&totalphosphorus<=Ⅳstandartotalphosphorus){
                return "Ⅳ类";
            }else if(codmn<=Ⅴstandardcodmn&&ammonianitrogen<=Ⅴstandarammonianitrogen&&totalphosphorus<=Ⅴstandartotalphosphorus){
                return "Ⅴ类";
            }else {
                return "劣V类";
            }
        }
    }

    public static  String getOverStandard(double mycodmn,double mytargetcodmn,double myammonianitrogen,double mytargetammonianitrogen,double mytotalphosphorus,double mytargettotalphosphorus){
/*        double mycodmn=Double.parseDouble(codmn);
    double mytargetcodmn=Double.parseDouble(targetcodmn);
    double myammonianitrogen=Double.parseDouble(ammonianitrogen);
    double mytargetammonianitrogen=Double.parseDouble(targetammonianitrogen);
    double mytotalphosphorus=Double.parseDouble(totalphosphorus);
    double mytargettotalphosphorus=Double.parseDouble(targettotalphosphorus);*/
int a=0;
        if(mycodmn>mytargetcodmn){
            a=a+1;
        }
        if(myammonianitrogen>mytargetammonianitrogen){
            a=a+1;
        }
        if(mytotalphosphorus>mytargettotalphosphorus){
            a=a+1;
        }
        if(a>0){
            return "未达标";
        }else {
            return"已达标";
        }
    }

    public static  String getOverStandard(double mycodmn,double mytargetcodmn,double myammonianitrogen,double mytargetammonianitrogen){
/*        double mycodmn=Double.parseDouble(codmn);
        double mytargetcodmn=Double.parseDouble(targetcodmn);
        double myammonianitrogen=Double.parseDouble(ammonianitrogen);
        double mytargetammonianitrogen=Double.parseDouble(targetammonianitrogen);*/
        int a=0;
        if(mycodmn>mytargetcodmn){
            a=a+1;
        }
        if(myammonianitrogen>mytargetammonianitrogen){
            a=a+1;
        }
        if(a>0){
            return "未达标";
        }else {
            return"已达标";
        }
    }

    //双指标传入2个指标
    public static  String getCurrentWaterQuality(double codmn,double  ammonianitrogen){
/*        double codmn=Double.parseDouble(mycodmn);
        double ammonianitrogen=Double.parseDouble(myammonianitrogen);*/
        if(codmn<=Istandardcodmn&&ammonianitrogen<=Istandarammonianitrogen){
            return "Ⅰ类";
        }else if(codmn<=IIstandardcodmn&&ammonianitrogen<=IIstandarammonianitrogen){
            return "Ⅱ类";
        }else if(codmn<=IIIstandardcodmn&&ammonianitrogen<=IIIstandarammonianitrogen){
            return "Ⅲ类";
        }else if(codmn<=Ⅳstandardcodmn&&ammonianitrogen<=Ⅳstandarammonianitrogen){
            return "Ⅳ类";
        }else if(codmn<=Ⅴstandardcodmn&&ammonianitrogen<=Ⅴstandarammonianitrogen){
            return "Ⅴ类";
        }else {
            return "劣V类";
        }
    }


public static  boolean getLakeOrku(double mytargetcodmn,double mytargetammonianitrogen,double mytargettotalphosphorus){
    if(mytargetcodmn==Istandardcodmn&&mytargetammonianitrogen==Istandarammonianitrogen&&mytargettotalphosphorus==IstandarLakeAndRetotalphosphorus){
        return true;
    }else if(mytargetcodmn==IIstandardcodmn&&mytargetammonianitrogen==IIstandarammonianitrogen&&mytargettotalphosphorus==IIstandarLakeAndRetotalphosphorus){
        return true;
    }else if(mytargetcodmn==IIIstandardcodmn&&mytargetammonianitrogen==IIIstandarammonianitrogen&&mytargettotalphosphorus==IIIstandarLakeAndRetotalphosphorus){
        return true;
    }else if(mytargetcodmn==Ⅳstandardcodmn&&mytargetammonianitrogen==Ⅳstandarammonianitrogen&&mytargettotalphosphorus==ⅣstandarLakeAndRetotalphosphorus){
        return true;
    }else if(mytargetcodmn==Ⅴstandardcodmn&&mytargetammonianitrogen==Ⅴstandarammonianitrogen&&mytargettotalphosphorus==ⅤstandarLakeAndRetotalphosphorus){
        return true;
    }else {
        return false;
    }
}

}
