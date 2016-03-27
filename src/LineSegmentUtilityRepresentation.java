import java.util.HashMap;

public class LineSegmentUtilityRepresentation {
	HashMap<LineSegment,String> shopSegmentMap;
	double lastUtilityPoint;
	
	public LineSegmentUtilityRepresentation(){
		shopSegmentMap = new HashMap<LineSegment,String>();
		lastUtilityPoint=0;
	}
	
	public void addUtilitySegment(String s, double utility){
		LineSegment ls = new LineSegment(lastUtilityPoint, lastUtilityPoint+utility);
		lastUtilityPoint += utility;
		shopSegmentMap.put(ls,s);
	}
	
	public String findCorrespondingShopforUtility(double point){
		for(LineSegment ls : shopSegmentMap.keySet()){
			if(ls.isPointOnSegment(point)){
				return shopSegmentMap.get(ls);
			}
		}
		return null;
	}
	
	public LineSegment findCorrespondingLineSegmentforUtility(double point){
		for(LineSegment ls : shopSegmentMap.keySet()){
			if(ls.isPointOnSegment(point)){
				return ls;
			}
		}
		return null;
	}
}
