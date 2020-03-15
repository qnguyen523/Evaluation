
public class NumberOfRows implements Comparable<NumberOfRows> {
	int num=0;
	public NumberOfRows (int num) {
		this.num=num;
	}
	@Override
	public String toString() {
		return num+"";
	}
	@Override
    public boolean equals(Object o) { 
        if (o == this) { 
            return true; 
        } 
        if (!(o instanceof NumberOfRows)) { 
            return false; 
        } 
        NumberOfRows c = (NumberOfRows) o; 
//        System.out.println("In equals");
//        System.out.println(num +""+ c.num);
        return num==c.num;
    } 
	@Override
	public int compareTo(NumberOfRows o) {
		return Integer.compare(num,o.num);
	}
}