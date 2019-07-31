package JianZhiOffer;

public class _1Find {
    public boolean Find(int target, int [][] array) {
        if(array.length == 0) return false;
        int row = array.length, col = array[0].length;
        int i = 0, j = col-1;
        boolean found = false;
        while(i < row && j >= 0){
            if(target == array[i][j]){
                found = true;
                break;
            }else if(array[i][j] > target)
                j--;
            else
                i++;
        }
        return found;
    }
}
