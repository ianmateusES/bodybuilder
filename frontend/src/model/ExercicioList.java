package model;

public class ExercicioList {

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    private String _id;
    private String division;
    private String exercise;
    private String methodology;
    private int series;
    private int repetitions;
    private String comments;
    
    public ExercicioList(String _id, String division, String exercise, String methodology, int series, int repetitions, String comments) {
        this._id = _id;
        this.division = division;
        this.exercise = exercise;
        this.methodology = methodology;
        this.series = series;
        this.repetitions = repetitions;
        this.comments = comments;
    }
}
