public class CompressionCalculation {

    public static final double UNDEFINED_LOG_LUMINOSITY = Double.MIN_VALUE;
    
    public static double calculateAvgLogLuminosity(QuadTreeNode node) {

        if (node.isTwigRoot()) {
            double res = 0;

            for (int i = 0; i < 4; i++) {
                res += Math.log(0.1 + node.getChildValue(i));
            }

            res = Math.exp(0.25 * res);
            return res;
        }

        return UNDEFINED_LOG_LUMINOSITY;
    }

    public static double calculateEpsilon(QuadTreeNode node) {

        double maxEpsilon = Double.MIN_VALUE;

        double avgLogLuminosity = calculateAvgLogLuminosity(node);

        for (int i = 0; i < 4; i++) {

            QuadTreeNode child = node.getChild(i);

            if (child != null && child.isLeaf()) {

                double epsilon = Math.abs(avgLogLuminosity - child.getValue());
                maxEpsilon = Math.max(maxEpsilon, epsilon);
            }
        }

        return maxEpsilon;
    }

    public static int getMax(int[] arr) {

        int max = arr[0];

        for (int i = 1; i < 4; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

}
