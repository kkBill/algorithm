package JianZhiOffer;

public class _2ReplaceSpace {
    public String replaceSpace(StringBuffer str) {
        int spaceCnt = 0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' ') spaceCnt++;
        }
        int p1 = str.length() - 1;
        int newLength = str.length() + 2 * spaceCnt;//每替换一个空格，长度加 2
        str.setLength(newLength);
        int p2 = str.length() - 1;

        while(p1 >= 0 && p2 > p1){
            if(str.charAt(p1) == ' '){
                str.setCharAt(p2--,'0');
                str.setCharAt(p2--,'2');
                str.setCharAt(p2--,'%');
                p1--;
            }else{
                str.setCharAt(p2--,str.charAt(p1--));
            }
        }
        return str.toString();
    }
}
