public class Square extends Expression {
    private Expression expression;

    public Square(Expression expression) {
        this.expression = expression;
    }
    @Override
    public String toString() {
        return ("(" +expression + " * " +expression +")");
    }
    @Override
    public int evaluate() {
        return (int) Math.pow(expression.evaluate(), 2);
    }

}
