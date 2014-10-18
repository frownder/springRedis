package my.starworld;

public class Util {

	/**
	 * check if fullStr has exact count of token.
	 * @param fullStr input string to check
	 * @param token  token to count
	 * @param count  wishing count
	 * @return true when count match
	 */
	protected static boolean checkTokenCount(String fullStr, String token, int count){
		
		if(fullStr == null) return false;
		
		String[] split = fullStr.split("!");
		if( split.length == count){
			for( String s: split){
				if ( !token.equals(s))
					return false;
			}
			return true;
		}
		return false;
	}
}
