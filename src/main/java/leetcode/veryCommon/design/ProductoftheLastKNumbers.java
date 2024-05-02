package leetcode.veryCommon.design;

import java.util.ArrayList;

public class ProductoftheLastKNumbers {
    class ProductOfNumbers {
        ArrayList<Integer> prodList;
        int lastZeroIdx = -1;

        public ProductOfNumbers() {
            prodList = new ArrayList<Integer>();
        }

        public void add(int num) {
            if (prodList.size() == 0)
                prodList.add(num);
            else {
                int prevProd = prodList.get(prodList.size() - 1);
                if (prevProd > 0)
                    prodList.add(prevProd * num);
                else
                    prodList.add(num);
            }
            if (num == 0)
                lastZeroIdx = prodList.size() - 1;
        }

        public int getProduct(int k) {
            if (k > prodList.size() - 1 - lastZeroIdx)
                return 0;
            else {
                int lastIdx = prodList.size() - 1;
                if (lastIdx - k < 0 || prodList.get(lastIdx - k) == 0)
                    return prodList.get(lastIdx);
                else
                    return prodList.get(lastIdx) / prodList.get(lastIdx - k);
            }
        }
    }
}
