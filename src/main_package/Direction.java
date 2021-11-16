package main_package;

public enum Direction {

    //TODO: x et y interverti (voir le tri de la liste, c'est peut-être ça)
    NORD(-1,0), SUD(1,0), EST(0,1), OUEST(0,-1);

    private Integer x;
    private Integer y;

    Direction(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }
}
