
public class LineSegment {
	double start;
	double end;
	
	public LineSegment(double start, double end){
		this.start=start;
		this.end=end;
	}

	boolean isPointOnSegment(double point){
		if(start<=point&&point<end){
			return true;
		}else{
			return false;
		}
	}
}
