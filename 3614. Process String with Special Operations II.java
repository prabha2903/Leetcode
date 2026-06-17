class Solution {
    public char processStr(String s, long k) {

        long len = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLetter(c)) {
                len++;
            } else if (c == '*') {
                if (len > 0) len--;
            } else if (c == '#') {
                len *= 2;
            }
        }

        if (k >= len) return '.';

        for (int i = s.length() - 1; i >= 0; i--) {

            char c = s.charAt(i);

            if (Character.isLetter(c)) {

                len--;

                if (k == len) return c;

            } else if (c == '#') {

                len /= 2;

                if (k >= len) k -= len;

            } else if (c == '%') {

                k = len - 1 - k;

            } else if (c == '*') {

                len++;
            }
        }

        return '.';
    }
}