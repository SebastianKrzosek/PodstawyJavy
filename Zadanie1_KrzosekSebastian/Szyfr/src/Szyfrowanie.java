public class Szyfrowanie {
    public static void main(String[] args) {

        System.out.println(encrypt(args));
    }

    public static String encrypt(String[] args){
    String word;
    char letter;
    String binaryLetter;
    String result = "";
        for (int i = 0; i < args.length; i++) {

        word = args[i];
        for (int j = 0; j < word.length(); j++) {
            letter = word.charAt(j);
            binaryLetter = Integer.toBinaryString(letter);
            binaryLetter = replace(binaryLetter);

            result += change(binaryLetter);
        }
        result += " ";
    }
        return result;
}

    public static char change(String let) {
        char[] chars = let.toCharArray();
        double value = 0;
        int count = 6;
        for (int i = 0; i < 7; i++) {
            if (chars[i] == '1') {
                value += Math.pow(2, count);
            }
            count--;
        }
        return (char) value;
    }

    public static String replace(String str) {
        char[] chars = str.toCharArray();
        char pom = chars[3];
        chars[3] = chars[4];
        chars[4] = pom;
        pom = chars[5];
        chars[5] = chars[6];
        chars[6] = pom;
        return String.valueOf(chars);
    }
}