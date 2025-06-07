package vn.edu.ptithcm.WebUIS.domain.enumeration;

public enum TrainingScoreClassify {
    EXCELLENT(90, 100),
    GOOD(80, 89),
    FAIR(65, 79),
    AVERAGE(50, 64),
    WEAK(35, 49),
    POOR(0, 34);

    private final int minScore;
    private final int maxScore;

    TrainingScoreClassify(int minScore, int maxScore) {
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public int getMinScore() {
        return minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    /**
     * Lấy loại điểm rèn luyện từ điểm số
     * 
     * @param score
     * @return
     */
    public static TrainingScoreClassify fromScore(int score) {
        for (TrainingScoreClassify classify : TrainingScoreClassify.values()) {
            if (score >= classify.getMinScore() && score <= classify.getMaxScore()) {
                return classify;
            }
        }
        return null;
    }

}
